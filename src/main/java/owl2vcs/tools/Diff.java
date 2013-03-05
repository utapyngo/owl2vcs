package owl2vcs.tools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyInputSourceException;
import org.semanticweb.owlapi.io.UnparsableOntologyException;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleIRIShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import owl2vcs.analysis.ChangeClassifier;
import owl2vcs.analysis.EntityClassifier;
import owl2vcs.analysis.EntityCollector;
import owl2vcs.analysis.EntityCollectorException;
import owl2vcs.analysis.PrefixExtractor;
import owl2vcs.changeset.ChangeSet;
import owl2vcs.changeset.FullChangeSet;
import owl2vcs.render.ChangeFormat;
import owl2vcs.render.ChangeRenderer;
import owl2vcs.render.FullFormProvider;
import owl2vcs.render.FunctionalChangeRenderer;
import owl2vcs.render.IriFormat;
import owl2vcs.render.QNameMultiMapShortFormProvider;
import owl2vcs.render.QuotedAnnotationValueShortFormProvider;
import owl2vcs.utils.OntologyUtils;
import owl2vcs.utils.TimeTracker;

import com.google.common.collect.Iterables;

class Settings {

    @Argument(required = true, index = 0, metaVar = "parent.owl", usage = "Parent version")
    public String parentFilename;
    @Argument(required = true, index = 1, metaVar = "child.owl", usage = "Child version")
    public String childFilename;
    @Option(name = "--prefixes", aliases = { "-p" }, required = false, usage = "Display used prefixes")
    public Boolean prefixes = false;
    @Option(name = "--entities", aliases = { "-e" }, required = false, usage = "Display changed entities")
    public Boolean entities = false;
    @Option(name = "--changes", aliases = { "-c" }, required = false, usage = "Display changes")
    public Boolean changes = false;
    @Option(name = "--format", aliases = { "-f" }, metaVar = "format", required = false, usage = "Format of statements: compact or indented")
    public ChangeFormat format = ChangeFormat.COMPACT;
    @Option(name = "--iriformat", aliases = { "-i" }, metaVar = "iriformat", required = false, usage = "Format of IRIs: simple, qname (default), full, label")
    public IriFormat iriFormat = IriFormat.QNAME;
    @Option(name = "--verbose", aliases = { "-v" }, required = false, usage = "Verbose output")
    public Boolean verbose = false;
    @Option(name = "--wait", aliases = { "-w" }, required = false, usage = "Do not exit, wait until user presses Enter")
    public Boolean wait = false;
    @Option(name = "--measure", aliases = { "-m" }, required = false, usage = "Measure time")
    public Boolean measure = false;
}

public final class Diff {

    private Diff() {
    };

    /**
     * Binary compare.
     *
     * @param parentSource
     * @param childSource
     * @return true if files are identical, false otherwise
     * @throws IOException
     */
    private static boolean binaryCompareSources(
            final FileDocumentSource parentSource,
            final FileDocumentSource childSource) throws IOException {
        final Reader sr = parentSource.getReader();
        final Reader cr = childSource.getReader();
        while (true) {
            final int a = sr.read();
            final int b = cr.read();
            if (a != b)
                break;
            if (a == -1)
                return true;
        }
        return false;
    }

    private static int showFilesDiff(final String parent, final String child,
            final Settings settings) {
        try {
            System.err.println("diff " + parent + " " + child);
            if (parent.equals(child)) {
                System.err.println("Same file");
                return 0;
            }
            final TimeTracker t = new TimeTracker();
            final FileDocumentSource parentSource = new FileDocumentSource(
                    new File(parent));
            final FileDocumentSource childSource = new FileDocumentSource(
                    new File(child));
            // binary compare
            final Boolean areIdenticalSources = binaryCompareSources(
                    parentSource, childSource);
            if (areIdenticalSources) {
                System.err.println("Binary identical files");
                return 0;
            }
            // when no options provided, assume user wants raw changes
            if (!settings.changes && !settings.entities)
                settings.changes = true;

            t.start("total");
            t.start("load");
            final OWLOntology o1 = OntologyUtils.loadOntology(parentSource);
            final OWLOntology o2 = OntologyUtils.loadOntology(childSource);
            t.end("load");

            t.start("diff");
            final FullChangeSet cs = new FullChangeSet(o1, o2);
            t.end("diff");

            final Set<OWLEntity> entities = new HashSet<OWLEntity>();
            final Set<OWLEntity> newEntities = new HashSet<OWLEntity>();
            final Set<OWLEntity> removedEntities = new HashSet<OWLEntity>();
            final Set<OWLEntity> modifiedEntities = new HashSet<OWLEntity>();
            if (settings.prefixes || settings.entities) {
                t.start("ce");
                cs.accept(new EntityCollector(entities,
                        new HashSet<OWLAnonymousIndividual>()));
                t.end("ce");

            }
            final OWLOntologyFormat parentFormat = o1.getOWLOntologyManager()
                    .getOntologyFormat(o1);
            final OWLOntologyFormat childFormat = o2.getOWLOntologyManager()
                    .getOntologyFormat(o2);
            if (settings.entities) {
                t.start("+-*");
                new EntityClassifier(o1, o2, entities, newEntities,
                        removedEntities, modifiedEntities);
                t.end("+-*");
            }
            t.end("total");

            final ShortFormProvider provider = createShortFormProvider(
                    settings.iriFormat, o1, parentFormat, childFormat);
            final ChangeRenderer changeRenderer = new FunctionalChangeRenderer(
                    provider, settings.format);

            // Calculate and display prefixes
            if (settings.prefixes) {
                if (parentFormat.isPrefixOWLOntologyFormat()
                        && childFormat.isPrefixOWLOntologyFormat()) {
                    t.start("xp");
                    final Map<String, String> prefixes = extractPrefixes(
                            entities, parentFormat, childFormat);
                    t.end("xp");
                    for (final Map.Entry<String, String> e : prefixes
                            .entrySet()) {
                        System.out.print(e.getKey() + "=");
                        System.out.println("<" + e.getValue() + ">");
                    }
                }
                System.out.println();
                System.out.println();
            }
            // Display changes
            if (settings.changes) {
                displayEntitylessChanges(cs, changeRenderer);
                System.out.println();
                if (settings.entities) {
                    t.start("cc");
                    final ChangeClassifier changesByEntity = new ChangeClassifier(
                            cs.getAxiomChanges());
                    t.end("cc");
                    displayEntitiesWithChanges(newEntities, removedEntities,
                            modifiedEntities, changesByEntity, provider,
                            changeRenderer);
                } else
                    for (final OWLOntologyChangeData c : cs.getAxiomChanges())
                        System.out.println(changeRenderer.render(c));
            } else if (settings.entities) {
                displayEntities(newEntities, removedEntities, modifiedEntities,
                        provider);
            } else {
                System.out.println("Total changes: " + cs.size());
                System.out
                        .println("Please use the --changes option to see them");
                System.out
                        .println("and the --entities option to see changed entities.");
                System.out
                        .println("Combine these options to see changes classified by entities.");
            }

            if (settings.measure) {
                System.err.println("Time");
                final int width = 6;
                t.outputTable(width, System.err);
            }

            return cs.size();
        } catch (final OWLOntologyInputSourceException e) {
            if (e.getCause() != null)
                System.err.println("Bad input source: " + e.getCause().getMessage());
            else
                System.err.println("Bad input source: " + e.getMessage());
        } catch (final FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (final IOException e) {
            e.printStackTrace(System.err);
        } catch (final UnparsableOntologyException e) {
            if (settings.verbose)
                System.err.println(e.toString());
            else
                System.err.println("Could not parse: "
                        + e.getDocumentIRI().toString());
        } catch (final OWLOntologyCreationException e) {
            e.printStackTrace(System.err);
        } catch (EntityCollectorException e) {
            // impossible
            e.printStackTrace(System.err);
        }
        return 0;
    }

    private static void displayEntitylessChanges(final ChangeSet cs,
            final ChangeRenderer changeRenderer) {
        if (cs.getFormatChange() != null)
            System.out.println(changeRenderer.render(cs.getFormatChange()));
        if (cs.getOntologyIdChange() != null)
            System.out.println(changeRenderer.render(cs.getOntologyIdChange()));
        for (final OWLOntologyChangeData c : Iterables.concat(
                cs.getPrefixChanges(), cs.getImportChanges(),
                cs.getAnnotationChanges()))
            System.out.println(changeRenderer.render(c));
    }

    private static void displayEntitiesWithChanges(
            final Set<OWLEntity> newEntities,
            final Set<OWLEntity> removedEntities,
            final Set<OWLEntity> modifiedEntities,
            final ChangeClassifier changesByEntity,
            final ShortFormProvider provider,
            final ChangeRenderer changeRenderer) {
        for (final OWLEntity e : removedEntities)
            displayEntityWithChanges("---", e, changesByEntity, provider,
                    changeRenderer);
        for (final OWLEntity e : newEntities)
            displayEntityWithChanges("+++", e, changesByEntity, provider,
                    changeRenderer);
        for (final OWLEntity e : modifiedEntities)
            displayEntityWithChanges("***", e, changesByEntity, provider,
                    changeRenderer);
    }

    private static void displayEntities(final Set<OWLEntity> newEntities,
            final Set<OWLEntity> removedEntities,
            final Set<OWLEntity> modifiedEntities,
            final ShortFormProvider provider) {
        for (final OWLEntity e : removedEntities)
            displayEntity("---", e, provider);
        for (final OWLEntity e : newEntities)
            displayEntity("+++", e, provider);
        for (final OWLEntity e : modifiedEntities)
            displayEntity("***", e, provider);
    }

    private static void displayEntity(final String modifier,
            final OWLEntity entity, final ShortFormProvider provider) {
        System.out.println(modifier + " " + entity.getEntityType() + ": "
                + provider.getShortForm(entity));
    }

    private static void displayEntityWithChanges(final String modifier,
            final OWLEntity entity, final ChangeClassifier changesByEntity,
            final ShortFormProvider provider,
            final ChangeRenderer changeRenderer) {
        System.out.println();
        displayEntity(modifier, entity, provider);
        for (final AxiomChangeData c : changesByEntity
                .getChangesByEntity(entity))
            System.out.println(changeRenderer.render(c));
    }

    private static Map<String, String> extractPrefixes(
            final Set<OWLEntity> entities,
            final OWLOntologyFormat parentFormat,
            final OWLOntologyFormat childFormat) {
        final Map<String, String> allPrefixes = new HashMap<String, String>();
        allPrefixes.putAll(parentFormat.asPrefixOWLOntologyFormat()
                .getPrefixName2PrefixMap());
        allPrefixes.putAll(childFormat.asPrefixOWLOntologyFormat()
                .getPrefixName2PrefixMap());
        final Map<String, String> prefixes = (new PrefixExtractor(entities,
                allPrefixes)).getPrefixName2PrefixMap();
        return prefixes;
    }

    private static ShortFormProvider createShortFormProvider(
            final IriFormat iriFormat, final OWLOntology ontology,
            final OWLOntologyFormat parentFormat,
            final OWLOntologyFormat childFormat) {
        ShortFormProvider provider;
        switch (iriFormat) {
        case SIMPLE:
            provider = new SimpleShortFormProvider();
            break;
        case FULL:
            provider = new FullFormProvider();
            break;
        case QNAME:
            final List<Map<String, String>> prefix2NamespaceMaps = new ArrayList<Map<String, String>>();
            if (parentFormat.isPrefixOWLOntologyFormat()) {
                prefix2NamespaceMaps.add(parentFormat
                        .asPrefixOWLOntologyFormat().getPrefixName2PrefixMap());
            }
            if (childFormat.isPrefixOWLOntologyFormat()) {
                prefix2NamespaceMaps.add(childFormat
                        .asPrefixOWLOntologyFormat().getPrefixName2PrefixMap());
            }
            provider = new QNameMultiMapShortFormProvider(prefix2NamespaceMaps);
            break;
        case LABEL:
            final List<OWLAnnotationProperty> annotationProperties = new ArrayList<OWLAnnotationProperty>();
            annotationProperties.add(ontology.getOWLOntologyManager()
                    .getOWLDataFactory().getRDFSLabel());
            provider = new QuotedAnnotationValueShortFormProvider(
                    ontology.getOWLOntologyManager(),
                    new SimpleShortFormProvider(),
                    new SimpleIRIShortFormProvider(), annotationProperties,
                    new HashMap<OWLAnnotationProperty, List<String>>());
            break;
        default:
            provider = new SimpleShortFormProvider();
        }
        return provider;
    }

    private static int showDirectoriesDiff(final File parent, final File child,
            final Settings settings) {
        int changesCount = 0;
        for (final File file : parent.listFiles()) {
            final String relative = parent.toURI().relativize(file.toURI())
                    .getPath();
            final File file2 = new File(child, relative);
            if (!file2.exists()) {
                System.err.println("Only in " + parent.getAbsolutePath() + ": "
                        + file.getName());
                changesCount++;
                continue;
            }
            if (file.isFile() && file2.isDirectory()) {
                System.err.println("File " + file.getAbsolutePath()
                        + " is a regular file while file "
                        + file2.getAbsolutePath() + " is a directory");
                changesCount++;
                continue;
            }
            if (file.isDirectory() && file2.isFile()) {
                System.err.println("File " + file.getAbsolutePath()
                        + " is a directory while file "
                        + file2.getAbsolutePath() + " is a regular file");
                changesCount++;
                continue;
            }
            if (file.isDirectory() && file2.isDirectory()) {
                changesCount += showDirectoriesDiff(file, file2, settings);
                continue;
            }
            if (file.isFile() && file2.isFile()) {
                changesCount += showFilesDiff(file.getAbsolutePath(),
                        file2.getAbsolutePath(), settings);
                continue;
            }
        }
        for (final File file : child.listFiles()) {
            final String relative = child.toURI().relativize(file.toURI())
                    .getPath();
            final File file2 = new File(parent, relative);
            if (!file2.exists()) {
                System.err.println("Only in " + child.getAbsolutePath() + ": "
                        + file.getName());
                changesCount++;
                continue;
            }
        }
        return changesCount;
    }

    public static String getVersion() {
        final Class<Diff> clazz = Diff.class;
        final String className = clazz.getSimpleName() + ".class";
        final String classPath = clazz.getResource(className).toString();
        if (!classPath.startsWith("jar"))
            // Class not from JAR
            return "";
        final String manifestPath = classPath.substring(0,
                classPath.lastIndexOf("!") + 1)
                + "/META-INF/MANIFEST.MF";
        Manifest manifest;
        try {
            manifest = new Manifest(new URL(manifestPath).openStream());
        } catch (final MalformedURLException e) {
            return "";
        } catch (final IOException e) {
            return "";
        }
        final Attributes attr = manifest.getMainAttributes();
        return attr.getValue("Version");
    }

    public static void main(final String[] args) {
        final Settings settings = new Settings();
        final CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);

            final File parent = new File(settings.parentFilename);
            final File child = new File(settings.childFilename);
            if (!parent.canRead())
                throw new FileNotFoundException(settings.parentFilename);
            if (!child.canRead())
                throw new FileNotFoundException(settings.childFilename);
            if (parent.isDirectory() && child.isDirectory())
                showDirectoriesDiff(parent, child, settings);
            else
                showFilesDiff(settings.parentFilename, settings.childFilename,
                        settings);
            if (settings.wait)
                System.in.read();
        } catch (final CmdLineException e) {
            String version = getVersion();
            if (version != null)
                System.err.println("owl2diff " + getVersion());
            else
                System.err.println("owl2diff");
            System.err.print("Usage: owl2diff");
            parser.printSingleLineUsage(System.err);
            System.err.println();
            parser.printUsage(System.err);
            final int usageErrorCode = 64;
            System.exit(usageErrorCode);
        } catch (final FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }
}

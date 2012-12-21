package owl2vcs.changeset;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.AnnotationChange;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.ImportChange;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.RemoveOntologyAnnotation;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.vocab.PrefixOWLOntologyFormat;

import owl2vcs.changes.AddPrefix;
import owl2vcs.changes.ModifyPrefix;
import owl2vcs.changes.PrefixChange;
import owl2vcs.changes.RemovePrefix;
import owl2vcs.changes.RenamePrefix;
import owl2vcs.changes.SetOntologyFormat;

public class FullChangeSet extends ChangeSet {

    private final OWLOntology parent;

    public OWLOntology getParent() {
        return parent;
    }

    private final OWLOntology child;

    public OWLOntology getChild() {
        return child;
    }


    /**
     * Calculate changeset between the two ontologies.
     *
     * @param parent
     *            The original ontology
     * @param child
     *            The updated ontology
     */
    public FullChangeSet(final OWLOntology parent, final OWLOntology child) {

        this.parent = parent;
        this.child = child;

        // Ontology format
        final OWLOntologyFormat parentFormat = parent.getOWLOntologyManager()
                .getOntologyFormat(parent);
        final OWLOntologyFormat childFormat = child.getOWLOntologyManager()
                .getOntologyFormat(child);
        if (!parentFormat.equals(childFormat))
            setFormatChange(new SetOntologyFormat(parent, childFormat));

        // Prefixes
        setPrefixChanges(findPrefixChanges(parent, child));

        // Ontology ID
        if (!(parent.getOntologyID().isAnonymous() && child.getOntologyID()
                .isAnonymous()))
            if (!parent.getOntologyID().equals(child.getOntologyID()))
                setOntologyIdChange(new SetOntologyID(parent,
                        child.getOntologyID()));

        // Imports
        setImportChanges(findImportChanges(parent, child));

        // Annotations
        setAnnotationChanges(findAnnotationChanges(parent, child));

        // Axioms
        setAxiomChanges(findAxiomChanges(parent, child));
    }


    private Collection<ImportChange> findImportChanges(
            final OWLOntology parent, final OWLOntology child) {
        final Collection<ImportChange> importChanges = new ArrayList<ImportChange>();
        final Collection<OWLImportsDeclaration> parentImports = parent
                .getImportsDeclarations();
        final Collection<OWLImportsDeclaration> childImports = child
                .getImportsDeclarations();
        for (final OWLImportsDeclaration decl : parentImports)
            if (!childImports.contains(decl))
                importChanges.add(new RemoveImport(parent, decl));
        for (final OWLImportsDeclaration decl : childImports)
            if (!parentImports.contains(decl))
                importChanges.add(new AddImport(parent, decl));
        return importChanges;
    }


    private Collection<AnnotationChange> findAnnotationChanges(
            final OWLOntology parent, final OWLOntology child) {
        final Collection<AnnotationChange> annotationChanges = new ArrayList<AnnotationChange>();
        final Collection<OWLAnnotation> parentAnnotations = parent
                .getAnnotations();
        final Collection<OWLAnnotation> childAnnotations = child
                .getAnnotations();
        for (final OWLAnnotation annotation : parentAnnotations)
            if (!childAnnotations.contains(annotation))
                annotationChanges.add(new RemoveOntologyAnnotation(parent,
                        annotation));
        for (final OWLAnnotation annotation : childAnnotations)
            if (!parentAnnotations.contains(annotation))
                annotationChanges.add(new AddOntologyAnnotation(parent,
                        annotation));
        return annotationChanges;
    }


    private Collection<OWLAxiomChange> findAxiomChanges(
            final OWLOntology parent, final OWLOntology child) {
        final Collection<OWLAxiomChange> axiomChanges = new ArrayList<OWLAxiomChange>();
        if (parent.getOWLOntologyManager().getOntologyFormat(parent) instanceof ManchesterOWLSyntaxOntologyFormat) {
            // collect annotation properties
            final Set<OWLAnnotationProperty> parentAnnotationProperties = new HashSet<OWLAnnotationProperty>();
            for (final OWLAnnotation a : parent.getAnnotations())
                parentAnnotationProperties.add(a.getProperty());
            for (final OWLAxiom axiom : parent.getAxioms()) {
                // ignore declarations of used entities
                if (axiom.isOfType(AxiomType.DECLARATION)) {
                    final OWLEntity e = ((OWLDeclarationAxiom) axiom)
                            .getEntity();
                    if (parentAnnotationProperties.contains(e)
                            || (parent.getReferencingAxioms(e).size() > 1))
                        // skip this declaration axiom
                        continue;
                }
                if (!child.containsAxiom(axiom))
                    axiomChanges.add(new RemoveAxiom(parent, axiom));
            }
        } else
            for (final OWLAxiom axiom : parent.getAxioms())
                if (!child.containsAxiom(axiom))
                    axiomChanges.add(new RemoveAxiom(parent, axiom));
        if (child.getOWLOntologyManager().getOntologyFormat(child) instanceof ManchesterOWLSyntaxOntologyFormat) {
            // collect annotation properties
            final Set<OWLAnnotationProperty> childAnnotationProperties = new HashSet<OWLAnnotationProperty>();
            for (final OWLAnnotation a : parent.getAnnotations())
                childAnnotationProperties.add(a.getProperty());
            for (final OWLAxiom axiom : child.getAxioms()) {
                if (axiom.isOfType(AxiomType.DECLARATION)) {
                    // ignore declarations of used entities
                    final OWLEntity e = ((OWLDeclarationAxiom) axiom)
                            .getEntity();
                    if (childAnnotationProperties.contains(e)
                            || (child.getReferencingAxioms(e).size() > 1))
                        // skip this declaration axiom
                        continue;
                }
                if (!parent.containsAxiom(axiom))
                    axiomChanges.add(new AddAxiom(parent, axiom));
            }
        } else
            for (final OWLAxiom axiom : child.getAxioms())
                if (!parent.containsAxiom(axiom))
                    axiomChanges.add(new AddAxiom(parent, axiom));
        return axiomChanges;
    }


    private Collection<PrefixChange> findPrefixChanges(
            final OWLOntology parent, final OWLOntology child) {
        final OWLOntologyFormat parentFormat = parent.getOWLOntologyManager()
                .getOntologyFormat(parent);
        final OWLOntologyFormat childFormat = child.getOWLOntologyManager()
                .getOntologyFormat(child);
        final Collection<PrefixChange> prefixChanges = new ArrayList<PrefixChange>();
        if (parentFormat instanceof PrefixOWLOntologyFormat) {
            final PrefixOWLOntologyFormat parentPrefixFormat = (PrefixOWLOntologyFormat) parentFormat;
            if (childFormat instanceof PrefixOWLOntologyFormat) {
                final PrefixOWLOntologyFormat childPrefixFormat = (PrefixOWLOntologyFormat) childFormat;
                for (final String parentPrefixName : parentPrefixFormat.getPrefixNames()) {
                    final String parentPrefixValue = parentPrefixFormat.getPrefix(parentPrefixName);
                    final String childPrefixValue = childPrefixFormat.getPrefix(parentPrefixName);
                    if (parentPrefixValue.equals(childPrefixValue))
                        continue;
                    String childPrefixName = null;
                    for (final Entry<String, String> e : childPrefixFormat
                            .getPrefixName2PrefixMap().entrySet())
                        if (e.getValue().equals(parentPrefixValue))
                            childPrefixName = e.getKey();
                    if ((childPrefixValue != null)
                            && (!childPrefixValue.equals(parentPrefixValue)))
                        prefixChanges.add(new ModifyPrefix(parent,
                                parentPrefixName, parentPrefixValue, childPrefixValue));
                    if ((childPrefixName != null)
                            && (!childPrefixName.equals(parentPrefixName)))
                        prefixChanges
                                .add(new RenamePrefix(parent, parentPrefixName,
                                        parentPrefixValue, childPrefixName));
                    if ((childPrefixValue == null) && (childPrefixName == null))
                        prefixChanges.add(new RemovePrefix(parent,
                                parentPrefixName, parentPrefixValue));
                }
                for (final String childPrefixName : childPrefixFormat
                        .getPrefixNames()) {
                    final String parentPrefixValue = parentPrefixFormat
                            .getPrefix(childPrefixName);
                    final String childPrefixValue = childPrefixFormat
                            .getPrefix(childPrefixName);
                    if (childPrefixValue.equals(parentPrefixValue))
                        continue;
                    String parentPrefixName = null;
                    for (final Entry<String, String> e : parentPrefixFormat
                            .getPrefixName2PrefixMap().entrySet())
                        if (e.getValue().equals(parentPrefixValue))
                            parentPrefixName = e.getKey();
                    if ((parentPrefixValue == null) && (parentPrefixName == null))
                        prefixChanges.add(new AddPrefix(parent,
                                childPrefixName, childPrefixValue));
                }
            } else
                // Child has not-prefix format, remove all prefixes
                for (final String prefixName : parentPrefixFormat
                        .getPrefixNames()) {
                    final String prefix = parentPrefixFormat
                            .getPrefix(prefixName);
                    prefixChanges.add(new RemovePrefix(parent, prefixName,
                            prefix));
                }
        } else if (childFormat instanceof PrefixOWLOntologyFormat) {
            // Parent has not-prefix format, add all child prefixes
            final PrefixOWLOntologyFormat childPrefixFormat = (PrefixOWLOntologyFormat) childFormat;
            for (final String prefixName : childPrefixFormat.getPrefixNames()) {
                final String prefix = childPrefixFormat.getPrefix(prefixName);
                prefixChanges.add(new AddPrefix(parent, prefixName, prefix));
            }
        }
        return prefixChanges;
    }

    /**
     * @return Prefix name to prefix map
     */
    public Map<String, String> getAllPrefixes() {
        final Map<String, String> prefixName2PrefixMap = new HashMap<String, String>();
        final Map<String, String> prefixName2PrefixMap1 = parent
                .getOWLOntologyManager().getOntologyFormat(parent)
                .asPrefixOWLOntologyFormat().getPrefixName2PrefixMap();
        final Map<String, String> prefixName2PrefixMap2 = child
                .getOWLOntologyManager().getOntologyFormat(child)
                .asPrefixOWLOntologyFormat().getPrefixName2PrefixMap();
        prefixName2PrefixMap.putAll(prefixName2PrefixMap1);
        prefixName2PrefixMap.putAll(prefixName2PrefixMap2);
        return prefixName2PrefixMap;
    }
}

package owl2vcs.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.coode.owlapi.functionalrenderer.OWLFunctionalSyntaxOntologyStorer;
import org.coode.owlapi.latex.LatexOntologyStorer;
import org.coode.owlapi.obo.renderer.OBOFlatFileOntologyStorer;
import org.coode.owlapi.owlxml.renderer.OWLXMLOntologyStorer;
import org.coode.owlapi.rdf.rdfxml.RDFXMLOntologyStorer;
import org.coode.owlapi.turtle.TurtleOntologyStorer;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.MissingImportHandlingStrategy;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.NonMappingOntologyIRIMapper;

import uk.ac.manchester.cs.owl.owlapi.EmptyInMemOWLOntologyFactory;
import uk.ac.manchester.cs.owl.owlapi.OWLOntologyManagerImpl;
import uk.ac.manchester.cs.owl.owlapi.ParsableOWLOntologyFactory;
import uk.ac.manchester.cs.owl.owlapi.mansyntaxrenderer.ManchesterOWLSyntaxOntologyStorer;
import de.uulm.ecs.ai.owlapi.krssrenderer.KRSS2OWLSyntaxOntologyStorer;

class OntologyManagerThatDoesNotLoadImports extends OWLOntologyManagerImpl {

    private static final long serialVersionUID = -1874568361784481161L;

    public OntologyManagerThatDoesNotLoadImports(OWLDataFactory dataFactory) {
        super(dataFactory);
    }

    @Override
    protected OWLOntology loadImports(OWLImportsDeclaration declaration,
            OWLOntologyLoaderConfiguration configuration) throws OWLOntologyCreationException {
        return null;
    }

    public static OWLOntologyManager create() {
        OWLOntologyManager ontologyManager = new OntologyManagerThatDoesNotLoadImports(
                OWLManager.getOWLDataFactory());

        ontologyManager.addOntologyStorer(new RDFXMLOntologyStorer());
        ontologyManager.addOntologyStorer(new OWLXMLOntologyStorer());
        ontologyManager.addOntologyStorer(new OWLFunctionalSyntaxOntologyStorer());
        ontologyManager.addOntologyStorer(new ManchesterOWLSyntaxOntologyStorer());
        ontologyManager.addOntologyStorer(new OBOFlatFileOntologyStorer());
        ontologyManager.addOntologyStorer(new KRSS2OWLSyntaxOntologyStorer());
        ontologyManager.addOntologyStorer(new TurtleOntologyStorer());
        ontologyManager.addOntologyStorer(new LatexOntologyStorer());

        ontologyManager.addIRIMapper(new NonMappingOntologyIRIMapper());

        ontologyManager.addOntologyFactory(new EmptyInMemOWLOntologyFactory());
        ontologyManager.addOntologyFactory(new ParsableOWLOntologyFactory());

        return ontologyManager;
    }

}

public class OntologyUtils {
    protected OntologyUtils() {
    };

    public static OWLOntology loadOntology(final FileDocumentSource source)
            throws OWLOntologyCreationException {

        final OWLOntologyLoaderConfiguration config = new OWLOntologyLoaderConfiguration();
        config.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);
        final OWLOntologyManager manager = OntologyManagerThatDoesNotLoadImports.create();
        final OWLOntology ontology = manager.loadOntologyFromOntologyDocument(source, config);
        return ontology;
    }

    public static OWLOntology loadOntology(final String name, Class<?> klass)
            throws OWLOntologyCreationException {
        final FileDocumentSource source = new FileDocumentSource(new File(
                klass.getResource(name).getFile()));
        return OntologyUtils.loadOntology(source);
    }

    public static OWLOntology copyOntology(OWLOntology original)
            throws OWLOntologyCreationException {
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
        OWLOntology ontology = manager.createOntology(original.getOntologyID().getOntologyIRI());
        manager.addAxioms(ontology, original.getAxioms());
        List<OWLOntologyChange> changes = new ArrayList<OWLOntologyChange>(
                original.getAnnotations().size() + original.getImportsDeclarations().size() + 2);
        // annotations
        for (OWLAnnotation a : original.getAnnotations()) {
            changes.add(new AddOntologyAnnotation(ontology, a));
        }
        // imports
        for (OWLImportsDeclaration decl : original.getImportsDeclarations()) {
            changes.add(new AddImport(ontology, decl));
        }
        manager.applyChanges(changes);
        OWLOntologyManager originalManager = original.getOWLOntologyManager();
        manager.setOntologyFormat(ontology, originalManager.getOntologyFormat(original));
        return ontology;
    }
}

package owl2vcs.utils;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.MissingImportHandlingStrategy;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class OntologyUtils {
    protected OntologyUtils() { };

    public static OWLOntology loadOntology(final FileDocumentSource source)
            throws OWLOntologyCreationException {
        final OWLOntologyLoaderConfiguration config = new OWLOntologyLoaderConfiguration();
        config.setMissingImportHandlingStrategy(MissingImportHandlingStrategy.SILENT);
        final OWLOntologyManager manager = OWLManager
                .createOWLOntologyManager();
        final OWLOntology ontology = manager.loadOntologyFromOntologyDocument(
                source, config);
        return ontology;
    }
}

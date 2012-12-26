package owl2vcs.utils;

import static org.junit.Assert.fail;

import org.junit.Test;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import owl2vcs.changeset.ChangeSet;
import owl2vcs.changeset.FullChangeSet;
import owl2vcs.io.FunctionalChangesetSerializer;

public class OntologyUtilsTest {

    @Test
    public void testCopyOntologyEquals() throws OWLOntologyCreationException {
        OWLOntology original = OntologyUtils.loadOntology("pizza13.owl", OntologyUtilsTest.class);
        OWLOntology copy = OntologyUtils.copyOntology(original);
        ChangeSet difference = new FullChangeSet(original, copy);
        if (!difference.isEmpty()) {
            System.out.println("Expected no changes, but found the following changes:");
            (new FunctionalChangesetSerializer()).write(difference, System.out);
            fail();
        }
    }

    @Test
    public void testCopyOntologyChangedNotEquals() throws OWLOntologyCreationException {
        OWLOntology original = OntologyUtils.loadOntology("pizza13.owl", OntologyUtilsTest.class);
        OWLOntology copy = OntologyUtils.copyOntology(original);
        OWLDataFactory factory = copy.getOWLOntologyManager().getOWLDataFactory();
        copy.getOWLOntologyManager().addAxiom(copy, factory.getOWLDeclarationAxiom(factory.getOWLClass(IRI.create("dummy:iri"))));
        ChangeSet difference = new FullChangeSet(original, copy);
        if (!(difference.size() == 1)) {
            System.out.println("Expected 1 axiom change, but found the following changes:");
            (new FunctionalChangesetSerializer()).write(difference, System.out);
            fail();
        }
    }

}

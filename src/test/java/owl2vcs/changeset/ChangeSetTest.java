package owl2vcs.changeset;

import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;

import org.antlr.runtime.RecognitionException;
import org.coode.owlapi.functionalparser.ParseException;
import org.junit.Test;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import owl2vcs.changes.UnknownOntologyFormatException;
import owl2vcs.io.FunctionalChangesetParser;
import owl2vcs.io.FunctionalChangesetSerializer;
import owl2vcs.utils.OntologyUtils;

public class ChangeSetTest {

    private static MutableChangeSet loadChangeset(final String name)
            throws OWLOntologyCreationException, RecognitionException,
            UnknownOntologyFormatException, ParseException, IOException {
        final String filename = ChangeSetTest.class.getResource(name).getFile();
        FunctionalChangesetParser parser = new FunctionalChangesetParser(filename);
        parser.init();
        return parser.changeset();
    }

    @Test
    public void testApplyTo() throws OWLOntologyCreationException, RecognitionException, UnknownOntologyFormatException, ParseException, IOException, UnsupportedOntologyFormatException {
        OWLOntology parent = OntologyUtils.loadOntology("parent.owl", ChangeSetTest.class);
        OWLOntology expected = OntologyUtils.loadOntology("child.owl", ChangeSetTest.class);
        MutableChangeSet changeSet = loadChangeset("changeset.txt");
        OWLOntology actual = OntologyUtils.copyOntology(parent);
        changeSet.applyTo(actual);
        FullChangeSet difference  = new FullChangeSet(actual, expected);
        if (!difference.isEmpty()) {
            // print the difference
            (new FunctionalChangesetSerializer()).write(difference, System.out);
            fail();
        }
    }

}

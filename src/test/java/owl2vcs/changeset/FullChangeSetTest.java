package owl2vcs.changeset;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.antlr.runtime.RecognitionException;
import org.coode.owlapi.functionalparser.ParseException;
import org.junit.Test;
import org.semanticweb.owlapi.change.SetOntologyIDData;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyID;

import owl2vcs.changes.AddPrefixData;
import owl2vcs.changes.PrefixChangeData;
import owl2vcs.changes.UnknownOntologyFormatException;
import owl2vcs.io.FunctionalChangesetParser;
import owl2vcs.io.FunctionalChangesetSerializer;
import owl2vcs.utils.ChangeSetUtils;
import owl2vcs.utils.OntologyUtils;

public class FullChangeSetTest {

    private static Map<String, String> loadMap(final String name) throws FileNotFoundException {
        final Map<String, String> map = new HashMap<String, String>();
        final Scanner scanner = new Scanner(new FileReader(FullChangeSetTest.class
                .getResource(name).getFile()));
        try {
            while (scanner.hasNextLine()) {
                final String[] columns = scanner.nextLine().split("=");
                map.put(columns[0], columns[1]);
            }
        } finally {
            scanner.close();
        }
        return map;
    }

    private static OWLOntology loadOntology(final String name) throws OWLOntologyCreationException {
        return OntologyUtils.loadOntology(name, FullChangeSetTest.class);
    }

    private static MutableChangeSet loadChangeset(final String name)
            throws OWLOntologyCreationException, RecognitionException,
            UnknownOntologyFormatException, ParseException, IOException {
        final String filename = FullChangeSetTest.class.getResource(name).getFile();
        FunctionalChangesetParser parser = new FunctionalChangesetParser(filename);
        parser.init();
        return parser.changeset();
    }

    @Test
    public void testFullChangeSetPrefixes() throws OWLOntologyCreationException {
        final OWLOntology parent = loadOntology("prefixes1.owl");
        final OWLOntology child = loadOntology("prefixes2.owl");
        final Collection<PrefixChangeData> expected = new ArrayList<PrefixChangeData>();
        expected.add(new AddPrefixData("dc:", "http://purl.org/dc/elements/1.1/"));
        final FullChangeSet cs = new FullChangeSet(parent, child);
        assertTrue(cs.getPrefixChanges().equals(expected));
    }

    @Test
    public void testGetAllPrefixes() throws OWLOntologyCreationException, FileNotFoundException {
        final OWLOntology parent = loadOntology("prefixes1.owl");
        final OWLOntology child = loadOntology("prefixes2.owl");
        final FullChangeSet cs = new FullChangeSet(parent, child);
        final Map<String, String> prefixes = cs.getAllPrefixes();
        final Map<String, String> expected = loadMap("allPrefixes.txt");
        assertTrue(prefixes.equals(expected));
    }

    @Test
    public void testFullChangeSet() throws OWLOntologyCreationException, RecognitionException,
            UnknownOntologyFormatException, ParseException, IOException {
        final OWLOntology parent = loadOntology("parent.owl");
        final OWLOntology child = loadOntology("child.owl");
        final FullChangeSet actual = new FullChangeSet(parent, child);
        final MutableChangeSet expected = loadChangeset("changeset.txt");
        if (!actual.equals(expected)) {
            FunctionalChangesetSerializer serializer = new FunctionalChangesetSerializer();
            PrintStream sysout = new PrintStream(System.out, true, "UTF-8");
            ChangeSet missing = ChangeSetUtils.subtractChangesets(expected, actual);
            ChangeSet excess = ChangeSetUtils.subtractChangesets(actual, expected);
            sysout.println("Missing changes:");
            serializer.write(missing, sysout);
            sysout.println();
            sysout.println("Excess changes:");
            serializer.write(excess, sysout);
            fail();
        }
    }

    @Test
    public void testEquals() throws OWLOntologyCreationException {
        final OWLOntology parent1 = loadOntology("parent.owl");
        final OWLOntology child1 = loadOntology("child.owl");
        final FullChangeSet changeSet1 = new FullChangeSet(parent1, child1);
        final OWLOntology parent2 = loadOntology("parent.owl");
        final OWLOntology child2 = loadOntology("child.owl");
        final FullChangeSet changeSet2 = new FullChangeSet(parent2, child2);
        assertTrue(changeSet1.equals(changeSet2));
        final MutableChangeSet mutableChangeSet = new MutableChangeSet(changeSet2);
        assertTrue(changeSet1.equals(mutableChangeSet));
        assertTrue(mutableChangeSet.equals(changeSet1));
        mutableChangeSet.setOntologyIdChange(new SetOntologyIDData(new OWLOntologyID(IRI
                .create("dummy:id"))));
        assertFalse(changeSet1.equals(mutableChangeSet));
        assertFalse(mutableChangeSet.equals(changeSet1));
    }

}

package owl2vcs.io;

import static org.junit.Assert.*;

import java.io.IOException;

import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.coode.owlapi.functionalparser.ParseException;
import org.junit.Test;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import owl2vcs.changes.UnknownOntologyFormatException;
import owl2vcs.changeset.ChangeSet;

public class FunctionalChangesetParserTest {

    @Test
    public void testChangeset() throws RecognitionException,
            UnknownOntologyFormatException, ParseException,
            OWLOntologyCreationException, IOException {
        String s = "xsd:=<http://www.w3.org/2001/XMLSchema#>\r\n"
                + "owl:=<http://www.w3.org/2002/07/owl#>\r\n"
                + ":=<http://www.co-ode.org/ontologies/pizza/pizza.owl#>\r\n"
                + "rdf:=<http://www.w3.org/1999/02/22-rdf-syntax-ns#>\r\n"
                + "pizza:=<http://www.co-ode.org/ontologies/pizza/pizza.owl#>\r\n"
                + "rdfs:=<http://www.w3.org/2000/01/rdf-schema#>\r\n"
                + "skos:=<http://www.w3.org/2004/02/skos/core#>\r\n"
                + "gn:=<http://www.geonames.org/ontology#>\r\n"
                + "foaf:=<http://xmlns.com/foaf/0.1/>\r\n"
                + "dcterms:=<http://purl.org/dc/terms/>\r\n"

                + "* OntologyFormat(\"OWL Functional Syntax\")\r\n"
                + "* OntologyID(<http://www.co-ode.org/ontologies/pizza/pizza.owl>)\r\n"
                + "+ Prefix(xsd:=<http://www.w3.org/2001/XMLSchema#>)\r\n"
                + "+ Annotation(<http://www.w3.org/2002/07/owl#versionInfo> \"version 1.5\"^^<http://www.w3.org/2001/XMLSchema#string>)\r\n"
                + "+ Declaration(ObjectProperty(<http://www.co-ode.org/ontologies/pizza/pizza.owl#hasBase>))"
                + "+ DisjointClasses(pizza:American pizza:PrinceCarlo)\r\n"
                + "+ SubClassOf(pizza:Giardiniera ObjectSomeValuesFrom(pizza:hasTopping pizza:MozzarellaTopping))\r\n"
                + "+ AnnotationAssertion(rdfs:label <http://www.co-ode.org/ontologies/pizza/pizza.owl#SpicyTopping> \"CoberturaTemperada\"@pt)\r\n"
                + "+ SubClassOf(pizza:Caprina ObjectSomeValuesFrom(pizza:hasTopping pizza:GoatsCheeseTopping))\r\n"
                + "+ AnnotationAssertion(skos:inScheme <http://www.geonames.org/ontology#S.MNNI> <http://www.geonames.org/ontology#S>)\r\n"
                + "+ ClassAssertion(foaf:Person <http://data.semanticweb.org/person/bernard-vatant>)\r\n"
                + "+ AnnotationAssertion(skos:definition <http://www.geonames.org/ontology#S.STNB> \"помещение, которое используется в качестве базы для проведения различных научных исследований\"@ru)\r\n"
                + "+ AnnotationAssertion(skos:definition <http://www.geonames.org/ontology#S.MFGSG> \"a facility for converting raw sugar into refined sugar\"@en)\r\n"
                + "+ SubDataPropertyOf(gn:geonamesID dcterms:identifier)\r\n";

        FunctionalChangesetParser parser = new FunctionalChangesetParser(
                new CommonTokenStream(new FunctionalChangesetLexer(
                        new ANTLRStringStream(s))));
        parser.init();
        ChangeSet cs = parser.changeset();
        final int expected = 14;
        assertEquals(cs.size(), expected);
    }
}

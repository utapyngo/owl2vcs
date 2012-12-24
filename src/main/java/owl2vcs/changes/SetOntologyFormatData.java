package owl2vcs.changes;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.io.OWLFunctionalSyntaxOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public class SetOntologyFormatData extends CustomOntologyChangeData {

    private static final long serialVersionUID = 4000984588491273026L;

    private final OWLOntologyFormat newFormat;

    public SetOntologyFormatData(OWLOntologyFormat newFormat) {
        if (newFormat == null) {
            throw new NullPointerException("newFormat must not be null");
        }
        this.newFormat = newFormat;
    }

    /**
     * Creates a set ontology format change, which will set the format of the
     * ontology to the specified new format specified as a string.
     *
     * @param ont
     *            The ontology whose format is to be changed
     * @param newOntologyFormat
     *            A string representing the new ontology format
     * @throws UnknownOntologyFormatException
     */
    public SetOntologyFormatData(final String newFormatString)
            throws UnknownOntologyFormatException {
        super();
        OWLOntologyFormat newOntologyFormat;
        if (newFormatString.equals("RDF/XML"))
            newOntologyFormat = new RDFXMLOntologyFormat();
        else if (newFormatString.equals("OWL/XML"))
            newOntologyFormat = new OWLXMLOntologyFormat();
        else if (newFormatString.equals("Turtle"))
            newOntologyFormat = new TurtleOntologyFormat();
        else if (newFormatString.equals("OWL Functional Syntax"))
            newOntologyFormat = new OWLFunctionalSyntaxOntologyFormat();
        else if (newFormatString.equals("Manchester OWL Syntax"))
            newOntologyFormat = new ManchesterOWLSyntaxOntologyFormat();
        else
            throw new UnknownOntologyFormatException("Unknown format: " + newFormatString);
        this.newFormat = newOntologyFormat;
    }

    @Override
    public SetOntologyFormat createOntologyChange(OWLOntology ontology) {
        if (ontology == null) {
            throw new NullPointerException("ontology must not be null");
        }
        return new SetOntologyFormat(ontology, newFormat);
    }

    public OWLOntologyFormat getNewFormat() {
        return newFormat;
    }

    @Override
    public int hashCode() {
        return "SetOntologyFormatData".hashCode() + newFormat.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SetOntologyFormatData)) {
            return false;
        }
        SetOntologyFormatData other = (SetOntologyFormatData) obj;
        return newFormat.equals(other.newFormat);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SetOntologyFormatData");
        sb.append("(OntologyFormat(");
        sb.append(newFormat);
        sb.append("))");
        return sb.toString();
    }

    @Override
    public <R, E extends Exception> R accept(
            CustomOntologyChangeDataVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

}

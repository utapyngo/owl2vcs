package owl2vcs.changes;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.coode.owlapi.turtle.TurtleOntologyFormat;
import org.semanticweb.owlapi.io.OWLFunctionalSyntaxOntologyFormat;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;

import owl2vcs.changeset.CustomOntologyChangeVisitor;

public class SetOntologyFormat extends CustomOntologyChange {

    private final OWLOntologyFormat ontologyFormat;

    private final OWLOntologyFormat newOntologyFormat;

    /**
     * Creates a set ontology format change, which will set the format of the
     * ontology to the specified new format.
     *
     * @param ont
     *            The ontology whose format is to be changed
     * @param newOntologyFormat
     *            The new ontology format
     */
    public SetOntologyFormat(final OWLOntology ont,
            final OWLOntologyFormat newOntologyFormat) {
        super(ont);
        this.ontologyFormat = ont.getOWLOntologyManager()
                .getOntologyFormat(ont);
        this.newOntologyFormat = newOntologyFormat;
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
    public SetOntologyFormat(final OWLOntology ont,
            final String newFormatString) throws UnknownOntologyFormatException {
        super(ont);
        this.ontologyFormat = ont.getOWLOntologyManager()
                .getOntologyFormat(ont);
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
        this.newOntologyFormat = newOntologyFormat;
    }

    /**
     * Gets the original format of the ontology whose format was changed.
     *
     * @return The original format
     */
    public OWLOntologyFormat getOriginalOntologyFormat() {
        return ontologyFormat;
    }

    /**
     * @return the new format - i.e. the format of the ontology after the change
     *         was applied.
     */
    public OWLOntologyFormat getNewOntologyFormat() {
        return newOntologyFormat;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((newOntologyFormat == null) ? 0 : newOntologyFormat
                        .hashCode());
        result = prime * result
                + ((ontologyFormat == null) ? 0 : ontologyFormat.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof SetOntologyFormat))
            return false;
        SetOntologyFormat other = (SetOntologyFormat) obj;
        if (newOntologyFormat == null) {
            if (other.newOntologyFormat != null)
                return false;
        } else if (!newOntologyFormat.equals(other.newOntologyFormat))
            return false;
        if (ontologyFormat == null) {
            if (other.ontologyFormat != null)
                return false;
        } else if (!ontologyFormat.equals(other.ontologyFormat))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("SET ONTOLOGY FORMAT: ");
        sb.append(getNewOntologyFormat().toString());
        return sb.toString();
    }

    @Override
    public void accept(final CustomOntologyChangeVisitor visitor) {
        visitor.visit(this);
    }


    @Override
    public SetOntologyFormatData getChangeData() {
        return new SetOntologyFormatData(newOntologyFormat);
    }

}

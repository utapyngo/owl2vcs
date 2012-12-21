package owl2vcs.changes;

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

    @Override
    public <R, E extends Exception> R accept(
            CustomOntologyChangeDataVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
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

}

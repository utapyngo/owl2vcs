package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;
import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public class AddPrefixData extends PrefixChangeData {

    private static final long serialVersionUID = 2801228470061214801L;

    public AddPrefixData(String prefixName, String prefix) {
        super(prefixName, prefix);
    }

    @Override
    public <R, E extends Exception> R accept(
            CustomOntologyChangeDataVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public AddPrefix createOntologyChange(OWLOntology ontology) {
        if (ontology == null) {
            throw new NullPointerException("ontology must not be null");
        }
        return new AddPrefix(ontology, getPrefixName(), getPrefix());
    }

    @Override
    public int hashCode() {
        return "AddPrefixData".hashCode()
                + getPrefixName().hashCode()
                + getPrefix().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof AddPrefixData)) {
            return false;
        }
        AddPrefixData other = (AddPrefixData) obj;
        return getPrefixName().equals(other.getPrefixName())
                && getPrefix().equals(other.getPrefix());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("AddPrefixData");
        sb.append("(");
        sb.append(getPrefixName().toString());
        sb.append("=");
        sb.append(getPrefix().toString());
        sb.append(")");
        return sb.toString();
    }
}

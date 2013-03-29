package owl2vcs.changes;

import java.util.Collection;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public class RenamePrefixData extends PrefixChangeData {

    private static final long serialVersionUID = -3363060595690396626L;
    private final String oldPrefixName;

    public RenamePrefixData(String oldPrefixName, String prefix, String newPrefixName) {
        super(newPrefixName, prefix);
        this.oldPrefixName = oldPrefixName;
    }

    @Override
    public <R, E extends Exception> R accept(
            CustomOntologyChangeDataVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public RenamePrefix createOntologyChange(OWLOntology ontology) {
        if (ontology == null) {
            throw new NullPointerException("ontology must not be null");
        }
        return new RenamePrefix(ontology, getOldPrefixName(), getPrefix(), getPrefixName());
    }

    /**
     * @return the newPrefixName
     */
    public String getOldPrefixName() {
        return oldPrefixName;
    }

    @Override
    public int hashCode() {
        return "RenamePrefixData".hashCode()
                + getPrefixName().hashCode()
                + getPrefix().hashCode()
                + getOldPrefixName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof RenamePrefixData)) {
            return false;
        }
        RenamePrefixData other = (RenamePrefixData) obj;
        return getPrefixName().equals(other.getPrefixName())
                && getPrefix().equals(other.getPrefix())
                && getOldPrefixName().equals(other.getOldPrefixName());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("RenamePrefixData");
        sb.append("(");
        sb.append(getOldPrefixName().toString());
        sb.append(" ");
        sb.append(getPrefixName().toString());
        sb.append("=");
        sb.append(getPrefix().toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public Collection<String> getPrefixSignature() {
        final Collection<String> sig = super.getPrefixSignature();
        sig.add(getOldPrefixName());
        return sig;
    }
}

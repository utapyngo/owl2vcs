package owl2vcs.changes;

import java.util.Collection;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public class ModifyPrefixData extends PrefixChangeData {

    private static final long serialVersionUID = -4830654067341708579L;

    private final String newPrefix;

    public ModifyPrefixData(String prefixName, String prefix, String newPrefix) {
        super(prefixName, prefix);
        this.newPrefix = newPrefix;
    }

    @Override
    public <R, E extends Exception> R accept(
            CustomOntologyChangeDataVisitor<R, E> visitor) throws E {
        return visitor.visit(this);
    }

    @Override
    public ModifyPrefix createOntologyChange(OWLOntology ontology) {
        if (ontology == null) {
            throw new NullPointerException("ontology must not be null");
        }
        return new ModifyPrefix(ontology, getPrefixName(), getPrefix(), getNewPrefix());
    }

    /**
     * @return the newPrefix
     */
    public String getNewPrefix() {
        return newPrefix;
    }

    @Override
    public int hashCode() {
        return "ModifyPrefixData".hashCode()
                + getPrefixName().hashCode()
                + getPrefix().hashCode()
                + getNewPrefix().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ModifyPrefixData)) {
            return false;
        }
        ModifyPrefixData other = (ModifyPrefixData) obj;
        return getPrefixName().equals(other.getPrefixName())
                && getPrefix().equals(other.getPrefix())
                && getNewPrefix().equals(other.getNewPrefix());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ModifyPrefixData");
        sb.append("(");
        sb.append(getPrefixName().toString());
        sb.append("=");
        sb.append(getPrefix().toString());
        sb.append(" ");
        sb.append(getNewPrefix().toString());
        sb.append(")");
        return sb.toString();
    }

    @Override
    public Collection<String> getPrefixSignature() {
        final Collection<String> sig = super.getPrefixSignature();
        sig.add(getNewPrefix());
        return sig;
    }
}

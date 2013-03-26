package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.CustomOntologyChangeVisitor;

public class ModifyPrefix extends PrefixChange {

    private final String oldPrefix;

    public ModifyPrefix(final OWLOntology ont, final String prefixName,
            final String oldPrefix, final String newPrefix) {
        super(ont, prefixName, newPrefix);
        this.oldPrefix = oldPrefix;
    }

    public String getOldPrefix() {
        return oldPrefix;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("MODIFY PREFIX: ");
        sb.append(getPrefixName().toString() + "=" + getPrefix().toString());
        return sb.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((oldPrefix == null) ? 0 : oldPrefix.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof ModifyPrefix))
            return false;
        ModifyPrefix other = (ModifyPrefix) obj;
        if (oldPrefix == null) {
            if (other.oldPrefix != null)
                return false;
        } else if (!oldPrefix.equals(other.oldPrefix))
            return false;
        return true;
    }

    @Override
    public void accept(final CustomOntologyChangeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public ModifyPrefixData getChangeData() {
        return new ModifyPrefixData(getPrefixName(), getOldPrefix(), getPrefix());
    }
}

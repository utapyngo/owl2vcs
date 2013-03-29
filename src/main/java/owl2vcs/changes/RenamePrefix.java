package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.CustomOntologyChangeVisitor;

public class RenamePrefix extends PrefixChange {

    private final String oldPrefixName;

    public RenamePrefix(final OWLOntology ont, final String oldPrefixName,
            final String prefix, final String newPrefixName) {
        super(ont, newPrefixName, prefix);
        this.oldPrefixName = oldPrefixName;
    }

    public String getOldPrefixName() {
        return oldPrefixName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((oldPrefixName == null) ? 0 : oldPrefixName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof RenamePrefix))
            return false;
        RenamePrefix other = (RenamePrefix) obj;
        if (oldPrefixName == null) {
            if (other.oldPrefixName != null)
                return false;
        } else if (!oldPrefixName.equals(other.oldPrefixName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RENAME PREFIX: ");
        sb.append(getPrefixName().toString() + "=" + getPrefix().toString());
        return sb.toString();
    }

    @Override
    public void accept(final CustomOntologyChangeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public RenamePrefixData getChangeData() {
        return new RenamePrefixData(getOldPrefixName(), getPrefix(), getPrefixName());
    }
}

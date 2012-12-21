package owl2vcs.changes;

import java.util.Collection;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.CustomOntologyChangeVisitor;

public class RenamePrefix extends PrefixChange {

    private final String newPrefixName;

    public RenamePrefix(final OWLOntology ont, final String prefixName,
            final String prefix, final String newPrefixName) {
        super(ont, prefixName, prefix);
        this.newPrefixName = newPrefixName;
    }

    public String getNewPrefixName() {
        return newPrefixName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result
                + ((newPrefixName == null) ? 0 : newPrefixName.hashCode());
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
        if (newPrefixName == null) {
            if (other.newPrefixName != null)
                return false;
        } else if (!newPrefixName.equals(other.newPrefixName))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("RENAME PREFIX: ");
        sb.append(getNewPrefixName().toString() + "=" + getPrefix().toString());
        return sb.toString();
    }

    @Override
    public void accept(final CustomOntologyChangeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Collection<String> getPrefixSignature() {
        final Collection<String> sig = super.getPrefixSignature();
        sig.add(getNewPrefixName());
        return sig;
    }

    @Override
    public RenamePrefixData getChangeData() {
        return new RenamePrefixData(getPrefixName(), getPrefix(), getNewPrefixName());
    }
}

package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;

public abstract class PrefixChange extends CustomOntologyChange {

    private final String prefixName;
    private final String prefix;

    protected PrefixChange(final OWLOntology ont, final String prefixName,
            final String prefix) {
        super(ont);
        this.prefixName = prefixName;
        this.prefix = prefix;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public String getPrefix() {
        return prefix;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result
                + ((prefixName == null) ? 0 : prefixName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PrefixChange))
            return false;
        PrefixChange other = (PrefixChange) obj;
        if (prefix == null) {
            if (other.prefix != null)
                return false;
        } else if (!prefix.equals(other.prefix))
            return false;
        if (prefixName == null) {
            if (other.prefixName != null)
                return false;
        } else if (!prefixName.equals(other.prefixName))
            return false;
        return true;
    }
}

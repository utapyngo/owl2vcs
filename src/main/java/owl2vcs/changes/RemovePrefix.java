package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.OntologyChangeVisitor;

public class RemovePrefix extends PrefixChange {

    public RemovePrefix(final OWLOntology ont, final String prefixName,
            final String prefix) {
        super(ont, prefixName, prefix);
    }

    @Override
    public int hashCode() {
        final int prime = 3;
        return prime * super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof RemovePrefix))
            return false;
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("REMOVE PREFIX: ");
        sb.append(getPrefixName().toString() + "=" + getPrefix().toString());
        return sb.toString();
    }

    @Override
    public void accept(final OntologyChangeVisitor visitor) {
        visitor.visit(this);
    }
}

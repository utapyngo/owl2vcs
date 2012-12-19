package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.OntologyChangeVisitor;

public class AddPrefix extends PrefixChange {

    public AddPrefix(final OWLOntology ont, final String prefixName,
            final String prefix) {
        super(ont, prefixName, prefix);
    }
/*
    @Override
    public int hashCode() {
        return 17 + getPrefixName().hashCode() * 13 + getPrefix().hashCode()
                * 13;
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == this)
            return true;
        if (!(obj instanceof AddPrefix))
            return false;
        final AddPrefix change = (AddPrefix) obj;
        return change.getPrefixName().equals(this.getPrefixName())
                && change.getPrefix().equals(this.getPrefix());
    }
*/
    
    @Override
    public int hashCode() {
        final int prime = 2;
        return prime * super.hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (!(obj instanceof AddPrefix))
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("ADD PREFIX: ");
        sb.append(getPrefixName().toString() + "=" + getPrefix().toString());
        return sb.toString();
    }

    @Override
    public void accept(final OntologyChangeVisitor visitor) {
        visitor.visit(this);
    }
}

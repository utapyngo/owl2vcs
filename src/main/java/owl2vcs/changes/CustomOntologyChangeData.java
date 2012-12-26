package owl2vcs.changes;

import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.change.OWLOntologyChangeDataVisitor;

import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public abstract class CustomOntologyChangeData extends OWLOntologyChangeData {

    private static final long serialVersionUID = -6017236080840691763L;

    public abstract <R, E extends Exception> R accept(CustomOntologyChangeDataVisitor<R, E> visitor) throws E;

    @Override
    public <R, E extends Exception> R accept(
            OWLOntologyChangeDataVisitor<R, E> visitor) throws E {
        // Do nothing since the visitor knows nothing about us
        return null;
    }

}

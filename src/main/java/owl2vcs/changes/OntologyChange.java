package owl2vcs.changes;

import org.semanticweb.owlapi.model.OWLOntology;

import owl2vcs.changeset.OntologyChangeVisitor;

public abstract class OntologyChange {

    private final OWLOntology ont;

    public OntologyChange(final OWLOntology ont) {
        this.ont = ont;
    }

    public OWLOntology getOntology() {
        return ont;
    }

    public abstract void accept(OntologyChangeVisitor visitor);

}

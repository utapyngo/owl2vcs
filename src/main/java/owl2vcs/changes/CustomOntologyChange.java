package owl2vcs.changes;

import java.util.Collections;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyChangeVisitor;
import org.semanticweb.owlapi.model.OWLOntologyChangeVisitorEx;

import owl2vcs.changeset.CustomOntologyChangeVisitor;

public abstract class CustomOntologyChange extends OWLOntologyChange {

    public CustomOntologyChange(final OWLOntology ont) {
        super(ont);
    }

    public abstract void accept(CustomOntologyChangeVisitor visitor);


    @Override
    public boolean isAxiomChange() {
        return false;
    }


    @Override
    public boolean isAddAxiom() {
        return false;
    }


    @Override
    public OWLAxiom getAxiom() {
        throw new UnsupportedOperationException("Not an axiom change");
    }


    @Override
    public boolean isImportChange() {
        return false;
    }


    @Override
    public Set<OWLEntity> getSignature() {
        return Collections.emptySet();
    }


    @Override
    public void accept(OWLOntologyChangeVisitor visitor) {
        // Do nothing since visitor does not know anything about us
    }


    @Override
    public <O> O accept(OWLOntologyChangeVisitorEx<O> visitor) {
        // Do nothing since visitor does not know anything about us
        return null;
    }
}

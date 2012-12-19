package owl2vcs.analysis;

import java.util.Collection;
import java.util.Set;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.RemoveOntologyAnnotation;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.util.OWLEntityCollector;

import owl2vcs.changes.AddPrefix;
import owl2vcs.changes.ModifyPrefix;
import owl2vcs.changes.RemovePrefix;
import owl2vcs.changes.RenamePrefix;
import owl2vcs.changes.SetOntologyFormat;
import owl2vcs.changeset.OntologyChangeVisitor;

public class EntityCollector extends OWLEntityCollector implements
        OntologyChangeVisitor {

    public EntityCollector(final Set<OWLEntity> toReturn,
            final Collection<OWLAnonymousIndividual> anonsToReturn) {
        super(toReturn, anonsToReturn);
    }

    @Override
    public void visit(final AddAxiom change) {
        change.getAxiom().accept(this);
    }

    @Override
    public void visit(final RemoveAxiom change) {
        change.getAxiom().accept(this);
    }

    @Override
    public void visit(final SetOntologyID change) {
    }

    @Override
    public void visit(final AddImport change) {
    }

    @Override
    public void visit(final RemoveImport change) {
    }

    @Override
    public void visit(final AddOntologyAnnotation change) {
        change.getAnnotation().accept(this);
    }

    @Override
    public void visit(final RemoveOntologyAnnotation change) {
        change.getAnnotation().accept(this);
    }

    @Override
    public void visit(final SetOntologyFormat change) {
    }

    @Override
    public void visit(final AddPrefix change) {
    }

    @Override
    public void visit(final RemovePrefix change) {
    }

    @Override
    public void visit(final ModifyPrefix change) {
    }

    @Override
    public void visit(final RenamePrefix change) {
    }
}

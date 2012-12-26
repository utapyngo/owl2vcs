package owl2vcs.analysis;

import java.util.Collection;
import java.util.Set;

import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.AddImportData;
import org.semanticweb.owlapi.change.AddOntologyAnnotationData;
import org.semanticweb.owlapi.change.RemoveAxiomData;
import org.semanticweb.owlapi.change.RemoveImportData;
import org.semanticweb.owlapi.change.RemoveOntologyAnnotationData;
import org.semanticweb.owlapi.change.SetOntologyIDData;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.util.OWLEntityCollector;

import owl2vcs.changes.AddPrefixData;
import owl2vcs.changes.ModifyPrefixData;
import owl2vcs.changes.RemovePrefixData;
import owl2vcs.changes.RenamePrefixData;
import owl2vcs.changes.SetOntologyFormatData;
import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public class EntityCollector extends OWLEntityCollector implements
        CustomOntologyChangeDataVisitor<Object, EntityCollectorException> {

    public EntityCollector(final Set<OWLEntity> toReturn,
            final Collection<OWLAnonymousIndividual> anonsToReturn) {
        super(toReturn, anonsToReturn);
    }

    @Override
    public Object visit(AddAxiomData data) {
        data.getAxiom().accept(this);
        return null;
    }

    @Override
    public Object visit(RemoveAxiomData data) {
        data.getAxiom().accept(this);
        return null;
    }

    @Override
    public Object visit(AddOntologyAnnotationData data) {
        data.getAnnotation().accept(this);
        return null;
    }

    @Override
    public Object visit(RemoveOntologyAnnotationData data) {
        data.getAnnotation().accept(this);
        return null;
    }

    @Override
    public Object visit(SetOntologyIDData data) {
        return null;
    }

    @Override
    public Object visit(AddImportData data) {
        return null;
    }

    @Override
    public Object visit(RemoveImportData data) {
        return null;
    }

    @Override
    public Object visit(SetOntologyFormatData data) {
        return null;
    }

    @Override
    public Object visit(AddPrefixData data) {
        return null;
    }

    @Override
    public Object visit(RemovePrefixData data) {
        return null;
    }

    @Override
    public Object visit(ModifyPrefixData data) {
        return null;
    }

    @Override
    public Object visit(RenamePrefixData data) {
        return null;
    }
}

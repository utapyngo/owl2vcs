package owl2vcs.analysis;

import java.util.Set;

import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.AddImportData;
import org.semanticweb.owlapi.change.AddOntologyAnnotationData;
import org.semanticweb.owlapi.change.RemoveAxiomData;
import org.semanticweb.owlapi.change.RemoveImportData;
import org.semanticweb.owlapi.change.RemoveOntologyAnnotationData;
import org.semanticweb.owlapi.change.SetOntologyIDData;

import owl2vcs.changes.AddPrefixData;
import owl2vcs.changes.ModifyPrefixData;
import owl2vcs.changes.PrefixChangeData;
import owl2vcs.changes.RemovePrefixData;
import owl2vcs.changes.RenamePrefixData;
import owl2vcs.changes.SetOntologyFormatData;
import owl2vcs.changeset.CustomOntologyChangeDataVisitor;

public class PrefixCollector implements
        CustomOntologyChangeDataVisitor<Object, PrefixCollectorException> {

    private Set<String> names;
    private Set<String> values;

    public PrefixCollector(final Set<String> names, final Set<String> values) {
        this.names = names;
        this.values = values;
    }

    protected void visitPrefixChange(PrefixChangeData data) {
        names.add(data.getPrefixName());
        values.add(data.getPrefix());
    }

    @Override
    public Object visit(AddAxiomData data) {
        return null;
    }

    @Override
    public Object visit(RemoveAxiomData data) {
        return null;
    }

    @Override
    public Object visit(AddOntologyAnnotationData data) {
        return null;
    }

    @Override
    public Object visit(RemoveOntologyAnnotationData data) {
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
        visitPrefixChange(data);
        return null;
    }

    @Override
    public Object visit(RemovePrefixData data) {
        visitPrefixChange(data);
        return null;
    }

    @Override
    public Object visit(ModifyPrefixData data) {
        visitPrefixChange(data);
        values.add(data.getOldPrefix());
        return null;
    }

    @Override
    public Object visit(RenamePrefixData data) {
        visitPrefixChange(data);
        values.add(data.getOldPrefixName());
        return null;
    }

}

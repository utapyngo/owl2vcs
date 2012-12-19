package owl2vcs.analysis;

import java.util.Set;

import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.RemoveOntologyAnnotation;
import org.semanticweb.owlapi.model.SetOntologyID;

import owl2vcs.changes.AddPrefix;
import owl2vcs.changes.ModifyPrefix;
import owl2vcs.changes.PrefixChange;
import owl2vcs.changes.RemovePrefix;
import owl2vcs.changes.RenamePrefix;
import owl2vcs.changes.SetOntologyFormat;
import owl2vcs.changeset.OntologyChangeVisitor;

public class PrefixCollector implements OntologyChangeVisitor {

    private Set<String> names;
    private Set<String> values;
    
    public PrefixCollector(final Set<String> names, final Set<String> values) {
        this.names = names;
        this.values = values;
    }
    
    @Override
    public void visit(AddAxiom change) {
    }

    @Override
    public void visit(RemoveAxiom change) {
    }

    @Override
    public void visit(SetOntologyID change) {
    }

    @Override
    public void visit(AddImport change) {
    }

    @Override
    public void visit(RemoveImport change) {
    }

    @Override
    public void visit(AddOntologyAnnotation change) {
    }

    @Override
    public void visit(RemoveOntologyAnnotation change) {
    }

    @Override
    public void visit(SetOntologyFormat change) {
    }

    protected void visitPrefixChange(PrefixChange change) {
        names.add(change.getPrefixName());
        values.add(change.getPrefix());
    }
    
    @Override
    public void visit(AddPrefix change) {
        visitPrefixChange(change);
    }

    @Override
    public void visit(RemovePrefix change) {
        visitPrefixChange(change);
    }

    @Override
    public void visit(ModifyPrefix change) {
        visitPrefixChange(change);
        values.add(change.getNewPrefix());
    }

    @Override
    public void visit(RenamePrefix change) {
        visitPrefixChange(change);
        names.add(change.getNewPrefixName());
    }

}

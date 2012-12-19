package owl2vcs.changeset;

import org.semanticweb.owlapi.model.OWLOntologyChangeVisitor;

import owl2vcs.changes.AddPrefix;
import owl2vcs.changes.ModifyPrefix;
import owl2vcs.changes.RemovePrefix;
import owl2vcs.changes.RenamePrefix;
import owl2vcs.changes.SetOntologyFormat;

public interface OntologyChangeVisitor extends OWLOntologyChangeVisitor {

    void visit(SetOntologyFormat change);

    void visit(AddPrefix change);

    void visit(RemovePrefix change);

    void visit(ModifyPrefix change);

    void visit(RenamePrefix change);
}

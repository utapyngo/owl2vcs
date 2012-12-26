package owl2vcs.changeset;

import org.semanticweb.owlapi.change.OWLOntologyChangeDataVisitor;
import owl2vcs.changes.AddPrefixData;
import owl2vcs.changes.ModifyPrefixData;
import owl2vcs.changes.RemovePrefixData;
import owl2vcs.changes.RenamePrefixData;
import owl2vcs.changes.SetOntologyFormatData;

public interface CustomOntologyChangeDataVisitor<R, E extends Exception>
    extends OWLOntologyChangeDataVisitor<R, E> {

    R visit(SetOntologyFormatData data) throws E;

    R visit(AddPrefixData data) throws E;

    R visit(RemovePrefixData data) throws E;

    R visit(ModifyPrefixData data) throws E;

    R visit(RenamePrefixData data) throws E;
}

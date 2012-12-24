package owl2vcs.changeset;

import java.util.Collection;
import java.util.HashSet;

import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.change.ImportChangeData;
import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.change.SetOntologyIDData;
import owl2vcs.changes.PrefixChangeData;
import owl2vcs.changes.SetOntologyFormatData;

public class MutableChangeSet extends ChangeSet {

    public MutableChangeSet() {
        setFormatChange(null);
        setPrefixChanges(new HashSet<PrefixChangeData>());
        setOntologyIdChange(null);
        setImportChanges(new HashSet<ImportChangeData>());
        setAnnotationChanges(new HashSet<OWLOntologyChangeData>());
        setAxiomChanges(new HashSet<AxiomChangeData>());
    }

    public MutableChangeSet(final ChangeSet cs) {
        setFormatChange(cs.getFormatChange());
        setPrefixChanges(new HashSet<PrefixChangeData>(cs.getPrefixChanges()));
        setOntologyIdChange(cs.getOntologyIdChange());
        setImportChanges(new HashSet<ImportChangeData>(cs.getImportChanges()));
        setAnnotationChanges(new HashSet<OWLOntologyChangeData>(
                cs.getAnnotationChanges()));
        setAxiomChanges(new HashSet<AxiomChangeData>(cs.getAxiomChanges()));
    }

    public MutableChangeSet(final SetOntologyFormatData formatChange,
            final Collection<PrefixChangeData> prefixChanges,
            final SetOntologyIDData ontologyIdChange,
            final Collection<ImportChangeData> importChanges,
            final Collection<OWLOntologyChangeData> annotationChanges,
            final Collection<AxiomChangeData> axiomChanges) {
        setFormatChange(formatChange);
        setPrefixChanges(prefixChanges);
        setOntologyIdChange(ontologyIdChange);
        setImportChanges(importChanges);
        setAnnotationChanges(annotationChanges);
        setAxiomChanges(axiomChanges);
    }

    @Override
    public void setFormatChange(final SetOntologyFormatData formatChange) {
        super.setFormatChange(formatChange);
    }

    @Override
    public void setPrefixChanges(final Collection<PrefixChangeData> prefixChanges) {
        super.setPrefixChanges(prefixChanges);
    }

    public void addPrefixChange(final PrefixChangeData prefixChange) {
        getPrefixChanges().add(prefixChange);
    }

    @Override
    public void setOntologyIdChange(final SetOntologyIDData ontologyIdChange) {
        super.setOntologyIdChange(ontologyIdChange);
    }

    @Override
    public void setImportChanges(final Collection<ImportChangeData> importChanges) {
        super.setImportChanges(importChanges);
    }

    public void addImportChange(final ImportChangeData importChange) {
        getImportChanges().add(importChange);
    }

    @Override
    public void setAnnotationChanges(
            final Collection<OWLOntologyChangeData> annotationChanges) {
        super.setAnnotationChanges(annotationChanges);
    }

    public void addAnnotationChange(
            final OWLOntologyChangeData annotationChange) {
        getAnnotationChanges().add(annotationChange);
    }

    @Override
    public void setAxiomChanges(final Collection<AxiomChangeData> axiomChanges) {
        super.setAxiomChanges(axiomChanges);
    }

    public void addAxiomChange(final AxiomChangeData axiomChange) {
        getAxiomChanges().add(axiomChange);
    }
}

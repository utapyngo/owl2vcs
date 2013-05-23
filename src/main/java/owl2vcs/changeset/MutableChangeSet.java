package owl2vcs.changeset;

import java.util.Collection;
import java.util.Collections;
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
    public Collection<PrefixChangeData> getPrefixChanges() {
        return Collections.unmodifiableCollection(super.getPrefixChanges());
    }

    @Override
    public void setPrefixChanges(final Collection<PrefixChangeData> prefixChanges) {
        super.setPrefixChanges(prefixChanges);
    }

    public void addPrefixChange(final PrefixChangeData prefixChange) {
        super.getPrefixChanges().add(prefixChange);
    }

    public void removePrefixChange(final PrefixChangeData prefixChange) {
        super.getPrefixChanges().remove(prefixChange);
    }

    @Override
    public void setOntologyIdChange(final SetOntologyIDData ontologyIdChange) {
        super.setOntologyIdChange(ontologyIdChange);
    }

    @Override
    public Collection<ImportChangeData> getImportChanges() {
        return Collections.unmodifiableCollection(super.getImportChanges());
    }

    @Override
    public void setImportChanges(final Collection<ImportChangeData> importChanges) {
        super.setImportChanges(importChanges);
    }

    public void addImportChange(final ImportChangeData importChange) {
        super.getImportChanges().add(importChange);
    }

    public void removeImportChange(final ImportChangeData importChange) {
        super.getImportChanges().remove(importChange);
    }

    @Override
    public Collection<OWLOntologyChangeData> getAnnotationChanges() {
        return Collections.unmodifiableCollection(super.getAnnotationChanges());
    }

    @Override
    public void setAnnotationChanges(
            final Collection<OWLOntologyChangeData> annotationChanges) {
        super.setAnnotationChanges(annotationChanges);
    }

    public void addAnnotationChange(
            final OWLOntologyChangeData annotationChange) {
        super.getAnnotationChanges().add(annotationChange);
    }

    public void removeAnnotationChange(
            final OWLOntologyChangeData annotationChange) {
        super.getAnnotationChanges().remove(annotationChange);
    }

    @Override
    public Collection<AxiomChangeData> getAxiomChanges() {
        return Collections.unmodifiableCollection(super.getAxiomChanges());
    }

    @Override
    public void setAxiomChanges(final Collection<AxiomChangeData> axiomChanges) {
        super.setAxiomChanges(axiomChanges);
    }

    public void addAxiomChange(final AxiomChangeData axiomChange) {
        super.getAxiomChanges().add(axiomChange);
    }

    public void removeAxiomChange(final AxiomChangeData axiomChange) {
        super.getAxiomChanges().remove(axiomChange);
    }
}

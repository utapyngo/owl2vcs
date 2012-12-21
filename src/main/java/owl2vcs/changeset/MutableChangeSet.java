package owl2vcs.changeset;

import java.util.Collection;
import java.util.HashSet;

import org.semanticweb.owlapi.model.AnnotationChange;
import org.semanticweb.owlapi.model.ImportChange;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.SetOntologyID;

import owl2vcs.changes.PrefixChange;
import owl2vcs.changes.SetOntologyFormat;

public class MutableChangeSet extends ChangeSet {

    public MutableChangeSet() {
        setFormatChange(null);
        setPrefixChanges(new HashSet<PrefixChange>());
        setOntologyIdChange(null);
        setImportChanges(new HashSet<ImportChange>());
        setAnnotationChanges(new HashSet<AnnotationChange>());
        setAxiomChanges(new HashSet<OWLAxiomChange>());
    }

    public MutableChangeSet(final ChangeSet cs) {
        setFormatChange(cs.getFormatChange());
        setPrefixChanges(new HashSet<PrefixChange>(cs.getPrefixChanges()));
        setOntologyIdChange(cs.getOntologyIdChange());
        setImportChanges(new HashSet<ImportChange>(cs.getImportChanges()));
        setAnnotationChanges(new HashSet<AnnotationChange>(
                cs.getAnnotationChanges()));
        setAxiomChanges(new HashSet<OWLAxiomChange>(cs.getAxiomChanges()));
    }

    public MutableChangeSet(final SetOntologyFormat formatChange,
            final Collection<PrefixChange> prefixChanges,
            final SetOntologyID ontologyIdChange,
            final Collection<ImportChange> importChanges,
            final Collection<AnnotationChange> annotationChanges,
            final Collection<OWLAxiomChange> axiomChanges) {
        setFormatChange(formatChange);
        setPrefixChanges(prefixChanges);
        setOntologyIdChange(ontologyIdChange);
        setImportChanges(importChanges);
        setAnnotationChanges(annotationChanges);
        setAxiomChanges(axiomChanges);
    }

    @Override
    public void setFormatChange(final SetOntologyFormat formatChange) {
        super.setFormatChange(formatChange);
    }

    @Override
    public void setPrefixChanges(final Collection<PrefixChange> prefixChanges) {
        super.setPrefixChanges(prefixChanges);
    }

    public void addPrefixChange(final PrefixChange prefixChange) {
        getPrefixChanges().add(prefixChange);
    }

    @Override
    public void setOntologyIdChange(final SetOntologyID ontologyIdChange) {
        super.setOntologyIdChange(ontologyIdChange);
    }

    @Override
    public void setImportChanges(final Collection<ImportChange> importChanges) {
        super.setImportChanges(importChanges);
    }

    public void addImportChange(final ImportChange importChange) {
        getImportChanges().add(importChange);
    }

    @Override
    public void setAnnotationChanges(
            final Collection<AnnotationChange> annotationChanges) {
        super.setAnnotationChanges(annotationChanges);
    }

    public void addAnnotationChange(
            final AnnotationChange annotationChange) {
        getAnnotationChanges().add(annotationChange);
    }

    @Override
    public void setAxiomChanges(final Collection<OWLAxiomChange> axiomChanges) {
        super.setAxiomChanges(axiomChanges);
    }

    public void addAxiomChange(final OWLAxiomChange axiomChange) {
        getAxiomChanges().add(axiomChange);
    }
}

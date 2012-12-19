package owl2vcs.changeset;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.AnnotationChange;
import org.semanticweb.owlapi.model.ImportChange;
import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.SetOntologyID;

import owl2vcs.changes.OntologyChange;
import owl2vcs.changes.PrefixChange;
import owl2vcs.changes.SetOntologyFormat;

public abstract class ChangeSet {

    protected ChangeSet() {
    }

    private SetOntologyFormat formatChange;

    private Collection<PrefixChange> prefixChanges;

    private SetOntologyID ontologyIdChange;

    private Collection<ImportChange> importChanges;

    private Collection<AnnotationChange> annotationChanges;

    private Collection<OWLAxiomChange> axiomChanges;

    public SetOntologyFormat getFormatChange() {
        return formatChange;
    }

    public Collection<PrefixChange> getPrefixChanges() {
        return prefixChanges;
    }

    public SetOntologyID getOntologyIdChange() {
        return ontologyIdChange;
    }

    public Collection<ImportChange> getImportChanges() {
        return importChanges;
    }

    public Collection<AnnotationChange> getAnnotationChanges() {
        return annotationChanges;
    }

    public Collection<OWLAxiomChange> getAxiomChanges() {
        return axiomChanges;
    }

    protected void setFormatChange(final SetOntologyFormat formatChange) {
        this.formatChange = formatChange;
    }

    protected void setPrefixChanges(final Collection<PrefixChange> prefixChanges) {
        this.prefixChanges = prefixChanges;
    }

    protected void setOntologyIdChange(final SetOntologyID ontologyIdChange) {
        this.ontologyIdChange = ontologyIdChange;
    }

    protected void setImportChanges(final Collection<ImportChange> importChanges) {
        this.importChanges = importChanges;
    }

    protected void setAnnotationChanges(
            final Collection<AnnotationChange> annotationChanges) {
        this.annotationChanges = annotationChanges;
    }

    protected void setAxiomChanges(final Collection<OWLAxiomChange> axiomChanges) {
        this.axiomChanges = axiomChanges;
    }

    /**
     * Count all changes.
     *
     * @return
     */
    public int size() {
        int sum = 0;
        if (formatChange != null)
            sum++;
        if (ontologyIdChange != null)
            sum++;
        sum += prefixChanges.size();
        sum += importChanges.size();
        sum += annotationChanges.size();
        sum += axiomChanges.size();
        return sum;
    }

    public void accept(final OntologyChangeVisitor visitor) {
        if (formatChange != null)
            formatChange.accept(visitor);
        for (final OntologyChange c : prefixChanges)
            c.accept(visitor);
        if (ontologyIdChange != null)
            ontologyIdChange.accept(visitor);
        for (final OWLOntologyChange c : importChanges)
            c.accept(visitor);
        for (final OWLOntologyChange c : annotationChanges)
            c.accept(visitor);
        for (final OWLOntologyChange c : axiomChanges)
            c.accept(visitor);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime
                * result
                + ((annotationChanges == null) ? 0 : annotationChanges
                        .hashCode());
        result = prime * result
                + ((axiomChanges == null) ? 0 : axiomChanges.hashCode());
        result = prime * result
                + ((formatChange == null) ? 0 : formatChange.hashCode());
        result = prime * result
                + ((importChanges == null) ? 0 : importChanges.hashCode());
        result = prime
                * result
                + ((ontologyIdChange == null) ? 0 : ontologyIdChange.hashCode());
        result = prime * result
                + ((prefixChanges == null) ? 0 : prefixChanges.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof ChangeSet))
            return false;
        ChangeSet other = (ChangeSet) obj;
        if (annotationChanges == null) {
            if (other.annotationChanges != null)
                return false;
        } else if (!collectionsEqual(annotationChanges, other.annotationChanges))
            return false;
        if (axiomChanges == null) {
            if (other.axiomChanges != null)
                return false;
        } else if (!collectionsEqual(axiomChanges, other.axiomChanges))
            return false;
        if (formatChange == null) {
            if (other.formatChange != null)
                return false;
        } else if (!formatChange.equals(other.formatChange))
            return false;
        if (importChanges == null) {
            if (other.importChanges != null)
                return false;
        } else if (!collectionsEqual(importChanges, other.importChanges))
            return false;
        if (ontologyIdChange == null) {
            if (other.ontologyIdChange != null)
                return false;
        } else if (!ontologyIdChange.equals(other.ontologyIdChange))
            return false;
        if (prefixChanges == null) {
            if (other.prefixChanges != null)
                return false;
        } else if (!collectionsEqual(prefixChanges, other.prefixChanges))
            return false;
        return true;
    }

    private <T> boolean collectionsEqual(Collection<T> c1, Collection<T> c2) {
        if ((c1 instanceof Set) && (c2 instanceof Set))
            return c1.equals(c2);
        if ((c1 instanceof Set))
            return c1.equals(new HashSet<T>(c2));
        if ((c2 instanceof Set))
            return new HashSet<T>(c1).equals(c2);
        return false;
    }

    public boolean isEmpty() {
        return (formatChange == null)
            && (prefixChanges == null || prefixChanges.isEmpty())
            && (ontologyIdChange == null)
            && (importChanges == null || importChanges.isEmpty())
            && (annotationChanges == null || annotationChanges.isEmpty())
            && (axiomChanges == null || axiomChanges.isEmpty());
        }

}

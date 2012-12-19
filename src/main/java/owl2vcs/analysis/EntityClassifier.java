package owl2vcs.analysis;

import java.util.Collection;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;

import com.google.common.collect.Iterables;

public class EntityClassifier {

    private final Collection<OWLEntity> newEntities;
    private final Collection<OWLEntity> removedEntities;
    private final Collection<OWLEntity> modifiedEntities;

    public Collection<OWLEntity> getNewEntities() {
        return newEntities;
    }

    public Collection<OWLEntity> getRemovedEntities() {
        return removedEntities;
    }

    public Collection<OWLEntity> getModifiedEntities() {
        return modifiedEntities;
    }

    public Iterable<OWLEntity> getAllEntities() {
        return Iterables.concat(removedEntities, newEntities, modifiedEntities);
    }

    /**
     * Classify all entities into removed, added and modified ones.
     * 
     * @param parentEntities
     * @param childEntities
     * @param affectedEntities
     */
    public EntityClassifier(final OWLOntology parent, final OWLOntology child,
            final Collection<OWLEntity> affectedEntities,
            final Set<OWLEntity> newEntities,
            final Set<OWLEntity> removedEntities,
            final Set<OWLEntity> modifiedEntities) {
        this.newEntities = newEntities;
        this.removedEntities = removedEntities;
        this.modifiedEntities = modifiedEntities;
        for (final OWLEntity e : affectedEntities)
            if (parent.containsEntityInSignature(e)) {
                if (child.containsEntityInSignature(e))
                    modifiedEntities.add(e);
                else
                    removedEntities.add(e);
            } else
                // assert child.containsEntityInSignature(e);
                newEntities.add(e);
    }
}

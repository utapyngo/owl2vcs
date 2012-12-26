package owl2vcs.analysis;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.change.RemoveAxiomData;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import owl2vcs.changeset.FullChangeSet;

import com.google.common.collect.Iterables;

public class SmartEntityClassifier {

    private final FullChangeSet changeSet;
    private final Collection<OWLEntity> newEntities;
    private final Collection<OWLEntity> removedEntities;
    private final Collection<OWLEntity> modifiedEntities;

    public FullChangeSet getChangeSet() {
        return changeSet;
    }

    public Collection<OWLEntity> getNewEntities() {
        return newEntities;
    }

    public Collection<OWLEntity> getRemovedEntities() {
        return removedEntities;
    }

    public Collection<OWLEntity> getModifiedEntities() {
        return modifiedEntities;
    }

    public Iterable<OWLEntity> getEntities() {
        return Iterables.concat(removedEntities, newEntities, modifiedEntities);
    }

    public SmartEntityClassifier(final FullChangeSet cs) {
        this.changeSet = cs;
        final OWLOntology parent = cs.getParent();
        final OWLOntology child = cs.getChild();

        // Classify all entities into removed, added and modified ones
        newEntities = new TreeSet<OWLEntity>();
        removedEntities = new TreeSet<OWLEntity>();
        modifiedEntities = new TreeSet<OWLEntity>();
        for (final AxiomChangeData c : cs.getAxiomChanges()) {
            final Set<OWLEntity> signature = c.getAxiom().getSignature();
            if (c instanceof AddAxiomData)
                for (final OWLEntity e : signature)
                    if (parent.containsEntityInSignature(e, false))
                        modifiedEntities.add(e);
                    else
                        newEntities.add(e);
            else if (c instanceof RemoveAxiomData)
                for (final OWLEntity e : signature)
                    if (child.containsEntityInSignature(e, false))
                        modifiedEntities.add(e);
                    else
                        removedEntities.add(e);
        }
    }
}

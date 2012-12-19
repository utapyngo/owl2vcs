package owl2vcs.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLEntity;

public class ChangeClassifier {

    private final Map<OWLEntity, Collection<OWLAxiomChange>> changesByEntity;

    public ChangeClassifier(final Collection<OWLAxiomChange> changes) {
        changesByEntity = new HashMap<OWLEntity, Collection<OWLAxiomChange>>();
        for (final OWLAxiomChange c : changes)
            for (final OWLEntity e : c.getAxiom().getSignature()) {
                Collection<OWLAxiomChange> affectingChanges = changesByEntity
                        .get(e);
                if (affectingChanges == null) {
                    affectingChanges = new ArrayList<OWLAxiomChange>();
                    changesByEntity.put(e, affectingChanges);
                }
                affectingChanges.add(c);
            }
    }

    public Collection<OWLAxiomChange> getChangesByEntity(final OWLEntity e) {
        return changesByEntity.get(e);
    }

}

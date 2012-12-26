package owl2vcs.analysis;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.model.OWLEntity;

public class ChangeClassifier {

    private final Map<OWLEntity, Collection<AxiomChangeData>> changesByEntity;

    public ChangeClassifier(final Collection<AxiomChangeData> changes) {
        changesByEntity = new HashMap<OWLEntity, Collection<AxiomChangeData>>();
        for (final AxiomChangeData c : changes)
            for (final OWLEntity e : c.getAxiom().getSignature()) {
                Collection<AxiomChangeData> affectingChanges = changesByEntity.get(e);
                if (affectingChanges == null) {
                    affectingChanges = new ArrayList<AxiomChangeData>();
                    changesByEntity.put(e, affectingChanges);
                }
                affectingChanges.add(c);
            }
    }

    public Collection<AxiomChangeData> getChangesByEntity(final OWLEntity e) {
        return changesByEntity.get(e);
    }

}

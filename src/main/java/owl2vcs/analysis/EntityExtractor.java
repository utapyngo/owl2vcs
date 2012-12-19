package owl2vcs.analysis;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLAxiomChange;
import org.semanticweb.owlapi.model.OWLEntity;

public class EntityExtractor {

    private final Set<OWLEntity> entities;

    public Set<OWLEntity> getEntities() {
        return entities;
    }

    public EntityExtractor(final Collection<OWLAxiomChange> changes) {
        entities = new HashSet<OWLEntity>();
        for (final OWLAxiomChange c : changes)
            entities.addAll(c.getAxiom().getSignature());
    }
}

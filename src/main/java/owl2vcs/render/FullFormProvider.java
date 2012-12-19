package owl2vcs.render;

import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.util.ShortFormProvider;

public class FullFormProvider implements ShortFormProvider {

    @Override
    public final String getShortForm(final OWLEntity entity) {
        return "<" + entity.getIRI().toString() + ">";
    }

    @Override
    public void dispose() {
    }

}

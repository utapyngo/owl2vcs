package owl2vcs.render;

import org.semanticweb.owlapi.model.OWLOntologyChange;

public interface ChangeRenderer {

    String render(OWLOntologyChange change);
}

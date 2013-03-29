package owl2vcs.render;

import org.semanticweb.owlapi.change.OWLOntologyChangeData;

public interface ChangeRenderer {

    String render(OWLOntologyChangeData change);
    Character getSymbol(OWLOntologyChangeData change);
}

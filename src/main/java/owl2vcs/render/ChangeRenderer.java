package owl2vcs.render;

import org.semanticweb.owlapi.model.OWLOntologyChange;

import owl2vcs.changes.OntologyChange;

public interface ChangeRenderer {
    String render(OntologyChange change);

    String render(OWLOntologyChange change);
}

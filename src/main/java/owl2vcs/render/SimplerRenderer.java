package owl2vcs.render;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.util.SimpleRenderer;

import uk.ac.manchester.cs.owl.owlapi.OWLClassImpl;

public class SimplerRenderer extends SimpleRenderer {
    public void visit(IRI iri) {
        OWLClass c = new OWLClassImpl(iri);
        super.visit(c);
    }
}

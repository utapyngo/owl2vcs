package owl2vcs.render;

import java.util.List;
import java.util.Map;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologySetProvider;
import org.semanticweb.owlapi.util.IRIShortFormProvider;
import org.semanticweb.owlapi.util.OWLObjectVisitorAdapter;
import org.semanticweb.owlapi.util.ShortFormProvider;

public class QuotedAnnotationValueShortFormProvider implements
        ShortFormProvider {

    private final OWLOntologySetProvider ontologySetProvider;

    private final ShortFormProvider alternateShortFormProvider;

    private final IRIShortFormProvider alternateIRIShortFormProvider;

    private final List<OWLAnnotationProperty> annotationProperties;

    private final Map<OWLAnnotationProperty, List<String>> preferredLanguageMap;

    /**
     * Constructs an annotation short form provider.
     *
     * @param annotationProperties
     *            A <code>List</code> of preferred annotation properties. The
     *            list is searched from start to end, so that annotations that
     *            have a property at the start of the list have a higher
     *            priority and are selected over annotations with properties
     *            that appear towards or at the end of the list.
     * @param preferredLanguageMap
     *            A map which maps annotation properties to preferred languages.
     *            For any given annotation property there may be a list of
     *            preferred languages. Languages at the start of the list have a
     *            higher priority over languages at the end of the list. This
     *            parameter may be empty but it must not be <code>null</code>.
     * @param ontologySetProvider
     *            An <code>OWLOntologySetProvider</code> which provides a set of
     *            ontology from which candidate annotation axioms should be
     *            taken. For a given entity, all ontologies are examined.
     * @param alternateShortFormProvider
     *            A short form provider which will be used to generate the short
     *            form for an entity that does not have any annotations. This
     *            provider will also be used in the case where the value of an
     *            annotation is an <code>OWLIndividual</code> for providing the
     *            short form of the individual.
     * @param alternateIRIShortFormProvider
     *            the alternate IRI short form provider
     */
    public QuotedAnnotationValueShortFormProvider(
            final OWLOntologySetProvider ontologySetProvider,
            final ShortFormProvider alternateShortFormProvider,
            final IRIShortFormProvider alternateIRIShortFormProvider,
            final List<OWLAnnotationProperty> annotationProperties,
            final Map<OWLAnnotationProperty, List<String>> preferredLanguageMap) {
        this.ontologySetProvider = ontologySetProvider;
        this.alternateShortFormProvider = alternateShortFormProvider;
        this.alternateIRIShortFormProvider = alternateIRIShortFormProvider;
        this.annotationProperties = annotationProperties;
        this.preferredLanguageMap = preferredLanguageMap;
    }

    @Override
    public String getShortForm(final OWLEntity entity) {

        // visit the properties in order of preference
        for (final OWLAnnotationProperty prop : annotationProperties) {
            final AnnotationLanguageFilter checker = new AnnotationLanguageFilter(
                    prop, preferredLanguageMap.get(prop));

            for (final OWLOntology ontology : ontologySetProvider
                    .getOntologies())
                for (final OWLAnnotationAssertionAxiom ax : entity
                        .getAnnotationAssertionAxioms(ontology))
                    ax.accept(checker);
            if (checker.getMatch() != null)
                return getRendering(checker.getMatch());
        }

        return alternateShortFormProvider.getShortForm(entity);
    }

    /**
     * Obtains the rendering of the specified object. If the object is a
     * constant then the rendering is equal to the literal value, if the object
     * is an individual then the rendering is equal to the rendering of the
     * individual as provided by the alternate short form provider
     *
     * @param object
     *            The object to the rendered
     * @return The rendering of the object.
     */
    private String getRendering(final OWLObject object) {
        // We return the literal value of constants or use the alternate
        // short form provider to render individuals.
        if (object instanceof OWLLiteral)
            return "\"" + ((OWLLiteral) object).getLiteral() + "\"";
        else if (object instanceof IRI)
            return alternateIRIShortFormProvider.getShortForm((IRI) object);
        else
            return alternateShortFormProvider.getShortForm((OWLEntity) object);
    }

    /**
     * @return the annotation URIs that this short form provider uses.
     */
    public List<OWLAnnotationProperty> getAnnotationProperties() {
        return annotationProperties;
    }

    /**
     * @return the preferred language map
     */
    public Map<OWLAnnotationProperty, List<String>> getPreferredLanguageMap() {
        return preferredLanguageMap;
    }

    @Override
    public void dispose() {
    }

    private static class AnnotationLanguageFilter extends
            OWLObjectVisitorAdapter {

        private final OWLAnnotationProperty prop;

        private final List<String> preferredLanguages;

        private OWLObject candidateValue = null;

        private int lastLangMatchIndex = Integer.MAX_VALUE;

        AnnotationLanguageFilter(final OWLAnnotationProperty prop,
                final List<String> preferredLanguages) {
            this.prop = prop;
            this.preferredLanguages = preferredLanguages;
        }

        public OWLObject getMatch() {
            return candidateValue;
        }

        @Override
        public void visit(final OWLAnnotationAssertionAxiom anno) {
            // a perfect match - no need to carry on search
            if (lastLangMatchIndex > 0 && anno.getProperty().equals(prop))
                anno.getValue().accept(this);
        }

        @Override
        public void visit(final OWLLiteral node) {
            if (preferredLanguages == null || preferredLanguages.isEmpty()) {
                lastLangMatchIndex = 0;
                candidateValue = node;
            } else {
                final int index = preferredLanguages.indexOf(node.getLang());
                if (index >= 0 && index < lastLangMatchIndex) {
                    lastLangMatchIndex = index;
                    candidateValue = node;
                }
            }
        }

        @Override
        public void visit(final IRI iri) {
            // No language
            candidateValue = iri;
        }
    }
}

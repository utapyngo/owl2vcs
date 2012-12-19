package owl2vcs.render;

import java.util.HashMap;
import java.util.Map;

import org.semanticweb.owlapi.io.XMLUtils;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.util.ShortFormProvider;

/**
 * A short form provider which creates QNames for entities. */
public class QNameShortFormProvider implements ShortFormProvider {
    private final Map<String, String> namespace2PrefixMap;

    /** Creates a QNameShortFormProvider where namespace prefix mappings will
     * automatically be generated. */
    public QNameShortFormProvider() {
        this(new HashMap<String, String>());
    }

    /** Creates a QNameShortFormProvider which does not generate any prefixes.
     *
     * @param prefix2NamespaceMap
     *            The map which contains a prefix -> namespace mapping. */
    public QNameShortFormProvider(Map<String, String> prefix2NamespaceMap) {
        namespace2PrefixMap = new HashMap<String, String>();
        for (Map.Entry<String, String> e : prefix2NamespaceMap.entrySet()) {
            String prefixName = e.getKey();
            if (prefixName.endsWith(":"))
                prefixName = prefixName.substring(0, prefixName.length() - 1);
            namespace2PrefixMap.put(e.getValue(), prefixName);
        }
    }

    @Override
    public String getShortForm(OWLEntity entity) {
        IRI iri = entity.getIRI();
        String namespace = XMLUtils.getNCNamePrefix(iri.toString());
        String prefix = namespace2PrefixMap.get(namespace);
        String toReturn;
        if (prefix != null) {
            String localName = XMLUtils.getNCNameSuffix(iri.toString());
            toReturn = prefix + ":" + (localName != null ? localName : "");
        } else {
            toReturn = iri.toQuotedString();
        }
        return toReturn;
    }

    @Override
    public void dispose() { }
}

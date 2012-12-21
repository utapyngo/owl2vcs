package owl2vcs.render;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.semanticweb.owlapi.io.XMLUtils;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.util.ShortFormProvider;

/**
 * A short form provider which creates QNames for entities. */
public class QNameMultiMapShortFormProvider implements ShortFormProvider {
    private final List<Map<String, String>> namespace2PrefixMaps;

    /** Creates a QNameMultiMapShortFormProvider where namespace prefix mappings will
     * automatically be generated. */
    public QNameMultiMapShortFormProvider() {
        this(new HashMap<String, String>());
    }

    /** Creates a QNameMultiMapShortFormProvider which does not generate any prefixes.
     *
     * @param prefix2NamespaceMaps
     *            The list which contains prefix -> namespace mappings. */
    public QNameMultiMapShortFormProvider(Map<String, String> prefix2NamespaceMap) {
        namespace2PrefixMaps = new ArrayList<Map<String, String>>();
        addPrefix2NamespaceMap(prefix2NamespaceMap);
    }

    /** Creates a QNameShortFormProvider which does not generate any prefixes.
    *
    * @param prefix2NamespaceMap
    *            The map which contains a prefix -> namespace mapping. */
    public QNameMultiMapShortFormProvider(List<Map<String, String>> prefix2NamespaceMaps) {
        namespace2PrefixMaps = new ArrayList<Map<String, String>>();
        for (Map<String, String> prefix2NamespaceMap : prefix2NamespaceMaps) {
            addPrefix2NamespaceMap(prefix2NamespaceMap);
        }
    }

    public void addPrefix2NamespaceMap(Map<String, String> prefix2NamespaceMap) {
        Map<String, String> namespace2PrefixMap = new HashMap<String, String>();
        for (Map.Entry<String, String> e : prefix2NamespaceMap.entrySet()) {
            String prefixName = e.getKey();
            if (prefixName.endsWith(":"))
                prefixName = prefixName.substring(0, prefixName.length() - 1);
            namespace2PrefixMap.put(e.getValue(), prefixName);
        }
        namespace2PrefixMaps.add(namespace2PrefixMap);
    }

    @Override
    public String getShortForm(OWLEntity entity) {
        IRI iri = entity.getIRI();
        String namespace = XMLUtils.getNCNamePrefix(iri.toString());
        String prefix = null;
        for (Map<String, String> namespace2PrefixMap : namespace2PrefixMaps) {
            prefix = namespace2PrefixMap.get(namespace);
            if (prefix != null) {
                break;
            }
        }
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

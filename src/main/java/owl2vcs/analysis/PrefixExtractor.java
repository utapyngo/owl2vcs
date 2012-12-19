package owl2vcs.analysis;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

import org.semanticweb.owlapi.model.OWLEntity;

public class PrefixExtractor {

    private final Map<String, String> prefixName2PrefixMap;

    public PrefixExtractor(final Collection<OWLEntity> entities,
            final Map<String, String> allPrefixes) {
        final Collection<String> prefixes = new HashSet<String>();
        for (final OWLEntity e : entities)
            prefixes.add(e.getIRI().getStart());
        prefixName2PrefixMap = new HashMap<String, String>();
        for (final Entry<String, String> e : allPrefixes.entrySet()) {
            final String prefix = e.getValue();
            if (prefixes.contains(prefix)) {
                final String prefixName = e.getKey();
                prefixName2PrefixMap.put(prefixName, prefix);
            }
        }
    }

    public Map<String, String> getPrefixName2PrefixMap() {
        return prefixName2PrefixMap;
    }
}

package owl2vcs.changes;

import java.util.ArrayList;
import java.util.Collection;


public abstract class PrefixChangeData extends CustomOntologyChangeData {

    private static final long serialVersionUID = -1862023752748180868L;

    private final String prefixName;
    private final String prefix;

    public PrefixChangeData(String prefixName, String prefix) {
        if (prefixName == null) {
            throw new NullPointerException("prefixName must not be null");
        }
        if (prefix == null) {
            throw new NullPointerException("prefix must not be null");
        }
        this.prefixName = prefixName;
        this.prefix = prefix;
    }

    /**
     * @return the prefixName
     */
    public String getPrefixName() {
        return prefixName;
    }

    /**
     * @return the prefix
     */
    public String getPrefix() {
        return prefix;
    }

    public Collection<String> getPrefixSignature() {
        final Collection<String> sig = new ArrayList<String>();
        sig.add(getPrefixName());
        sig.add(getPrefix());
        return sig;
    }

}

package owl2vcs.utils;

import java.util.ArrayList;
import java.util.Collection;

import owl2vcs.changeset.ChangeSet;
import owl2vcs.changeset.MutableChangeSet;

public final class ChangeSetUtils {

    private ChangeSetUtils() { }

    protected static <T> Collection<T> intersectCollections(
            final Collection<T> c1, final Collection<T> c2) {
        final Collection<T> c = new ArrayList<T>(c1);
        c.retainAll(c2);
        return c;
    }

    protected static <T> T intersectScalars(final T s1, final T s2) {
        if (s1.equals(s2))
            return s1;
        else
            return null;
    }

    protected static <T> Collection<T> subtractCollections(
            final Collection<T> c1, final Collection<T> c2) {
        final Collection<T> c = new ArrayList<T>(c1);
        c.removeAll(c2);
        return c;
    }

    protected static <T> T subtractScalars(final T s1, final T s2) {
        if (s1 == null || s1.equals(s2))
            return null;
        else
            return s1;
    }

    public static ChangeSet intersectChangesets(final ChangeSet cs1,
            final ChangeSet cs2) {
        return new MutableChangeSet(intersectScalars(cs1.getFormatChange(),
                cs2.getFormatChange()), intersectCollections(
                cs1.getPrefixChanges(), cs2.getPrefixChanges()),
                intersectScalars(cs1.getOntologyIdChange(),
                        cs2.getOntologyIdChange()), intersectCollections(
                        cs1.getImportChanges(), cs2.getImportChanges()),
                intersectCollections(cs1.getAnnotationChanges(),
                        cs2.getAnnotationChanges()), intersectCollections(
                        cs1.getAxiomChanges(), cs2.getAxiomChanges()));
    }

    public static ChangeSet subtractChangesets(final ChangeSet cs1,
            final ChangeSet cs2) {
        return new MutableChangeSet(
                subtractScalars(cs1.getFormatChange(),
                                cs2.getFormatChange()),
                subtractCollections(cs1.getPrefixChanges(),
                                    cs2.getPrefixChanges()),
                subtractScalars(cs1.getOntologyIdChange(),
                                cs2.getOntologyIdChange()),
                subtractCollections(cs1.getImportChanges(),
                                    cs2.getImportChanges()),
                subtractCollections(cs1.getAnnotationChanges(),
                                    cs2.getAnnotationChanges()),
                subtractCollections(cs1.getAxiomChanges(),
                                    cs2.getAxiomChanges()));
    }

}

package owl2vcs.io;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.util.ShortFormProvider;

import owl2vcs.changeset.ChangeSet;
import owl2vcs.render.ChangeFormat;
import owl2vcs.render.ChangeRenderer;
import owl2vcs.render.FullFormProvider;
import owl2vcs.render.FunctionalChangeRenderer;

public class FunctionalChangesetSerializer {

    public void write(ChangeSet cs, PrintStream out) {
        write(cs, out, new FullFormProvider(), ChangeFormat.COMPACT);
    }

    public void write(ChangeSet cs, PrintStream out,
            ShortFormProvider provider, ChangeFormat changeFormat) {
        PrintStream utf8out;
        try {
            utf8out = new PrintStream(out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            utf8out = out;
        }
        if (cs.isEmpty()) {
            utf8out.println("no changes");
            return;
        }
        ChangeRenderer cr = new FunctionalChangeRenderer(
                provider, changeFormat);
        if (cs.getFormatChange() != null)
            utf8out.println(cr.render(cs.getFormatChange()));
        if (cs.getOntologyIdChange() != null)
            utf8out.println(cr.render(cs.getOntologyIdChange()));
        for (final OWLOntologyChangeData c : cs.getPrefixChanges())
            utf8out.println(cr.render(c));
        for (final OWLOntologyChangeData c : cs.getImportChanges())
            utf8out.println(cr.render(c));
        for (final OWLOntologyChangeData c : cs.getAnnotationChanges())
            utf8out.println(cr.render(c));
        for (final OWLOntologyChangeData c : cs.getAxiomChanges())
            utf8out.println(cr.render(c));
    }
}

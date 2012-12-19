package owl2vcs.render;

import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.AddOntologyAnnotation;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.model.RemoveImport;
import org.semanticweb.owlapi.model.RemoveOntologyAnnotation;
import org.semanticweb.owlapi.model.SetOntologyID;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleRenderer;

import owl2vcs.changes.AddPrefix;
import owl2vcs.changes.ModifyPrefix;
import owl2vcs.changes.OntologyChange;
import owl2vcs.changes.RemovePrefix;
import owl2vcs.changes.RenamePrefix;
import owl2vcs.changes.SetOntologyFormat;

public class FunctionalChangeRenderer implements ChangeRenderer {

    private OWLObjectRenderer objectRenderer;
    private ChangeFormat changeFormat;

    public FunctionalChangeRenderer(final ShortFormProvider provider,
            final ChangeFormat changeFormat) {
        objectRenderer = new SimpleRenderer();
        objectRenderer.setShortFormProvider(provider);
        this.changeFormat = changeFormat;
    }

    /**
     * Render built-in types of ontology changes.
     */
    @Override
    public final String render(final OWLOntologyChange change) {
        if (change == null)
            return null;
        if (change instanceof SetOntologyID)
            return render((SetOntologyID) change);
        else if (change instanceof AddImport)
            return render((AddImport) change);
        else if (change instanceof RemoveImport)
            return render((RemoveImport) change);
        else if (change instanceof AddOntologyAnnotation)
            return render((AddOntologyAnnotation) change);
        else if (change instanceof RemoveOntologyAnnotation)
            return render((RemoveOntologyAnnotation) change);
        else if (change instanceof AddAxiom)
            return render((AddAxiom) change);
        else if (change instanceof RemoveAxiom)
            return render((RemoveAxiom) change);
        else
            return "UnknownChangeType: " + change.getClass().getName();
    }

    /**
     * Render extended ontology changes.
     */
    @Override
    public final String render(final OntologyChange change) {
        if (change instanceof SetOntologyFormat)
            return render((SetOntologyFormat) change);
        else if (change instanceof AddPrefix)
            return render((AddPrefix) change);
        else if (change instanceof RemovePrefix)
            return render((RemovePrefix) change);
        else if (change instanceof ModifyPrefix)
            return render((ModifyPrefix) change);
        else if (change instanceof RenamePrefix)
            return render((RenamePrefix) change);
        else
            return "UnknownChangeType: " + change.getClass().getName();
    }

    public final String render(final SetOntologyFormat change) {
        return indentString("*", "OntologyFormat(\""
                + change.getNewOntologyFormat().toString() + "\")");
    }

    public final String render(final AddPrefix change) {
        return indentString("+", "Prefix(" + change.getPrefixName() + "=<"
                + change.getPrefix() + ">)");
    }

    public final String render(final RemovePrefix change) {
        return indentString("-", "Prefix(" + change.getPrefixName() + "=<"
                + change.getPrefix() + ">)");
    }

    public final String render(final ModifyPrefix change) {
        return indentString("*", "Prefix(" + change.getPrefixName() + "=<"
                + change.getPrefix() + "> <" + change.getNewPrefix() + ">)");
    }

    public final String render(final RenamePrefix change) {
        return indentString("#", "Prefix(" + change.getPrefixName() + " "
                + change.getNewPrefixName() + "=<"
                + change.getPrefix() + ">)");
    }

    private String renderOntologyId(final OWLOntologyID id) {
        if (id.isAnonymous())
            return "";
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append(id.getOntologyIRI().toQuotedString());
            if (id.getVersionIRI() != null) {
                sb.append(" ");
                sb.append(id.getVersionIRI().toQuotedString());
            }
            return sb.toString();
        }
    }

    public final String render(final SetOntologyID change) {
        final OWLOntologyID oldid = change.getOriginalOntologyID();
        final OWLOntologyID newid = change.getNewOntologyID();
        String prefix;
        String s;
        if (newid.isAnonymous()) {
            prefix = "-";
            s = "OntologyID(" + renderOntologyId(oldid) + ")";
        } else if (oldid != null && oldid.isAnonymous()) {
            prefix = "+";
            s = "OntologyID(" + renderOntologyId(newid) + ")";
        } else {
            prefix = "*";
            s = "OntologyID(" + renderOntologyId(newid) + ")";
        }
        return indentString(prefix, s);
    }

    public final String render(final AddImport change) {
        return indentString("+", "Import(<"
                + change.getImportDeclaration().getIRI().toString() + ">)");
    }

    public final String render(final RemoveImport change) {
        return indentString("-", "Import(<"
                + change.getImportDeclaration().getIRI().toString() + ">)");
    }

    public final String render(final AddOntologyAnnotation change) {
        return indentString("+", objectRenderer.render(change.getAnnotation()));
    }

    public final String render(final RemoveOntologyAnnotation change) {
        return indentString("-", objectRenderer.render(change.getAnnotation()));
    }

    public final String render(final AddAxiom change) {
        return indentString("+", objectRenderer.render(change.getAxiom()));
    }

    public final String render(final RemoveAxiom change) {
        return indentString("-", objectRenderer.render(change.getAxiom()));
    }

    private String writeIndent(final StringBuilder sb, final int indent) {
        sb.append("\n");
        for (int i = 0; i < indent; i++)
            sb.append(" ");
        return sb.toString();
    }

    protected final String indentString(final String prefix,
            final String compactString) {
        String indentedString = compactString;
        if (changeFormat == ChangeFormat.INDENTED) {
            final int indentSize = 2;
            final StringBuilder sb = new StringBuilder();
            int indent;
            if (prefix != null)
                indent = prefix.length() + 1;
            else
                indent = 0;
            boolean quote = false;
            for (final char c : compactString.toCharArray())
                switch (c) {
                case '(':
                    indent += indentSize;
                    // sb.append(':');
                    writeIndent(sb, indent);
                    break;
                case ')':
                    indent -= indentSize;
                    writeIndent(sb, indent);
                    break;
                case ' ':
                    if (quote)
                        sb.append(c);
                    else
                        writeIndent(sb, indent);
                    break;
                case '"':
                    quote = !quote;
                default:
                    sb.append(c);
                }
            indentedString = sb.toString().trim();
        }
        if (prefix == null)
            return indentedString;
        else
            return prefix + " " + indentedString;
    }

}

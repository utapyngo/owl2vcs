package owl2vcs.render;

import org.semanticweb.owlapi.change.AddAxiomData;
import org.semanticweb.owlapi.change.AddImportData;
import org.semanticweb.owlapi.change.AddOntologyAnnotationData;
import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.change.RemoveAxiomData;
import org.semanticweb.owlapi.change.RemoveImportData;
import org.semanticweb.owlapi.change.RemoveOntologyAnnotationData;
import org.semanticweb.owlapi.change.SetOntologyIDData;
import org.semanticweb.owlapi.io.OWLObjectRenderer;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleRenderer;

import owl2vcs.changes.AddPrefixData;
import owl2vcs.changes.ModifyPrefixData;
import owl2vcs.changes.RemovePrefixData;
import owl2vcs.changes.RenamePrefixData;
import owl2vcs.changes.SetOntologyFormatData;

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
     * Render ontology changes.
     */
    @Override
    public final String render(final OWLOntologyChangeData change) {
        if (change == null)
            return null;
        // Standard changes
        if (change instanceof SetOntologyFormatData)
            return render((SetOntologyFormatData) change);
        else if (change instanceof AddPrefixData)
            return render((AddPrefixData) change);
        else if (change instanceof RemovePrefixData)
            return render((RemovePrefixData) change);
        else if (change instanceof ModifyPrefixData)
            return render((ModifyPrefixData) change);
        else if (change instanceof RenamePrefixData)
            return render((RenamePrefixData) change);
        // Custom changes
        else if (change instanceof SetOntologyIDData)
            return render((SetOntologyIDData) change);
        else if (change instanceof AddImportData)
            return render((AddImportData) change);
        else if (change instanceof RemoveImportData)
            return render((RemoveImportData) change);
        else if (change instanceof AddOntologyAnnotationData)
            return render((AddOntologyAnnotationData) change);
        else if (change instanceof RemoveOntologyAnnotationData)
            return render((RemoveOntologyAnnotationData) change);
        else if (change instanceof AddAxiomData)
            return render((AddAxiomData) change);
        else if (change instanceof RemoveAxiomData)
            return render((RemoveAxiomData) change);
        else
            return "UnknownChangeType: " + change.getClass().getName();
    }

    public final String render(final SetOntologyFormatData change) {
        return indentString("*", "OntologyFormat(\""
                + change.getNewFormat().toString() + "\")");
    }

    public final String render(final AddPrefixData change) {
        return indentString("+", "Prefix(" + change.getPrefixName() + "=<"
                + change.getPrefix() + ">)");
    }

    public final String render(final RemovePrefixData change) {
        return indentString("-", "Prefix(" + change.getPrefixName() + "=<"
                + change.getPrefix() + ">)");
    }

    public final String render(final ModifyPrefixData change) {
        return indentString("*", "Prefix(" + change.getPrefixName() + "=<"
                + change.getPrefix() + "> <" + change.getNewPrefix() + ">)");
    }

    public final String render(final RenamePrefixData change) {
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

    public final String render(final SetOntologyIDData change) {
        final OWLOntologyID newid = change.getNewId();
        return indentString("*", "OntologyID(" + renderOntologyId(newid) + ")");
    }

    public final String render(final AddImportData change) {
        return indentString("+", "Import(<"
                + change.getDeclaration().getIRI().toString() + ">)");
    }

    public final String render(final RemoveImportData change) {
        return indentString("-", "Import(<"
                + change.getDeclaration().getIRI().toString() + ">)");
    }

    public final String render(final AddOntologyAnnotationData change) {
        return indentString("+", objectRenderer.render(change.getAnnotation()));
    }

    public final String render(final RemoveOntologyAnnotationData change) {
        return indentString("-", objectRenderer.render(change.getAnnotation()));
    }

    public final String render(final AddAxiomData change) {
        return indentString("+", objectRenderer.render(change.getAxiom()));
    }

    public final String render(final RemoveAxiomData change) {
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

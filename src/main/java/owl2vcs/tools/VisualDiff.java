package owl2vcs.tools;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.semanticweb.owlapi.change.AxiomChangeData;
import org.semanticweb.owlapi.change.ImportChangeData;
import org.semanticweb.owlapi.change.OWLOntologyChangeData;
import org.semanticweb.owlapi.change.SetOntologyIDData;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyInputSourceException;
import org.semanticweb.owlapi.io.UnparsableOntologyException;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.util.ShortFormProvider;

import owl2vcs.analysis.ChangeClassifier;
import owl2vcs.analysis.EntityClassifier;
import owl2vcs.analysis.EntityCollector;
import owl2vcs.analysis.EntityCollectorException;
import owl2vcs.changes.PrefixChangeData;
import owl2vcs.changes.SetOntologyFormatData;
import owl2vcs.changeset.FullChangeSet;
import owl2vcs.render.ChangeFormat;
import owl2vcs.render.ChangeRenderer;
import owl2vcs.render.FunctionalChangeRenderer;
import owl2vcs.render.IriFormat;
import owl2vcs.utils.CommonUtils;
import owl2vcs.utils.OntologyUtils;
import sun.swing.DefaultLookup;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;

class VisualDiffSettings {

    @Argument(required = true, index = 0, metaVar = "parent.owl", usage = "Parent version")
    public String parentFilename;
    @Argument(required = true, index = 1, metaVar = "child.owl", usage = "Child version")
    public String childFilename;
    @Option(name = "--format", aliases = { "-f" }, metaVar = "format", required = false, usage = "Format of statements: compact or indented")
    public ChangeFormat format = ChangeFormat.COMPACT;
    @Option(name = "--iriformat", aliases = { "-i" }, metaVar = "iriformat", required = false, usage = "Format of IRIs: simple, qname (default), full, label")
    public IriFormat iriFormat = IriFormat.QNAME;
}

class VisualDiffData {
    public OWLOntology o1;
    public OWLOntology o2;
    public Map<String, String> prefixes;
    public ChangeClassifier changesByEntity;
    public HashSet<OWLEntity> entities;
    public HashSet<OWLEntity> newEntities;
    public HashSet<OWLEntity> removedEntities;
    public HashSet<OWLEntity> modifiedEntities;
    public FullChangeSet changeset;

    public VisualDiffData(final String parent, final String child) {
        try {
            if (parent.equals(child)) {
                CommonUtils.messageBox("Paths are identical");
                return;
            }
            final FileDocumentSource parentSource = new FileDocumentSource(
                    new File(parent));
            final FileDocumentSource childSource = new FileDocumentSource(
                    new File(child));
            // binary compare
            final Boolean areIdenticalSources = Diff.binaryCompareSources(
                    parentSource, childSource);
            if (areIdenticalSources) {
                CommonUtils.messageBox("Files are binary identical");
                return;
            }
            o1 = OntologyUtils.loadOntology(parentSource);
            o2 = OntologyUtils.loadOntology(childSource);
            changeset = new FullChangeSet(o1, o2);

            entities = new HashSet<OWLEntity>();
            newEntities = new HashSet<OWLEntity>();
            removedEntities = new HashSet<OWLEntity>();
            modifiedEntities = new HashSet<OWLEntity>();
            changeset.accept(new EntityCollector(entities,
                    new HashSet<OWLAnonymousIndividual>()));
            new EntityClassifier(o1, o2, entities, newEntities,
                    removedEntities, modifiedEntities);
            // prefixes
            final OWLOntologyFormat parentFormat = o1.getOWLOntologyManager()
                    .getOntologyFormat(o1);
            final OWLOntologyFormat childFormat = o2.getOWLOntologyManager()
                    .getOntologyFormat(o2);
            if (parentFormat.isPrefixOWLOntologyFormat()
                    && childFormat.isPrefixOWLOntologyFormat()) {
                prefixes = Diff.extractPrefixes(entities, parentFormat,
                        childFormat);
            }
            // changes by entity
            changesByEntity = new ChangeClassifier(changeset.getAxiomChanges());

        } catch (final OWLOntologyInputSourceException e) {
            if (e.getCause() != null)
                CommonUtils.errorBox("Bad input source: "
                        + e.getCause().getMessage());
            else
                CommonUtils.errorBox("Bad input source: " + e.getMessage());
        } catch (final FileNotFoundException e) {
            CommonUtils.errorBox("File not found: " + e.getMessage());
        } catch (final IOException e) {
            CommonUtils.errorBox(CommonUtils.stackTrackAsString(e));
        } catch (final UnparsableOntologyException e) {
            CommonUtils.errorBox("Could not parse: "
                    + e.getDocumentIRI().toString());
        } catch (final OWLOntologyCreationException e) {
            CommonUtils.errorBox(CommonUtils.stackTrackAsString(e));
        } catch (EntityCollectorException e) {
            // impossible
            CommonUtils.errorBox(CommonUtils.stackTrackAsString(e));
        }
    }
}

class TextAreaRenderer extends JTextArea implements TableCellRenderer {

    private static final long serialVersionUID = 7356544505909915181L;
    private Color unselectedForeground;
    private Color unselectedBackground;
    private int cellPadding;

    private static final Border SAFE_NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1,
            1);
    private static final Border DEFAULT_NO_FOCUS_BORDER = new EmptyBorder(1, 1,
            1, 1);
    protected static Border noFocusBorder = DEFAULT_NO_FOCUS_BORDER;

    public TextAreaRenderer(int cellPadding) {
        setLineWrap(true);
        setWrapStyleWord(true);
        this.cellPadding = cellPadding;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        if (table == null) {
            return this;
        }

        Color fg = null;
        Color bg = null;

        if (isSelected) {
            super.setForeground(fg == null ? table.getSelectionForeground()
                    : fg);
            super.setBackground(bg == null ? table.getSelectionBackground()
                    : bg);
        } else {
            Color background = unselectedBackground != null ? unselectedBackground
                    : table.getBackground();
            if (background == null
                    || background instanceof javax.swing.plaf.UIResource) {
                Color alternateColor = DefaultLookup.getColor(this, ui,
                        "Table.alternateRowColor");
                if (alternateColor != null && row % 2 != 0) {
                    background = alternateColor;
                }
            }
            super.setForeground(unselectedForeground != null ? unselectedForeground
                    : table.getForeground());
            super.setBackground(background);
        }

        setFont(table.getFont());
        setLineWrap(false);

        setValue(value);

        int height_wanted = (int) getPreferredSize().getHeight();
        if (height_wanted != table.getRowHeight(row))
            table.setRowHeight(row, height_wanted);
        return this;
    }

    protected void setValue(Object value) {
        setText((value == null) ? "" : value.toString());
    }

    public void setForeground(Color c) {
        super.setForeground(c);
        unselectedForeground = c;
    }

    public void setBackground(Color c) {
        super.setBackground(c);
        unselectedBackground = c;
    }

}

public class VisualDiff {

    protected VisualDiffData data;
    protected ChangeRenderer renderer;

    private JFrame frmVisualdiff;
    private JPanel panel_Prefixes;
    private JPanel panel_PrefixChanges;
    private JPanel panel_ImportChanges;
    private JPanel panel_AxiomChanges;
    private JPanel panel_AnnotationChanges;
    private JTable table_PrefixChanges;
    private JTable table_ImportChanges;
    private JTable table_AnnotationChanges;
    private JTable table_AxiomChanges;
    private JTable table_Prefixes;
    private JTextField textField_Format;
    private JTextField textField_OntologyIRI;
    private JTextField textField_VersionIRI;
    private JTabbedPane tabbedPane;

    private String IriToString(IRI iri) {
        if (iri != null)
            return iri.toString();
        else
            return "";
    }

    private void update() {
        Color addedColor = Color.GREEN;
        Color removedColor = Color.RED;
        Color neutralColor = new Color(238, 238, 238);

        // Ontology format
        SetOntologyFormatData formatChange = data.changeset.getFormatChange();
        if (formatChange != null) {
            textField_Format.setText(formatChange.getNewFormat().toString());
            OWLOntologyFormat oldFormat = data.o1.getOWLOntologyManager()
                    .getOntologyFormat(data.o1);
            textField_Format.setToolTipText("Previous value: "
                    + oldFormat.toString());
            textField_Format.setBackground(addedColor);
        } else {
            textField_Format.setText(data.o2.getOWLOntologyManager()
                    .getOntologyFormat(data.o2).toString());
            textField_Format.setBackground(neutralColor);
        }
        // Ontology ID
        SetOntologyIDData ontologyIdChange = data.changeset
                .getOntologyIdChange();
        IRI ontologyIRI;
        IRI originalOntologyIRI = data.o1.getOntologyID().getOntologyIRI();
        IRI versionIRI;
        IRI originalVersionIRI = data.o1.getOntologyID().getVersionIRI();
        textField_OntologyIRI.setBackground(neutralColor);
        textField_VersionIRI.setBackground(neutralColor);
        if (ontologyIdChange != null) {
            OWLOntologyID newId = ontologyIdChange.getNewId();
            ontologyIRI = newId.getOntologyIRI();
            if (!((ontologyIRI == originalOntologyIRI) || ontologyIRI
                    .equals(originalOntologyIRI))) {
                textField_OntologyIRI.setBackground(addedColor);
                textField_OntologyIRI.setToolTipText("Previous value: "
                        + IriToString(originalOntologyIRI));
            }
            versionIRI = newId.getVersionIRI();
            if (!((versionIRI == originalVersionIRI) || versionIRI
                    .equals(originalVersionIRI))) {
                textField_VersionIRI.setBackground(addedColor);
                textField_VersionIRI.setToolTipText("Previous value: "
                        + IriToString(originalVersionIRI));
            }
        } else {
            ontologyIRI = originalOntologyIRI;
            versionIRI = originalVersionIRI;
        }
        textField_OntologyIRI.setText(IriToString(ontologyIRI));
        textField_VersionIRI.setText(IriToString(versionIRI));
        // Prefixes
        DefaultTableModel model = (DefaultTableModel) table_Prefixes.getModel();
        for (Map.Entry<String, String> e : data.prefixes.entrySet()) {
            model.addRow(new Object[] { e.getKey(), e.getValue() });
        }
        if (model.getRowCount() == 0)
            tabbedPane.remove(panel_Prefixes);
        // Prefix changes
        model = (DefaultTableModel) table_PrefixChanges.getModel();
        for (PrefixChangeData c : data.changeset.getPrefixChanges()) {
            model.addRow(new Object[] { renderer.getSymbol(c),
                    c.getPrefixName(), c.getPrefix() });
        }
        if (model.getRowCount() == 0)
            tabbedPane.remove(panel_PrefixChanges);
        // Import changes
        model = (DefaultTableModel) table_ImportChanges.getModel();
        for (ImportChangeData c : data.changeset.getImportChanges()) {
            model.addRow(new Object[] { renderer.getSymbol(c),
                    c.getDeclaration().getIRI().toString() });
        }
        if (model.getRowCount() == 0)
            tabbedPane.remove(panel_ImportChanges);
        // Annotations
        model = (DefaultTableModel) table_AnnotationChanges.getModel();
        for (OWLOntologyChangeData c : data.changeset.getAnnotationChanges()) {
            model.addRow(new Object[] { renderer.render(c) });
        }
        if (model.getRowCount() == 0)
            tabbedPane.remove(panel_AnnotationChanges);
        // Axiom changes
        model = (DefaultTableModel) table_AxiomChanges.getModel();
        for (AxiomChangeData c : data.changeset.getAxiomChanges()) {
            model.addRow(new Object[] { renderer.render(c) });
        }
        if (model.getRowCount() == 0)
            tabbedPane.remove(panel_AxiomChanges);
    }

    static abstract class MyRunnable implements Runnable {

        protected VisualDiffData data;
        protected ChangeRenderer renderer;

        public MyRunnable(VisualDiffData data, ChangeRenderer renderer) {
            this.data = data;
            this.renderer = renderer;
        }
    };

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        VisualDiffSettings settings = new VisualDiffSettings();
        CmdLineParser parser = new CmdLineParser(settings);
        VisualDiffData data = null;
        ChangeRenderer renderer = null;
        try {
            parser.parseArgument(args);
            data = new VisualDiffData(settings.parentFilename,
                    settings.childFilename);
            ShortFormProvider provider = Diff.createShortFormProvider(
                    settings.iriFormat, data.o1, data.o2);
            renderer = new FunctionalChangeRenderer(provider, settings.format);
        } catch (CmdLineException e) {
            ByteArrayOutputStream baos1 = new ByteArrayOutputStream();
            parser.printSingleLineUsage(baos1);
            ByteArrayOutputStream baos2 = new ByteArrayOutputStream();
            parser.printUsage(baos2);
            CommonUtils.errorBox("Usage: owl2diff" + baos1.toString() + "\n"
                    + baos2.toString());
            final int usageErrorCode = 64;
            System.exit(usageErrorCode);
        }

        EventQueue.invokeLater(new MyRunnable(data, renderer) {
            @Override
            public void run() {
                try {
                    VisualDiff window = new VisualDiff();
                    window.data = this.data;
                    window.renderer = this.renderer;
                    window.update();
                    window.frmVisualdiff.setLocationRelativeTo(null);
                    window.frmVisualdiff.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VisualDiff() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
        rightRenderer.setHorizontalAlignment(JLabel.RIGHT);

        int CELL_PADDING = 5;
        TableCellRenderer multilineRenderer = new TextAreaRenderer(CELL_PADDING);

        Font TABLE_FONT = new Font("Consolas", Font.PLAIN, 15);
        int ROW_HEIGHT = TABLE_FONT.getSize() + CELL_PADDING;

        int MAX_SCROLLABLE_WIDTH = 10000;

        frmVisualdiff = new JFrame();
        frmVisualdiff.setTitle("VisualDiff");
        frmVisualdiff.setBounds(100, 100, 622, 503);
        frmVisualdiff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frmVisualdiff.getContentPane().add(panel, BorderLayout.NORTH);

        JLabel lblFormatCaption = new JLabel("Format");
        lblFormatCaption.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel lblOntologyIriCaption = new JLabel("Ontology IRI");
        lblOntologyIriCaption.setHorizontalAlignment(SwingConstants.RIGHT);

        JLabel lblVersionIriCaption = new JLabel("Version IRI");
        lblVersionIriCaption.setHorizontalAlignment(SwingConstants.RIGHT);

        textField_Format = new JTextField();
        textField_Format.setFont(TABLE_FONT);
        textField_Format.setEditable(false);
        textField_Format.setColumns(10);

        textField_OntologyIRI = new JTextField();
        textField_OntologyIRI.setFont(TABLE_FONT);
        textField_OntologyIRI.setEditable(false);
        textField_OntologyIRI.setColumns(10);

        textField_VersionIRI = new JTextField();
        textField_VersionIRI.setFont(TABLE_FONT);
        textField_VersionIRI.setEditable(false);
        textField_VersionIRI.setColumns(10);
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(gl_panel
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                        gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        gl_panel.createParallelGroup(
                                                Alignment.LEADING)
                                                .addGroup(
                                                        gl_panel.createSequentialGroup()
                                                                .addComponent(
                                                                        lblOntologyIriCaption,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        73,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        ComponentPlacement.RELATED)
                                                                .addComponent(
                                                                        textField_OntologyIRI,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        512,
                                                                        Short.MAX_VALUE))
                                                .addGroup(
                                                        gl_panel.createSequentialGroup()
                                                                .addComponent(
                                                                        lblVersionIriCaption,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        73,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        ComponentPlacement.RELATED)
                                                                .addComponent(
                                                                        textField_VersionIRI,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        512,
                                                                        Short.MAX_VALUE))
                                                .addGroup(
                                                        gl_panel.createSequentialGroup()
                                                                .addComponent(
                                                                        lblFormatCaption,
                                                                        GroupLayout.PREFERRED_SIZE,
                                                                        73,
                                                                        GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                        ComponentPlacement.RELATED)
                                                                .addComponent(
                                                                        textField_Format,
                                                                        GroupLayout.DEFAULT_SIZE,
                                                                        512,
                                                                        Short.MAX_VALUE)))
                                .addContainerGap()));
        gl_panel.setVerticalGroup(gl_panel
                .createParallelGroup(Alignment.LEADING)
                .addGroup(
                        gl_panel.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        gl_panel.createParallelGroup(
                                                Alignment.BASELINE)
                                                .addComponent(lblFormatCaption)
                                                .addComponent(
                                                        textField_Format,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(
                                        gl_panel.createParallelGroup(
                                                Alignment.BASELINE)
                                                .addComponent(
                                                        lblOntologyIriCaption)
                                                .addComponent(
                                                        textField_OntologyIRI,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(
                                        gl_panel.createParallelGroup(
                                                Alignment.BASELINE)
                                                .addComponent(
                                                        lblVersionIriCaption)
                                                .addComponent(
                                                        textField_VersionIRI,
                                                        GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        panel.setLayout(gl_panel);

        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
        frmVisualdiff.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        panel_Prefixes = new JPanel();
        panel_Prefixes.setBorder(new EmptyBorder(8, 8, 8, 8));
        tabbedPane.addTab("Prefixes", null, panel_Prefixes, null);
        panel_Prefixes.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane = new JScrollPane();
        panel_Prefixes.add(scrollPane);

        table_Prefixes = new JTable() {
            public boolean getScrollableTracksViewportWidth() {
                return getPreferredSize().width < getParent().getWidth();
            }
        };
        table_Prefixes.setFont(TABLE_FONT);
        table_Prefixes.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                "Name", "Value"
            }
        ) {
            Class[] columnTypes = new Class[] {
                String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_Prefixes.getColumnModel().getColumn(0).setPreferredWidth(50);
        table_Prefixes.getColumnModel().getColumn(0).setMinWidth(40);
        table_Prefixes.getColumnModel().getColumn(1).setPreferredWidth(350);
        table_Prefixes.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table_Prefixes.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
        table_Prefixes.setRowHeight(ROW_HEIGHT);
        scrollPane.setViewportView(table_Prefixes);

        panel_PrefixChanges = new JPanel();
        panel_PrefixChanges.setBorder(new EmptyBorder(8, 8, 8, 8));
        tabbedPane.addTab("Prefix Changes", null, panel_PrefixChanges, null);
        panel_PrefixChanges.setLayout(new BorderLayout(8, 8));

        JScrollPane scrollPane_1 = new JScrollPane();
        panel_PrefixChanges.add(scrollPane_1, BorderLayout.CENTER);

        table_PrefixChanges = new JTable();
        scrollPane_1.setViewportView(table_PrefixChanges);
        table_PrefixChanges.setFont(TABLE_FONT);
        table_PrefixChanges.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                " ", "Name", "Value"
            }
        ) {
            Class[] columnTypes = new Class[] {
                String.class, String.class, String.class
            };
            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }
            boolean[] columnEditables = new boolean[] {
                false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_PrefixChanges.getColumnModel().getColumn(0).setResizable(false);
        table_PrefixChanges.getColumnModel().getColumn(0).setPreferredWidth(20);
        table_PrefixChanges.getColumnModel().getColumn(0).setMinWidth(20);
        table_PrefixChanges.getColumnModel().getColumn(0).setMaxWidth(20);
        table_PrefixChanges.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_PrefixChanges.getColumnModel().getColumn(1).setPreferredWidth(40);
        table_PrefixChanges.getColumnModel().getColumn(1).setMinWidth(40);
        table_PrefixChanges.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
        table_PrefixChanges.getColumnModel().getColumn(2).setPreferredWidth(340);
        table_PrefixChanges.setBorder(null);
        table_PrefixChanges.setRowHeight(ROW_HEIGHT);

        panel_ImportChanges = new JPanel();
        panel_ImportChanges.setBorder(new EmptyBorder(8, 8, 8, 8));
        tabbedPane.addTab("Import Changes", null, panel_ImportChanges, null);
        panel_ImportChanges.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_2 = new JScrollPane();
        panel_ImportChanges.add(scrollPane_2, BorderLayout.CENTER);

        table_ImportChanges = new JTable();
        scrollPane_2.setViewportView(table_ImportChanges);
        table_ImportChanges.setShowVerticalLines(false);
        table_ImportChanges.setFont(TABLE_FONT);
        table_ImportChanges.setModel(new DefaultTableModel(
            new Object[][] {
            },
            new String[] {
                " ", "Import"
            }
        ) {
            boolean[] columnEditables = new boolean[] {
                false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_ImportChanges.getColumnModel().getColumn(0).setResizable(false);
        table_ImportChanges.getColumnModel().getColumn(0).setPreferredWidth(20);
        table_ImportChanges.getColumnModel().getColumn(0).setMinWidth(20);
        table_ImportChanges.getColumnModel().getColumn(0).setMaxWidth(20);
        table_ImportChanges.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        table_ImportChanges.getColumnModel().getColumn(1).setPreferredWidth(380);
        table_ImportChanges.getColumnModel().getColumn(1).setMinWidth(80);
        table_ImportChanges.setBorder(null);
        table_ImportChanges.setRowHeight(ROW_HEIGHT);

        panel_AnnotationChanges = new JPanel();
        panel_AnnotationChanges.setBorder(new EmptyBorder(8, 8, 8, 8));
        tabbedPane.addTab("Annotation Changes", null, panel_AnnotationChanges,
                null);
        panel_AnnotationChanges.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel_AnnotationChanges.add(scrollPane_3, BorderLayout.CENTER);

        table_AnnotationChanges = new JTable();
        scrollPane_3.setViewportView(table_AnnotationChanges);
        table_AnnotationChanges.setFont(TABLE_FONT);
        table_AnnotationChanges.setModel(new DefaultTableModel(
                new Object[][] {}, new String[] { "Annotation Change" }) {
            Class[] columnTypes = new Class[] { String.class };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[] { false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        table_AnnotationChanges.getColumnModel().getColumn(0)
                .setResizable(false);
        table_AnnotationChanges.getColumnModel().getColumn(0)
                .setPreferredWidth(MAX_SCROLLABLE_WIDTH);
        table_AnnotationChanges.setBorder(null);
        table_AnnotationChanges.getColumnModel().getColumn(0)
        .setCellRenderer(multilineRenderer);
        table_AnnotationChanges.setTableHeader(null);
        table_AnnotationChanges.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        panel_AxiomChanges = new JPanel();
        panel_AxiomChanges.setBorder(new EmptyBorder(8, 8, 8, 8));
        tabbedPane.addTab("Axiom Changes", null, panel_AxiomChanges, null);
        panel_AxiomChanges.setLayout(new BorderLayout(0, 0));

        JScrollPane scrollPane_4 = new JScrollPane();
        scrollPane_4.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        panel_AxiomChanges.add(scrollPane_4, BorderLayout.CENTER);

        table_AxiomChanges = new JTable();
        scrollPane_4.setViewportView(table_AxiomChanges);
        table_AxiomChanges.setFont(TABLE_FONT);
        table_AxiomChanges.setModel(new DefaultTableModel(new Object[][] {},
                new String[] { "Axiom Change" }) {
            Class[] columnTypes = new Class[] { String.class };

            public Class getColumnClass(int columnIndex) {
                return columnTypes[columnIndex];
            }

            boolean[] columnEditables = new boolean[] { false };

            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });

        table_AxiomChanges.getColumnModel().getColumn(0).setPreferredWidth(MAX_SCROLLABLE_WIDTH);
        table_AxiomChanges.setBorder(null);
        table_AxiomChanges.getColumnModel().getColumn(0)
                .setCellRenderer(multilineRenderer);
        table_AxiomChanges.setTableHeader(null);
        table_AxiomChanges.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

}

package owl2vcs.tools;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.FileDocumentSource;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.util.ShortFormProvider;
import org.semanticweb.owlapi.util.SimpleIRIShortFormProvider;
import org.semanticweb.owlapi.util.SimpleShortFormProvider;

import owl2vcs.analysis.ConflictFinder;
import owl2vcs.render.ChangeFormat;
import owl2vcs.render.FullFormProvider;
import owl2vcs.render.FunctionalChangeRenderer;
import owl2vcs.render.IriFormat;
import owl2vcs.render.QNameMultiMapShortFormProvider;
import owl2vcs.render.QuotedAnnotationValueShortFormProvider;
import owl2vcs.utils.OntologyUtils;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class MergeSettings {
    @Option(name = "-o", usage = "Output", metaVar = "outfile", required = false)
    public String output;
    @Option(name = "--auto", usage = "Don't display GUI if no conflicts are found", required = false)
    public Boolean auto;
    @Option(name = "--format", aliases = {"-f"}, metaVar = "format", usage = "Format of changes: compact or indented", required = false)
    public ChangeFormat format = ChangeFormat.COMPACT;
    @Option(name = "--iriformat", aliases = { "-i" }, metaVar = "iriformat", required = false, usage = "Format of IRIs: simple, qname (default), full, label")
    public IriFormat iriFormat = IriFormat.QNAME;
    @Argument
    public ArrayList<String> extraArgs = new ArrayList<String>();
}

@SuppressWarnings("serial")
class CheckedMap extends HashMap<Object, Boolean> {
    public Boolean get(Object key, Boolean def) {
        if (this.containsKey(key))
            return this.get(key);
        else
            return def;
    }
    public Collection<Object> getCheckedItems() {
        Collection<Object> result = new ArrayList<Object>();
        for (Object key : this.keySet())
            if (this.get(key))
                result.add(key);
        return result;
    }
}

class Merge {
    private String baseFilename;
    private String localFilename;
    private String remoteFilename;
    private String outputFilename;
    private ConflictFinder conflictFinder;
    private boolean modified;
    private CheckedMap checkedChanges;

    private static ShortFormProvider createShortFormProvider(
            final IriFormat iriFormat, final OWLOntology ontology,
            final OWLOntologyFormat baseFormat,
            final OWLOntologyFormat localFormat,
            final OWLOntologyFormat remoteFormat) {
        ShortFormProvider provider;
        switch (iriFormat) {
        case SIMPLE:
            provider = new SimpleShortFormProvider();
            break;
        case FULL:
            provider = new FullFormProvider();
            break;
        case QNAME:
            final List<Map<String, String>> prefix2NamespaceMaps =
                new ArrayList<Map<String, String>>();
            if (baseFormat.isPrefixOWLOntologyFormat()) {
                prefix2NamespaceMaps.add(baseFormat.asPrefixOWLOntologyFormat().getPrefixName2PrefixMap());
            }
            if (localFormat.isPrefixOWLOntologyFormat()) {
                prefix2NamespaceMaps.add(localFormat.asPrefixOWLOntologyFormat().getPrefixName2PrefixMap());
            }
            if (remoteFormat.isPrefixOWLOntologyFormat()) {
                prefix2NamespaceMaps.add(remoteFormat.asPrefixOWLOntologyFormat().getPrefixName2PrefixMap());
            }
            provider = new QNameMultiMapShortFormProvider(prefix2NamespaceMaps);
            break;
        case LABEL:
            final List<OWLAnnotationProperty> annotationProperties = new ArrayList<OWLAnnotationProperty>();
            annotationProperties.add(ontology.getOWLOntologyManager()
                    .getOWLDataFactory().getRDFSLabel());
            provider = new QuotedAnnotationValueShortFormProvider(
                    ontology.getOWLOntologyManager(),
                    new SimpleShortFormProvider(),
                    new SimpleIRIShortFormProvider(), annotationProperties,
                    new HashMap<OWLAnnotationProperty, List<String>>());
            break;
        default:
            provider = new SimpleShortFormProvider();
        }
        return provider;
    }

    void load(String baseFilename, String localFilename,
            String remoteFilename, String outputFilename, IriFormat iriFormat) {
        if (null == remoteFilename)
            remoteFilename = localFilename;
        this.baseFilename = new File(baseFilename).getAbsolutePath();
        this.localFilename = new File(localFilename).getAbsolutePath();
        this.remoteFilename = new File(remoteFilename).getAbsolutePath();
        if (null != outputFilename) {
            if (outputFilename.equals("STDOUT"))
                this.outputFilename = outputFilename;
            else
                this.outputFilename = new File(outputFilename).getAbsolutePath();
        }
        try {
            OWLOntology baseOntology = OntologyUtils.loadOntology(new FileDocumentSource(new File(baseFilename)));
            OWLOntology localOntology = OntologyUtils.loadOntology(new FileDocumentSource(new File(localFilename)));
            OWLOntology remoteOntology = OntologyUtils.loadOntology(new FileDocumentSource(new File(remoteFilename)));
            conflictFinder = new ConflictFinder(baseOntology, remoteOntology, localOntology);
            modified = true;

            checkedChanges = new CheckedMap();
            for (Object c : conflictFinder.getCommonChanges().all())
                checkedChanges.put(c, true);
            for (Object c : conflictFinder.getRemoteNonconflictingChanges().all())
                checkedChanges.put(c, true);
            for (Object c : conflictFinder.getLocalNonconflictingChanges().all())
                checkedChanges.put(c, true);
            for (Object c : conflictFinder.getRemoteConflicts().all())
                checkedChanges.put(c, false);
            for (Object c : conflictFinder.getLocalConflicts().all())
                checkedChanges.put(c, false);
        }
        catch (Exception ee) {
            ee.printStackTrace();
        }
    }
}

public class VisualMerge {

    private JFrame frmMerge;
    private JTable table;
    private final ButtonGroup bgrpChangeFormat = new ButtonGroup();
    private final ButtonGroup bgrpIriFormat = new ButtonGroup();
    private final JFileChooser fileChooser = new JFileChooser();
    protected static Merge merge;

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        merge = new Merge();
        MergeSettings settings = new MergeSettings();
        CmdLineParser parser = new CmdLineParser(settings);
        if (settings.auto != null && settings.output != null &&
                settings.extraArgs.size() > 2) {
            // Trying to merge without user intervention
            String baseFilename = settings.extraArgs.get(0);
            String localFilename = settings.extraArgs.get(1);
            String remoteFilename = settings.extraArgs.get(2);
            merge.load(baseFilename, localFilename, remoteFilename, settings.output, settings.iriFormat);
            // exit program
            return;
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final VisualMerge window = new VisualMerge();
                    window.frmMerge.setVisible(true);
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public VisualMerge() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frmMerge = new JFrame();
        frmMerge.setTitle("owl2merge");
        frmMerge.setBounds(100, 100, 450, 300);
        frmMerge.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frmMerge.getContentPane().add(tabbedPane, BorderLayout.CENTER);

        final JPanel panelMatches = new JPanel();
        tabbedPane.addTab("Matches", null, panelMatches, null);

        final JPanel panelConflicts = new JPanel();
        tabbedPane.addTab("Conflicts", null, panelConflicts, null);

        final JPanel panelOther = new JPanel();
        tabbedPane.addTab("Other", null, panelOther, null);

        JPanel panelManual = new JPanel();
        tabbedPane.addTab("Manual", null, panelManual, null);

        final JPanel panelResult = new JPanel();
        tabbedPane.addTab("Result", null, panelResult, null);

        table = new JTable();
        table.setFillsViewportHeight(true);
        table.setSurrendersFocusOnKeystroke(true);
        table.setShowVerticalLines(false);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setModel(new DefaultTableModel(new Object[][] { { Boolean.FALSE,
                null, null }, }, new String[] { " ", " ", "Change" }) {
            /**
             *
             */
            private static final long serialVersionUID = 5907522015410794221L;
            Class<?>[] columnTypes = new Class[] { Boolean.class, String.class,
                    String.class };

            @Override
            public Class<?> getColumnClass(final int columnIndex) {
                return columnTypes[columnIndex];
            }
        });
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getColumnModel().getColumn(0).setPreferredWidth(20);
        table.getColumnModel().getColumn(0).setMinWidth(20);
        table.getColumnModel().getColumn(0).setMaxWidth(20);
        table.getColumnModel().getColumn(1).setResizable(false);
        table.getColumnModel().getColumn(1).setPreferredWidth(20);
        table.getColumnModel().getColumn(1).setMinWidth(20);
        table.getColumnModel().getColumn(1).setMaxWidth(20);
        table.getColumnModel().getColumn(2).setPreferredWidth(400);
        panelResult.setLayout(new BorderLayout(0, 0));
        panelResult.add(table);

        final JMenuBar menuBar = new JMenuBar();
        frmMerge.setJMenuBar(menuBar);

        final JMenu mnfile = new JMenu("File");
        menuBar.add(mnfile);

        final JMenuItem mntmOpen = new JMenuItem("Open...");
        mnfile.add(mntmOpen);

        final JMenuItem mntmSave = new JMenuItem("Save...");
        mnfile.add(mntmSave);

        final JSeparator separator = new JSeparator();
        mnfile.add(separator);

        final JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        mnfile.add(mntmExit);

        final JMenu mnView = new JMenu("View");
        menuBar.add(mnView);

        final JMenu mnIriFormat = new JMenu("IRI format");
        mnView.add(mnIriFormat);

        final JRadioButtonMenuItem rdbtnmntmSimple = new JRadioButtonMenuItem(
                "Simple");
        bgrpIriFormat.add(rdbtnmntmSimple);
        mnIriFormat.add(rdbtnmntmSimple);

        final JRadioButtonMenuItem rdbtnmntmQname = new JRadioButtonMenuItem(
                "QName");
        bgrpIriFormat.add(rdbtnmntmQname);
        rdbtnmntmQname.setSelected(true);
        mnIriFormat.add(rdbtnmntmQname);

        final JRadioButtonMenuItem rdbtnmntmFull = new JRadioButtonMenuItem(
                "Full");
        bgrpIriFormat.add(rdbtnmntmFull);
        mnIriFormat.add(rdbtnmntmFull);

        JRadioButtonMenuItem rdbtnmntmLabel = new JRadioButtonMenuItem("Label");
        bgrpIriFormat.add(rdbtnmntmLabel);
        mnIriFormat.add(rdbtnmntmLabel);

        final JMenu mnChangeFormat = new JMenu("Changes format");
        mnView.add(mnChangeFormat);



        final JRadioButtonMenuItem rdbtnmntmCompact = new JRadioButtonMenuItem(
                "Compact");
        bgrpChangeFormat.add(rdbtnmntmCompact);
        mnChangeFormat.add(rdbtnmntmCompact);

        final JRadioButtonMenuItem rdbtnmntmIndented = new JRadioButtonMenuItem(
                "Indented");
        bgrpChangeFormat.add(rdbtnmntmIndented);
        rdbtnmntmIndented.setSelected(true);
        mnChangeFormat.add(rdbtnmntmIndented);

        frmMerge.setLocationRelativeTo(null);
    }

}

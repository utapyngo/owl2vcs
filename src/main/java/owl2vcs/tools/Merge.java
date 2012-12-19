package owl2vcs.tools;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Merge {

    private JFrame frmMerge;
    private JTable table;
    private final ButtonGroup bgrpChangeFormat = new ButtonGroup();
    private final ButtonGroup bgrpIriFormat = new ButtonGroup();

    /**
     * Launch the application.
     */
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Merge window = new Merge();
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
    public Merge() {
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

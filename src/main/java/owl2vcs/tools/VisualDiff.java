package owl2vcs.tools;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.BorderLayout;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.GridLayout;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.BevelBorder;
import javax.swing.SwingConstants;

public class VisualDiff {

	private JFrame frmVisualdiff;
	private JTable table_Prefixes;
	private JTable table_Imports;
	private JTable table_OntologyAnnotations;
	private JTable table_Axioms;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VisualDiff window = new VisualDiff();
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
		frmVisualdiff = new JFrame();
		frmVisualdiff.setTitle("VisualDiff");
		frmVisualdiff.setBounds(100, 100, 622, 503);
		frmVisualdiff.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frmVisualdiff.getContentPane().add(panel, BorderLayout.NORTH);

		JLabel lblFormatCaption = new JLabel("Format");
		lblFormatCaption.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblFormat = new JLabel("Unchanged");

		JLabel lblOntologyIriCaption = new JLabel("Ontology IRI");
		lblOntologyIriCaption.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblOntologyIri = new JLabel("Ontology IRI");

		JLabel lblVersionIriCaption = new JLabel("Version IRI");
		lblVersionIriCaption.setHorizontalAlignment(SwingConstants.RIGHT);

		JLabel lblVersionIri = new JLabel("Version IRI");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblOntologyIriCaption, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblVersionIriCaption, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblFormatCaption, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblFormat, GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
							.addContainerGap())
						.addComponent(lblOntologyIri, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)
						.addComponent(lblVersionIri, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 509, Short.MAX_VALUE)))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblFormat)
						.addComponent(lblFormatCaption))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblOntologyIri)
						.addComponent(lblOntologyIriCaption))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblVersionIri)
						.addComponent(lblVersionIriCaption))
					.addContainerGap())
		);
		panel.setLayout(gl_panel);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frmVisualdiff.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		JPanel panel_Prefixes = new JPanel();
		panel_Prefixes.setBorder(new EmptyBorder(8, 8, 8, 8));
		tabbedPane.addTab("Prefixes", null, panel_Prefixes, null);
		panel_Prefixes.setLayout(new BorderLayout(8, 8));

		table_Prefixes = new JTable();
		table_Prefixes.setModel(new DefaultTableModel(
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
		});
		table_Prefixes.getColumnModel().getColumn(0).setResizable(false);
		table_Prefixes.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_Prefixes.getColumnModel().getColumn(0).setMinWidth(20);
		table_Prefixes.getColumnModel().getColumn(1).setPreferredWidth(80);
		table_Prefixes.getColumnModel().getColumn(2).setPreferredWidth(300);
		table_Prefixes.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Prefixes.add(table_Prefixes);

		JPanel panel_Imports = new JPanel();
		panel_Imports.setBorder(new EmptyBorder(8, 8, 8, 8));
		tabbedPane.addTab("Imports", null, panel_Imports, null);
		panel_Imports.setLayout(new BorderLayout(0, 0));

		table_Imports = new JTable();
		table_Imports.setShowVerticalLines(false);
		table_Imports.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				" ", "Import"
			}
		));
		table_Imports.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_Imports.getColumnModel().getColumn(0).setMinWidth(20);
		table_Imports.getColumnModel().getColumn(1).setPreferredWidth(380);
		table_Imports.getColumnModel().getColumn(1).setMinWidth(80);
		table_Imports.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Imports.add(table_Imports, BorderLayout.CENTER);

		JPanel panel_OntologyAnnotations = new JPanel();
		panel_OntologyAnnotations.setBorder(new EmptyBorder(8, 8, 8, 8));
		tabbedPane.addTab("OntologyAnnotations", null, panel_OntologyAnnotations, null);
		panel_OntologyAnnotations.setLayout(new BorderLayout(0, 0));

		table_OntologyAnnotations = new JTable();
		table_OntologyAnnotations.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				" ", "Annotation"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_OntologyAnnotations.getColumnModel().getColumn(0).setResizable(false);
		table_OntologyAnnotations.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_OntologyAnnotations.getColumnModel().getColumn(0).setMinWidth(20);
		table_OntologyAnnotations.getColumnModel().getColumn(1).setPreferredWidth(380);
		table_OntologyAnnotations.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_OntologyAnnotations.add(table_OntologyAnnotations, BorderLayout.CENTER);

		JPanel panel_Axioms = new JPanel();
		panel_Axioms.setBorder(new EmptyBorder(8, 8, 8, 8));
		tabbedPane.addTab("Axioms", null, panel_Axioms, null);
		panel_Axioms.setLayout(new BorderLayout(0, 0));

		table_Axioms = new JTable();
		table_Axioms.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				" ", "Axiom"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_Axioms.getColumnModel().getColumn(0).setResizable(false);
		table_Axioms.getColumnModel().getColumn(0).setPreferredWidth(20);
		table_Axioms.getColumnModel().getColumn(0).setMinWidth(20);
		table_Axioms.getColumnModel().getColumn(1).setPreferredWidth(380);
		table_Axioms.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_Axioms.add(table_Axioms, BorderLayout.CENTER);
	}

}

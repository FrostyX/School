package zp4jv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -3489120285099256952L;

	private JPanel mainPanel;
	private JPanel controlsPanel;
	private JPanel centerPanel;
	//private JList<Timesheet> listTimesheets;
	private JButton bAddActivity;
	private JButton bAddTimesheet;
	private JButton bEditTimesheet;
	private JButton bDelTimesheet;
	private JTree tree;
	private ArrayList<Timesheet> timesheets;
	private JTable table;
	private MyTableModel tableModel;
	private JMenuBar mainMenu;
	private JScrollPane tableScroll;
	private JPanel formPanel;

	private JTextField tName;

	private JTextField tOccupation;

	private JButton bSave;

	private JButton bCancel;


	public MainForm(ArrayList<Timesheet> timesheets) {
		super();
		
		// Stored data
		this.timesheets = timesheets;

		// Panels
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		centerPanel = new JPanel();
		centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));;
		mainPanel.add(centerPanel, BorderLayout.CENTER);
		
		// Initialize components
		initializeMainMenu();
		initializeTree();
		initializeTable();
		initializeControlsPanel();
		initializeTimesheetForm(new Timesheet());
		showTable(new Object());
		
		// Populate components
		populateTree();
		populateTableWithTimesheets();

		// Add content to main panel
		mainPanel.add(controlsPanel, BorderLayout.SOUTH);
		
		// Configure the window
		this.setTitle("Timesheets");
		this.setPreferredSize(new Dimension(450, 400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();
	}
	
	private void initializeMainMenu() {
		mainMenu = new JMenuBar();

		/*
		 * Timesheet
		 */
		JMenu menuTimesheet = new JMenu("Timesheet");
		mainMenu.add(menuTimesheet);

		// Add timesheet
		JMenuItem menuItemAdd = new JMenuItem("Add");
		menuItemAdd.addActionListener(new AddTimesheet());
		menuTimesheet.add(menuItemAdd);

		// Edit timesheet
		JMenuItem menuItemEdit = new JMenuItem("Edit");
		menuItemEdit.addActionListener(new EditTimesheet());
		menuTimesheet.add(menuItemEdit);

		// Delete timesheet
		JMenuItem menuItemDel = new JMenuItem("Delete");
		menuItemDel.addActionListener(new DelTimesheet());
		menuTimesheet.add(menuItemDel);

		
		/*
		 * Activity
		 */
		JMenu menuActivity = new JMenu("Activity");
		mainMenu.add(menuActivity);

		// Add activity
		JMenuItem menuItemAddActivity = new JMenuItem("Add");
		//menuItemAddActivity.addActionListener(new AddTimesheet());
		menuActivity.add(menuItemAddActivity);

		// Edit activity 
		JMenuItem menuItemEditActivity = new JMenuItem("Edit");
		//menuItemEditActivity.addActionListener(new EditTimesheet());
		menuActivity.add(menuItemEditActivity);

		// Delete activity 
		JMenuItem menuItemDelActivity = new JMenuItem("Delete");
		//menuItemDelActivity.addActionListener(new DelTimesheet());
		menuActivity.add(menuItemDelActivity);



		// Set as main menu
		setJMenuBar(mainMenu);
	}

	private void initializeControlsPanel() {
		bAddActivity = new JButton("Add Activity");
		bAddTimesheet = new JButton("Add");
		bEditTimesheet = new JButton("Edit");
		bDelTimesheet = new JButton("Delete");

		bAddActivity.addActionListener(new ShowActivities());
		bAddTimesheet.addActionListener(new AddTimesheet());
		bEditTimesheet.addActionListener(new EditTimesheet());
		bDelTimesheet.addActionListener(new DelTimesheet());

		controlsPanel = new JPanel();
		controlsPanel.add(bAddActivity);
		controlsPanel.add(bAddTimesheet);
		controlsPanel.add(bEditTimesheet);
		controlsPanel.add(bDelTimesheet);
	}
	
	private void initializeTable() {

		String[] columns = new String[] {};
		Object[][] values = new Object[][] {};
		
		tableModel = new MyTableModel(columns, values);
		table = new JTable(tableModel);
		table.getSelectionModel().addListSelectionListener(new TableRowSelectionListener());

		tableScroll = new JScrollPane(table);
		
		centerPanel.add(tableScroll);
	}
	
	private void populateTableWithTimesheets() {
		tableModel.clear();
		
		String[] columns = new String[] {"", "Name", "Occupation"};
		tableModel.setColumns(columns);

		int i = 1;
		for(Timesheet sheet : timesheets) {
			tableModel.addRow(i, sheet.getName(), sheet.getOccupation());
			i++;
		}
	}
	
	private void initializeTimesheetForm(Timesheet sheet) {
		formPanel = new JPanel();
		formPanel.setLayout(new FlowLayout());
		
		// Name
		JLabel lName = new JLabel("Name:", JLabel.TRAILING);
		tName = new JTextField(20);
		lName.setLabelFor(tName);
		formPanel.add(lName);
		formPanel.add(tName);

		// Occupation
		JLabel lOccupation = new JLabel("Occupation:", JLabel.TRAILING);
		tOccupation = new JTextField(20);
		lName.setLabelFor(tOccupation);
		formPanel.add(lOccupation);
		formPanel.add(tOccupation);
		
		// Save
		bSave = new JButton("Save");
		bSave.addActionListener(new SaveTimesheet());
		formPanel.add(bSave);

		// Cancel
		bCancel = new JButton("Cancel");
		bCancel.addActionListener(new CancelSaveTimesheet());
		formPanel.add(bCancel);
		
		
		centerPanel.add(formPanel);
	}
	
	private final class MyTableModel extends AbstractTableModel {
		private static final long serialVersionUID = 1L;
		private String[] columns;
		private Object[][] values;
		
		public MyTableModel(String[] columns, Object[][] values) {
			super();
			this.columns = columns;
			this.values = values;
		}
		
		@Override
		public int getColumnCount() {
			return columns.length;
		}

		@Override
		public int getRowCount() {
			return values.length;
		}

		@Override
		public Object getValueAt(int r, int c) {
			return values[r][c];
		}
		
		@Override
		public String getColumnName(int column) {
			return columns[column];
		}

		@Override
		public boolean isCellEditable(int rowIndex, int columnIndex) {
			if (columnIndex >= 1) return true;
			return false;
		}

		@Override
		public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
			values[rowIndex][columnIndex] = aValue;
			fireTableCellUpdated(rowIndex, columnIndex);
		}
	
		public void setColumns(String[] columns) {
			this.columns = columns;
			fireTableDataChanged();
		}

		public void addRow(int i, String name, String occupation) {
			values = Arrays.copyOf(values, values.length + 1);
			values[values.length - 1] = new Object[] {i, name, occupation};
			fireTableStructureChanged();
		}
		
		public void clear() {
			values = new Object[][] {};
			fireTableStructureChanged();
		}
		
	}
	
	private void initializeTree() {
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");
		final DefaultTreeModel model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.addTreeSelectionListener(new MyTreeSelectionListener());

		mainPanel.add(new JScrollPane(tree), BorderLayout.WEST);
	}
	
	private void populateTree() {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren();
		
		for(Timesheet sheet : timesheets) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(sheet);
			for(Activity a : sheet.getActivities()) {
				child.add(new DefaultMutableTreeNode(a.getDate()));;
			}
			root.add(child);
		}
		model.reload(root);
	}
	
	private void showTable(Object o) {
		tableScroll.setVisible(true);
		formPanel.setVisible(false);
	}
	
	private void showForm(Object o) {
		tableScroll.setVisible(false);
		formPanel.setVisible(true);

		if(o instanceof Timesheet) {
			Timesheet sheet = (Timesheet) o;
			tName.setText(sheet.getName());
			tOccupation.setText(sheet.getOccupation());

			// Change ActionListener (give argument to SaveTimesheet)
			for(ActionListener l : bSave.getActionListeners()) {
				bSave.removeActionListener(l);
			}
			bSave.addActionListener(new SaveTimesheet(sheet));
		}
	}

	private class AddTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showForm(new Timesheet());
		}
	}

	private class EditTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (tree.getSelectionPath() == null) {
				showSelectTimesheetMessage();
			}
			else {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Timesheet s = (Timesheet) node.getUserObject();
				showForm(s);
			}
		}
	}

	private class DelTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (tree.getSelectionPath() == null) {
				showSelectTimesheetMessage();
			}
			else {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				Timesheet s = (Timesheet) node.getUserObject();
				Main.timesheets.remove(s);
				populateTree();
				populateTableWithTimesheets();
			}
		}
	}

	private class ShowActivities implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			/*
			if (listTimesheets.getSelectedIndex() != -1) {
				JFrame form = new ActivitiesForm(listTimesheets.getSelectedValue());
				form.setVisible(true);
			}
			else {
				showSelectTimesheetMessage();
			}
			*/
		}
	}

	private class SaveTimesheet implements ActionListener {
		private Timesheet old = null;
		
		public SaveTimesheet() {
			super();
		}
		public SaveTimesheet(Timesheet old) {
			super();
			this.old = old;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			Timesheet sheet = new Timesheet(tName.getText(), tOccupation.getText());
			
			System.out.println(old);
			if(Main.timesheets.contains(old)) {
				int index = Main.timesheets.indexOf(old);
				Main.timesheets.set(index, sheet);
			}
			else {
				Main.timesheets.add(sheet);
			}

			populateTableWithTimesheets();
			populateTree();
			showTable(new Object());
		}
	}

	private class CancelSaveTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showTable(new Object());
		}
	}
	
	private class MyTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
	
			// Selected node
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

			// If nothing is selected 
			if (node == null)
				return;
			
			try {
				Timesheet sheet = (Timesheet) node.getUserObject();
				
				for(int i = 0; i < tableModel.getRowCount(); i++) {
					Timesheet rowSheet = new Timesheet(
						(String) tableModel.getValueAt(i, 1), 
						(String) tableModel.getValueAt(i, 2)
					);
					if(sheet.equals(rowSheet)) {
						table.setRowSelectionInterval(i, i);
						break;
					}
				}
			}
			catch(Exception ex) {
				// Its an activity
			}
		}
	}
	
	private class TableRowSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent event) {
			
			// If no row is selected
			if(table.getSelectedRow() == -1) {
				return;
			}

			Timesheet sheet = new Timesheet(
				(String) tableModel.getValueAt(table.getSelectedRow(), 1),
				(String) tableModel.getValueAt(table.getSelectedRow(), 2)
			);
			
			
			DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
			Enumeration e = root.preorderEnumeration();
		    while(e.hasMoreElements()){
		    	
		    	DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
		        try {
					Timesheet sheetRow = (Timesheet) node.getUserObject();
					if(sheet.equals(sheetRow)) {
						tree.setSelectionPath(new TreePath(node.getPath()));
						break;
					}
		        }
		        catch(Exception ex) {
		        	// Activity
		        	continue;
		        }
		    }
		}
	}
	
	private void showSelectTimesheetMessage() {
			JOptionPane.showMessageDialog(new JFrame(),
				"Select a timesheet first",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
}

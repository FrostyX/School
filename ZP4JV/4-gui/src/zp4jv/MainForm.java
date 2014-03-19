package zp4jv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -3489120285099256952L;

	private JPanel mainPanel;
	private JPanel centerPanel;
	private JTree tree;
	private Company company;
	private JTable table;
	private MyTableModel tableModel;
	private MyTableModel activitiesTableModel;
	private JMenuBar mainMenu;
	private JScrollPane tableScroll;
	private JPanel timesheetFormPanel;
	private JTextField tName;
	private JTextField tOccupation;
	private JButton bSaveTimesheet;
	private JButton bCancelTimesheet;
	private JPanel activityPanel;
	private JScrollPane activitiesTableScroll;
	private JTable activitiesTable;
	private JPanel activityFormPanel;
	private JTextField tDate;
	private JTextField tHours;
	private JTextField tText;
	private JButton bSaveActivity;
	private JButton bCancelActivity;


	public MainForm(Company company) {
		super();
		
		// Stored data
		this.company = company;

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
		initializeTimesheetForm(new Timesheet());
		initializeActivityForm(new Activity());

		showTable(new Object());
		
		// Populate components
		populateTree();
		populateTableWithTimesheets();

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
		menuItemAddActivity.addActionListener(new AddActivity());
		menuActivity.add(menuItemAddActivity);

		// Set as main menu
		setJMenuBar(mainMenu);
	}

	private void initializeTable() {

		String[] columns = new String[] {};
		Object[][] values = new Object[][] {};
		
		tableModel = new MyTableModel(columns, values);
		table = new JTable(tableModel);
		table.addMouseListener(new TableRowSelectionListener());
		tableScroll = new JScrollPane(table);
		
		centerPanel.add(tableScroll);
	}
	
	private void populateTableWithTimesheets() {
		tableModel.clear();
		
		String[] columns = new String[] {"", "Name", "Occupation"};
		tableModel.setColumns(columns);

		int i = 1;
		for(Timesheet sheet : company.getTimesheets()) {
			tableModel.addRow(i, sheet.getName(), sheet.getOccupation());
			i++;
		}
	}

	private void populateTableWithActivities(Timesheet selectedTimesheet) {
		activitiesTableModel.clear(); 
		int i = 1;
		for(Activity a : selectedTimesheet.getActivities()) {
			activitiesTableModel.addRow(i, a.getDate(), a.getHours(), a.getText());
			i++;
		}
	}
	
	private void initializeTimesheetForm(Timesheet sheet) {
		timesheetFormPanel = new JPanel();
		timesheetFormPanel.setLayout(new FlowLayout());
		
		// Name
		JLabel lName = new JLabel("Name:", JLabel.TRAILING);
		tName = new JTextField(20);
		lName.setLabelFor(tName);
		timesheetFormPanel.add(lName);
		timesheetFormPanel.add(tName);

		// Occupation
		JLabel lOccupation = new JLabel("Occupation:", JLabel.TRAILING);
		tOccupation = new JTextField(20);
		lName.setLabelFor(tOccupation);
		timesheetFormPanel.add(lOccupation);
		timesheetFormPanel.add(tOccupation);
		
		// Save
		bSaveTimesheet = new JButton("Save");
		bSaveTimesheet.addActionListener(new SaveTimesheet());
		timesheetFormPanel.add(bSaveTimesheet);

		// Cancel
		bCancelTimesheet = new JButton("Cancel");
		bCancelTimesheet.addActionListener(new CancelSaveTimesheet(sheet));
		timesheetFormPanel.add(bCancelTimesheet);
		
		centerPanel.add(timesheetFormPanel);
	}

	private void initializeActivityForm(Activity activity) {

		activityPanel = new JPanel();
		activityPanel.setLayout(new BoxLayout(activityPanel, BoxLayout.PAGE_AXIS));;
		
		// Form for adding/editing activity
		activityFormPanel = new JPanel();
		activityFormPanel.setLayout(new BoxLayout(activityFormPanel, BoxLayout.PAGE_AXIS));;
		
		// Date
		JLabel lDate = new JLabel("Date:", JLabel.TRAILING);
		tDate = new JTextField(20);
		lDate.setLabelFor(tDate);
		activityFormPanel.add(lDate);
		activityFormPanel.add(tDate);

		// Hours
		JLabel lHours = new JLabel("Hours:", JLabel.TRAILING);
		tHours = new JTextField(2);
		lHours.setLabelFor(tHours);
		activityFormPanel.add(lHours);
		activityFormPanel.add(tHours);

		// Text
		JLabel lText = new JLabel("Text:", JLabel.TRAILING);
		tText = new JTextField(20);
		lText.setLabelFor(tOccupation);
		activityFormPanel.add(lText);
		activityFormPanel.add(tText);
		
		// Save
		bSaveActivity = new JButton("Save");
		bSaveActivity.addActionListener(new SaveActivity());
		activityFormPanel.add(bSaveActivity);

		// Cancel
		bCancelActivity = new JButton("Cancel");
		bCancelActivity.addActionListener(new CancelSaveActivity());
		activityFormPanel.add(bCancelActivity);
		
		activityPanel.add(activityFormPanel);
		
		
		// Table of activities
		String[] columns = new String[] {"", "Date", "Hours", "Text"};
		Object[][] values = new Object[][] {};
		
		activitiesTableModel = new MyTableModel(columns, values);
		activitiesTable = new JTable(activitiesTableModel);
		activitiesTableScroll = new JScrollPane(activitiesTable);
		activityPanel.add(activitiesTableScroll);
		
		centerPanel.add(activityPanel);
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

		public void addRow(int i, String date, int hours, String text) {
			values = Arrays.copyOf(values, values.length + 1);
			values[values.length - 1] = new Object[] {i, date, hours, text};
			fireTableStructureChanged();
		}
		
		public void clear() {
			values = new Object[][] {};
			fireTableStructureChanged();
		}
		
	}
	
	private void initializeTree() {
		final DefaultMutableTreeNode root = new DefaultMutableTreeNode(company);
		final DefaultTreeModel model = new DefaultTreeModel(root);
		tree = new JTree(model);
		tree.addTreeSelectionListener(new MyTreeSelectionListener());

		mainPanel.add(new JScrollPane(tree), BorderLayout.WEST);
	}
	
	private void populateTree() {
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
		DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
		root.removeAllChildren();
		
		for(Timesheet sheet : company.getTimesheets()) {
			DefaultMutableTreeNode child = new DefaultMutableTreeNode(sheet);
			for(Activity a : sheet.getActivities()) {
				child.add(new DefaultMutableTreeNode(a));;
			}
			root.add(child);
		}
		model.reload(root);
	}
	
	private void showTable(Object o) {
		tableScroll.setVisible(true);
		timesheetFormPanel.setVisible(false);
		activityPanel.setVisible(false);
	}
	
	private void showTimesheetForm(Object o) {
		tableScroll.setVisible(false);
		timesheetFormPanel.setVisible(true);
		activityPanel.setVisible(false);

		if(o instanceof Timesheet) {
			Timesheet sheet = (Timesheet) o;
			tName.setText(sheet.getName());
			tOccupation.setText(sheet.getOccupation());

			// Change ActionListener (give argument to SaveTimesheet)
			for(ActionListener l : bSaveTimesheet.getActionListeners()) {
				bSaveTimesheet.removeActionListener(l);
			}
			bSaveTimesheet.addActionListener(new SaveTimesheet(sheet));

			// Change ActionListener (give argument to CancelSaveTimesheet)
			for(ActionListener l : bCancelTimesheet.getActionListeners()) {
				bCancelTimesheet.removeActionListener(l);
			}
			bCancelTimesheet.addActionListener(new CancelSaveTimesheet(sheet));
		}
	}

	private class AddTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			showTimesheetForm(new Timesheet());
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
				showTimesheetForm(s);
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
				Main.company.remove(s);
				populateTree();
				populateTableWithTimesheets();
			}
		}
	}

	private void showActivities() {
		tableScroll.setVisible(false);
		timesheetFormPanel.setVisible(false);
		activityPanel.setVisible(true);
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
			
			if(Main.company.contains(old)) {
				int index = Main.company.indexOf(old);
				Main.company.get(index).setName(tName.getText());
				Main.company.get(index).setOccupation(tOccupation.getText());
			}
			else {
				Main.company.add(sheet);
			}

			populateTableWithTimesheets();
			populateTree();
			showTable(new Object());
		}
	}

	private class CancelSaveTimesheet implements ActionListener {
		private Timesheet old = null;
		
		public CancelSaveTimesheet(Timesheet old) {
			super();
			if((old.getActivities() != null) && (old.getName() != null))
				this.old = old;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(old == null)
				showTable(new Object());
			else {
				showActivities();
				populateTableWithActivities(old);
			}
		}
	}

	private class SaveActivity implements ActionListener {
		private Activity old = null;
		
		public SaveActivity() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (tree.getSelectionPath() == null) {
				showSelectTimesheetMessage();
			}
			else {
				// Find proper timesheet
				Timesheet sheet;
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				if(node.getUserObject() instanceof Timesheet) {
					sheet = (Timesheet) node.getUserObject();
				}
				else if(true) {
					sheet = new Timesheet();
				}
				
				// Create activity object from form
				Activity activity = new Activity(tDate.getText(), Integer.valueOf(tHours.getText()), tText.getText());

				// Save activity
				if(Main.company.get(Main.company.indexOf(sheet)).getActivities().contains(old)) {
					int index = Main.company.get(Main.company.indexOf(sheet)).getActivities().indexOf(old);
					Main.company.get(Main.company.indexOf(sheet)).getActivities().set(index, activity);
				}
				else {
					Main.company.get(Main.company.indexOf(sheet)).getActivities().add(activity);
				}
				
				populateTableWithActivities(sheet);
				tDate.setText("");
				tHours.setText("");
				tText.setText("");
			}
		}
	}

	private class AddActivity implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			// Selected node
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			
			if((node != null) && ((node.getUserObject() instanceof Timesheet) || (node.getUserObject() instanceof Activity))) {
				tDate.setText("");
				tHours.setText("");
				tText.setText("");
			}
			else {
				showSelectTimesheetMessage();
			}

		}
	}

	private class CancelSaveActivity implements ActionListener {

		private Activity old = new Activity();
		
		public CancelSaveActivity() {
			super();
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			tDate.setText(old.getDate());
			tHours.setText(old.getHours() == 0 ? "" : String.valueOf(old.getHours()));
			tText.setText(old.getText());
		}
	}
	
	private class MyTreeSelectionListener implements TreeSelectionListener {
		public void valueChanged(TreeSelectionEvent e) {
	
			// Selected node
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

			if ((node == null) || (node.getUserObject() instanceof Company)) {
				showTable(new Object());
			}
			else if(node.getUserObject() instanceof Timesheet) {
				showActivities();
				Timesheet sheet = (Timesheet) node.getUserObject();
				populateTableWithActivities(sheet);
			}
			else if(node.getUserObject() instanceof Activity) {
				DefaultMutableTreeNode parent = (DefaultMutableTreeNode) node.getParent();
				showActivities();
				Timesheet sheet = (Timesheet) parent.getUserObject();
				populateTableWithActivities(sheet);
			}
		}
	}
	
	private class TableRowSelectionListener extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 2) {
				JTable target = (JTable)e.getSource();
				int row = target.getSelectedRow();

				// Find selected timesheet (we need activities list too)
				Timesheet sheet = new Timesheet(
					(String) tableModel.getValueAt(row, 1),
					(String) tableModel.getValueAt(row, 2)
				);

				// Print table of activities
				for(Timesheet s : company.getTimesheets()) {
					if(sheet.equals(s)) {
						showActivities();
						populateTableWithActivities(s);
					}
				}
				
				// Select timesheet in tree
				DefaultMutableTreeNode root = (DefaultMutableTreeNode)tree.getModel().getRoot();
				Enumeration<?> enumerator = root.preorderEnumeration();
				while(enumerator.hasMoreElements()){
					
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumerator.nextElement();
					if(node.getUserObject() instanceof Timesheet) {
						Timesheet sheetRow = (Timesheet) node.getUserObject();
						if(sheet.equals(sheetRow)) {
							tree.setSelectionPath(new TreePath(node.getPath()));
							break;
						}
					}
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

package zp4jv;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JList;

public class MainForm extends JFrame {

	private static final long serialVersionUID = -3489120285099256952L;

	@SuppressWarnings("rawtypes")
	private DefaultListModel listModel;
	private JPanel mainPanel;
	private JPanel controlsPanel;
	private JList<Timesheet> listTimesheets;
	private JButton bAddActivity;
	private JButton bAddTimesheet;
	private JButton bEditTimesheet;
	private JButton bDelTimesheet;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public MainForm(DefaultListModel listModel) {
		super();

		// Main panel
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		// List of timesheets
		this.listModel = listModel;
		listTimesheets = new JList(listModel);

		// Initialize components
		initializeControlsPanel();
		addInitialTimesheets();

		// Add content to main panel
		mainPanel.add(listTimesheets);
		mainPanel.add(controlsPanel, BorderLayout.SOUTH);

		this.setTitle("Timesheets");
		this.setPreferredSize(new Dimension(450, 400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();
	}

	@SuppressWarnings("unchecked")
	private void addInitialTimesheets() {
		listModel.addElement(new Timesheet("Jakub Kadlčík", "Developer"));
		listModel.addElement(new Timesheet("Tony Stark", "Genius"));
		listModel.addElement(new Timesheet("Hulk", "Tank"));
		listModel.addElement(new Timesheet("Dr. House", "Healer"));
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

	private class AddTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame form = new SaveTimesheetForm(new Timesheet());
			form.setVisible(true);
		}
	}

	private class EditTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (listTimesheets.getSelectedIndex() != -1) {
				JFrame form = new SaveTimesheetForm(listTimesheets.getSelectedValue());
				form.setVisible(true);
			}
			else {
				showSelectTimesheetMessage();
			}
		}
	}

	private class DelTimesheet implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int index = listTimesheets.getSelectedIndex();
			if (listTimesheets.getSelectedIndex() != -1) {
				Main.listModel.removeElement(Main.listModel.get(index));
			}
			else {
				showSelectTimesheetMessage();
			}
		}
	}

	private class ShowActivities implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (listTimesheets.getSelectedIndex() != -1) {
				JFrame form = new ActivitiesForm(listTimesheets.getSelectedValue());
				form.setVisible(true);
			}
			else {
				showSelectTimesheetMessage();
			}
		}
	}

	public void setListModel(DefaultListModel listModel) {
		this.listModel = listModel;
	}

	public DefaultListModel getListModel() {
		return listModel;
	}
	
	private void showSelectTimesheetMessage() {
			JOptionPane.showMessageDialog(new JFrame(),
				"Select a timesheet first",
				"Warning",
				JOptionPane.WARNING_MESSAGE);
	}
}

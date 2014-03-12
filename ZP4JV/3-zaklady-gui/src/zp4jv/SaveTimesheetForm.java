package zp4jv;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class SaveTimesheetForm extends JFrame {

	private static final long serialVersionUID = -3489120285099256952L;
	private JTextField tName;
	private JTextField tOccupation;

	public SaveTimesheetForm(Timesheet sheet) {
		// Main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		
		// Name
		JLabel lName = new JLabel("Name:", JLabel.TRAILING);
		tName = new JTextField(sheet.getName(), 20);
		lName.setLabelFor(tName);
		mainPanel.add(lName);
		mainPanel.add(tName);

		// Occupation
		JLabel lOccupation = new JLabel("Occupation:", JLabel.TRAILING);
		tOccupation = new JTextField(sheet.getOccupation(), 20);
		lName.setLabelFor(tOccupation);
		mainPanel.add(lOccupation);
		mainPanel.add(tOccupation);
		
		// Save
		JButton bSave = new JButton("Save");
		bSave.addActionListener(new SaveActivity());
		mainPanel.add(bSave);

		this.setTitle("Timesheets - Save Timesheet");
		this.setPreferredSize(new Dimension(350, 400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();
	}

	private class SaveActivity implements ActionListener {
		@SuppressWarnings("unchecked")
		@Override
		public void actionPerformed(ActionEvent e) {
			Timesheet sheet = new Timesheet(tName.getText(), tOccupation.getText());

			if(Main.listModel.contains(sheet)) {
				// Edit existing timesheet
				int index = Main.listModel.indexOf(sheet);
				Main.listModel.setElementAt(sheet, index);
			}
			else {
				// Add new timesheet
				Main.listModel.addElement(sheet);
			}
			SaveTimesheetForm.this.dispose();
		}
	}
}

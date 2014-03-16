package zp4jv;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ActivitiesForm extends JFrame {

	private static final long serialVersionUID = -3489120285099256952L;
	private JTextField tDate;
	private JTextField tHours;
	private JTextField tText;
	private DefaultListModel<Activity> listModel;
	private JList<Activity> activitiesList;
	private Timesheet sheet;

	public ActivitiesForm(Timesheet sheet) {
		this.sheet = sheet;
		
		// Main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new FlowLayout());
		
		// Date
		JLabel lDate = new JLabel("Date:", JLabel.TRAILING);
		tDate = new JTextField(30);
		lDate.setLabelFor(tDate);
		mainPanel.add(lDate);
		mainPanel.add(tDate);
		
		// Hours 
		JLabel lHours = new JLabel("Hours:", JLabel.TRAILING);
		tHours = new JTextField(30);
		lHours.setLabelFor(tHours);
		mainPanel.add(lHours);
		mainPanel.add(tHours);
		
		// Text
		JLabel lText = new JLabel("Text:", JLabel.TRAILING);
		tText = new JTextField(30);
		lText.setLabelFor(tText);
		mainPanel.add(lText);
		mainPanel.add(tText);

		// Save
		JButton bAdd = new JButton("Add");
		bAdd.addActionListener(new AddActivity());
		mainPanel.add(bAdd);

		// Storno
		JButton bStorno = new JButton("Storno");
		bStorno.addActionListener(new Storno());
		mainPanel.add(bStorno);

		// List of activities
		this.listModel = new DefaultListModel<Activity>();
		activitiesList = new JList<Activity>(listModel);
		initializeActivites(sheet.getActivities());
		mainPanel.add(activitiesList);
		
		
		this.setTitle("Timesheets - Activities");
		this.setPreferredSize(new Dimension(350, 400));
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setContentPane(mainPanel);
		this.pack();
	}

	private class AddActivity implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Activity a = new Activity(
					tDate.getText(),
					Integer.valueOf(tHours.getText()),
					tText.getText()
			);
			listModel.addElement(a);
			sheet.getActivities().add(a);

			tDate.setText("");
			tHours.setText("");
			tText.setText("");
		}
	}

	private class Storno implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			ActivitiesForm.this.dispose();
			System.out.println("FOOO");
			//SaveTimesheetForm.this.dispose();
		}
	}
	
	private void initializeActivites(ArrayList<Activity> activities){
	    for(Activity a : activities){
	         listModel.addElement(a);
	    }    
	    activitiesList.setModel(listModel);     
	    activitiesList.setSelectedIndex(0);
	}

}

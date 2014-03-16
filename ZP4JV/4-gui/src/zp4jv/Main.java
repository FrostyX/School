package zp4jv;

import java.util.ArrayList;

public class Main {
	
	//public static DefaultListModel<Timesheet> listModel = new DefaultListModel<Timesheet>();
	//public static DefaultTreeModel treeModel = new DefaultTreeModel();
	
	@SuppressWarnings("serial")
	public static ArrayList<Timesheet> timesheets = new ArrayList<Timesheet>() {{
		add(new Timesheet("Jakub Kadlčík", "Developer", new ArrayList<Activity>() {{
			add(new Activity("12. 3. 2014", 8, "ZP4JV - Swing GUI - basics 1/2"));
			add(new Activity("13. 3. 2014", 9, "ZP4JV - Swing GUI - basics 2/2"));
			add(new Activity("15. 3. 2014", 7, "ZP4JV - Swing GUI - tables and trees"));
		}}));
		add(new Timesheet("Tony Stark", "Genius"));
		add(new Timesheet("Hulk", "Tank"));
		add(new Timesheet("Dr. House", "Healer"));
	}};
	

	public static void main(String[] args) {

		//MainForm form = new MainForm(listModel);
		MainForm form = new MainForm(timesheets);
		form.setVisible(true);
	}
}

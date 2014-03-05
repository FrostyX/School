package zp4jv;

import java.util.ArrayList;

public class Timesheet {
	private String name;
	private String occupation;
	private ArrayList<Activity> activities = new ArrayList<Activity>();

	public Timesheet() {
	}

	public Timesheet(String name, String occupation, ArrayList<Activity> activities) {
		this.name = name;
		this.occupation = occupation;
		this.activities = activities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public ArrayList<Activity> getActivities() {
		return activities;
	}

	public void setActivities(ArrayList<Activity> activities) {
		this.activities = activities;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(name + " (" + occupation + ")\n");
		for(Activity a : activities) {
			s.append("    --> " + a.getDate() + " - " + a.getHours() + " hours" + " - " + a.getText() + "\n");
		}
		return s.toString();
	}
}

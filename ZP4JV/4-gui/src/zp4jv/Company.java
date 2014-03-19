package zp4jv;

import java.util.ArrayList;

public class Company {
	private String name;
	private ArrayList<Timesheet> timesheets;

	public Company(String name, ArrayList<Timesheet> timesheets) {
		this.name = name;
		this.timesheets = timesheets;
	}
	
	public void remove(Timesheet sheet) {
		timesheets.remove(sheet);
	}
	
	public int indexOf(Timesheet sheet) {
		return timesheets.indexOf(sheet);
	}
	
	public Timesheet get(int index) {
		return timesheets.get(index);
	}
	
	public boolean contains(Timesheet sheet) {
		return timesheets.contains(sheet);
	}
	
	public void add(Timesheet sheet) {
		timesheets.add(sheet);
	}

	public String getName() {
		return name;
	}

	public ArrayList<Timesheet> getTimesheets() {
		return timesheets;
	}
	
	public String toString() {
		return name;
	}
}

package zp4jv;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {

	public static void main(String[] args) {
		try (Connection con = DriverManager.getConnection(DBConfig.host, DBConfig.user, DBConfig.password)) {		
			dbExample(con);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void dbExample(Connection con) throws Exception {
		// Initialize database managers
		TimesheetManager timesheetManager = new TimesheetManager(con);
		ActivityManager activityManager = new ActivityManager(con);
		
		// Clean everything
		timesheetManager.removeAll();
		activityManager.removeAll();
		
		// Insert timesheets
		Timesheet frostyx = timesheetManager.create("FrostyX", "Developer");
		Timesheet house = timesheetManager.create("Dr. House", "Healer");
		Timesheet stark = timesheetManager.create("Tony Stark", "Millionaire");
		
		// Update timesheets
		stark.setOccupation("IronMan");
		timesheetManager.commitChanges(stark);
		
		// Insert activities
		Activity bachelor = activityManager.create(frostyx.getId(), "22. 04. 2014", 5, "Work on bachelor thesis");
		activityManager.create(frostyx.getId(), "23. 04. 2014", 3, "Make Java homework");
		activityManager.create(house.getId(), "20. 04. 2013", 8, "Act in TV show");
		activityManager.create(house.getId(), "25. 05. 2013", 8, "Heal some people");
		activityManager.create(stark.getId(), "01. 03. 2010", 1, "Earn a bunch of money");
		
		// Update activities
		bachelor.setHours(6);
		activityManager.commitChanges(bachelor);
		
		// Print all
		for(Timesheet sheet : timesheetManager.getAll()) {
			System.out.print(sheet);
		}

		// Clean
		timesheetManager.close();
		activityManager.close();
	}
}

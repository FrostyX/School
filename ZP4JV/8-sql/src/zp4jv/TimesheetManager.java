package zp4jv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimesheetManager implements AutoCloseable {

	private Connection con = null;
	private PreparedStatement getAllStmt;
	private PreparedStatement getStmt;
	private PreparedStatement insertStmt;
	private PreparedStatement updateStmt;
	private PreparedStatement deleteStmt;
	private PreparedStatement deleteAllStmt;
	private PreparedStatement nextIDStmt;
	
	public TimesheetManager(Connection con) {
		this.con = con;
		try {
			getAllStmt = con.prepareStatement("SELECT * FROM timesheets");
			getStmt = con.prepareStatement("SELECT * FROM timesheets WHERE id = ?");
			insertStmt = con.prepareStatement("INSERT INTO timesheets (name, occupation) VALUES (?, ?)");
			updateStmt = con.prepareStatement("UPDATE timesheets SET name = ?, occupation = ? WHERE id = ?");
			deleteStmt = con.prepareStatement("DELETE FROM timesheets WHERE id = ?");
			deleteAllStmt = con.prepareStatement("DELETE FROM timesheets");
			nextIDStmt = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'timesheets' AND table_schema = DATABASE( ) ;");
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Timesheet get(int id) throws Exception {
		Timesheet sheet = null;
		getStmt.setInt(1, id);
		try (ResultSet results = getStmt.executeQuery()) {
			if (results.next())
				sheet = new Timesheet(
					results.getInt("id"),
					results.getString("name"),
					results.getString("occupation"),
					getActivities(id)
				);
		}
		return sheet;
	}
	
	public ArrayList<Timesheet> getAll() throws Exception {
		ArrayList<Timesheet> sheets = new ArrayList<Timesheet>();
			try (ResultSet results = getAllStmt.executeQuery()) {
				while (results.next())
					sheets.add(new Timesheet(
						results.getInt("id"),
						results.getString("name"),
						results.getString("occupation"),
						getActivities(results.getInt("id"))
					));
			}
		return sheets;
	}
	
	public Timesheet create(String name, String occupation) throws SQLException {
		int nextID = nextID();
		insertStmt.setString(1, name);
		insertStmt.setString(2, occupation);
		insertStmt.executeUpdate();
		return new Timesheet(nextID, name, occupation);
	}
	
	public void remove(Timesheet sheet) throws SQLException {
		deleteStmt.setInt(1, sheet.getId());
		deleteStmt.executeUpdate();
	}

	public void removeAll() throws SQLException {
		deleteAllStmt.executeUpdate();
	}
	
	public void commitChanges(Timesheet sheet) throws SQLException {
		updateStmt.setString(1, sheet.getName());
		updateStmt.setString(2, sheet.getOccupation());
		updateStmt.setInt(3, sheet.getId());
		updateStmt.executeUpdate();
	}

	@Override
	public void close() throws Exception {
		try {
			getAllStmt.close();
			getStmt.close();
			insertStmt.close();
			updateStmt.close();
			deleteStmt.close();
			deleteAllStmt.close();
			nextIDStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private int nextID() throws SQLException {
		int id = 0;
		try (ResultSet results = nextIDStmt.executeQuery()) {
			if (results.next()) 
				id = results.getInt(1);
		}
		return id;	
	}
	
	private ArrayList<Activity> getActivities(int idTimesheet) throws Exception {
		ActivityManager activityManager = new ActivityManager(con);
		ArrayList<Activity> activities = activityManager.getAll(idTimesheet);
		activityManager.close();
		return activities;
	}
}

package zp4jv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ActivityManager implements AutoCloseable {

	private PreparedStatement getAllStmt;
	private PreparedStatement getStmt;
	private PreparedStatement insertStmt;
	private PreparedStatement updateStmt;
	private PreparedStatement deleteStmt;
	private PreparedStatement deleteAllStmt;
	private PreparedStatement nextIDStmt;
	
	public ActivityManager(Connection con) {
		try {
			getAllStmt = con.prepareStatement("SELECT * FROM activities WHERE timesheet_id = ?");
			getStmt = con.prepareStatement("SELECT * FROM activities WHERE id = ?");
			insertStmt = con.prepareStatement("INSERT INTO activities (timesheet_id, date, hours, text) VALUES (?, ?, ?, ?)");
			updateStmt = con.prepareStatement("UPDATE activities SET date = ?, hours = ?, text = ? WHERE id = ?");
			deleteStmt = con.prepareStatement("DELETE FROM activities WHERE id = ?");
			deleteAllStmt = con.prepareStatement("DELETE FROM activities");
			nextIDStmt = con.prepareStatement("SELECT AUTO_INCREMENT FROM information_schema.tables WHERE table_name = 'activities' AND table_schema = DATABASE( ) ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Activity get(int id) throws SQLException {
		Activity activity = null;
		getStmt.setInt(1, id);
		try (ResultSet results = getStmt.executeQuery()) {
			if (results.next())
				activity = new Activity(
					results.getInt("id"),
					results.getString("date"),
					results.getInt("hours"),
					results.getString("text")
				);
		}
		return activity;
	}

	public ArrayList<Activity> getAll(int idTimesheet) throws SQLException {
		ArrayList<Activity> activities = new ArrayList<Activity>();
			getAllStmt.setInt(1, idTimesheet);
			try (ResultSet results = getAllStmt.executeQuery()) {
				while (results.next())
					activities.add(new Activity(
						results.getInt("id"),
						results.getInt("timesheet_id"),
						results.getString("date"),
						results.getInt("hours"),
						results.getString("text")
					));
			}
		return activities;
	}
	
	public Activity create(int idTimesheet, String date, int hours, String text) throws SQLException {
		int nextID = nextID();
		insertStmt.setInt(1, idTimesheet);
		insertStmt.setString(2, date);
		insertStmt.setInt(3, hours);
		insertStmt.setString(4, text);
		insertStmt.executeUpdate();
		return new Activity(nextID, idTimesheet, date, hours, text);
	}
	
	public void remove(Activity activity) throws SQLException {
		deleteStmt.setInt(1, activity.getId());
		deleteStmt.executeUpdate();
	}

	public void removeAll() throws SQLException {
		deleteAllStmt.executeUpdate();
	}
	
	public void commitChanges(Activity activity) throws SQLException {
		updateStmt.setString(1, activity.getDate());
		updateStmt.setInt(2, activity.getHours());
		updateStmt.setString(3, activity.getText());
		updateStmt.setInt(4, activity.getId());
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
}
package zp4jv;

public class Activity {
	private int id;
	private int idTimesheet;
	private String date;
	private int hours;
	private String text;

	public Activity() {
	}

	public Activity(int idTimesheet, String date, int hours, String text) {
		this.idTimesheet = idTimesheet;
		this.date = date;
		this.hours = hours;
		this.text = text;
	}

	public Activity(int id, int idTimesheet, String date, int hours, String text) {
		this.id = id;
		this.idTimesheet = idTimesheet;
		this.date = date;
		this.hours = hours;
		this.text = text;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdTimesheet() {
		return idTimesheet;
	}

	public void setIdTimesheet(int idTimesheet) {
		this.idTimesheet = idTimesheet;
	}
}

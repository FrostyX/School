package zp4jv;

public class Activity {
	private String date;
	private int hours;
	private String text;

	public Activity() {
	}

	public Activity(String date, int hours, String text) {
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

	@Override
	public String toString() {
		return "On " + date + " worked " + hours + " hours  -->  " + text;
	}
}

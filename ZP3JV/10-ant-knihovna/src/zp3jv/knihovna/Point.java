package zp3jv.knihovna;

import java.util.ArrayList;

public class Point implements GeometryObject {
	private double x;
	private double y;

	/**
	 * @param x
	 * @param y
	 */
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(ArrayList<Integer> l) {
		this.x = l.get(0);
		this.y = l.get(1);
	}

	public double distance(Point p) {
		return Math.sqrt(Math.pow(p.x - this.x, 2) + Math.pow(p.y - this.y, 2));
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(double y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return String.format("Point: [%s, %s]", x, y);
	}
}

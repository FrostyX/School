package zp3jv.knihovna;

import java.util.ArrayList;

public class Vector implements GeometryObject {
	private double x;
	private double y;

	/**
	 * @param x
	 * @param y
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector(ArrayList<Integer> l) {
		this.x = l.get(0);
		this.y = l.get(1);
	}

	/**
	 * @param a
	 * @param b
	 */
	public Vector(Point a, Point b) {
		this.x = b.getX() - a.getX();
		this.y = b.getY() - a.getY();
	}

	public Vector getNormalVector() {
		double x = this.y;
		double y = this.x;
		if (x < 0)
			x *= -1;
		else
			y *= -1;

		return new Vector(x, y);
	}

	/**
	 * @return the x
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public double getY() {
		return y;
	}

	@Override
	public String toString() {
		return String.format("Vector: [%s,  %s]", x, y); 
	}
}

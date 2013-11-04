package zp3jv;

public class Vector {
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
}

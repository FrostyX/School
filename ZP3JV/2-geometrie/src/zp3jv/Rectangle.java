package zp3jv;

public class Rectangle {
	protected Point a;
	protected Point b;
	protected Point c;
	protected Point d;
	protected double area;

	/**
	 * @param a
	 * @param c
	 */
	public Rectangle(Point a, Point c) {
		this.a = a;
		this.c = c;
		this.b = new Point(c.getX(), a.getY());
		this.d = new Point(a.getX(), c.getY());
		this.setArea();
	}

	/**
	 * @param a
	 * @param width
	 * @param height
	 */
	public Rectangle(Point a, double width, double height) {
		this.a = a;
		this.b = new Point(a.getX() + width, a.getY());
		this.c = new Point(a.getX() + width, a.getY() + height);
		this.d = new Point(a.getX(), a.getY() + height);
		this.setArea();
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	private void setArea() {
		this.area = this.a.distance(this.b) * this.b.distance(this.c);
	}

	public double distance(Point p) {
		double[] distances = { new Line(this.a, this.b).distance(p),
				new Line(this.b, this.c).distance(p),
				new Line(this.c, this.d).distance(p),
				new Line(this.d, this.a).distance(p), };
		double min = distances[0];
		for (int i = 1; i < distances.length; i++) {
			if (min > distances[i])
				min = distances[i];
		}
		return min;
	}
}

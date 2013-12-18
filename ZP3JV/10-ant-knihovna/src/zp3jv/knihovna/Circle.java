package zp3jv.knihovna;

import java.util.ArrayList;

public class Circle implements GeometryObject {
	private Point center;
	private double radius;
	private double area;

	/**
	 * @param center
	 * @param radius
	 */
	public Circle(Point center, double radius) {
		this.center = center;
		this.radius = radius;
		this.setArea();
	}
	
	public Circle(ArrayList<Integer> l) {
		this.center = new Point(l.get(0), l.get(1));
		this.radius = l.get(2);
		this.setArea();
	}

	public double distance(Point a) {
		// http://forum.matematika.cz/viewtopic.php?id=16456
		Line as = new Line(a, this.center);
		double cr = Math.pow(this.radius, 2) / as.getLength();
		return Math.abs(as.getLength() - cr);
	}

	/**
	 * @return the area
	 */
	public double getArea() {
		return area;
	}

	public void setArea() {
		// Kružnice nemá obsah, ale řekněme že jste chtěl obsah kruhu
		this.area = Math.PI * Math.pow(this.radius, 2);
	}

	@Override
	public String toString() {
		return String.format("Circle: S = %s; r = %s", center.toString(), radius);
	}
}

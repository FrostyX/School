package zp3jv.knihovna;

import java.util.ArrayList;

public class Line implements GeometryObject {
	private Point a;
	private Point b;
	private double length;

	/**
	 * @param a
	 * @param b
	 */
	public Line(Point a, Point b) {
		this.a = a;
		this.b = b;
		this.length = a.distance(b);
	}
	
	public Line(ArrayList<Integer> l) {
		this.a = new Point(l.get(0), l.get(1));
		this.b = new Point(l.get(2), l.get(3));
		this.length = a.distance(b);
	}

	/**
	 * @param p
	 */
	public double distance(Point p) {
		// http://www.e-matematika.cz/stredni-skoly/jak-urcit-obecnou-rovnici-primky-urcene-dvema-body.php
		// http://cs.wikipedia.org/wiki/Vzd%C3%A1lenost_bodu_od_p%C5%99%C3%ADmky
		Vector u = new Vector(this.a, this.b);
		Vector v = u.getNormalVector();
		double c = v.getX() * this.b.getX() * (-1) + v.getY() * this.b.getY() * (-1);
		double distance = 0;

		// Obecně nemůžeme říct, že vždy platí: Ax < Bx
		double minX = this.a.getX() > this.b.getX() ? this.b.getX() : this.a.getX();
		double maxX = this.a.getX() > this.b.getX() ? this.a.getX() : this.b.getX();

		// Pokud se bod p na ose x nachází mezi krajními body úsečky
		if ((p.getX() >= minX) && (p.getX() <= maxX))
			// Vzoreček pro vzdálenost bodu od přímky
			distance = Math.abs(v.getX() * p.getX() + v.getY() * p.getY() + c)
					/ Math.sqrt(Math.pow(v.getX(), 2) + Math.pow(v.getY(), 2));
		else
			// Vzdálenost k nejbližšímu hraničnímu bodu
			distance = p.distance(this.a) > p.distance(this.b) ? p.distance(this.b) : p.distance(a);

		return distance;
	}

	/**
	 * @return the a
	 */
	public Point getA() {
		return a;
	}

	/**
	 * @param a the a to set
	 */
	public void setA(Point a) {
		this.a = a;
	}

	/**
	 * @return the b
	 */
	public Point getB() {
		return b;
	}

	/**
	 * @param b the b to set
	 */
	public void setB(Point b) {
		this.b = b;
	}

	/**
	 * @return the length
	 */
	public double getLength() {
		return length;
	}

	@Override
	public String toString() {
		return String.format("Line: A = %s; B = %s", a.toString(), b.toString());
	}
}

package zp3jv;

public class Main {

	public static void main(String[] args) {
		Point p = new Point(0, 0);
		Line ab = new Line(new Point(1, 1), new Point(4, 4));
		Rectangle abcd = new Rectangle(new Point(2, 2), new Point(4, 5));
		Square klmn = new Square(new Point(2, 2), 4);
		Circle k = new Circle(p, 6);

		System.out.println(p.distance(new Point(0, 3)));
		System.out.println(ab.distance(new Point(0, 3)));
		System.out.println(abcd.distance(new Point(3, 3)));
		System.out.println(klmn.distance(new Point(3, 3)));
		System.out.println(k.distance(new Point(5, 0)));
	}

}

package zp3jv.app;

import java.util.ArrayList;
import java.util.Scanner;

import zp3jv.knihovna.*;

public class Main {

	private static ArrayList<GeometryObject> objects = new ArrayList<GeometryObject>();

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.print("[a/s/q] >> ");
			String operation = scan.next();
			
			if (operation.equals("a")) {
				System.out.println("Podporované objekty: Point | Line | Circle | Rectangle | Square | Vector");
				System.out.print(">> ");

				String object = scan.next();
				if(object.equals("Point")) { readPoint(); }
				else if(object.equals("Line")) { readLine(); }
				else if(object.equals("Circle")) { readCircle(); }
				else if(object.equals("Rectangle")) { readRectangle(); }
				else if(object.equals("Square")) { readSquare(); }
				else if(object.equals("Vector")) { readVector(); }
				else { System.out.println("Nepodporovaný objekt"); }
			}
			else if (operation.equals("s")) { closest(); }
			else if (operation.equals("q")) { break; }
			else { System.out.println("FAIL"); }
		}
		System.out.println("Bye :-)");
	}
	
	public static double distance(Object obj, Point p) { 
		if (obj instanceof Line) { 
			return ((Line) obj).distance(p); 
		} else if (obj instanceof Point) { 
			return ((Point) obj).distance(p); 
		} else if (obj instanceof Circle) { 
			return ((Circle) obj).distance(p); 
		} else if (obj instanceof Square) { 
			return ((Square) obj).distance(p); 
		} else if (obj instanceof Rectangle) { 
			return ((Rectangle) obj).distance(p); 
		} 
		return -1;
	} 
	
	private static void closest() {
		Scanner scan = new Scanner(System.in);
		System.out.print("X >> ");
		int x = scan.nextInt();
		System.out.print("Y >> ");
		int y = scan.nextInt();
		Point p = new Point(x, y);

		GeometryObject closest = objects.get(0);
		for(GeometryObject obj : objects) {
			if(distance(obj, p) < distance(closest, p)) {
				closest = obj;
			}
		}
		System.out.println(closest.toString());	
	}
	
	private static void readPoint() {
		Scanner scan = new Scanner(System.in);
		System.out.print("X >> ");
		int x = scan.nextInt();
		System.out.print("Y >> ");
		int y = scan.nextInt();
		objects.add(new Point(x, y));
	}
	
	private static void readLine() {
		Scanner scan = new Scanner(System.in);
		System.out.print("X1 >> ");
		int x1 = scan.nextInt();
		System.out.print("Y1 >> ");
		int y1 = scan.nextInt();

		System.out.print("X2 >> ");
		int x2 = scan.nextInt();
		System.out.print("Y2 >> ");
		int y2 = scan.nextInt();
		objects.add(new Line(new Point(x1, y1), new Point(x2, y2)));
	}

	private static void readCircle() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Sx >> ");
		int x = scan.nextInt();
		System.out.print("Sy >> ");
		int y = scan.nextInt();
		System.out.print("r >> ");
		int r = scan.nextInt();
		objects.add(new Circle(new Point(x, y), r));
	}

	private static void readRectangle() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Ax >> ");
		int x1 = scan.nextInt();
		System.out.print("Ay >> ");
		int y1 = scan.nextInt();

		System.out.print("Cx >> ");
		int x2 = scan.nextInt();
		System.out.print("Cy >> ");
		int y2 = scan.nextInt();
		objects.add(new Rectangle(new Point(x1, y1), new Point(x2, y2)));
	}

	private static void readSquare() {
		Scanner scan = new Scanner(System.in);
		System.out.print("Ax >> ");
		int x = scan.nextInt();
		System.out.print("Ay >> ");
		int y = scan.nextInt();
		System.out.print("a >> ");
		int a = scan.nextInt();
		objects.add(new Square(new Point(x, y), a));
	}
	
	private static void readVector() {
		Scanner scan = new Scanner(System.in);
		System.out.print("X >> ");
		int x = scan.nextInt();
		System.out.print("Y >> ");
		int y = scan.nextInt();
		objects.add(new Vector(x, y));
	}
}

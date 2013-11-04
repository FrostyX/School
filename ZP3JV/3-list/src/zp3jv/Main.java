package zp3jv;

import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		/*
		 * 1
		 */
		int[] a = new int[] { 1, 2, 3, 4, 5, 6 };
		for (int n : AwesomeArray.odd(a))
			System.out.print(n + ", ");
		System.out.println();

		/*
		 * 2
		 */
		List<Integer> b = Arrays.asList(1, 2, 3, 4, 5, 6);
		for (int n : AwesomeList.odd(b))
			System.out.print(n + ", ");
		System.out.println();

		/*
		 * 3
		 */
		List<Point> points = Arrays.asList(
			new Point(-2, -2),
			new Point(1, 1),
			new Point(3, 2)
		);
		Point nearestPoint = AwesomeList.nearest(new Point(0, 0), points);
		System.out.format("Nearest: [%.1f; %.1f]%n", nearestPoint.getX(), nearestPoint.getY());

		/*
		 * 4
		 */
		int[] x = new int[] { 2, 16, 32, 33, 72 };
		int[] y = new int[] { 0, 10, 55, 62, 64 };
		for (int n : AwesomeArray.merge(x, y))
			System.out.print(n + ", ");
		System.out.println();

		List<Integer> k = Arrays.asList(2, 16, 32, 33, 72);
		List<Integer> l = Arrays.asList(0, 10, 55, 62, 64);
		for (int n : AwesomeList.merge(k, l))
			System.out.print(n + ", ");
		System.out.println();

		/*
		 * 5
		 */
		for (int n : AwesomeList.mergeSort(Arrays.asList(29, 1, 46, 23, 13, 17, 1, 33)))
			System.out.print(n + ", ");
		System.out.println();
	}
}

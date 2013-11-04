package zp3jv;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

public class Main {

	public static void main(String[] args) {

		/*
		 * 1
		 */
		List<Integer> b = Arrays.asList(1, 2, 3, 4, 5, 6);
		for (int n : AwesomeList.odd(b))
			System.out.print(n + ", ");
		System.out.println();

		/*
		 * 2
		 */
		for (int n : AwesomeList.mergeSort(Arrays.asList(29, 1, 46, 23, 13, 17,
				1, 33)))
			System.out.print(n + ", ");
		System.out.println();

		/*
		 * 3
		 */
		Set<Integer> set1 = new HashSet<Integer>();
		Set<Integer> set2 = new HashSet<Integer>();
		for (int i = 1; i <= 20; i++) {
			if (i % 3 == 0)
				set2.add(i);
			set1.add(i);
		}

		for (int n : AwesomeList.intersect(set1, set2)) {
			System.out.print(n + ", ");
		}
		System.out.println();

		/*
		 * 4, 5, 6
		 */
		Set<Point> points = new HashSet<Point>();
		points.add(new Point(1, 1));
		points.add(new Point(2, 3));
		points.add(new Point(5, 2));
		points.add(new Point(1, 1));

		for (Point p : points) {
			System.out.println("[" + p.getX() + ", " + p.getY() + "]");
		}
	}
}

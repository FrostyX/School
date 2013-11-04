package zp3jv;

import java.util.List;
import java.util.ArrayList;

public class AwesomeList {
	static List<Integer> odd(List<Integer> numbers) {
		ArrayList<Integer> oddNumbers = new ArrayList<Integer>();
		for (int n : numbers)
			if (n % 2 != 0)
				oddNumbers.add(n);
		return oddNumbers;
	}

	static Point nearest(Point p, List<Point> points) {
		Point nearestPoint = points.get(0);
		for (Point point : points) {
			if (point.distance(p) < nearestPoint.distance(p))
				nearestPoint = point;
		}
		return new Point(1, 1);
	}

	static List<Integer> merge(List<Integer> a, List<Integer> b) {
		ArrayList<Integer> merged = new ArrayList<Integer>();
		int n = a.size() + b.size();

		for (int i = 0, ai = 0, bi = 0; i < n; i++) {
			if ((bi >= b.size())
					|| ((ai < a.size()) && (a.get(ai) < b.get(bi)))) {
				merged.add(a.get(ai));
				ai++;
			} else {
				merged.add(b.get(bi));
				bi++;
			}
		}
		return merged;
	}

	static List<Integer> mergeSort(List<Integer> list) {
		if (list.size() > 1) {
			int q = list.size() / 2;
			ArrayList<Integer> l = new ArrayList<Integer>(list.subList(0, q));
			ArrayList<Integer> r = new ArrayList<Integer>(list.subList(q, list.size()));

			return merge(mergeSort(l), mergeSort(r));
		}
		return list;
	}
}

package zp3jv;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class AwesomeList {
	static List<Integer> odd(List<Integer> numbers) {
		ArrayList<Integer> oddNumbers = new ArrayList<Integer>();
		for (int n : numbers)
			if (n % 2 != 0)
				oddNumbers.add(n);
		return oddNumbers;
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

	static Set<Integer> intersect(Set<Integer> set1, Set<Integer> set2) {
		Set<Integer> set = new HashSet<Integer>();
		for (int n : set1) {
			if (set2.contains(n))
				set.add(n);
		}
		return set;
	}
}

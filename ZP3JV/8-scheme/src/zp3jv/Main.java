package zp3jv;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

	public static void main(String[] args) {

		ArrayList<Object> l = new ArrayList<Object>();
		l.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));

		/*
		 * 1
		 */
		List<Object> facts = Scheme.map(l, new Mapping() {
			@Override
			public Object map(Object o) {
				int fact = 1;
				for (int i = 1; i <= (Integer) o; i++)
					fact *= i;

				return fact;
			}
		});

		for(Object o : facts) {
			System.out.println(o);
		}

		System.out.println("-----");

		/*
		 * 2
		 */
		List<Object> even1 = Scheme.filter(l, new Condition() {
			@Override
			public boolean condition(Object o) {
				return (Integer) o % 2 == 0;
			}
		});

		for(Object o : even1) {
			System.out.println(o);
		}

		System.out.println("-----");

		for(Object o : Scheme.even(l)) {
			System.out.println(o);
		}
	}
}

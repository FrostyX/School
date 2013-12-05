package zp3jv;

import java.util.ArrayList;

public class Scheme {
	public static ArrayList<Object> map(ArrayList<Object> list, Mapping m) {
		ArrayList<Object> l = new ArrayList<Object>();
		for(Object o : list) {
			l.add(m.map(o));
		}
		return l;
	}

	public static ArrayList<Object> filter(ArrayList<Object> list, Condition c) {
		ArrayList<Object> l = new ArrayList<Object>();
		for(Object o : list) {
			if(c.condition(o))
				l.add(o);
		}
		return l;
	}

	public static ArrayList<Object> even(ArrayList<Object> list) {
		return filter(list, new Even());
	}

	private static class Even implements Condition {
		@Override
		public boolean condition(Object o) {
			return (Integer) o % 2 == 0;
		}
	}
}

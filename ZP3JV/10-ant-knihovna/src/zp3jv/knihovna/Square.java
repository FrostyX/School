package zp3jv.knihovna;

import java.util.ArrayList;

public class Square extends Rectangle {

	public Square(Point a, double edge) {
		super(a, edge, edge);
	}

	public Square(ArrayList<Integer> l) {
		super(l);
	}

	@Override
	public String toString() {
		return String.format(
				"Square A = %s; B = %s; C = %s; D = %s",
				a.toString(),
				b.toString(),
				c.toString(),
				d.toString()
		);
	}
}

package zp3jv;

public class Diff {

	public static final String ADD_SYMBOL = ">";
	public static final String DEL_SYMBOL = "<";
	public static final String KEEP_SYMBOL = " ";

	public static DiffFile compare(File x, File y) {
		return diff(lcs(x, y), x, y, x.getContent().size(), y.getContent().size());
	}

	protected static DiffFile diff(int[][] c, File x, File y, int i, int j) {
		DiffFile d = new DiffFile();

		if((i>0) && (j>0) && (x.getContent().get(i-1).equals(y.getContent().get(j-1)))) {
			d.add(diff(c, x, y, i-1, j-1));
			d.add(new DiffLine(i, KEEP_SYMBOL, x.getContent().get(i-1)));
		}
		else {
			if ((j>0) && ((i==0) || c[i][j-1] >= c[i-1][j])) {
				d.add(diff(c, x, y, i, j-1));
				d.add(new DiffLine(j, ADD_SYMBOL, y.getContent().get(j-1)));
			}
			else if ((i>0) && ((j==0) || c[i][j-1] < c[i-1][j])) {
				d.add(diff(c, x, y, i-1, j));
				d.add(new DiffLine(i, DEL_SYMBOL, x.getContent().get(i-1)));
			}
		}
		return d;
	}

	public static int[][] lcs(File x, File y) {
		int m = x.getContent().size();
		int n = y.getContent().size();

		int[][] c = new int[m + 1][];
                for(int i = 0; i < c.length; i++) {
			c[i] = new int[n + 1];
                }

		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				c[i][j] = x.getContent().get(i-1).equals(y.getContent().get(j-1)) ?
					c[i-1][j-1] + 1 :
					Math.max(c[i][j-1], c[i-1][j]);
			}
		}
		return c;
	}
}


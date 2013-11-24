package zp3jv;

public class Diff {

	public static DiffFile compare(File x, File y) {
		return diff(lcs(x, y), x, y, x.getContent().size(), y.getContent().size());
	}

	protected static DiffFile diff(int[][] c, File x, File y, int i, int j) {
		if((i>0) && (j>0) && (x.getContent().get(i-1).equals(y.getContent().get(j-1)))) {
			diff(c, x, y, i-1, j-1);
			System.out.println("  " + x.getContent().get(i-1));
		}
		else {
			if ((j>0) && ((i==0) || c[i][j-1] >= c[i-1][j])) {
				diff(c, x, y, i, j-1);
				System.out.println("+ " + y.getContent().get(j-1));
			}
			else if ((i>0) && ((j==0) || c[i][j-1] < c[i-1][j])) {
				diff(c, x, y, i-1, j);
				System.out.println("- " + x.getContent().get(i-1));
			}
		}
		return null;
	}

	/*
	public static int[][] lcs(File x, File y) {
		int m = x.getContent().size();
		int n = y.getContent().size();

		int[][] c = new int[m + 1][];
		for(int i = 0; i < c.length; i++) {
			c[i] = new int[n+1];
			//System.out.println(n);
                }

		System.out.println(c.length);

		for(int i = 1; i < c.length; i++) {
			System.out.println(c[i].length);
			for(int j = 1; j < c[i].length; j++) {
			//System.out.println("FOO");
				if(x.getContent().get(i).equals(y.getContent().get(j))) {
				//if(isXYEqual(i, j)) {
					c[i][j] = c[i-1][j-1] + 1;
				} else {
					c[i][j] = Math.max(c[i][j-1], c[i-1][j]);
				}
			}
		}

		for(int[] k : c) {
			for(int l : k)
				System.out.println(l);
		}

		return c;
	}
	*/

	public static int[][] lcs(File x, File y) {
		int m = x.getContent().size();
		int n = y.getContent().size();
		//System.out.println(n);

		int[][] c = new int[m + 1][];
                for(int i = 0; i < c.length; i++) {
			c[i] = new int[n + 1];
			//for(int f : c[i])
				//System.out.println(f);
			//System.out.println(i);
                }

		//for(int[] k : c) {
			//for(int l : k)
				//System.out.println(l);
		//}


		for (int i = 1; i < m + 1; i++) {
			for (int j = 1; j < n + 1; j++) {
				if(x.getContent().get(i-1).equals(y.getContent().get(j-1))) {
					c[i][j] = c[i-1][j-1] + 1;
				} else {
					c[i][j] = Math.max(c[i][j-1], c[i-1][j]);
				}
				/*
				c[i][j] = x.getContent().get(i - 1) == y.getContent().get(j - 1) ?
					c[i-1][j-1] + 1 :
					Math.max(c[i][j-1], c[i-1][j])
				;
				*/
			}
		}

		//for(int[] k : c) {
			//for(int l : k)
				//System.out.println(l);
		//}
		return c;
	}

	/*
	public static String backTrack(int[][] c, File x, File y, int i, int j) {
		if((i == 0) || (j== 0))
			return "";
		else if (x.getContent().get(i-1) == y.getContent().get(j-1))
			return backTrack(c, x, y, i-1, j-1) + x.getContent().get(i-1);
		else
			return c[i][j-1] > c[i-1][j] ?
				backTrack(c, x, y, i, j-1) :
				backTrack(c, x, y, i-1, j)
			;
	}
	*/

	/*
	public static String[] backTrackAll(int c, File x, File y, int i, int j) {
		if((i == 0) || (j== 0))
			return new String[] {""};
		else if (x.getContent().get(i-1) == y.getContent().get(j-1))

		return "";
	}
	*/
}


package zp3jv;

/*
 * Implementace algoritmu "An-O(ND)-Difference-Algorithm" popsaného v PDF v kořenovém adresáři projektu
 * https://github.com/FrostyX/School/blob/master/ZP3JV/diff/An-O(ND)-Difference-Algorithm-and-Its-Variations.pdf
 * @author Jakub Kadlčík
 * @version 1.0
 */
public class Diff {

	public static final String ADD_SYMBOL = ">";
	public static final String DEL_SYMBOL = "<";
	public static final String KEEP_SYMBOL = " ";

	/**
	  * Porovná dva textové soubory a vrátí sadu řádků a jejich potřebných úprav pro vytvoření souboru Y ze souboru X
	  * @param x - Původní textový soubor
	  * @param y - Nový textový soubor
	  * @return Sada změn pro vytvoření nového souboru z původního
	  */
	public static DiffFile compare(File x, File y) {
		return diff(lcs(x, y), x, y, x.getContent().size(), y.getContent().size());
	}

	/**
	  * Na základě algoritmu "An-O(ND)-Difference-Algorithm" od E. W. MYERSe porovná dva textové soubory
	  * @param c - Nejdelší společná subsekvence
	  * @param x - Původní textový soubor
	  * @param y - Nový textový soubor
	  * @param i - WTF
	  * @param j - WTF
	  * @return Sada změn pro vytvoření nového souboru z původního
	  */
	private static DiffFile diff(int[][] c, File x, File y, int i, int j) {
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

	/**
	  * Řeší problém "the longest common subsequence" - tedy nalezení nejdelší společné subsekvence
	  * @param x - První soubor
	  * @param y - Druhý soubor
	  * @return WTF
	  */
	private static int[][] lcs(File x, File y) {
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


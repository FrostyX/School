using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	public class Diff
	{
		public static readonly string ADD_SYMBOL = ">";
		public static readonly string DEL_SYMBOL = "<";
		public static readonly string KEEP_SYMBOL = " ";


		/// <summary>Porovná dva textové soubory a vrátí sadu řádků a jejich potřebných úprav pro vytvoření souboru Y ze souboru X</summary>
		/// <param name="x">Původní textový soubor</param>
		/// <param name="y">Nový textový soubor</param>
		/// <returns>Sada změn pro vytvoření nového souboru z původního</returns>
		public static DiffFile compare(File x, File y)
		{
			return diff(lcs(x, y), x, y, x.Content.Count, y.Content.Count);
		}

		/// <summary>Na základě algoritmu "An-O(ND)-Difference-Algorithm" od E. W. MYERSe porovná dva textové soubory</summary>
		/// <param name="c">Nejdelší společná subsekvence</param>
		/// <param name="x">Původní textový soubor</param>
		/// <param name="y">Nový textový soubor</param>
		/// <param name="i"></param>
		/// <param name="j"></param>
		/// <returns>Sada změn pro vytvoření nového souboru z původního</returns>
		private static DiffFile diff(int[][] c, File x, File y, int i, int j)
		{
			DiffFile d = new DiffFile();

			if ((i > 0) && (j > 0) && (x.Content[i - 1].Equals(y.Content[j - 1])))
			{
				d.add(diff(c, x, y, i - 1, j - 1));
				d.add(new DiffLine(i, KEEP_SYMBOL, x.Content[i - 1]));
			}
			else
			{
				if ((j > 0) && ((i == 0) || c[i][j - 1] >= c[i - 1][j]))
				{
					d.add(diff(c, x, y, i, j - 1));
					d.add(new DiffLine(j, ADD_SYMBOL, y.Content[j - 1]));
				}
				else if ((i > 0) && ((j == 0) || c[i][j - 1] < c[i - 1][j]))
				{
					d.add(diff(c, x, y, i - 1, j));
					d.add(new DiffLine(i, DEL_SYMBOL, x.Content[i - 1]));
				}
			}
			return d;
		}

		/// <summary>Řeší problém "the longest common subsequence" - tedy nalezení nejdelší společné subsekvence</summary>
		/// <param name="x">První soubor</param>
		/// <param name="y">Druhý soubor</param>
		/// <returns>Už jen bůh ví, co přesně se vrátí</returns>
		private static int[][] lcs(File x, File y)
		{
			int m = x.Content.Count;
			int n = y.Content.Count;

			int[][] c = new int[m + 1][];
			for (int i = 0; i < c.Length; i++)
			{
				c[i] = new int[n + 1];
			}

			for (int i = 1; i < m + 1; i++)
			{
				for (int j = 1; j < n + 1; j++)
				{
					c[i][j] = x.Content[i - 1].Equals(y.Content[j - 1]) ?
						c[i - 1][j - 1] + 1 :
						Math.Max(c[i][j - 1], c[i - 1][j]);
				}
			}
			return c;
		}
	}
}

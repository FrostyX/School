using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

namespace _9_mnozina
{
	class Mnozina
	{
		private ArrayList seznam = null;

		public Mnozina(ArrayList seznam = null)
		{
			if (seznam == null)
				seznam = new ArrayList();
			this.seznam = seznam;
		}

		public static Mnozina operator >> (Mnozina m, int c)
		{
			m.seznam.Remove(c);
			return m;
		}

		public static Mnozina operator << (Mnozina m, int c)
		{
			m.seznam.Add(c);
			return m;
		}

		public static bool operator ! (Mnozina m)
		{
			return m.seznam.Count <= 0;
		}

		public static int operator + (Mnozina m)
		{
			return m.seznam.Count;
		}

		public static Mnozina operator + (Mnozina a, Mnozina b)
		{
			ArrayList c = new ArrayList();
			c.AddRange(a.seznam);
			c.AddRange(b.seznam);
			return new Mnozina(c);
		}

		public static Mnozina operator - (Mnozina a, Mnozina b)
		{
			ArrayList rozdil = new ArrayList();
			rozdil.AddRange(a.seznam);
			foreach(int x in b.seznam)
			{
				rozdil.Remove(x);
			}
			return new Mnozina(rozdil);
		}

		public override string ToString()
		{
			string s = "[ ";
			foreach (int x in this.seznam)
				s += x + " ";
			s += "]";
			return s;
		}
	}
}

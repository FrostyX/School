using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

namespace _8_osoba
{
	class Seznam
	{
		private ArrayList seznam = new ArrayList();

		public void add(Osoba o)
		{
			seznam.Add(o);
		}

		public Osoba this[string s]
		{
			get 
			{
				foreach (Osoba o in seznam)
				{
					if ((o.Jmeno + "-" + o.Prijmeni) == s)
						return o;
				}
				return null;
			}
		}

		public override string ToString()
		{
			string s = "";
			foreach (Osoba o in seznam)
				s += o + "\n";
			return s;
		}
	}
}

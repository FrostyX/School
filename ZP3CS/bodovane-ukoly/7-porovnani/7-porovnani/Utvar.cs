using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _7_porovnani
{
	abstract class Utvar : IPorovnani
	{
		public int VetsiObvod(IPorovnani utvar)
		{
			Utvar u = utvar as Utvar;
			if (obvod() < u.obvod()) return -1;
			if (obvod() > u.obvod()) return +1;
			else return 0;
		}

		public int VetsiObsah(IPorovnani utvar)
		{
			Utvar u = utvar as Utvar;
			if (obvod() < u.obvod()) return -1;
			if (obvod() > u.obvod()) return +1;
			else return 0;
		}

		public virtual double obvod() { return 0; }
		public virtual double obsah() { return 0; }
		public virtual string ToString() { return ""; }
	}
}

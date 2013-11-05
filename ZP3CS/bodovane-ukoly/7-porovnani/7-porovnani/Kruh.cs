using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _7_porovnani
{
	class Kruh : Utvar
	{
		double r;

		public Kruh(double r)
		{
			this.r = r;
		}

		public override double obvod()
		{
			return 2 * Math.PI * r;
		}

		public override double obsah()
		{
			return Math.PI * r * r;
		}

		override public string ToString()
		{
			return "Útvar je kruh(" + r + ") , ID: i, obvod je: " + Math.Round(obvod()) + " , obsah je: " + Math.Round(obsah());
		}
	}
}

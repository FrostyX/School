using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _7_porovnani
{
	class Obdelnik : Utvar
	{
		double a;
		double b;

		public Obdelnik(double a, double b)
		{
			this.a = a;
			this.b = b;
		}

		public override double obvod()
		{
			return (2*a) + (2*b);
		}

		public override double obsah()
		{
			return a * b;
		}

		override public string ToString()
		{
			return "Útvar je obdélník(" + a + "x" + b + ") , ID: i, obvod je: " + Math.Round(obvod()) + " , obsah je: " + Math.Round(obsah());
		}
	}
}

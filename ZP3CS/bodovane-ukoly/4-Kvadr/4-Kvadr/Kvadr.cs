using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _4_Kvadr
{
	class Kvadr
	{
		public double a;
		public double b;
		public double c;

		public Kvadr(double a=1, double b=1, double c=1)
		{
			this.a = a;
			this.b = b;
			this.c = c;
		}

		public double vratObjem()
		{
			return a*b*c;
		}

		public double vratPovrch()
		{
			return 2*(a*b + b*c + a*c);
		}

	}
}

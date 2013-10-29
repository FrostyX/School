using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _6_dedicnost_osob
{
	class Student : Student_skoly
	{
		protected string obor;

		public Student(string jmeno, string rc, string skola, string obor)
			: base(jmeno, rc, skola)
		{
			this.obor = obor;
		}

		public override void vypis()
		{
			base.vypis();
			Console.WriteLine("Obor: " + obor);
		}
	}
}

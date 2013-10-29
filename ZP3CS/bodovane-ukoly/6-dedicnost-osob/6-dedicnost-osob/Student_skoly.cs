using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _6_dedicnost_osob
{
	class Student_skoly : Osoba
	{
		public string skola;

		public Student_skoly(string jmeno, string rc, string skola) : base(jmeno, rc)
		{
			this.skola = skola;
		}

		public void nastav_skolu(string skola)
		{
			this.skola = skola;
		}

		public override void vypis()
		{
			base.vypis();
			Console.WriteLine("Skola: " + skola);
		}
	}
}

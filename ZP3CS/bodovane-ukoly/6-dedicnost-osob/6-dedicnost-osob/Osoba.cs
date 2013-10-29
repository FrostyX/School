using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _6_dedicnost_osob
{
	class Osoba
	{
		protected string jmeno;
		protected string rc;

		public Osoba(string jmeno, string rc)
		{
			this.jmeno = jmeno;
			this.rc = rc;
		}

		public string pohlavi()
		{
			return rc[2]-'0' > 1 ? "Žena" : "Muž";
		}

		public void zmen_jmeno(string jmeno)
		{
			this.jmeno = jmeno;
		}

		public void zmen_rc(string rc)
		{
			this.rc = rc;
		}

		public virtual void vypis()
		{
			Console.WriteLine("Jmeno: " + jmeno);
			Console.WriteLine("RČ: " + rc);
			Console.WriteLine("Pohlaví: " + pohlavi());
		}
	}
}

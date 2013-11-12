using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _8_osoba
{
	class Osoba
	{

		private string jmeno;
		private string prijmeni;
		private string adresa;
		private int vek;

		public Osoba(string jmeno, string prijmeni, string adresa, int vek)
		{
			this.jmeno = jmeno;
			this.prijmeni = prijmeni;
			this.adresa = adresa;
			this.vek = vek;
		}

		public override string ToString()
		{
			return jmeno + " " + prijmeni + " : " + adresa + ", " + vek;
		}

		public string Jmeno 
		{
			get { return jmeno; }
		}
		public string Prijmeni 
		{
			get { return prijmeni; }
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _8_osoba
{
	class Program
	{
		static void Main(string[] args)
		{
			int n = 5;

			Seznam s = new Seznam();
			for (int i = 0; i < n; i++)
			{
				s.add(new Osoba("Jmeno"+i, "Prijmeni"+i, "Adresa"+i, 10+i));
			}

			Console.WriteLine("Nalezen: " + s["Jmeno3-Prijmeni3"]);
			Console.WriteLine();
			Console.WriteLine(s);

			Console.ReadKey();
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _7_porovnani
{
	class Program
	{
		static void Main(string[] args)
		{
			Utvary utvary = new Utvary();
			utvary.utvary.Add(new Obdelnik(3, 2));
			utvary.utvary.Add(new Kruh(4));
			utvary.utvary.Add(new Kruh(2));
			utvary.utvary.Add(new Obdelnik(4, 3));
			utvary.utvary.Add(new Kruh(3));

			Console.WriteLine("Celkový obvod útvarů je: {0}", Math.Round(utvary.celkovyObvod()));
			Console.WriteLine("Celkový obsah útvarů je: {0}", Math.Round(utvary.celkovyObsah()));
			Console.WriteLine("Největší obvod útvaru je: {0}", Math.Round(utvary.maxObvod())); 
			Console.WriteLine("Největší obsah útvaru je: {0}", Math.Round(utvary.maxObsah()));
			Console.WriteLine("--------------");
			Console.WriteLine(utvary.ToString());

			Console.ReadKey();
		}
	}
}

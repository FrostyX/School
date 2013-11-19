using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _9_mnozina
{
	class Program
	{
		static void Main(string[] args)
		{
			Mnozina m = new Mnozina();
			Mnozina n = new Mnozina();

			m = m << 3;
			m = m << 4;
			m = m << 2;
			m = m << 5;
			m = m >> 4;

			n = n << 7;
			n = n << 4;
			n = n << 3;
			n = n << 5;

			Console.WriteLine("!m = {0}", !m);
			Console.WriteLine("+m = {0}", +m);
			Console.WriteLine("m + n = {0}", m + n);
			Console.WriteLine("m - n = {0}", m - n);
			Console.ReadKey();
		}
	}
}

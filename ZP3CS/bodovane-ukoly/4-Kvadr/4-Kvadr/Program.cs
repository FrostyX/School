using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _4_Kvadr
{
	class Program
	{
		static void Main(string[] args)
		{
			vypisKvadr(new Kvadr());
			vypisKvadr(new Kvadr(4));
			vypisKvadr(new Kvadr(4, 5));
			vypisKvadr(new Kvadr(4, 5, 6));
			Console.ReadKey();
		}

		static void vypisKvadr(Kvadr k)
		{
			Console.WriteLine(
				"Kvádr byl zadán stranami a={0}; b={1}; c={2} -> Objem: {3}; Povrch: {4}",
				k.a, k.b, k.c, k.vratObjem(), k.vratPovrch()
			);
		}
	}
}

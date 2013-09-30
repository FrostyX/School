using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class TUI : UI
	{
		public static Dictionary<int, char> symbols = new Dictionary<int,char>
		{
			{-2, '#'},  // Neprozkoumané pole
			{-1, '*'},  // Mina
			{0 , ' '}   // Žádná mina v okolí
		};

		public static void printGrid(int n, int[,] mines)
		{
			// Šířka (ve znacích) vertikální navigace
			const int indent = 4;

			// Horizontální navigace tabulky
			Console.Write("".PadRight(indent));
			for (int i = 0; i < n; i++)
			{
				// Potřeba dynamického odsazení. Pokud by bylo odsazení pomocí mezer,
				// při více-ciferných číslech by se rozpadlo
				Console.Write("  " + i.ToString().PadRight(2));
			}
			Console.Write("\n\n");

			// Výpis hrací desky
			// Sloupce
			for(int i=0; i<n; i++)
			{
				// Hrana tabulky nad každým řádkem
				Console.Write("".PadRight(indent));
				TUI.printHorizontalBorder(n); 

				// Řádky
				for(int j=0; j<n; j++)
				{
					// Vertikální navigace tabulky
					if (j == 0)
						Console.Write(i.ToString().PadRight(indent));

					int value = mines[j, i];
					//Console.WriteLine(value);
					string svalue = value<=0 ? Convert.ToString(TUI.symbols[value]) : Convert.ToString(value);
					Console.Write("| {0} ", svalue);

					// Poslední sloupec je potřeba uzavřít
					if(j == n-1)
						Console.Write("|");
				}
				Console.Write("\n");
			}

			// Spodní hrana tabulky
			Console.Write("".PadRight(indent));
			TUI.printHorizontalBorder(n);
		}

		public static void printStats(Stats s)
		{
		}

		public static void pressAnyKeyToExit()
		{
			Console.WriteLine("\nStisknutím libovolné klávesy ukončíte program");
			Console.ReadKey();
		}

		protected static void printHorizontalBorder(int cells)
		{
			for(int i=0; i<cells; i++)
			{
				Console.Write("----");
			}
			Console.Write("-\n");
		}
	}
}

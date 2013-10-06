using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Minesweeper;

namespace Minesweeper_cli
{
	// Třída pro prácí s textovým uživatelským rozhraním
	class TUI
	{
		// Na hrací desce se mohou kromě čísel, označujících počet min v okolí,
		// vyskytovat také speciální hodnoty. Zde jsou symboly, které se pro ně zobrazí.
		public static Dictionary<int, char> symbols = new Dictionary<int, char>
		{
			{CellValues.undiscovered, '#'},  // Neprozkoumané pole
			{CellValues.mine        , '*'},  // Mina
			{0                      , ' '}   // Žádná mina v okolí
		};

		// Vypíše herní desku s navigací
		public static void printGrid(int n, Cells cells)
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
			for (int y = 0; y < n; y++)
			{
				// Hrana tabulky nad každým řádkem
				Console.Write("".PadRight(indent));
				TUI.printHorizontalBorder(n);

				for (int x = 0; x < n; x++)
				{
					// Vertikální navigace tabulky
					if (x == 0)
						Console.Write(y.ToString().PadRight(indent));

					// Hodnoty tabulky
					TUI.printCell(cells.get(x, y), n);
				}
				Console.Write("\n");
			}

			// Spodní hrana tabulky
			Console.Write("".PadRight(indent));
			TUI.printHorizontalBorder(n);
		}

		// Vypíše panel statistik
		public static void printStats(Stats s)
		{
			Console.Write("Min ve hře:  {0}".PadRight(18), s.minesTotal);
			Console.Write("| Počet kroků: {0}\n", s.steps);
			Console.Write("Min označno: {0}", s.minesFound);
			TUI.printHorizontalBorder();
		}

		// Vypíše dialog pro ukončení aplikace
		public static void pressAnyKeyToContinue(string text)
		{
			Console.WriteLine(text);
			Console.ReadKey();
		}

		// Vypíše vodorovnou čáru
		public static void printHorizontalBorder()
		{
			Console.WriteLine("\n--------------------------\n");
		}

		// Vypíše vodorovnou čáru o šířce tabulky
		protected static void printHorizontalBorder(int cells)
		{
			for (int i = 0; i < cells; i++)
			{
				Console.Write("----");
			}
			Console.Write("-\n");
		}

		// Vypíše buňku tabulky
		protected static void printCell(Cell c, int n)
		{
			// Buď symbol reprezentující minu/neprozkoumané políčko, nebo počet min v okolí
			string value = c.value <= 0 ? Convert.ToString(TUI.symbols[c.value]) : Convert.ToString(c.value);
			Console.Write("| {0} ", value);

			// Poslední sloupec je potřeba uzavřít
			if (c.axis.x == n - 1)
				Console.Write("|");
		}
	}
}

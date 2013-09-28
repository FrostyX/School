using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class TUI : UI
	{
		public static void printGrid(int n, int[] values = null)
		{
			for(int i=0; i<n; i++)
			{
				TUI.printHorizontalBorder(n); // O sloupec víc kvůli navigaci
				for(int j=0; j<n; j++)
				{
					if(j==0)
						Console.Write(i + "    ");

					Console.Write("| x ");

					// Poslední sloupec je potřeba uzavřít
					if(j == n-1)
						Console.Write("|");
				}
				Console.Write("\n");
			}
			TUI.printHorizontalBorder(n);
		}

		protected static void printHorizontalBorder(int cells)
		{
			for(int i=0; i<cells; i++)
			{
				Console.Write("----");
			}
			Console.Write("-\n");
		}

		public static void printStats(Stats s)
		{
		}

		public static void pressAnyKeyToExit()
		{
			Console.WriteLine("Stisknutím libovolné klávesy ukončíte program");
			Console.ReadKey();
		}
	}
}

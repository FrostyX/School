using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class Program
	{
		static void Main(string[] args)
		{
			Console.WriteLine("Zadejte velikost mřížky.");
			Console.WriteLine("Volte čísla v rozsahu {0} - {1}", Minesweeper.minGridSize, Minesweeper.maxGridSize);			
			Console.WriteLine("Pro hodnotu 9 bude velikost mřížky 9x9");
			int size;
			do
			{
				Console.Write("Velikost [{0}]: ", Minesweeper.defaultGridSize);
				size = Convert.ToInt32(Console.ReadLine());
			} while ((size < Minesweeper.minGridSize) || (size > Minesweeper.maxGridSize));
			Console.WriteLine();

			Minesweeper m = new Minesweeper();
			m.size = size;
			m.generateMines();

			do
			{
				TUI.printGrid(m.size, m.discovered);
				Console.WriteLine("Na kterou hodnotu chcete kliknout?");
				Console.WriteLine("Zadejte dvě čísla oddělené mezerou.");
				Console.WriteLine("První hodnota reprezentuje osu X, druhá osu Y");
				Console.Write("Hodnoty: ");
				string[] input = Console.ReadLine().Split();
				char action = char.Parse(input[0]);
				int x = int.Parse(input[1]);
				int y = int.Parse(input[2]);

				m.action(action, x, y);
				if ((action == 's') && (m.toLiveOrNotToLive(x ,y)))
					break;
			} while(m.stats.minesTotal != m.stats.minesFound);

			Console.WriteLine("\n--------------------------\n");

			// Pokud uživatel neodhalil všechny miny -> na nějakou stoupl
			if (m.stats.minesTotal != m.stats.minesFound)
			{
				Console.WriteLine("Stoupl jste na minu a umřete za 3... 2... 1...");
				Console.WriteLine("Smůla. Jste mrtvý. Zkuste to znovu :-)");
			}
			else
			{
				Console.WriteLine("Jste rozený profík! Našel jste všechny miny.");
			}

			TUI.pressAnyKeyToExit();
		}
	}
}

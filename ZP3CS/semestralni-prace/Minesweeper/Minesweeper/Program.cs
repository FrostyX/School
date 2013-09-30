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
				string[] axis = Console.ReadLine().Split();
				int x = int.Parse(axis[0]);
				int y = int.Parse(axis[1]);

				m.discover(x, y);

			} while(m.stats.minesTotal != m.stats.minesFound);


			TUI.pressAnyKeyToExit();
		}
	}
}

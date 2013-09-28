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
			Console.WriteLine("Pro hodnotu 9 bude velikost mřížky 9x9");
			Console.Write("Velikost [{0}]: ", Minesweeper.defaultGridSize);
			
			int size = Convert.ToInt32(Console.ReadLine());
			Console.WriteLine(size);

			Minesweeper m = new Minesweeper();
			m.stats.size = size;
			TUI.printGrid(m.stats.size);


			TUI.pressAnyKeyToExit();
		}
	}
}

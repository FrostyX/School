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
			do // Uživatel se musí trefit do povoleného rozsahu velikostí
			{
				Console.Write("Velikost [{0}]: ", Minesweeper.defaultGridSize);
				size = Convert.ToInt32(Console.ReadLine());
			} while ((size < Minesweeper.minGridSize) || (size > Minesweeper.maxGridSize));
			Console.WriteLine();

			// Vytvoříme a vygenerujeme herní desku 
			Minesweeper m = new Minesweeper();
			m.size = size;
			m.generateMines();

			char action;
			do // Uživatel prozkoumává herní desku a označuje miny, dokud neřekne, že má hotovo
			{
				TUI.printGrid(m.size, m.cells);
				Console.WriteLine("Na kterou hodnotu chcete kliknout?");
				Console.WriteLine("Zadejte dvě čísla oddělené mezerou.");
				Console.WriteLine("První hodnota reprezentuje osu X, druhá osu Y");
				Console.Write("Hodnoty: ");

				// Parsujeme vstup
				string[] input = Console.ReadLine().Split();
				action = char.Parse(input[0]);
				int x = int.Parse(input[1]);
				int y = int.Parse(input[2]);

				// Pokud uživatel řekl, že už má hotovo
				if (action == 'q')
					break;

				// Provedeme požadovanou akci s požadovanými souřadnicemi
				m.action(action, x, y);

				// Pokud uživatel stoupl na minu
				if ((action == 's') && (m.toBeOrNotToBe(x, y)))
					break;

			} while(true); // Cyklus je ukončen zevnitř

			TUI.printHorizontalBorder();

			// Pokud uživatel stoupl na minu
			if (action == 's')
			{
				Console.WriteLine("Stoupl jste na minu a umřete za 3... 2... 1...");
				Console.WriteLine("Smůla. Jste mrtvý. Zkuste to znovu :-)");
			}
			else
			{
				// Pokud uživatel označil miny na správných místech
				if (m.foundAllMines())
					Console.WriteLine("Jste rozený profík! Našel jste všechny miny.");
				else
					Console.WriteLine("Bohužel jste nenašel všechny miny. Doufejme, že na ně nikdo nešlápne.");
			}
			TUI.pressAnyKeyToExit();
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Minesweeper;


namespace Minesweeper_cli
{
	class Program
	{
		static void Main(string[] args)
		{
			Console.WriteLine("Zadejte velikost mřížky.");
			Console.WriteLine("Volte čísla v rozsahu {0} - {1}", Minesweeper.Minesweeper.minGridSize, Minesweeper.Minesweeper.maxGridSize);
			Console.WriteLine("Pro hodnotu 9 bude velikost mřížky 9x9");
			int size = 0;
			do // Uživatel se musí trefit do povoleného rozsahu velikostí
			{
				try
				{
					Console.Write("Velikost [{0}]: ", Minesweeper.Minesweeper.defaultGridSize);
					size = Convert.ToInt32(Console.ReadLine());
				}
				catch
				{
					size = Minesweeper.Minesweeper.defaultGridSize;
				}
			} while ((size < Minesweeper.Minesweeper.minGridSize) || (size > Minesweeper.Minesweeper.maxGridSize));
			Console.WriteLine();

			// Vytvoříme a vygenerujeme herní desku 
			Minesweeper.Minesweeper m = new Minesweeper.Minesweeper();
			m.size = size;
			m.generateMines();

			char action = ' ';
			do // Uživatel prozkoumává herní desku a označuje miny, dokud neřekne, že má hotovo
			{
				// Nejdříve se vyčistí obrazovka
				Console.Clear();

				// Vypíšeme statistiky
				TUI.printStats(m.stats);

				TUI.printGrid(m.size, m.cells);
				Console.WriteLine();
				Console.WriteLine("Na kterou hodnotu chcete kliknout?");
				Console.WriteLine("Zadejte operaci následovanou dvěma čísly oddělenýmy mezerou.");
				Console.WriteLine("Operace jsou:\n s (stoupnout), m (označit minu), u (zrušit označení), q (vyhodnotit).");
				Console.WriteLine("První číselná hodnota reprezentuje osu X, druhá osu Y");
				Console.Write("\nVstup: ");

				// Parsujeme vstup [1/2]
				int x = 0, y = 0;
				try
				{
					string[] input = Console.ReadLine().Split();
					action = char.Parse(input[0]);

					// Pokud uživatel řekl, že už má hotovo
					if (action == 'q')
						break;

					// Parsujeme vstup [2/2]
					x = int.Parse(input[1]);
					y = int.Parse(input[2]);
				}
				catch { };

				// Pokud uživatel zadal neexistující souřadnice
				if (m.cells.get(x, y) == null)
				{
					TUI.pressAnyKeyToContinue(
						"\nNeplatné souřadnice.\n"
						+ "Pokračujte stiskem libovolné klávesy ..."
					);
					continue;
				}

				// Pokud by chtěl uživatel provést na prozkoumaných souřadnicích 
				if (m.cells.get(x, y).value >= 0)
				{
					TUI.pressAnyKeyToContinue(
						"\nNelze provést operaci na daných souřadnicích. Jsou již prozkoumané.\n"
						+ "Pokračujte stiskem libovolné klávesy ..."
					);
					continue;
				}

				// Provedeme požadovanou akci s požadovanými souřadnicemi
				m.action(action, x, y);

				// Pokud uživatel stoupl na minu
				if ((action == 's') && (m.toBeOrNotToBe(x, y)))
					break;

			} while (true); // Cyklus je ukončen zevnitř

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
				if (m.foundAllAndOnlyMines())
					Console.WriteLine("Jste rozený profík! Našel jste všechny miny.");
				else
					Console.WriteLine("Bohužel jste neoznačil všechny miny správně. Doufejme, že na ně nikdo nešlápne.");
			}
			TUI.pressAnyKeyToContinue("\nStisknutím libovolné klávesy ukončíte program");
		}
	}
}
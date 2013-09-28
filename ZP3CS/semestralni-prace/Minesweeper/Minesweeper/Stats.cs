using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class Stats
	{
		// Velikost hrací desky
		// Pro hodnotu 9 bude mřížka velká 9x9 políček
		int size;

		// Počet políček, na které uživatel klikl
		int steps = 0;

		// Počet odkrytých políček
		// != steps protože jedno kliknutí na políčko vedle kterého není žádná mina
		// odkryje všechny okolní políčka vedle kterých není mina
		int showed = 0;

		// Celkový počet min ve hře
		int minesTotal;

		// Počet nalezených min
		int minesFount = 0;

		// Počet vyhraných her v řadě (za sebou) 
		int gamesWin = 0;

		// Počet započatých her 
		int gamesTotal = 0;
	}
}

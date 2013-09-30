using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class Stats
	{
		// Počet políček, na které uživatel klikl
		public int steps = 0;

		// Počet odkrytých políček
		// != steps protože jedno kliknutí na políčko vedle kterého není žádná mina
		// odkryje všechny okolní políčka vedle kterých není mina
		public int showed = 0;

		// Celkový počet min ve hře
		public int minesTotal;

		// Počet nalezených min
		public int minesFound = 0;

		// Počet vyhraných her v řadě (za sebou) 
		public int gamesWin = 0;

		// Počet započatých her 
		public int gamesTotal = 0;
	}
}

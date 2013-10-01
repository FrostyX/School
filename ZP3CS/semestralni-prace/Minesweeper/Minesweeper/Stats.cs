using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class Stats
	{
		// Počet kroků, které uživatel provedl
		public int steps = 0;

		// Celkový počet min ve hře
		public int minesTotal;

		// Počet nalezených min
		public int minesFound = 0;
	}
}

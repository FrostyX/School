using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class Minesweeper
	{
		public const int defaultGridSize = 9;
		protected Stats _stats;

		public Minesweeper()
		{
			this._stats = new Stats();
		}

		public Stats stats
		{
			get { return this._stats; }
			set { this._stats = value; }
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	// Reprezentuje souřadnice objektu na osách x, y
	class Axis
	{
		protected int _x;
		protected int _y;

		public Axis(int x, int y)
		{
			this.x = x;
			this.y = y;
		}

		/*
		 * Gettery a settery
		 */
		public int x
		{
			get { return this._x; }
			set { this._x = value; }
		}

		public int y
		{
			get { return this._y; }
			set { this._y = value; }
		}
	}
}

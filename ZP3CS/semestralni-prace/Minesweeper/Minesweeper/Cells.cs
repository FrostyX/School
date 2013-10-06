using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	// Reprezentuje "inteligentní" seznam herních políček 
	public class Cells
	{
		// Všechna políčka hrací desky uložena pod souřadnicemi x, y
		protected Cell[,] cells;

		// Velikost hrací desky - Nikoliv cells.Length, protože pole cells je dvojrozměné.
		// Tedy _size * _size = cells.Length
		protected int _size;

		public Cells(int n)
		{
			this.cells = new Cell[n, n];
			this._size = n;
			this.generateCells();
		}

		// Vrátí políčko na daných souřadnicích
		public Cell get(int x, int y)
		{
			if ((x >= 0) && (y >= 0) && (x < this.size) && (y < this.size))
				return this.cells[x, y];
			return null;
		}

		// Změní políčko na daných souřadnicích
		public void set(int x, int y, Cell cell)
		{
			if ((x >= 0) && (y >= 0) && (x < this.size) && (y < this.size))
				this.cells[x, y] = cell;
		}

		// Vrátí počet min v okolí políčka daného souřadnicemi
		public int getLocalMinesCount(int x, int y)
		{
			int count = 0;
			List<Cell> sur = this.getSurrounding(x, y);
			foreach (Cell s in sur)
			{
				if (s.mine)
					count++;
			}
			return count;
		}

		// Vrátí okolní políčka
		public List<Cell> getSurrounding(int x, int y)
		{
			List<Cell> cells = new List<Cell>();
			int[] X = new int[] { x - 1, x - 1, x - 1, x + 0, x + 1, x + 1, x + 1, x + 0 };
			int[] Y = new int[] { y - 1, y + 0, y + 1, y + 1, y + 1, y + 0, y - 1, y - 1 };

			for (int i = 0; i < 8; i++)
			{
				Cell cell = this.get(X[i], Y[i]);
				if (cell != null)
					cells.Add(cell);
			}
			return cells;
		}

		// Zaplní celou hrací desku políčky. Tyto políčka neobsahují miny a jsou neprozkoumané
		protected void generateCells()
		{
			for (int y = 0; y < this.size; y++)
			{
				for (int x = 0; x < this.size; x++)
				{
					Cell c = new Cell();
					c.axis = new Axis(x, y);
					this.set(x, y, c);
				}
			}
		}

		/*
		 * Gettery a settery
		 */
		public int size
		{
			get { return this._size; }
		}
	}
}

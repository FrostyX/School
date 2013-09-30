using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	class Minesweeper
	{
		// Hodnota 18 je maximální počet sloupců, který se na šířku vleze do okna konzole
		// ve výchozí velikosti, s výchozím písmem při rozlišení obrazovky 1366x768
		public const int maxGridSize = 18;
		public const int minGridSize = 2;
		public const int defaultGridSize = 9;

		// Velikost hrací desky
		// Pro hodnotu 9 bude mřížka velká 9x9 políček
		public int _size;

		protected Stats _stats;
		protected int[,] mines;
		protected int[,] discoveredCells;

		public Minesweeper()
		{
			this._stats = new Stats();
			this.size = defaultGridSize;
		}

		/* Vrátí co se nachází na daném políčku
		 * -1 = Mina
		 * 0  = V okolí se nenachází žádná mina. 
		 * 1+ = Počet min v okolí
		 */
		public int getCellValue(int x, int y)
		{
			int value = 0;
			if((x>=0) && (y>=0) && (x<this.size) && (y<this.size))
				value = this.mines[x, y];
			return value;
		}

		// Vygeneruje a uloží miny. Počet je závislý na velikosti herní desky
		// Pozice min jsou náhodné
		public void generateMines()
		{
			this.stats.minesTotal = this.getOptimalNumberOfMines();

			Random r = new Random();
			for (int i = 0; i < this.stats.minesTotal; i++)
			{
				int x, y;
				do // Kdyby mina na dané pozici už existovala
				{
					x = r.Next(this.stats.minesTotal);
					y = r.Next(this.stats.minesTotal);
				} while(this.mines[x,y]==1);

				this.mines[x, y] = 1;
			}
		}

		public void action(char action, int x, int y)
		{
			switch (action)
			{
				case 's': { this.stepOn(x, y); break; } 
				case 'm': { this.mark(x, y)  ; break; }
				case 'u': { this.unMark(x, y); break; } 
			}
		}

		public void stepOn(int x, int y)
		{
			this.discoveredCells[x, y] = this.mines[x, y] == 1 ? -1 : this.getLocalMinesCount(x, y);
		}

		public void mark(int x, int y)
		{
			this.discoveredCells[x, y] = -1;
			this.stats.minesFound++;
		}

		public void unMark(int x, int y)
		{
			this.discoveredCells[x, y] = -2;
			this.stats.minesFound--;
		}


		public bool toLiveOrNotToLive(int x, int y)
		{
			return this.mines[x, y] == 1;
		}

		protected int getLocalMinesCount(int x, int y)
		{

			int count = 0;
			count += this.getCellValue(x-1, y-1);
			count += this.getCellValue(x-1, y+0);
			count += this.getCellValue(x-1, y+1);
			count += this.getCellValue(x+0, y+1);
			count += this.getCellValue(x+1, y+1);
			count += this.getCellValue(x+1, y+0);
			count += this.getCellValue(x+1, y-1);
			count += this.getCellValue(x+0, y-1);
			return count;
		}

		// Vrátí počet min který by byl optimální pro danou velikost hrací desky
		protected int getOptimalNumberOfMines()
		{
			// Počet min střední optížnosti by mohl odpovídat funkci 
			// f(n) = {0.2*n^2 | kde n je velikost mřížky }
			return Convert.ToInt32(Math.Ceiling(0.2 * this.size * this.size));
		}

		/*
		 * Gettery a settery
		 */
		public Stats stats
		{
			get { return this._stats; }
			set { this._stats = value; }
		}

		public int size 
		{
			get { return this._size; }

			set 
			{ 
				this._size = value;
				this.mines = new int[value, value];

				// Vytvoření herní desky a nastavení všech políček na neprozkoumané
				this.discoveredCells = new int[value, value];
				for (int i = 0; i < value; i++)
				{
					for (int j = 0; j < value; j++)
					{
						this.discoveredCells[j, i] = -2;
					}

				}
			}
		}

		public int[,] discovered
		{
			/* Vrátí co se nachází na daném políčku
			 * -2 = Neprozkoumané pole
			 * -1 = Mina
			 * 0  = V okolí se nenachází žádná mina. 
			 * 1+ = Počet min v okolí
			 */
			get { return this.discoveredCells; }
		}
	}
}

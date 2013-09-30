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

		// Kolik procent políček bude pokryto minami
		public const int minesPerc = 10;

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
		 *
		 * Spíš už jen
		 * 1 = Mina
		 * 0 = Nic
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
			if (this.discoveredCells[x, y] == 0)
				this.discoverZeros(x, y);
			this.stats.steps++;
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

		// Vrátí počet min v okolí políčka daného souřadnicemi
		protected int getLocalMinesCount(int x, int y)
		{
			int count = 0;
			Dictionary<char, int[]> sur = this.getSurrounding(x, y);
			for(int i=0; i<sur['x'].Length; i++)
				count += this.getCellValue(sur['x'][i], sur['y'][i]);
			return count;
		}

		protected void discover(int x, int y)
		{
			if((x>=0) && (y>=0) && (x<this.size) && (y<this.size))
				this.discoveredCells[x, y] = this.mines[x, y] == 1 ? -1 : this.getLocalMinesCount(x, y);
		}

		// Prozkoumá všechny okolní políčka, která vedle sebe nemají žádnou minu
		protected void discoverZeros(int x, int y)
		{
			Dictionary<char, int[]> sur = this.getSurrounding(x, y);
			for (int i = 0; i < sur['x'].Length; i++)
			{
				int xi = sur['x'][i];
				int yi = sur['y'][i];

				// Pokud okolo vybraného okolního políčka není žádná mina
				if (this.getCellValue(xi, yi) == 0)
				{
					// Pokud nehledáme za hranou
					if ((xi >= 0) && (yi >= 0) && (xi < this.size) && (yi < this.size))
					{
						// Pokud okolní políčko zatím nebylo prozkoumané
						if (this.discoveredCells[xi, yi] == -2)
						{
							this.discover(xi, yi);
							this.discoverZeros(sur['x'][i], sur['y'][i]);
						}
					}
				}
			}
				
			/*
			// Pokud je na x, y 0, pak prozkoumat okolí
			if (this.getCellValue(x, y) == 0)
			{
				if(this.getLocalMinesCount(x, y) == 0)
				{
					this.discoveredCells[x, y] = 0;

					Dictionary<char, int[]> sur = this.getSurrounding(x, y);
					for (int i = 0; i < sur['x'].Length; i++)
					{
						// Pokud je kdekoliv nalezena nula, zavolat rekurzivně tuto funkci
						if (this.getCellValue(sur['x'][i], sur['y'][i]) == 0)
						{
							if (this.getLocalMinesCount(sur['x'][i], sur['y'][i]) == 0)
							{
								if ((sur['x'][i] > 0) && (sur['y'][i] > 0) && (sur['x'][i] < this.size) && (sur['y'][i] < this.size))
									this.discoverZeros(sur['x'][i], sur['y'][i]);
							}
						}
					}
				}
				*/
				/*
				if ((x > 0) && (y > 0) && (x < this.size) && (y < this.size))
				{
					this.discoveredCells[x, y] = 0;
					Dictionary<char, int[]> sur = this.getSurrounding(x, y);
					for (int i = 0; i < sur['x'].Length; i++)
					{
						// Pokud je kdekoliv nalezena nula, zavolat rekurzivně tuto funkci
						if (this.getCellValue(sur['x'][i], sur['y'][i]) == 0)
							this.discoverZeros(sur['x'][i], sur['y'][i]);
					}
				}
			}
				*/
		}

		/* Vrátí sousední políčka
		 * (některé z políček nemusejí skutečně existovat - platí pro políčka na hranici desky)
		 * Sousedních by mělo být 8 políček, například hodnotu prvního lze získat pomocí:
		 *     var sur = getSurrounding(x, y)
		 *     sur['x'][0], sur['y'][0]
		 */
		protected Dictionary<char, int[]> getSurrounding(int x, int y)
		{
			Dictionary<char, int[]> sur = new Dictionary<char, int[]>();
			sur['x'] = new int[] {x-1, x-1, x-1, x+0, x+1, x+1, x+1, x+0};
			sur['y'] = new int[] {y-1, y+0, y+1, y+1, y+1, y+0, y-1, y-1};
			return sur;
		}

		// Vrátí počet min který by byl optimální pro danou velikost hrací desky
		protected int getOptimalNumberOfMines()
		{
			// Počet min střední optížnosti by mohl odpovídat funkci 
			// f(n) = {0.2*n^2 | kde n je velikost mřížky }
			return Convert.ToInt32(Math.Ceiling((minesPerc / 100.0) * this.size * this.size));
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
			/* Vrátí co se nachází na daném políčku --- !
			 * -2 = Neprozkoumané pole
			 * -1 = Mina
			 * 0  = V okolí se nenachází žádná mina. 
			 * 1+ = Počet min v okolí
			 */
			get { return this.discoveredCells; }
		}
	}
}

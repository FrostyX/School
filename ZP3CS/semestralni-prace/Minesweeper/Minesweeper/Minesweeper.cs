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
		protected Cells _cells;

		public Minesweeper()
		{
			this._stats = new Stats();
			this.size = defaultGridSize;
		}

		// Vygeneruje a uloží miny na náhodné pozice tabulky. 
		// Jejich počet je závislý na velikosti herní desky a procentu výskytu min
		public void generateMines()
		{
			// Zjistíme, kolik min by bylo vhodné vygenerovat
			this.stats.minesTotal = this.getOptimalNumberOfMines();

			Random r = new Random();
			for (int i = 0; i < this.stats.minesTotal; i++)
			{
				int x, y;
				do // Kdyby mina na dané pozici už existovala
				{
					x = r.Next(this.stats.minesTotal);
					y = r.Next(this.stats.minesTotal);
				} while(this.cells.get(x, y).mine);

				// Uložíme minu
				this.cells.get(x, y).mine = true;
			}
		}

		// Zavolá jednu z uživatelských akcí
		public void action(char action, int x, int y)
		{
			switch (action)
			{
				case 's': { this.stepOn(x, y); break; } 
				case 'm': { this.mark(x, y)  ; break; }
				case 'u': { this.unMark(x, y); break; } 
			}
		}

		// Vstoupí na políčko a zjistí jeho hodnotu
		// != discover(..) -> Narozdíl od něj je to uživatelská akce
		// a tedy započítá uživatelovo kliknutí
		public void stepOn(int x, int y)
		{
			this.discover(x, y);
			this.stats.steps++;
		}

		// Označí, kde si uživatel myslí, že je mina
		public void mark(int x, int y)
		{
			this.cells.get(x, y).value = CellValues.mine;
			this.stats.minesFound++;
			this.stats.steps++;
		}

		// Zruší uživatelské označení miny
		public void unMark(int x, int y)
		{
			this.cells.get(x, y).value = CellValues.undiscovered;
			this.stats.minesFound--;
			this.stats.steps++;
		}

		// Zjistí zda uživatel stoupl na minu
		public bool toBeOrNotToBe(int x, int y) // that is the question
		{ // --- W. Shakespeare
			return this.cells.get(x, y).mine;
		}

		// Zjistí, zda uživatel našel vsechny miny
		public bool foundAllMines()
		{
			for (int y = 0; y < this.size; y++)
			{
				for (int x = 0; x < this.size; x++)
				{
					// Pokud je na políčku mina a uživatel řekl, že je na něm mina
					Cell c = this.cells.get(x, y);
					if ((c.mine) && (c.value != CellValues.mine))
						return false;
				}
			}
			return true;
		}

		// Prozkoumá dané políčko tabulky
		protected void discover(int x, int y)
		{
			Cell c = this.cells.get(x, y);
			c.value = c.mine ? CellValues.mine : this.cells.getLocalMinesCount(x, y);
			if (c.value == 0)
				this.discoverZeros(x, y);
		}

		// Prozkoumá všechny okolní políčka, která vedle sebe nemají žádnou minu
		protected void discoverZeros(int x, int y)
		{
			List<Cell> sur = this.cells.getSurrounding(x, y);
			foreach(Cell s in sur)
			{
				// Pokud okolo vybraného okolního políčka není žádná mina
				if (this.cells.getLocalMinesCount(x, y) == 0)
				{
					// Pokud okolní políčko zatím nebylo prozkoumané
					if(s.value == -2)
					{
						//Console.WriteLine(s.axis.x + "-" + s.axis.y);
						this.discover(s.axis.x, s.axis.y);
						this.discoverZeros(s.axis.x, s.axis.y);
					}
				}
			}
		}

		// Vrátí počet min který by byl optimální pro danou velikost hrací desky
		protected int getOptimalNumberOfMines()
		{
			// Počet min střední optížnosti by mohl odpovídat funkci 
			// f(n) = {(p/100)*n^2 | kde n je velikost mřížky; p je procento výskytu min}
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

		public Cells cells 
		{
			get { return this._cells; }
			set { this._cells= value; }
		}

		public int size 
		{
			get { return this._size; }
			set 
			{ 
				this._size = value;
				this._cells = new Cells(value);
			}
		}
	}
}

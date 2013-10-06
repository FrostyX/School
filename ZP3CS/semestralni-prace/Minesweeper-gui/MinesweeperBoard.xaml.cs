using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using Minesweeper;

namespace Minesweeper_gui
{
	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MinesweeperBoard : Window
	{
		protected Minesweeper.Minesweeper m = null;

		public MinesweeperBoard(int size)
		{
			InitializeComponent();
			this.m = new Minesweeper.Minesweeper();
			this.m.size = size;
			this.m.generateMines();
			this.printBoard();
			this.refreshStats();
		}

		// Kliknutí na políčko herní desky
		protected void cell_Click(object sender, MouseEventArgs e)
		{
			AxisButton b = sender as AxisButton;

			// Pravé myšítko (od)označí minu
			if (e.RightButton == MouseButtonState.Pressed)
				this.m.toggleMark(b.Axis.x, b.Axis.y);

			// Levé myšítko otevře políčko
			else if (e.LeftButton == MouseButtonState.Pressed)
			{
				this.m.stepOn(b.Axis.x, b.Axis.y);

				// Pokud uživatel stoupl na minu
				if (this.m.toBeOrNotToBe(b.Axis.x, b.Axis.y))
				{
					this.lockControls();
					MessageBox.Show(
						"Stoupl jste na minu a umřete za 3... 2... 1...\n"
						+ "Smůla. Jste mrtvý. Zkuste to znovu :-)"
					);
				}
			}
			this.refreshValues();
			this.refreshStats();
		}

		// Kliknutí na tlačítko pro vyhodnocení
		public void bDone_Click(object sender, RoutedEventArgs e)
		{
			string msg = m.foundAllAndOnlyMines() ?
				"Jste rozený profík! Našel jste všechny miny." :
				"Bohužel jste neoznačil všechny miny správně. Doufejme, že na ně nikdo nešlápne."
			;
			this.lockControls();
			MessageBox.Show(msg);
		}

		// Kliknutí na tlačítko Hra -> Nová hra
		public void newGame_Click(object sender, RoutedEventArgs e)
		{
			MainWindow win = new MainWindow();
			win.Show();
			this.Close();
		}

		// Kliknutí na tlačítko nápovědy
		public void help_Click(object sender, RoutedEventArgs e)
		{
			Help help = new Help();
			help.Show();
		}

		// Na herní desku vypíše jednotlivé buňky
		protected void printBoard()
		{
			for (int i = 0; i < this.m.size; i++)
			{
				// Vytvoříme panel, který bude odpovídat řádku tabulky
				StackPanel row = new StackPanel();
				row.Orientation = Orientation.Horizontal;

				for (int j = 0; j < this.m.size; j++)
				{
					// Vytvoříme nové políčko
					Cell c = this.m.cells.get(j, i);
					AxisButton b = new AxisButton()
					{
						Height = 25,
						Width = 25,
						Content = "",
						Axis = c.axis,
					};

					// Po kliknutí na políčko bude zavolána metoda cell_Click(...)
					b.PreviewMouseDown += new MouseButtonEventHandler(cell_Click);

					// A přidáme ho do řádku
					row.Children.Add(b);
				}

				// Přidáme řádek do herní desky
				this.board.Children.Add(row);
			}
		}

		// Aktualizuje hodnoty vypsané na herní desce
		protected void refreshValues()
		{
			foreach (StackPanel i in this.board.Children)
			{
				foreach (AxisButton button in i.Children)
				{
					int value = this.m.cells.get(button.Axis.x, button.Axis.y).value;

					// Pokud bylo políčko rozkliknuté
					if (value >= 0)
					{
						button.IsEnabled = false;
						button.Content = value;
					}

					// Pokud na políčku uživatel označil minu
					else if (value == CellValues.mine)
					{
						Image img = new Image();
						img.Source = new BitmapImage(new Uri("img/mine.png", UriKind.RelativeOrAbsolute));
						img.Width = 8;
						img.Height = 8;
						button.Content = img;
					}

					// Pokud políčko není prozkoumané
					else
						button.Content = "";
				}
			}
		}

		// Aktualizuje vypsané statistiky
		protected void refreshStats()
		{
			this.lMinesTotal.Content = "Min ve hře: " + this.m.stats.minesTotal;
			this.lMinesFound.Content = "Min označeno: " + this.m.stats.minesFound;
			this.lSteps.Content = "Počet kroků: " + this.m.stats.steps;
		}

		// Uzamče všechny tlačítka
		protected void lockControls()
		{
			this.bDone.IsEnabled = false;
			foreach (StackPanel i in this.board.Children)
			{
				foreach (AxisButton j in i.Children)
				{
					j.IsEnabled = false;
				}
			}
		}
	}
}
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
	public partial class MainWindow : Window
	{
		protected Minesweeper.Minesweeper m = new Minesweeper.Minesweeper();

		public MainWindow()
		{
			InitializeComponent();
			this.m.size = 5;
			this.m.generateMines();
			this.printBoard();
			this.refreshStats();
		}

		protected void printBoard()
		{
			for(int i=0; i<this.m.size; i++)
			{
				StackPanel row = new StackPanel();
				row.Orientation = Orientation.Horizontal;
				for(int j=0; j<this.m.size; j++)
				{
					Cell c = this.m.cells.get(j, i);
					AxisButton b = new AxisButton() 
					{ 
						Height = 25, 
						Width = 25, 
						Content = "",
						Axis = c.axis,
					};
					b.PreviewMouseDown += new MouseButtonEventHandler(cell_Click);
					row.Children.Add(b);
				}
				this.board.Children.Add(row);
			}
		}

		protected void cell_Click(object sender, MouseEventArgs e)
		{
			AxisButton b = sender as AxisButton;

			if (e.RightButton == MouseButtonState.Pressed)
				this.m.toggleMark(b.Axis.x, b.Axis.y);
			else if (e.LeftButton == MouseButtonState.Pressed)
			{
				this.m.stepOn(b.Axis.x, b.Axis.y);
				if (this.m.cells.get(b.Axis.x, b.Axis.y).mine)
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

		public void bDone_Click(object sender, RoutedEventArgs e)
		{
			string msg = m.foundAllAndOnlyMines() ? 
				"Jste rozený profík! Našel jste všechny miny." :
				"Bohužel jste neoznačil všechny miny správně. Doufejme, že na ně nikdo nešlápne."
			;
			this.lockControls();
			MessageBox.Show(msg);
		}

		public void newGame_Click(object sender, RoutedEventArgs e)
		{
			MainWindow win = new MainWindow();
			win.Show();
			this.Close();
		}

		public void help_Click(object sender, RoutedEventArgs e)
		{
			Help help = new Help();
			help.Show();
		}

		protected void refreshValues()
		{
			foreach (StackPanel i in this.board.Children)
			{
				foreach (AxisButton j in i.Children)
				{
					int value = this.m.cells.get(j.Axis.x, j.Axis.y).value;
					if (value >= 0)
					{
						j.IsEnabled = false;
						j.Content = value;
					}
					else if (value == -1)
					{
						Image img = new Image();
						img.Source = new BitmapImage(new Uri("img/mine.png", UriKind.RelativeOrAbsolute));
						img.Width = 8;
						img.Height = 8;
						j.Content = img;
					}
					else
						j.Content = "";
				}
			}
		}

		protected void refreshStats()
		{
			this.lMinesTotal.Content = "Min ve hře: " + this.m.stats.minesTotal;
			this.lMinesFound.Content = "Min označeno: " + this.m.stats.minesFound;
			this.lSteps.Content = "Počet kroků: " + this.m.stats.steps;
		}

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

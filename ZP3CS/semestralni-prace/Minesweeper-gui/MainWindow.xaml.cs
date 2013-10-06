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
using System.Windows.Shapes;

namespace Minesweeper_gui
{
	/// <summary>
	/// Interaction logic for BoardSizeDialog.xaml
	/// </summary>
	public partial class MainWindow : Window
	{
		public MainWindow()
		{
			InitializeComponent();
		}

		private void bStart_Click(object sender, RoutedEventArgs e)
		{
			try
			{
				int size = Convert.ToInt32(this.tSize.Text);
				if (size < Minesweeper.Minesweeper.minGridSize)
					MessageBox.Show("Hodnota je příliš malá");
				else
				{
					MinesweeperBoard board = new MinesweeperBoard(size);
					board.Show();
					this.Close();
				}
			}
			catch (Exception)
			{
				MessageBox.Show("Zadejte prosím číselnou hodnotu");
			}

		}
	}
}

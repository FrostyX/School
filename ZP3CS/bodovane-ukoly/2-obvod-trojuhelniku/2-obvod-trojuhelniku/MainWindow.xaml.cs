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

namespace _2_obvod_trojuhelniku
{
	/// <summary>
	/// Interaction logic for MainWindow.xaml
	/// </summary>
	public partial class MainWindow : Window
	{
		public MainWindow()
		{
			InitializeComponent();
		}

		protected bool lzeSestrojitTrojuhelnik(int a, int b, int c)
		{
			if (a + b < c) return false;
			if (a + c < b) return false;
			if (b + c < a) return false;
			return true;
		}

		protected int obvodTrojuhelnika(int a, int b, int c)
		{
			return a + b + c;
		}

		private void bVypocti_Click(object sender, RoutedEventArgs e)
		{
			int a = Convert.ToInt32(tA.Text);
			int b = Convert.ToInt32(tB.Text);
			int c = Convert.ToInt32(tC.Text);
			bool lze = this.lzeSestrojitTrojuhelnik(a, b, c);

			if (lze)
			{
				lVysledek.Content = "Trojúhelník lze sestrojit\n";
				lVysledek.Content += "Obvod: " + this.obvodTrojuhelnika(a, b, c).ToString();
			}
			else
			{
				lVysledek.Content = "Trojúhelník nelze sestrojit";
			}
		}
	}
}

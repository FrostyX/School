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

namespace _3_prvocisla
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

		private void bVypocti_Click(object sender, RoutedEventArgs e)
		{
			try
			{
				int n = Convert.ToInt32(this.tVstup.Text);
				this.tPrvocisla.Text = "";
				for (int i = 2; i < n; i++)
				{
					if (this.jePrvocislo(i))
						this.tPrvocisla.Text += i + ", ";
				}
			}
			//finally
			catch(Exception)
			{
				this.lChyba.Content = "Prosím zadejte číslo";
				this.tPrvocisla.Text = "";
			}
		}

		private void tVstup_TextChanged(object sender, TextChangedEventArgs e)
		{
			this.lChyba.Content = "";
		}

		protected bool jePrvocislo(int x)
		{
			for (int i = 2; i < x; i++)
			{
				if (x % i == 0)
					return false;
			}
			return true;
		}
	}
}

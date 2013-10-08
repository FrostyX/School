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
				if (n <= 0)
					throw new NotNaturalNumberException("Zadejte prosím celé kladné číslo");
				this.tPrvocisla.Text = "";
				for (int i = 2; i < n; i++)
				{
					if (this.jePrvocislo(i))
						this.tPrvocisla.Text += i + ", ";
				}
			}
			catch (NotNaturalNumberException ex)
			{
				this.tPrvocisla.Text = "";
				MessageBox.Show(ex.Message);
			}
			catch (OverflowException)
			{
				this.tPrvocisla.Text = "";
				MessageBox.Show("Zadali jste moc vysoké číslo");
			}
			catch (Exception)
			{
				this.tPrvocisla.Text = "";
				MessageBox.Show("Prosím zadejte číslo");
			}
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

	public class NotNaturalNumberException : Exception
	{
		public NotNaturalNumberException(string message) : base(message){}
	}
}

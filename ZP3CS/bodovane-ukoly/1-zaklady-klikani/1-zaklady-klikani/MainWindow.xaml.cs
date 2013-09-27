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

namespace _1_zaklady_klikani
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

        private void bVypis_Click(object sender, RoutedEventArgs e)
        {
            lVystup.Content = tJmeno.Text + "\n";
            lVystup.Content += tUlice.Text + "\n";
            lVystup.Content += tPsc.Text + "\n";
            lVystup.Content += tMesto.Text;
        }
    }
}

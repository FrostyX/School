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

namespace Minesweeper_gui
{
	class AxisButton : Button
	{
		protected Minesweeper.Axis axis = new Minesweeper.Axis(0, 0);

		public Minesweeper.Axis Axis
		{
			get { return this.axis; }
			set { this.axis = value; }
		}
	}
}

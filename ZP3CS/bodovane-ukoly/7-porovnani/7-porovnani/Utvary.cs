using System;
using System.Collections.Generic;
using System.Collections;
using System.Linq;
using System.Text;

namespace _7_porovnani
{
	class Utvary
	{
		public ArrayList utvary = new ArrayList();

		public double celkovyObvod()
		{
			double count = 0;
			foreach (Utvar u in utvary)
				count += u.obvod();
			return count;
		}

		public double celkovyObsah()
		{
			double count = 0;
			foreach (Utvar u in utvary)
				count += u.obsah();
			return count;
		}

		public double maxObvod()
		{
			double max = 0;
			foreach (Utvar u in utvary)
			{
				if (max < u.obvod())
					max = u.obvod();
			}
			return max;
		}

		public double maxObsah()
		{
			double max = 0;
			foreach (Utvar u in utvary)
			{
				if (max < u.obsah())
					max = u.obsah();
			}
			return max;
		}

		new public string ToString()
		{
			string s = "";
			foreach (Utvar u in utvary)
				s += u.ToString() + "\n";
			return s;
		}
	}
}

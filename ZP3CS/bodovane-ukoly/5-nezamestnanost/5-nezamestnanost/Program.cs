using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections;

namespace _5_nezamestnanost
{
	class Program
	{
		static Hashtable mesice = new Hashtable();
		static void Main(string[] args)
		{
			vygenerujNezamestnanost();
			DictionaryEntry max = nejvetsiNezamestnanost();
			DictionaryEntry min = nejmensiNezamestnanost();
			double prumer = prumernaNezamestnanost();

			Console.WriteLine("Největší nezaměstnanost byla v měsíci {0} a to {1} %", max.Key, max.Value);
			Console.WriteLine("Nejmenší nezaměstnanost byla v měsíci {0} a to {1} %", min.Key, min.Value);
			Console.WriteLine("Průměrná nezaměstnanost byla {0} %", prumer);
			Console.ReadKey();
		}

		static DictionaryEntry nejvetsiNezamestnanost()
		{
			DictionaryEntry max = new DictionaryEntry();
			foreach (DictionaryEntry m in mesice)
			{
				if (Convert.ToDouble(m.Value) > Convert.ToDouble(max.Value))
					max = m;
			}
			return max;
		}

		static DictionaryEntry nejmensiNezamestnanost()
		{
			DictionaryEntry min = new DictionaryEntry("MAX", 100);
			foreach (DictionaryEntry m in mesice)
			{
				if (Convert.ToDouble(m.Value) <= Convert.ToDouble(min.Value))
					min = m;
			}
			return min;
		}

		static double prumernaNezamestnanost()
		{
			double prumer = 0;
			foreach (DictionaryEntry m in mesice)
				prumer += Convert.ToDouble(m.Value);
			return prumer / 12;
		}

		static void vygenerujNezamestnanost()
		{
			mesice["Leden"] = 23;
			mesice["Unor"] = 26;
			mesice["Brezen"] = 68;
			mesice["Duben"] = 12;
			mesice["Kveten"] = 3;
			mesice["Cerven"] = 47;
			mesice["Cervenec"] = 60;
			mesice["Srpen"] = 24;
			mesice["Zari"] = 35;
			mesice["Rijen"] = 72;
			mesice["Listopad"] = 23;
			mesice["Prosinec"] = 27;
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	public class IO
	{
		protected static bool colored = true;
		protected static bool numberLines = false;

		/// <summary>
		/// Výstupní zařízení.
		/// Podporované: standardní výstup, textový soubor
		/// Pro výstup do souboru, nastavte do této proměnné cestu
		/// </summary>
		protected static String output = null;


		/// <summary>Vypíše objekt do textového souboru</summary>
		/// <param name="obj">Vypisovaný objekt</param>
		/// <param name="path">Cesta k souboru</param>
		public static void writeToFIle(Object obj, string path)
		{
			System.IO.StreamWriter file = new System.IO.StreamWriter(path);
			file.WriteLine(obj.ToString());
			file.Close();
		}

		/// <summary>Vrátí obsah textového souboru</summary>
		/// <param name="path">Cesta k souboru</param>
		public static List<string> readFile(string path)
		{
			List<string> file = new List<string>();
			System.IO.StreamReader reader = new System.IO.StreamReader(path);

			string line;
			while ((line = reader.ReadLine()) != null)
			{
				file.Add(line);
			}
			reader.Close();

			return file;
		}

		public static bool Colored
		{
			get { return colored; }
			set { IO.colored = value; }
		}

		public static bool NumberLines
		{
			get { return numberLines; }
			set { IO.numberLines = value; }
		}

		public static string Output 
		{
			get { return output; }
			set { IO.output = value; }
		}
	}
}

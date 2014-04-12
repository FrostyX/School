﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace diff_cli
{
	class IO : Diff.IO
	{
		/**
		  * Vypíše objekt na požadovaném rozhraní
		  * Podporované: standardní výstup, textový soubor
		  * @param obj - Vypisovaný objekt
		  * @throws FileNotFoundException - Soubor neexistuje, Nedostatečné oprávnění
		  * @throws IOException
		  */
		public static void write(Object obj)
		{
			Console.Write(obj.ToString());
		}

		/**
		  * Vrátí obarvení textu podle symbolu operace
		  * @param symbol - Symbol operace
		  * @return Řetězec obarvující konzoli daného OS
		  * @see zp3jv.Console.Constants
		  * @see zp3jv.Diff
		  */
		private static ConsoleColor colorForSymbol(string symbol) {
			if(symbol.Equals(Diff.Diff.ADD_SYMBOL)) return ConsoleColor.Green;
			if(symbol.Equals(Diff.Diff.DEL_SYMBOL)) return ConsoleColor.Red;

			Console.ResetColor();
			return Console.ForegroundColor;
		}

		/**
		  * Vypíše virtuální soubor diffu. Na základě hodnot třídy (ne)vypíše čísla řádků a barvičky
		  * @param file - Vypisovaný soubor
		  * @throws FileNotFoundException - Soubor neexistuje, Nedostatečné oprávnění
		  * @throws IOException
		  */
		public static void write(Diff.DiffFile file)
		{
			// Počet cifer posledního číslá řádku
			// http://stackoverflow.com/a/1306751
			int width = (int)(Math.Log10(file.Content[file.Content.Count-1].Number)+1);

			// Pro vytvoření řetězce k uložení do souboru
			// Pokud jde výpis na STDOUT, rovnou se vypisuje
			//    (Příkazová řádká windows nepodporuje ANSI řídící znaky pro barvy,
			//     pouze přímou změnu barvy v konzoli)
			StringBuilder sb = new StringBuilder();

			foreach(Diff.DiffLine line in file.Content) {
				if(colored) {
					Console.ForegroundColor = colorForSymbol(line.Symbol);
				}
				if(numberLines) {
					// Zarovná čísla řádků do sloupečků (podle čísla s největším počtem cifer)
					String f1 = line.Symbol.Equals(Diff.Diff.ADD_SYMBOL) ? "" : Convert.ToString(line.Number);
					String f2 = line.Symbol.Equals(Diff.Diff.DEL_SYMBOL) ? "" : Convert.ToString(line.Number);
					String s = String.Format("{0,-" + width + "} | {1,-" + width + "} | ", f1, f2);

					if(output == null) write(s);
					else sb.Append(s);
				}
				
				if(output == null) write(line.ToString() + Environment.NewLine);
				else sb.Append(line.ToString() + Environment.NewLine);
			}
			if (output != null)
				writeToFIle(sb.ToString(), output);
		}
	}
}
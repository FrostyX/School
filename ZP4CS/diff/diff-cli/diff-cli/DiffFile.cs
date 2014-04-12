using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	/* Reprezentuje virtuální soubor jež je výsledkem porovnání dvou textových souborů
	 * @author Jakub Kadlčík
	 * @version 1.0
	 */
	class DiffFile
	{
		private List<DiffLine> content = new List<DiffLine>();

		/**
		  * Přidá řádek do virtuálního souboru
		  * @param line - Přidávaný řádek
		  */
		public void add(DiffLine line)
		{
			content.Add(line);
		}

		/**
		  * Připojí jeden virtuální soubor na konec druhého
		  * @param line - Přidávaný soubor
		  */
		public void add(DiffFile file) {
			foreach(DiffLine line in file.Content)
				content.Add(line);
		}

		public List<DiffLine> Content
		{
			get { return content; }
		}

		/**
		  * Vrátí obsah celého souboru jako řetězec.
		  */
		public override string ToString() {
			StringBuilder s = new StringBuilder();
			foreach(DiffLine line in content) {
				s.Append(line.ToString() + Environment.NewLine);
			}
			return s.ToString();
		}
	}
}

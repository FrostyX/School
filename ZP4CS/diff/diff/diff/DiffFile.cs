using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	/// <summary>Reprezentuje virtuální soubor jež je výsledkem porovnání dvou textových souborů</summary>
	public class DiffFile
	{
		private List<DiffLine> content = new List<DiffLine>();

		
		/// <summary>Přidá řádek do virtuálního souboru</summary>
		/// <param name="line">Přidávaný řádek</param>
		public void add(DiffLine line)
		{
			content.Add(line);
		}

		/// <summary>Připojí jeden virtuální soubor na konec druhého</summary>
		/// <param name="file">Přidávaný soubor</param>
		public void add(DiffFile file) {
			foreach(DiffLine line in file.Content)
				content.Add(line);
		}

		public List<DiffLine> Content
		{
			get { return content; }
		}

		/// <summary>Vrátí obsah celého souboru jako řetězec.</summary>
		public override string ToString() {
			StringBuilder s = new StringBuilder();
			foreach(DiffLine line in content) {
				s.Append(line.ToString() + Environment.NewLine);
			}
			return s.ToString();
		}
	}
}

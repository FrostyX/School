using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	public class DiffLine
	{
		private int number;
		private string symbol;
		private string text;

		/// <summary>Vytvoří nový řádek virtualního souboru diffu</summary>
		/// <param name="number">Číslo řádku</param>
		/// <param name="symbol">Symbol prováděné operace (Diff.KEEP_SYMBOL, Diff.ADD_SYMBOL, Diff.DEL_SYMBOL)</param>
		/// <param name="text">Text řádku</param>
		public DiffLine(int number, string symbol, string text)
		{
			this.number = number;
			this.symbol = symbol;
			this.text = text;
		}

		public int Number 
		{
			get { return number; }
			set { number = value; }
		}

		public string Symbol 
		{
			get { return symbol; }
			set { symbol = value; }
		}

		public string Text
		{
			get { return text; }
			set { text = value; }
		}


		/*
		 * @TODO
		 */

		// hashCode()
		// equals()

		public override string ToString()
		{
			return symbol + " " + text;
		}
	}
}

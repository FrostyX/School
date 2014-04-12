using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	class DiffLine
	{
		private int number;
		private string symbol;
		private string text;

		/**
		 * Vytvoří nový řádek virtualního souboru diffu
		 * @param number - Číslo řádku
		 * @param symbol - Symbol prováděné operace {@link zp3jv.Diff.KEEP_SYMBOL}, {@link zp3jv.Diff.ADD_SYMBOL}, {@link zp3jv.Diff.DEL_SYMBOL}
		 * @param text - Text řádku
		 */
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

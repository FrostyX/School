using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Minesweeper
{
	// Reprezentuje políčko ve hře "Hledání min"
	class Cell
	{
		// Je v políčku umístěna mina?
		protected bool _mine = false;

		// Co o políčku ví uživatel?
		// Ve výchozím stavu je pro něj políčko neprozkoumané
		protected int _value = CellValues.undiscovered;

		// Souřadnice buňky (na osách x, y)
		protected Axis _axis;


		/*
		 * Gettery a settery
		 */
		public bool mine 
		{
			get { return this._mine; }
			set { this._mine = value; }
		}

		public int value
		{
			get { return this._value; }
			set { this._value = value; }
		}

		public Axis axis 
		{
			get { return this._axis; }
			set { this._axis = value; }
		}
	}
}

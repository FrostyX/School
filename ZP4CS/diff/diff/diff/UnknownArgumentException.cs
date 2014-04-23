using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	public class UnknownArgumentException : Exception
	{
		public UnknownArgumentException(string message) : base(message)
		{
		}
	}
}

using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	class UnknownArgumentException : Exception
	{
		public UnknownArgumentException(string message) : base(message)
		{
		}
	}
}

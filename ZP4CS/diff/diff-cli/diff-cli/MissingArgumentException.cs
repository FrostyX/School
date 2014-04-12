using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace Diff
{
	class MissingArgumentException : Exception
	{
		public MissingArgumentException(string message) : base(message)
		{
		}
	}
}

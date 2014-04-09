using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace diff_cli
{
	class File
	{
		private string path = "";
		private List<string> content = new List<string>();

		public File(string path)
		{
			this.path = path;
			this.content = IO.readFile(path);
		}

		public string Path
		{
			get { return path; }
		}

		public List<string> Content
		{
			get { return content; }
		}
	}
}

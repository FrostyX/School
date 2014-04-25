using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Diff;

namespace diff_cli
{
	class Program
	{
		static void Main(string[] args)
		{
			try
			{
				if ((args.Length > 0) && ((args[0].Equals("-h")) || (args[0].Equals("--help"))))
				{
					printHelp();
					return;
				}

				// Nastaví výstupní parametry podle vstupních požadavků
				IObyArguments(args);

				// Nebyly předány dva soubory
				if (args.Length < 2)
					throw new MissingArgumentException("Missing argument: What files do you want compare?");

				File f1 = new File(args[0]);
				File f2 = new File(args[1]);
				IO.write(Diff.Diff.compare(f1, f2));
			}
			catch (System.IO.FileNotFoundException e)
			{
				Console.WriteLine(e.Message);
			}
			catch (System.IO.IOException e)
			{
				Console.WriteLine(e.StackTrace);
			}
			catch (UnknownArgumentException e)
			{
				Console.WriteLine(e.Message);
			}
			catch (MissingArgumentException e)
			{
				Console.WriteLine(e.Message);
			}
		}


		/// <summary>Nastaví výstupní parametry podle argumentů předaných při spuštění</summary>
		/// <param name="args">Proměnná funkce main - String[] args</param>
		public static void IObyArguments(String[] args)
		{
			// Na indexech 0 a 1 jsou vždy soubory, které se mají porovnat
			for(int i=2; i<args.Length; i++)
			{
				// Po parametru --output následuje hodnota, nikoliv další parametr
				if((i > 2) && (args[i-1].Equals("-o")) || (args[i-1].Equals("--output"))) {
					continue;
				}

				if((args[i].Equals("-nc")) || (args[i].Equals("--no-color"))) {
					IO.Colored = false;
				}
				else if((args[i].Equals("-l")) || (args[i].Equals("--number-lines"))) {
					IO.NumberLines = true;
				}
				else if((args[i].Equals("-o")) || (args[i].Equals("--output"))) {
					if(i >= args.Length-1) {
						throw new MissingArgumentException("Missing argument for: --output");
					}
					IO.Output = args[i+1];
				}
				else {
					throw new UnknownArgumentException("Unknown argument: " + args[i]);
				}
			}
		}

		/// <summary>Vypíše nápovědu pro použití tohoto programu</summary>
		public static void printHelp()
		{
			String help = new StringBuilder()
				.Append("## Info\n")
				.Append("Humble implementation of UNIX tool diff\n\n")

				.Append("## Author\n")
				.Append("Jakub Kadlčík [jakub.kadlcik01@upol.cz]\n\n")

				.Append("## Usage\n")
				.Append("`-nc`, `--no-color`\n")
				.Append("    Do not color the output\n")
				.Append("    (Colors are supported only for UNIX systems.\n")
				.Append("    That means this parameter do nothing on Windows.)\n\n")
				.Append("`-l`, `--number-lines`\n")
				.Append("    Print line numbers\n\n")
				.Append("`-o`, `--output` soubor\n")
				.Append("    Write output to text file\n\n")
				.Append("`-h`, `--help`\n")
				.Append("    Print this help text\n")
				.ToString();

			Console.Write(help);
		}
	}
}

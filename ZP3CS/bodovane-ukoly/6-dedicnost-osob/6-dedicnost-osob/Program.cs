using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace _6_dedicnost_osob
{
	class Program
	{
		static void Main(string[] args)
		{
			Student s = new Student("Jakub Kadlčík", "9305046168", "UPOL", "Informatika");
			s.vypis();
			Console.ReadKey();
		}
	}
}

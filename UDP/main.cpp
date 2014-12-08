#include <iostream>
#include <stdlib.h>
#include <fstream>
#include <sstream>

using namespace std;

#define INPUT_FILE "input.txt"
#define OUTPUT_FILE "output.txt"

int main()
{
	ifstream input(INPUT_FILE);
	ofstream output(OUTPUT_FILE);
	if((!input.is_open()) || (!output.is_open()))
	{
		cout << "Unable to open input or output file" << endl;
	}
	else
	{
		string line;
		while(getline(input, line))
		{
			int n = atoi(line.c_str());

			ostringstream outputLine;
			for(int i=2; i<=n; i++)
			{
				if(n % i == 0)
				{
					n /= i;
					if(outputLine.str() != "") outputLine << " * ";
					outputLine << i;
					i = 1;
				}
			}
			output << line << " = " << outputLine.str() << endl;
		}
		input.close();
		output.close();
	}
	return 0;
}

#include <stdlib.h>
#include <iostream>
#include <sys/stat.h>
#include <unistd.h>
#include <cstring>
#include <cmath>
#include <cstdio>
#include <errno.h>
#include <fcntl.h>
#include <algorithm>

using namespace std;

struct Param
{
	int base;
	int target;
	bool uppercase;
} param = {10, 16, false};

#define PIPE_IN "/tmp/PrevodCiselR"
#define PIPE_OUT "/tmp/PrevodCiselW"
#define BUFFSIZE 512

string dec2num(string dec);
string num2dec(string num);


int main()
{
	// Create the pipes
	int pipeIn = mkfifo(PIPE_IN, S_IRUSR | S_IWUSR);
	int pipeOut = mkfifo(PIPE_OUT, S_IRUSR | S_IWUSR);

	pipeIn = open(PIPE_IN, O_RDWR);
	pipeOut = open(PIPE_OUT, O_RDWR);

	if((pipeIn == -1) || (pipeOut == -1))
	{
		cout << "Unable to start server" << endl;
		cout << strerror(errno) << endl;
	}
	else
	{
		while(true)
		{
			size_t count;

			// Receive message from client
			char buff[BUFFSIZE];
			count = read(pipeIn, &buff, BUFFSIZE);
			buff[count] = '\0';
			string input(buff);

			if(input == "#")
				break;

			string output = "";
			switch(input[0])
			{
				case '.': param.base = atoi(input.substr(1).c_str()); break;
				case ':':
				{
					switch(input[1])
					{
						case 'm': param.uppercase = 0; break;
						case 'v': param.uppercase = 1; break;
						default: param.target = atoi(input.substr(1).c_str()); break;
					}
					break;
				}
				default:
				{
					output = dec2num(num2dec(input));
				}
			}

			// Send message to client
			count = write(pipeOut, output.c_str(), BUFFSIZE);
		}
	}

	// Close the pipe
	unlink(PIPE_IN);
	unlink(PIPE_OUT);

	cout << "Quiting server" << endl;
	return 0;
}


string num2dec(string num)
{
	int dec = 0;
	for(int i=num.length()-1, j=0; i>=0; i--, j++)
	{
		// http://stackoverflow.com/a/13534586/3285282
		dec += (num[i]-'0') * pow(param.base, j);
	}
	char output[BUFFSIZE];
	sprintf(output, "%d", dec);
	return output;
}

string dec2num(string dec)
{
	char num[BUFFSIZE];
	int offset = (param.uppercase ? 'A' : 'a') - '9' - 1;
	int d = atoi(dec.c_str());
	int i = 0;

	while(d != 0)
	{
		char modulo[BUFFSIZE];
		sprintf(modulo, "%d", d % param.target);
		num[i] = strlen(modulo) == 1 ? modulo[0] : (atoi(modulo) + '0') + offset;
		d /= param.target;
		i++;
	}
	num[i] = '\0';
	string output(num);
	reverse(output.begin(), output.end());
	return output;
}

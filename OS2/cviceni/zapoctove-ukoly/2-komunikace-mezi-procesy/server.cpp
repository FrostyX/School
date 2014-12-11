#include <stdlib.h>
#include <iostream>
#include <sys/stat.h>
#include <unistd.h>
#include <cstring>
#include <errno.h>
#include <fcntl.h>

using namespace std;

struct Param
{
	string base;
	string target;
	bool uppercase;
	string number;
} param = {"10", "16", false, "0"};

#define PIPE_IN "/tmp/PrevodCiselR"
#define PIPE_OUT "/tmp/PrevodCiselW"
#define BUFFSIZE 512

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
				case '.': param.base = input; break;
				case ':':
				{
					switch(input[1])
					{
						case 'm': param.uppercase = 0; break;
						case 'v': param.uppercase = 1; break;
						default: param.target = input; break;
					}
					break;
				}
				default:
				{
					param.number = input;
					output = "BAR";
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

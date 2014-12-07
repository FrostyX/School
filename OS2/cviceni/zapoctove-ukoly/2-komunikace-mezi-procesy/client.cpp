#include <iostream>
#include <fcntl.h>
#include <unistd.h>
#include <cstring>
#include <errno.h>

using namespace std;

#define PIPE_IN "/tmp/PrevodCiselW"
#define PIPE_OUT "/tmp/PrevodCiselR"
#define BUFFSIZE 512

int main()
{
	int pipeIn = open(PIPE_IN, O_RDWR);
	int pipeOut = open(PIPE_OUT, O_RDWR);
	if((pipeIn == -1) || (pipeOut == -1))
	{
		cout << "Unable to connect" << endl;
		cout << strerror(errno) << endl;
	}
	else
	{
		while(true)
		{
			string input;
			cout << ">> ";
			cin >> input;

			// Send to server
			//write(pipeOut, input.c_str(), 1);
			size_t count = write(pipeOut, "25", 2);
			cout << count << endl;

			// Print response
			// char buff[BUFFSIZE];
			// size_t count;
			// while((count = read(pipeIn, buff, BUFFSIZE)) <= 0) { ; }
			// buff[count] = '\0';
			// cout << buff;

			break;
		}
	}
	return 0;
}

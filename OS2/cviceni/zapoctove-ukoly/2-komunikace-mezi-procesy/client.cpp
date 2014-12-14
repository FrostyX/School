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
			size_t count;

			// Read number from user
			cout << ">> ";
			cin >> input;

			// Send to server
			count = write(pipeOut, input.c_str(), BUFFSIZE);

			if(input == "#")
				break;

			// Get response from server
			char buff[BUFFSIZE];
			count = read(pipeIn, &buff, BUFFSIZE);
			buff[count] = '\0';
			input = buff;

			if(input.length())
			{
				cout << input << endl;
			}
		}
	}
	return 0;
}

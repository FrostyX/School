#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <netdb.h>
#include <iostream>
#include <arpa/inet.h>

using namespace std;

int main (int argc, char * const argv[]) {
	if (argc < 0)
	{
		cout << "syntax: 8-resolveip google.com" << endl;
		return 1;
	}

	for(int i = 1; i<argc; i++)
	{
		struct hostent *h;
		h = NULL;
		if ((h = gethostbyname(argv[i])) == NULL)
		{
			cout << "Host not found" << endl;
			continue;
		}

		cout << "Hostname: " << h->h_name << endl << endl;
		cout <<"Aliases: " << endl;

		int j = 0;
		while (h->h_addr_list[j] != NULL)
		{
			struct hostent *hs;
			hs = gethostbyaddr(h->h_addr_list[j], sizeof(h->h_addr_list[j]), AF_INET);
			cout << inet_ntoa(*(in_addr *)h->h_addr_list[j]) << " -> " << hs->h_name  << endl;
			j++;
		}
	}
	return 0;
}

#include <iostream>
#include <sys/socket.h>
#include <sys/types.h>
#include <linux/if_ether.h>
#include <netpacket/packet.h>
#include <netinet/in.h>
#include <netinet/ip.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <iomanip>
#include <sstream>

using namespace std;

#define MAX 65536


string protocol(int p);
string mac(sockaddr_ll addr);

int main(int argc, char *argv[])
{
	// Argumenty z konzole
	int interface = -1;
	if(argc == 2)
		interface = atoi(argv[1]);

	int sock, lenght, count;
	unsigned short int frag_off;
	char buffer[MAX];
	sockaddr_ll addr;
	size_t size;
	iphdr *ip;
	register char mf;
	register short int offset;
	fd_set mySet;

	if((sock = socket(PF_PACKET, SOCK_DGRAM, htons(ETH_P_IP))) == -1)
	{
		cout << "socket fail" << endl;
		return 1;
	}


	// Hlavička tabulky
	cout    << setw(4)  << "No."         << " |"
		<< setw(20) << "Destination" << " |"
		<< setw(20) << "Source"      << " |"
		<< setw(20) << "Source MAC"  << " |"
		<< setw(8)  << "Version"     << " |"
		<< setw(9)  << "Protocol"    << " |"
		<< setw(4)  << "TTL"         << " |"
		<< setw(12) << "Header size" << " |"
		<< setw(12) << "Packet size" << " |"
		<< endl;

	// Podtržení hlavičky
	for(int i=0; i<=126; i++) cout << "-"; cout << endl;

	int no = 1;
	while(true)
	{
		FD_ZERO(&mySet);
		FD_SET(sock, &mySet);
		if (select(sock + 1, &mySet, NULL, NULL, NULL) == -1)
		{
			cout << "select fail" << endl;
			close(sock);
			return 2;
		}
		size = sizeof(addr);
		if ((lenght = recvfrom(sock, buffer, MAX, 0, (sockaddr *)&addr, &size)) == -1)
		{
			cout << "recvfrom fail" << endl;
			close(sock);
			return 3;
		}

		if((interface < 0) || (interface == addr.sll_ifindex))
		{
			ip = (iphdr*)buffer;
			string dst=strdup(inet_ntoa(*(in_addr*)&ip->daddr));
			string src=strdup(inet_ntoa(*(in_addr*)&ip->saddr));

			cout    << setw(4)  << no << " |"
				<< setw(20) << dst << " |"
				<< setw(20) << src << " |"
				<< setw(20) << mac(addr) << " |"
				<< setw(7)  << "IPv" << ip->version << " |"
				<< setw(9)  << protocol(ip->protocol) << " |"
				<< setw(4)  << to_string(ip->ttl) << " |"
				<< setw(12)  << ip->ihl * 4 << " |"
				<< setw(12)  << ntohs(ip->tot_len) << " |"
				<< endl;

			no++;
		}
	}
	close(sock);
	return 0;
}


string protocol(int p)
{
	switch (p)
	{
		case IPPROTO_UDP: { return "UDP"; }
		case IPPROTO_TCP: { return "TCP"; }
		case IPPROTO_ICMP: { return "ICMP"; }
		default: { return "Unknown"; }
	}
}

string mac(sockaddr_ll addr)
{
	if (addr.sll_halen == 0)
		return "Unknown";

	int size = 6;
	char **m = new char*[size];
	for(int i=0; i<size; i++)
		m[i] = new char[2];

	for(int i=0; i<addr.sll_halen; i++)
		sprintf(m[i], "%x", addr.sll_addr[i]);

	string mac = "";
	for(int i=0; i<size; i++)
	{
		mac += m[i];
		if(i<size-1)
			mac += ":";
	}
	return mac;
}

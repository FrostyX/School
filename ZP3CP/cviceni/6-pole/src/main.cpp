#include <iostream>
#include "Pole.cpp"

using namespace std;

int main(int argc, char **argv)
{
	Pole p(0, 8);
	p += 3;
	p += 4;
	p += 2;

	Pole q(0, 2);
	q += 6;
	q += 7;

	cout << "*p[0] = " << *p[0] << endl;
	cout << "!p = " << !p << endl;
	cout << "+p = " << +p << endl;

	p += q;

	p -= 4;

	for(int i=0; i<8; i++)
	{
		cout << *p[i] << endl;
	}


	return 0;
}

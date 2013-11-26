#include <iostream>
#include "Zlomek.cpp"

using namespace std;

int main(int argc, char **argv)
{
	cout << endl;

	Zlomek zlomky[] =
	{
		Zlomek(5,4),
		Zlomek(6,14),
		Zlomek(5,3),
		Zlomek(9,1),
		Zlomek(5,9),
		Zlomek(4,12),
		Zlomek(5,1),
		Zlomek(2,7),
		Zlomek(3,2),
		Zlomek(1,1),
	};

	bubble_sort(zlomky ,10);
	for(int i=0;i<10;i++)
	{
		cout << zlomky[i]() << " ";
	}
	cout << endl;
	return 0;
}

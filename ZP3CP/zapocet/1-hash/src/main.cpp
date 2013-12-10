#include <iostream>
#include "Hash.cpp"
#include "HashZlomky.cpp"

using namespace std;

template<unsigned n>
void najit(HashZlomky<n> &hz, const Zlomek &z);

int main(int argc, char **argv)
{
	typedef Zlomek Z;

	HashZlomky<11> hz;
	hz
		<< Z(1, 3)
		<< Z(3, 4)
		<< Z(4, 3)
		<< Z(3, 5)
		<< Z(3, 7)
		<< Z(9, 5)
		<< Z(1, 2)
		<< Z(2, 5)
		<< Z(1, 5)
	;
	cout << "V tabulce je " << +hz << " zlomku." << endl;

	hz.prvni();
	//do
		//hz.aktual()();
	//while(hz.dalsi());

	najit(hz, Z(3, 7));
	najit(hz, Z(2, 5));
	najit(hz, Z(9, 5));
	najit(hz, Z(2, 3));

	return 0;
}

template<unsigned n>
void najit(HashZlomky<n> &hz, const Zlomek &z)
{
	auto p = hz.najit(z);
	if (!p)
	{
		cout << "nenalezen: ";
		z();
	}
	else
	{
		cout << "nalezen: ";
		(*p)();
	}
}

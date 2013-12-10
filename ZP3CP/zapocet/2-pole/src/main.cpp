#include <iostream>
#include "UsporadanePole.cpp"
#include "Zlomek.cpp"

using namespace std;

template<class T, unsigned n>
void najit(UsporadanePole<T, n> &pz, const Zlomek &z);

int main(int argc, char **argv)
{
	typedef Zlomek Z;

	UsporadanePole<Zlomek, 20> pz;
	pz
		<< Z(1, 3)
		<< Z(3, 5)
		<< Z(7, 4)
		<< Z(3, 4)
		<< Z(2, 3)
		<< Z(7, 2)
		<< Z(5, 4)
		<< Z(1, 4)
		<< Z(6, 7)
		<< Z(4, 3)
		<< Z(2, 3)
	;

	pz -= Z(6, 7);
	pz -= Z(2, 7);
	cout << "pocet " << pz << endl;

	while (pz.dalsi())
	{
		pz.aktual()();
	}

	najit(pz, Z(1, 4));
	najit(pz, Z(7, 2));
	najit(pz, Z(1, 2));

	return 0;
}

template<class T, unsigned n>
void najit(UsporadanePole<T, n> &pz, const Zlomek &z)
{
	auto p = pz.najit(z);
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

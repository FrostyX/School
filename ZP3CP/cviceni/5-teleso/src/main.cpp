#include <iostream>
#include <typeinfo>
#include "Telesa.cpp"

using namespace std;

int main(int argc, char **argv)
{
	Teleso *t[3] =
	{
		new Kvadr(2, 3, 3),
		new Valec(2, 3),
		new Kuzel(2, 3)
	};

	for(int i=0; i<3; i++)
	{
		cout << typeid(*t[i]).name() << " " << t[i]->objem() << endl;
	}

	return 0;
}

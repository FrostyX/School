#include <iostream>
#include <typeinfo>
#include <cxxabi.h>
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
		// http://stackoverflow.com/a/2216993
		int status;
		char *name = abi::__cxa_demangle(typeid(*t[i]).name(), 0, 0, &status);
		cout << name << " " << t[i]->objem() << endl;
	}

	return 0;
}

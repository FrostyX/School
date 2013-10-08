#include <iostream>
#include "KvadratickaRovnice.h"

using namespace std;

int main(int argc, char **argv)
{
	int a, b, c;
	cout << "Strana a: ";
	cin >> a;

	cout << "Strana b: ";
	cin >> b;

	cout << "Strana c: ";
	cin >> c;

	KvadratickaRovnice r(a, b, c);
	int korenu = r.pocetKorenu();
	if(korenu == 0)
		cout << "Rovnice nemá řešení v R" << endl;
	else if(korenu == 1)
		cout << "Řešením je " << r.koren1() << endl;
	else
		cout << "Řešením je " << r.koren1() << " a " << r.koren2() << endl;

	return 0;
}

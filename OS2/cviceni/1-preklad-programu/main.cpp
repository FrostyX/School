#include <iostream>
#include "fce.h"

using namespace std;

int main()
{
	int a, b;
	cout << "Zadejte a: ";
	cin >> a;
	cout << "Zadejte b: ";
	cin >> b;
	cout << nejvetsiSpolecnyDelitel(a, b) << endl;
}

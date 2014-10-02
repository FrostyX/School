#include<iostream>
#include "slovo.h"
#include "veta.h"
#include "strfce.h"

using namespace std;

int main()
{
	cout << "Věta: " << veta << endl;
	cout << "Slovo: " << slovo << endl;
	cout << "Počet výskytů: " << pocet_vyskytu(veta, slovo) << endl;
	return 0;
}

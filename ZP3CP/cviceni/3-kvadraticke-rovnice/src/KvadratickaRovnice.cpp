#include "KvadratickaRovnice.h"

KvadratickaRovnice::KvadratickaRovnice(double a, double b, double c)
{
	this->a = a;
	this->b = b;
	this->c = c;
}

int KvadratickaRovnice::pocetKorenu()
{
	double d = this->diskriminant();
	if(d==0)     return 1;
	else if(d>0) return 2;
	else         return 0;
}

double KvadratickaRovnice::koren1()
{
	return (-b + sqrt(this->diskriminant())) / (2*a);
}

double KvadratickaRovnice::koren2()
{
	return (-b - sqrt(this->diskriminant())) / (2*a);
}

double KvadratickaRovnice::diskriminant()
{
	return (this->b * this->b) - (4 * this->a * this->c);
}

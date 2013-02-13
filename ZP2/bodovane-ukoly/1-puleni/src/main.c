/* Zadání:
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int puleni(int cisla[], int a, int b, int hledane);

int main(int argc, char **argv)
{
	int cisla[] = {1, 5, 8, 9, 10, 14, 15, 18};

	int i;
	for(i=0; i<20; i++)
	{
		printf("Hodnota %i je na indexu %i\n", i, puleni(cisla, 0, 7, i));
	}

	return 0;
}

int puleni(int cisla[], int a, int b, int hledane)
{
	if(a>b) return -1;

	int iProstredni = (a+b)/2;
	if(cisla[iProstredni]==hledane)
		return iProstredni;
	else if(hledane<cisla[iProstredni])
		return puleni(cisla, a, iProstredni-1, hledane);
	else
		return puleni(cisla, iProstredni+1, b, hledane);
}

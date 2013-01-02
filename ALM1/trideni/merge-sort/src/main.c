/* Zadání: Implementace algoritmu merge-sort
 * Řazení vzestupně
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	int cisla[] = {8, 9, 7, 3, 2, 6, 10, 5};
	int n = sizeof(cisla)/sizeof(int);
	mergesort(cisla, 0, n-1);
	return 0;
}

int mergesort(int a[], int zacatek, int konec)
{

}

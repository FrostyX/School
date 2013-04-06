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

void statistika_roku(int pocet, pole_funkci* fce, double *udaje);
long double prumer(int pocet, ...);
long double median(int pocet, ...);
long double rozptyl(int pocet, ...);

typedef struct pole_funkci
{
	long double(*p)(int pocet,...);

	/* Identifikace funkce
	 * 'p' - průměr
	 * 'm' - medián
	 * 'r' - rozptyl
	 */
	char typ;
};

int main(int argc, char **argv)
{
	return 0;
}

void statistika_roku(int pocet, pole_funkci* fce, double *udaje)
{
}

long double prumer(int pocet, ...)
{
}

long double median(int pocet, ...)
{
}

long double rozptyl(int pocet, ...)
{
}

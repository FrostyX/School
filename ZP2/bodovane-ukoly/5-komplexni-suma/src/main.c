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
#include <stdarg.h>

typedef struct
{
	double r;
	double c;
} komplexni;

komplexni suma(int pocet, ...);

int main(int argc, char **argv)
{
	komplexni c1 = {4, 2};
	komplexni c2 = {5, 3};
	komplexni c3 = {7, 8};
	komplexni vysledek = suma(3, c1, c2, c3);

	printf("Suma: %g+%gi", vysledek.r, vysledek.c);
	return 0;
}

komplexni suma(int pocet, ...)
{
	komplexni out = {0, 0};
	va_list param;
	va_start(param, pocet);

	int i;
	for(i=0; i<pocet; i++)
	{
		komplexni tmp = va_arg(param, komplexni);
		out.r += tmp.r;
		out.c += tmp.c;
	}

	va_end(param);
	return out;
}

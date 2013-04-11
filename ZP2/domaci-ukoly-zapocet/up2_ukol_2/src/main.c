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

//typedef struct pole_funkci
typedef struct
{
	long double(*p)(int pocet, ...);

	/* Identifikace funkce
	 * 'p' - průměr
	 * 'm' - medián
	 * 'r' - rozptyl
	 */
	char typ;
} pole_funkci;

void statistika_roku(int pocet, pole_funkci *fce, double *udaje);
long double prumer(int pocet, ...);
long double median(int pocet, ...);
long double rozptyl(int pocet, ...);
void bubbleSort(double cisla[], int n);

int main(int argc, char **argv)
{
	double udaje[] = {9.7, 9.6, 9.2, 8.6, 8.2, 8.1, 8.2, 8.2, 8.0, 7.9, 8.0, 8.6};

	pole_funkci fce[] =
	{
		{prumer, 'p'},
		{median, 'm'},
		{rozptyl, 'r'}
	};

	printf("Vývoj nezaměstnanosti v roce 2011\n");
	statistika_roku(3, fce, udaje);

	return 0;
}

// Údaje mají být vypsány v této funkce a ne v main?
void statistika_roku(int pocet, pole_funkci *fce, double *udaje)
{
	char *mesice[] =
	{
		"leden",
		"únor",
		"březen",
		"duben",
		"květen",
		"červen",
		"červenec",
		"srpen",
		"září",
		"říjen",
		"listopad",
		"prosinec"
	};
	int mesicu = sizeof(mesice)/sizeof(char*);

	// Výpis údajů za jednotlivé měsíce
	int i;
	for(i=0; i<mesicu; i++)
	{
		printf("%s: %.1f\n", mesice[i], udaje[i]);
	}
	printf("\n");

	// Vypočítání a výpis výsledků funkcí
	for(i=0; i<pocet; i++)
	{
		// Vypočítání průměru, mediánu a rozptylu
		long double x = fce[i].p(mesicu, udaje[0], udaje[1], udaje[2], udaje[3], udaje[4], udaje[5], udaje[6], udaje[7], udaje[8], udaje[9], udaje[10], udaje[11]);

		// Vypsání vypočítané hodnoty
		switch(fce[i].typ)
		{
			case 'p':
			{
				printf("Průměr je: %.2Lf\n", x);
				break;
			}
			case 'm':
			{
				printf("Medián je: %.2Lf\n", x);
				break;
			}
			case 'r':
			{
				printf("Rozptyl je: %.2Lf\n", x);
				break;
			}
		}
	}
}

long double prumer(int pocet, ...)
{
	double prumer = 0;
	va_list param;
	va_start(param, pocet);

	int i;
	for(i=0; i<pocet; i++)
	{
		prumer += va_arg(param, double);
	}
	va_end(param);

	prumer = prumer / pocet;
	return prumer;
}

long double median(int pocet, ...)
{
	double hodnoty[pocet];
	va_list param;
	va_start(param, pocet);

	// Hodnoty uložíme, abychom je mohli seřadit
	int i;
	for(i=0; i<pocet; i++)
	{
		hodnoty[i] = va_arg(param, double);
	}
	va_end(param);

	// Vzestupně seřadíme hodnoty
	bubbleSort(hodnoty, pocet);

	// Podle definice mediánu
	return pocet%2 ?
		hodnoty[(int)pocet/2] :
		prumer(2, hodnoty[(pocet/2)], hodnoty[pocet/2])
	;
}

long double rozptyl(int pocet, ...)
{
	double suma = 0;
	double prumer = 0;
	va_list param;
	va_start(param, pocet);

	int i;
	for(i=0; i<pocet; i++)
	{
		double x = va_arg(param, double);
		suma += x*x;
		prumer += x;
	}
	va_end(param);

	prumer = prumer / pocet;
	return (((double)1/pocet)*suma)-prumer*prumer;
}

void bubbleSort(double cisla[], int n)
{
	int i, j;
	for(j=0; j<n-1; j++)
	{
		// Posunutí jednoho prvku nakonec
		for(i=0; i<n-j-1; i++)
		{
			// Pokud je číslo větší než následující
			if(cisla[i]>cisla[i+1])
			{
				// Prohodíme čísla
				double tmp = cisla[i+1];
				cisla[i+1] = cisla[i];
				cisla[i] = tmp;
			}
		}
	}
}

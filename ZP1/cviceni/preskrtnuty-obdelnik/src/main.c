/* Zadání: V jazyce C vytvořte program, který po zadání znaku (libovolný tisknutelný znak) a velikosti
obrazce (liché číslo větší nebo rovno než 3) vykreslí za pomoci daného znaku v konzoli
„přeškrtnutý obdélník“ zadané velikosti.
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
	// Výška obdélníku. (šířka je 2x větší než výška)
	int vyska;
	printf("Zadejte výšku obdélníku: ");
	scanf("%i", &vyska);

	// Vstup je ze zadání omezen
	if((vyska<3) || !(vyska%2))
	{
		printf("Výška musí být liché číslo větší nebo rovno třem\n");
		return 0;
	}


	// Vypíšeme jednotlivé řádky obdélníku
	int j;
	for(j=0; j<vyska; j++)
	{
		// Vypíšeme řádek obdélníku
		int i;
		for(i=0; i<2*vyska; i++)
		{
			// Pokud vypisujeme první nebo poslední řádek, nebo první nebo poslední soupec, nebo přeškrtnutí
			printf("%c", ((j==0) || (j==vyska-1) || (i==0) || (i==vyska*2-1) || (i==2*j) || (i==2*j+1)) ? 'x' : ' ');
		}

		// Nový řádek obdélníku
		printf("\n");
	}

	return 0;
}

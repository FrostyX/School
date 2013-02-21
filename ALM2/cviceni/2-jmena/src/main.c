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
#include "Jmena.h"

void sort(const char *x[], int n);
void fastSwap (const char **i, const char **d);
int indexJmena(char jmeno[], const char *pole[], int n);

int main(int argc, char **argv)
{
	sort(Jmena, Pocet);
	char jmeno[50];

	// Cyklus je ukončen podmínkou v jeho těle
	while(1)
	{
		printf("Zadej jméno: ");
		scanf("%s", jmeno);

		// http://www.cplusplus.com/reference/cctype/isalpha/
		if(!isalpha(jmeno[0]))
			break;

		int pozice = indexJmena(jmeno, Jmena, Pocet);
		if(pozice>=0)
			printf("Pozice: %i; Jmeno: %s\n", pozice, jmeno);
		else
			printf("Jméno %s nebylo nalezeno\n", jmeno);
	}
	return 0;
}

/* Bublinkové setřízení
 * x - pole prvků
 * n - počet prvků v poli
 */
void sort(const char *x[], int n)
{
	int i,j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n-i-1; j++)
		{
			// strcmp - http://www.cplusplus.com/reference/cstring/strcmp/
			if(strcmp(x[j], x[j+1])>0)
			{
				// Prohození - http://stackoverflow.com/questions/3816233/swapping-two-string-pointers
				const char *tmp = x[j];
				x[j] = x[j+1];
				x[j+1] = tmp;
			}
		}
	}
}

/* Vrátí index na kterém se jméno v poli nachází
 * n - počet prvků
 */
int indexJmena(char *jmeno, const char *pole[], int n)
{
	int i;
	for(i=0; i<n; i++)
	{
		if(!strcmp(jmeno, pole[i]))
		{
			return i;
		}
	}
	return -1;
}

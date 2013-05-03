/* Zadání: ../doc/Zapoctova uloha.pdf
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>
#include "tree.h"



int main(int argc, char **argv)
{
	int pocty[] = {100, 300, 1000, 3000, 10000, 30000, 100000};
	int n = pocty[sizeof(pocty)/sizeof(int)-1]; // Poslední hodnota z pocty[]
	int iPocty=0;

	Node *T = NULL;

	printf("Pocet | Prum | Vyska | Podil\n");
	printf("----------------------------\n");
	int i;
	for(i=1; i<=n; i++)
	{
		Insert(&T, rand()%1000000);
		if(i==pocty[iPocty])
		{
			printf("%i | %0.2f | %i | %0.2f\n", i, 0.0, Height(T), 0.0);
			iPocty++;
		}
	}

	return 0;
}


/* Zadání: V jazyce C vytvořte program, který po zadání znaku
 *    (libovolný tisknutelný znak) a velikosti obrazce
 *    (liché číslo větší nebo rovno než 3)
 *    vykreslí za pomoci daného znaku v konzoli „domeček“ zadané velikosti.
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
	// Šířka domečku
	int velikost;
	printf("Zadejte šírku domecku: ");
	scanf("%i", &velikost);

	// Šířka domečku musí být liché číslo větší nebo rovno 3
	if(!(velikost%2) || (velikost<3))
	{
		printf("Šířka domečku musí být liché číslo větší nebo rovno třem\n");
		return 0;
	}

	// Pozice na které bude začínat špička střechy
	int spicka = (velikost/2);

	// Výpis celého domečku
	// velikost je výška a šířka čtverce ze kterého domeček je
	// spicka+1 reprezentuje střechu
	int i;
	for(i=0; i<velikost+spicka+1; i++)
	{
		// Výpis jednoho řádku
		int j;
		for(j=0; j<velikost; j++)
		{
			// Pokud vypisujeme střechu
			if(i<spicka+1)
			{
				// Každým řádkem bude x vzdálenější špičce (po ose x)
				if((j==spicka-i) || (j==spicka+i))
					printf("x");
				else
					printf(" ");
			}
			// Pokud vypisujeme zdi
			else
			{
				// Pokud vypisujeme první, nebo poslední řádek čtverce
				//    nebo pokud vypisujeme první, nebo poslední sloupec ve čtverci
				if((i==spicka+1) || (i==velikost+spicka) || (j==0) || (j==velikost-1))
					printf("x");
				else
					printf(" ");
			}
		}
		printf("\n");
	}

	return 0;
}

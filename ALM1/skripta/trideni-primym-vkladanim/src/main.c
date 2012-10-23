/* Zadání: Implementace algoritmu třízení přímým vkládáním
 * Řazení vzestupně
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	// Deklarace pole, které budeme třídit, s inicializací výchozích prvků
	int cisla[] = {4, 2, 3, 10, 1, 7};

	// Počet čísel v poli
	int count = sizeof(cisla)/sizeof(int);

	// Určíme hranici mezi setřízenou a nesetřízenou částí
	int setrizenych = 1;

	// Pro dočasné uložení prvního prvku nesetříděné části
	int tmp;

	// Projdeme nesetřízenou část pole
	int i, j;
	for(i=setrizenych; i<count; i++)
	{
		// Uložíme si první prvek z nesetříděné části
		tmp = cisla[setrizenych];

		// Od konce projdeme setřízenou část pole
		for(j=setrizenych-1; j>=0; j--)
		{
			// Pokud je prvek ze setřízené části větší než zapamatovaný
			if(cisla[j]>tmp)
				cisla[j+1] = cisla[j];

			// Pokud je celá setřízená část porovnána, nebo
			//    je zapamatovaný prvek >= prvek ze setřízené části
			if((j==0) || (cisla[j]<=tmp))
			{
				cisla[j] = tmp;
				break;
			}
		}

		// Posuneme hranici mezi setřízenou a nesetřízenou částí pole
		setrizenych++;
	}

	// Výpis seřazeného pole
	for(i=0; i<count; i++)
		printf("%i\n", cisla[i]);

	return 0;
}

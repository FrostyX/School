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


	// Výpis seřazeného pole
	int i;
	for(i=0; i<count; i++)
		printf("%i\n", cisla[i]);

	return 0;
}

int shellSort(int cisla[], int n)
{
	// Pro dočasné uložení prvního prvku nesetříděné části
	int tmp;

	// Projdeme nesetřízenou část pole
	int i, j;
	for(i=1; i<n; i++)
	{
		// Uložíme si první prvek z nesetříděné části
		tmp = cisla[i];

		// Od konce projdeme setřízenou část pole
		for(j=i; j>0; j--)
		{
			// Pokud je prvek ze setřízené části větší než zapamatovaný
			if(cisla[j-1]>tmp)
			{
				cisla[j] = cisla[j-1];
				continue;
			}

			// Srovnávání skončí když je zapamatovaná hodnota
			//    ze setřízené části je <= zapamatovaná hodnota z nesetříděné
			break;
		}
		cisla[j] = tmp;
	}
}

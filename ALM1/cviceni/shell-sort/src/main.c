/* Zadání: Implementace algoritmu shellsort
 * Řazení vzestupně
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

int shellSort(int cisla[], int n);

int main(int argc, char **argv)
{
	// Deklarace pole, které budeme třídit, s inicializací výchozích prvků
	int cisla[] = {7, 6, 5, 4, 3, 2, 1, 0};

	// Počet čísel v poli
	int count = sizeof(cisla)/sizeof(int);

	// Setřízení pole pomocí algoritmu shellsort
	shellSort(cisla, count);

	// Výpis seřazeného pole
	int i;
	for(i=0; i<count; i++)
		printf("%i\n", cisla[i]);

	return 0;
}

int shellSort(int cisla[], int n)
{
	int h;
	for(h=n/2; h>0; h=h/2)
	{
		// Projdeme nesetřízenou část pole
		int i, j;
		for(i=h; i<n; i++)
		{
			// Pro dočasné uložení prvního prvku nesetříděné části
			int tmp;

			// Uložíme si první prvek z nesetříděné části
			tmp = cisla[i];

			// Od konce projdeme setřízenou část pole
			for(j=i; j>0; j=j-h)
			{
				// Pokud bychom chtěli sáhnout před první prvek pole
				if(j-h<0)
					break;

				// Pokud je prvek ze setřízené části větší než zapamatovaný
				if(cisla[j-h]>tmp)
				{
					cisla[j] = cisla[j-h];
					continue;
				}

				// Srovnávání skončí když je zapamatovaná hodnota
				//    ze setřízené části je <= zapamatovaná hodnota z nesetříděné
				break;
			}
			cisla[j] = tmp;
		}
	}
}

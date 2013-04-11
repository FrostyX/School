/* Zadání: Implementace algoritmu třízení přímou výměnou (bublinkové třízení)
 * Řazení vzestupně
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

void bubbleSort(int cisla[], int n);

int main(int argc, char **argv)
{
	// Deklarace pole, které budeme třídit, s inicializací výchozích prvků
	int cisla[] = {4, 2, 3, 10, 1, 7};

	// Počet čísel v poli
	int count = sizeof(cisla)/sizeof(int);

	// Seřazení pole
	bubbleSort(cisla, count);

	// Výpis seřazeného pole
	int i;
	for(i=0; i<count; i++)
		printf("%i\n", cisla[i]);

	return 0;
}

void bubbleSort(int cisla[], int n)
{
	// Průchod neseřazenou částí pole
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
				int tmp = cisla[i+1];
				cisla[i+1] = cisla[i];
				cisla[i] = tmp;
			}
		}
	}
}

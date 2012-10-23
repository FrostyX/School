/* Zadání: Implementace algoritmu třízení přímou výměnou (bublinkové třízení)
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

	// Počet pozic, které je potřeba projít a porovnat
	int projit = count;

	// Průchod neseřazenou částí pole
	int i, j;
	for(j=0; j<=projit; j++)
	{
		// Posunutí jednoho prvku nakonec
		for(i=0; i<=projit; i++)
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

		// Příště nám bude stačit projít méně prvků,
		//    protože některé jsou již seřazeny
		projit--;
	}

	// Výpis seřazeného pole
	for(i=0; i<count; i++)
		printf("%i\n", cisla[i]);

	return 0;
}

/* Zadání: Implementace algoritmu třízení přímým výběrem
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

	// Počet setřízených prvků
	int i,j;
	for(i=0; i<count; i++)
	{
		// Zapamatujeme si pozici prvního prvku v nesetřízené části
		int tmp_pozice = i;

		// Projdeme nesetřízenou část
		// První prvek máme již zapamatován
		for(j=i+1; j<count; j++)
		{
			if(cisla[j]<cisla[tmp_pozice])
				tmp_pozice = j;
		}

		// Prohodíme nejmenší nalezený prvek
		//    s prvním prvkem v nesetřízené části.
		int tmp = cisla[i];
		cisla[i] = cisla[tmp_pozice];
		cisla[tmp_pozice] = tmp;
	}

	// Výpis seřazeného pole
	for(i=0; i<count; i++)
		printf("%i\n", cisla[i]);

	return 0;
}

/* Zadání: V programu deklarujte jednorozměrné pole,
 * naplněte ho čísly a vypište hodnoty čísel
 * v tomto poli na obrazovku.
 * Poté obraťte pořadí čísel v tomto poli
 * (tj. první bude původně poslední číslo,
 * druhé původně předposlední atd.)
 * a hodnoty ve změněném poli vypište na obrazovku.
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	int pole[5] = {1, 2, 3, 4, 5};
	int i;

	// Výpis původních hodnot
	printf("Původní hodnoty: ");
	for(i=0; i<5; i++)
		printf("%i ", pole[i]);
	printf("\n");


	// Převrácení pole
	int tmp;
	int j;
	for(i=0, j=4; i<5/2; i++, j--)
	{
		tmp = pole[j];
		pole[j] = pole[i];
		pole[i] = tmp;
	}

	// Výpis nových hodnot
	printf("Nové hodnoty: ");
	for(i=0; i<5; i++)
		printf("%i ", pole[i]);

	printf("\n");
	return 0;
}

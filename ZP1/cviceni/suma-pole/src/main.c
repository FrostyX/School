/* Zadání: Definujte v jazyku C funkci odpovídající deklaraci
 *    double suma_pole(double pole[], int pocet),
 *    která vypočítá a vrátí součet čísel v daném poli.
 *    Parametr pole slouží pro předání pole čísel,
 *    parametr pocet pak odpovídá počtu čísel v předávaném poli.
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

double suma_pole(double pole[], int pocet);

int main(int argc, char **argv)
{
	double cisla[] = {1, 5.5, 8.33, 3};
	printf("%.3f\n", suma_pole(cisla, 4));
	return 0;
}

double suma_pole(double pole[], int pocet)
{
	double suma = 0;
	int i;
	for(i=0; i<pocet; i++)
	{
		suma += pole[i];
	}
	return suma;
}

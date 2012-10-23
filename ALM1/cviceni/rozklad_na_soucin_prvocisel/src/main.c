/*
 * Rozklad čísla na součin prvočísel
 * Jakub Kadlčík
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	int cislo;
	printf("Zadejte číslo: ");
	scanf("%i", &cislo);

	int i;
	for(i=2; i<cislo; i++)
	{
		if(cislo%i==0)
		{
			cislo /= i;
			printf("%i * ", i);

			// Nemůže být 2, protože v dalším průběhu cyklu se i iteruje
			// i=1; i++ dá kýženou dvojku
			i = 1;
		}
	}
	printf("%i\n", cislo);
	return 0;
}

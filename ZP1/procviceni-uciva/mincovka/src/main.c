/* Zadání: http://jazykc.inf.upol.cz/procviceni-uciva-i/mincovka.htm
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int mincovka(unsigned int castka, unsigned int **platidla);

int main(int argc, char **argv)
{
	int castka;
	printf("Zadejte částku: ");
	scanf("%i", &castka);

	unsigned int *platidla;
	int n = mincovka(castka, &platidla);
	int i;
	for(i=0; i<n; i++)
		printf("%i\n", platidla[i]);

	return 0;
}

int mincovka(unsigned int castka, unsigned int **platidla)
{
	int n = 0;
	int hodnoty[] = {5000, 2000, 1000, 500, 200, 100, 50, 20, 10, 5, 2, 1};

	// Lze se obejít bez malloc? Očividně není potřeba nastavovat
	//     výchozí velikost tohoto pole, ani ji nijak zvětšovat.
	unsigned int *out = (unsigned int *) malloc(0);
	*platidla = out;

	int i=0;
	while(castka>0)
	{
		while(castka<hodnoty[i])
			i++;

		castka -= hodnoty[i];
		*out = hodnoty[i];
		out++;
		n++;
	}

	return n;
}

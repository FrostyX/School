/* Zadání:
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	FILE *f;
	char znak = ' ';

	// Vytvoření a vynulování pole pro ukládání počtu znaků
	int znaky[150];
	int i;
	for(i=0; i<150; i++)
	{
		znaky[i] = 0;
	}

	if((f = fopen(argv[1], "r")) == NULL)
		return 0;

	while((znak = getc(f)) != EOF)
	{
		// Pokud je rozlišování velikosti písmen zapnuto/vypnuto
		if((strcmp(argv[2], "0")==0) && (znak>='A') && (znak<='Z'))
			znak += 32;

		if(((znak>='A') && (znak<='Z')) || ((znak>='a') && (znak<='z')) || ((znak>='0') && (znak<='9')))
			znaky[znak]++;
	}

	// Výpis
	for(i=0; i<150; i++)
	{
		if(znaky[i]!=0)
			printf("%c: %i\n", i, znaky[i]);
	}
	fclose(f);

	return 0;
}

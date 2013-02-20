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

int *suma_radku(int prvky[][4], int radku);

int main(int argc, char **argv)
{
	int p[][4]={{10,2,15,-2},{-52,41,0,12},{15,3,1,-8}};
	int *sr=suma_radku(p,3);
	printf("Soucty na radcich jsou: %d, %d, %d\n",sr[0],sr[1],sr[2]);
	return 0;
}

int *suma_radku(int prvky[][4], int radku)
{
	int *sumy;
	sumy = (int *) malloc(radku);
	int i, j;
	for(i=0; i<radku; i++)
	{
		sumy[i] = 0;
		for(j=0; j<4; j++)
		{
			sumy[i] += prvky[i][j];
		}
	}
	return sumy;
}

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
#include <string.h>

int* vyskyty(char* texty[], int pocet, char* hledany);

int main(int argc, char **argv)
{
	char *texty[] = {"foo", "bar", "lol"};
	char hledany[] = {'f', 'o', 'x', 't'};

	int *v = vyskyty(texty, 3, hledany);
	int i;
	for(i=0; i<=3; i++)
	{
		printf("%c - %i\n", hledany[i], v[i]);
	}
	return 0;
}

int* vyskyty(char* texty[], int pocet, char* hledany)
{
	int *v;
	v = (int*) malloc(strlen(hledany)*sizeof(int));

	int i, j, y;
	for(i=0; i<strlen(hledany); i++)
	{
		v[i] = 0;
		for(j=0; j<pocet; j++)
		{
			for(y=0; y<strlen(texty[j]); y++)
			{
				if(texty[j][y]==hledany[i])
					v[i] += 1;
			}
		}
	}
	return v;
}

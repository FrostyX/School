/* Zadání: http://jazykc.inf.upol.cz/procviceni-uciva-i/cetnost-znaku.htm
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int *cetnost(char *text);

int main(int argc, char **argv)
{
	char retezec[] = "Hello world!";
	int *vyskyty = cetnost(retezec);

	int i;
	for(i=0; i<256; i++)
	{
		if(vyskyty[i]!=0)
			printf("%c:%i\n", i, vyskyty[i]);
	}

	return 0;
}

int *cetnost(char *text)
{
	int *vyskyty;
	vyskyty = (int *) malloc(256);

	int i;
	for(i=0; i<256; i++)
		vyskyty[i] = 0;

	char *p = text;
	while(*p!='\0')
	{
		vyskyty[(int)*p]++;
		p++;
	}
	return vyskyty;
}

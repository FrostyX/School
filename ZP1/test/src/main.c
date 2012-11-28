/* Přetlumočené zadání: napište těla funkcí pro zakódování a dekódování řetězce
 *     Zakódování = každý znak řetězce je posunut o danou hodnotu v ASCII tabulce
 *     Tělo funkce main a hlavičky funkcí jsou přesně dány zadáním
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

char *zakoduj(char *r, int n);
char *dekoduj(char *r, int n);

int main(int argc, char **argv)
{
	char *zak, *dek, retezec[] = "Ahoj svete";
	int posun=3;
	zak = zakoduj(retezec, posun);
	printf("Zakodovany retezec:%s\n", zak);
	dek = dekoduj(zak, posun);
	printf("dekodovany retezec:%s\n", dek);
	return 0;
}

char *zakoduj(char *r, int n)
{
	char *p = r;
	while(*p!='\0')
	{
		*p += n;
		p++;
	}
	return r;
}
char *dekoduj(char *r, int n)
{
	char *p = r;
	while(*p!='\0')
	{
		*p -= n;
		p++;
	}
	return r;
}

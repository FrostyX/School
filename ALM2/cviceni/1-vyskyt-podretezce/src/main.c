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

int pocetVyskytu(char *str, char *slovo);
void vypis(char *str);

int main(int argc, char **argv)
{
	char *v = "In the C++ programming language, the string class is a standard representation for a class of text."
		"The class provides some typical string operations like comparsion, concatenation, find and replace.";
	char *s[] = {"string", "class"};

	int pocet_slov = sizeof(s)/sizeof(s[0]);
	int i;
	for(i=0; i<pocet_slov; i++)
	{
		vypis(s[i]);
		printf(": ");
		printf("%i\n", pocetVyskytu(v, s[i]));
	}
}

// Najde počet výskytů slova v řetězci
int pocetVyskytu(char *retezec, char *slovo)
{
	char *p = retezec;
	int vyskyt=0;

	while(*p!='\0')
	{
		// memcmp - http://www.elook.org/programming/c/memcmp.html
		if(!memcmp(p, slovo, strlen(slovo)))
			vyskyt++;
		p++;
	}
	return vyskyt;
}

// Na obrazovku vypíše předaný řetězec
void vypis(char *str)
{
	char *p = str;
	while(*p!='\0')
	{
		printf("%c", *p);
		p++;
	}
}

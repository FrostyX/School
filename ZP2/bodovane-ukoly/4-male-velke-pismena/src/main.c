/* Zadání:
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include<stdlib.h>
#include<string.h>
#include<stdio.h>

typedef struct
{
	int cislo;
	char *nazev;
	char *(*f)(char *);
} fce;

char *mala(char *t);
char *velka(char *t);
char *pozpatku(char *t);

int main()
{
	char vstup[255],*vystup;
	int cislo;
	fce seznam[3];
	seznam[0].cislo=1;
	seznam[0].nazev="Funkce vrati pouze velka pismena";
	seznam[0].f=velka;
	seznam[1].cislo=2;
	seznam[1].nazev="Funkce vrati pouze mala pismena";
	seznam[1].f=mala;
	seznam[2].cislo=3;
	seznam[2].nazev="Funkce vrati zadany text pozpatku";
	seznam[2].f=pozpatku;

	printf("Vyber funkci(1-3), kterou chces pouzit:\n");
	int i=0;
	for(;i<3;i++)
		printf("%d - %s\n",seznam[i].cislo,seznam[i].nazev);
	scanf("%d",&cislo);
	if((cislo>0)&&(cislo<4))
	{
		printf("Zadej vstupni retezec:\n");
		scanf("%s", vstup);
		vystup=seznam[cislo-1].f(vstup);
		printf("\n%s\n",vystup);
	}
	return 0;
}

char *velka(char *t)
{
	char *v;
	v=(char *)malloc(strlen(t)*sizeof(char));

	int i, j=0;
	for(i=0; i<strlen(t); i++)
	{
		if((t[i]>=65) && (t[i]<=90))
		{
			v[j] = t[i];
			j++;
		}
	}
	return v;
}

char *mala(char *t)
{
	char *v;
	v=(char *)malloc(strlen(t)*sizeof(char));

	int i, j=0;
	for(i=0; i<strlen(t); i++)
	{
		if((t[i]>=97) && (t[i]<=122))
		{
			v[j] = t[i];
			j++;
		}
	}
	return v;
}


char *pozpatku(char *t)
{
	char *v;
	v=(char *)malloc(strlen(t)*sizeof(char));

	int i, j=strlen(t)-1;
	for(i=0; i<strlen(t); i++, j--)
	{
		v[i] = t[j];
	}
	return v;
}

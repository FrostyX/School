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

typedef struct
{
	char jmeno[10];
	char prijmeni[15];
	char adresa[20];
	char den;
	char mesic;
	int rok;
	char telefon[16];
	char email[15];
} osoba;

int vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob);
int zrus_osobu(char *celejmeno, osoba *seznam);
osoba *najdi_osobu(char *kde, char *co, osoba *seznam_osob);
int tisk(osoba *seznam_osob);

int main(int argc, char **argv)
{
	return 0;
}

int vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob)
{
	return 0;
}

int zrus_osobu(char *celejmeno, osoba *seznam)
{
	return 0;
}

osoba *najdi_osobu(char *kde, char *co, osoba *seznam_osob)
{
	return 0;
}

int tisk(osoba *seznam_osob)
{
	return 0;
}

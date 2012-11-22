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
	char *jmeno;
	char *prijmeni;
	char *adresa;
	char den;
	char mesic;
	int rok;
	char *telefon;
	char *email;
	/*
	char jmeno[10];
	char prijmeni[15];
	char adresa[20];
	char den;
	char mesic;
	int rok;
	char telefon[16];
	char email[15];
	*/
} osoba;

int vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob);
int zrus_osobu(char *celejmeno, osoba *seznam);
osoba *najdi_osobu(char *kde, char *co, osoba *seznam_osob);
int tisk(osoba *seznam_osob);

int main(int argc, char **argv)
{
	// Vytvoření dynamického pole pro ukládání prvků datového typu osoba
	osoba *osoby;

	// Použití funkcí pro práci s osobami
	vytvor_osobu("Tony", "Stark", "New York City", 2, 3, 1963, "987 654 321", "ironman@foo.com", osoby);
	//vytvor_osobu("Bruce", "Wayne", "Gotham City", 1, 5, 1939, "123 456 789", "batman@foo.com", osoby);
	return 0;
}

int vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob)
{
	// Vytvoření datové struktury z předaných parametrů
	osoba novaOsoba = {jmeno, prijmeni, adresa, den, mesic, rok, telefon, email};

	// Zvětšení pole, aby se do něj vešel další prvek datového typu osoba
	int n = sizeof(seznam_osob)/sizeof(osoba); // Počet prvků v poli
	seznam_osob = (osoba*)malloc(sizeof(seznam_osob)+sizeof(osoba));

	// Přidání nové osoby do předaného pole
	seznam_osob[n] = novaOsoba;

	return n+1;
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

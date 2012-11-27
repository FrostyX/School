/* Zadání:
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Seznamy: http://www.linuxsoft.cz/article.php?id_article=868
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct s
{
	char jmeno[10];
	char prijmeni[15];
	char adresa[20];
	char den;
	char mesic;
	int rok;
	char telefon[16];
	char email[15];
	struct s *dalsi;
} osoba;

//int vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob);
osoba *vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob);
int zrus_osobu(char *celejmeno, osoba *seznam);
osoba *najdi_osobu(char *kde, char *co, osoba *seznam_osob);
int tisk(osoba *seznam_osob);

int main(int argc, char **argv)
{
	// Vytvoření dynamického pole pro ukládání prvků datového typu osoba
	osoba *osoby = NULL;

	// Použití funkcí pro práci s osobami
	osoby = vytvor_osobu("Tony", "Stark", "New York City", 2, 3, 1963, "987 654 321", "ironman@foo.com", osoby);
	osoby = vytvor_osobu("Bruce", "Wayne", "Gotham City", 1, 5, 1939, "123 456 789", "batman@foo.com", osoby);

	while(osoby!=NULL)
	{
		printf("%s\n", osoby->jmeno);
		osoby = osoby->dalsi;
	}
	return 0;
}

osoba *vytvor_osobu(char *jmeno, char *prijmeni, char *adresa, char den, char mesic, int rok, char *telefon, char *email, osoba *seznam_osob)
{
	// Vytvoření datové struktury z předaných parametrů
	osoba *novaOsoba = (osoba*)malloc(sizeof(osoba));
	strcpy(novaOsoba->jmeno, jmeno);
	strcpy(novaOsoba->prijmeni, prijmeni);
	strcpy(novaOsoba->adresa, adresa);
	novaOsoba->den = den;
	novaOsoba->mesic = mesic;
	novaOsoba->rok = rok;
	strcpy(novaOsoba->telefon, telefon);
	strcpy(novaOsoba->email, email);

	// Tohle ne tak úplně chápu
	novaOsoba->dalsi = seznam_osob;
	seznam_osob = novaOsoba;
	return seznam_osob;
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

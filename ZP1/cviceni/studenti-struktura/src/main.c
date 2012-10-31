/* Zadání: Vytvořte v jazyku C strukturovaný datový typ datum se
 *    členy den, mesic a rok. Poté vytvořte strukturovaný typ student
 *    se členy jmeno, prijmeni a narozen.
 *    Pro reprezentaci jednotlivých členů struktur zvolte vhodné datové typy.

   Dále napište funkci int porovnej_vek(student s1, student s2),
   která porovná věk (resp. datum narození) daných studentů
   a vrátí hodnotu -1 v případě, že první student je starší,
   1 v případě, že druhý student je starší a 0
   v případě shodného data narození u obou studentů.
   Podle návratové hodnoty funce porovnej_vek pak ve funkci main
   vypište vhodný text na obrazovku.
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
	int den, mesic, rok;
} datum;

typedef struct
{
	char jmeno[10];
	char prijmeni[10];
	datum narozen;
} student;

int porovnej_vek(student s1, student s2);

int main(int argc, char **argv)
{
	student s1 = {"Jan", "Novák", {1, 2, 1993}};
	student s2 = {"Jakub", "Kadlčík", {4, 5, 1993}};
	student s3 = {"Pepa", "Malý", {5, 8, 1992}};

	switch(porovnej_vek(s1, s2))
	{
		case -1:
			printf("Student %s %s je starší než %s %s\n", s1.jmeno, s1.prijmeni, s2.jmeno, s2.prijmeni);
			break;
		case 0:
			printf("Studenti %s %s a %s %s jsou stejně staří\n", s1.jmeno, s1.prijmeni, s2.jmeno, s2.prijmeni);
			break;
		case 1:
			printf("Student %s %s je mladší než %s %s\n", s1.jmeno, s1.prijmeni, s2.jmeno, s2.prijmeni);
			break;
	}

	return 0;
}

int porovnej_vek(student s1, student s2)
{
	if(s1.narozen.rok<s2.narozen.rok)
		return -1;
	else if(s1.narozen.rok>s2.narozen.rok)
		return 1;

	if(s1.narozen.mesic<s2.narozen.mesic)
		return -1;
	else if(s1.narozen.mesic>s2.narozen.mesic)
		return 1;

	if(s1.narozen.den>s2.narozen.den)
		return -1;
	else if(s1.narozen.den<s2.narozen.den)
		return 1;
	return 0;

}

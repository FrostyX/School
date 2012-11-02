/* Zadání: Napište program, který pro vstupní řetězec
 *     o maximální délce 200 znaků, vypíše,
 *     kolikrát se v něm který znak vyskytuje
 *     (vypište pouze znaky, které se v řetězci vyskytují)
 *     a znak (respektive více znaků), které se v řetězci
 *     vyskytují nejčastěji. Velká a malá písmena se v tomto případě
 *     chápou jako stejné znaky
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
#include <ctype.h> // tolower

typedef struct
{
	char znak;
	int vyskyt;
} vyskytZnak;

void pridejVyskytZnaku(char znak, vyskytZnak *kam, int *count);
int vratPoziciZnaku(char znak, vyskytZnak *kde, int max);
int vratVyskytNejpocetnejsiho(vyskytZnak *pole, int max);

int main(int argc, char **argv)
{
	printf("Zadej řetězec: ");
	char slovo[200] = {};
	scanf("%s", slovo);
	printf("\n");

	// Česká abeceda sestává ze 42 písmen
	// Do pole budeme přidávat strukturované prvky
	//    alternativa k asociativnímu poli.
	vyskytZnak vyskyt[43] = {};


	// Projdeme slovo a uložíme počet výskytů jednotlivých znaků
	int count = 0;
	int i;
	for(i=0; i<strlen(slovo); i++)
	{
		pridejVyskytZnaku(tolower(slovo[i]), vyskyt, &count);
	}

	// Vypíšeme kolikrát se který znak vyskytuje
	//int count = vratPocetZnaku(vyskyt, sizeof(vyskyt)/sizeof(vyskytZnak));
	for(i=0; i<count; i++)
	{
		printf("Znak %c se v řetězci vyskytuje %ix\n", vyskyt[i].znak, vyskyt[i].vyskyt);
	}
	printf("\n");

	// Vypíšeme nejpočetnější znaky
	int vyskytNejpocetnejsiho = vratVyskytNejpocetnejsiho(vyskyt, count);
	printf("Nejčastěji se v řetězci vyskytuje znak: ");
	for(i=0; i<count; i++)
	{
		if(vyskyt[i].vyskyt==vyskytNejpocetnejsiho)
			printf("%c ", vyskyt[i].znak);
	}
	printf("\n");

	return 0;
}

// Do pole *kam započítá výskyt daného znaku
// Pomocí ukazatele *count vrací počet prvků v poli
void pridejVyskytZnaku(char znak, vyskytZnak *kam, int *count)
{
	int pozice = vratPoziciZnaku(znak, kam, *count);
	if(pozice!=-1)
	{
		kam[pozice].vyskyt++;
	}
	else
	{
		vyskytZnak novyZnak = {znak, 1};
		kam[*count] = novyZnak;
		*count = *count+1;
	}
}

// Vrátí pozici na které je uložen hledaný znak.
// Pokud znak nebude nalezen, vrátí -1
int vratPoziciZnaku(char znak, vyskytZnak *kde, int max)
{
	int count = sizeof(kde)/sizeof(vyskytZnak);
	int i;
	for(i=0; i<max; i++)
	{
		if(kde[i].znak==znak)
			return i;
	}
	return -1;
}

// Vrátí, kolikrát se opakuje nejpočetnější znak
int vratVyskytNejpocetnejsiho(vyskytZnak *pole, int max)
{
	int i;
	int vyskyt = 0;
	for(i=0; i<max; i++)
	{
		if(pole[i].vyskyt>vyskyt)
			vyskyt = pole[i].vyskyt;
	}
	return vyskyt;
}

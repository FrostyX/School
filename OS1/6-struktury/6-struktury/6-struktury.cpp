// 6-struktury.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>

struct osoba
{
	char* jmeno;
	char* datum_narozeni; // DD-MM-YYYY
	unsigned char vyska;
	unsigned short vaha;
};

void db_add(char* jmeno, char datum[], unsigned char vyska, unsigned short vaha);
void db_print();
void db_print_asm();
unsigned char db_avg_height();
unsigned char db_avg_height_asm();
unsigned short db_avg_weight();


struct osoba db[10];
int db_i = 0;

int _tmain(int argc, _TCHAR* argv[])
{
	db_add("Jmeno1", "04-05-1993", 85, 71);
	db_add("Jmeno2", "08-10-2004", 60, 50);
	db_add("Jmeno3", "09-03-1998", 72, 80);
	db_print();
	printf("Avg height: %i | Avg weight: %i\n", db_avg_height(), db_avg_weight());

	printf("\n-------------------------------\n\n");
	db_print_asm();
	printf("Avg height: %i | Avg weight: %i\n", db_avg_height_asm(), db_avg_weight());

	printf("\n");
	system("PAUSE");
	return 0;
}


void db_add(char* jmeno, char datum[], unsigned char vyska, unsigned short vaha)
{
	struct osoba o;
	o.jmeno = jmeno;
	o.datum_narozeni = datum;
	o.vyska = vyska;
	o.vaha = vaha;
	db[db_i] = o;
	db_i++;
}

void db_print()
{
	for(int i=0; i<db_i; i++)
	{
		printf("%s - %s - %i, %i\n", db[i].jmeno, db[i].datum_narozeni, db[i].vyska, db[i].vaha);
	}
}

void db_print_asm()
{
	char *format = "%s - %s - %i, %i\n";
	_asm {
		mov ebx, 0
		for:
			cmp ebx, db_i
			jae end

			mov eax, 12 
			mul ebx

			mov esi, [offset db]
			movsx edx, [esi + eax + 10] // Width
			push edx
			movsx edx, [esi + eax + 8] // Height
			push edx
			push [esi + eax + 4] // Birth
			push [esi + eax + 0] // Name

			push dword ptr format
			call dword ptr [printf]
			add esp, 20

			inc ebx
			jmp for

		end:
	}
}

unsigned char db_avg_height()
{
	unsigned char sum = 0;
	for(int i=0; i<db_i; i++)
	{
		sum += db[i].vyska;
	}
	return sum / db_i;
}

unsigned char db_avg_height_asm()
{
	_asm {
		mov ebx, 0
		mov ecx, db_i
		for:
			mov eax, 12
			dec ecx
			mul ecx
			inc ecx

			mov esi, [offset db]
			movsx edx, [esi + eax + 8]
			add ebx, edx
		loop for

		mov eax, ebx
		cdq
		div db_i
	}
}

unsigned short db_avg_weight()
{
	unsigned char sum = 0;
	for(int i=0; i<db_i; i++)
	{
		sum += db[i].vaha;
	}
	return sum / db_i;
}
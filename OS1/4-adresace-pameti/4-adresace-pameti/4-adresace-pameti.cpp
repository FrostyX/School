// 4-adresace-pameti.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>

void nasobky(short n);
void mocniny();
void mocniny2();
int avg(unsigned int n);
void mocniny3(int n);
void print_array(int x[]);
void print_short_array(short x[]);

static int static_int_array[10];
static short static_short_array[10];
int *malloc_int_array = (int*) malloc(10);

int _tmain(int argc, _TCHAR* argv[])
{
	// 1
	printf("Nasobky: ");
	nasobky(5);
	print_array(static_int_array);

	// 2
	printf("Mocniny: ");
	mocniny();
	print_short_array(static_short_array);
	
	// 3
	int *malloc_int_array = (int*) malloc(10);
	printf("Mocniny2: ");
	mocniny2();
	print_array(malloc_int_array);


	system("PAUSE");
	return 0;
}

// Do daného pole uloží násobky èísla n
void nasobky(short n)
{
	_asm {
		mov ecx, 0
		for:
			mov eax, ecx
			inc eax
			mul n
			mov [static_int_array + 4 * ecx], eax

			inc ecx
			cmp ecx, 10
			jl for
	}
}

// Do daného pole uloží èísla 1^2, 2^2, ..., 10^2
void mocniny()
{
	_asm {
		mov cx, 0
		for:
			mov ax, cx
			inc ax
			mul ax
			mov [static_short_array + 2 * ecx], ax

			inc cx
			cmp cx, 10
			jl for
	}
}

// Do daného pole uloží èísla 2^0, 2^1, ..., 2^9
void mocniny2()
{
	_asm {
		mov [malloc_int_array], 1
		mov [malloc_int_array + 4], 2

		mov ch, 2		
		for:

			// Výpoèet aktuální hodnoty
			mov eax, 2
			mov ebx, 2
			mov cl, 1
			exp:
				mul ebx

				inc cl
				cmp cl, ch
				jl exp 
				

			movsx edx, ch
			mov [malloc_int_array + 4 * edx], eax

			inc ch
			cmp ch, 10
			jl for
	}
}

// Výpoèítá aritmetický prùmìr èísel v daném poli
int avg(unsigned int n)
{
	return 0;
}

// Do daného pole uloží èísla n^0, n^1, ..., n^9
void mocniny3(int n)
{
}

void print_array(int x[])
{
	for(int i=0; i<10; i++)
	{
		printf("%d | ", x[i]);
	}
	printf("\n");
}

void print_short_array(short x[])
{
	for(int i=0; i<10; i++)
	{
		printf("%d | ", x[i]);
	}
	printf("\n");
}
// quicksort.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>


// C implementation here:
// http://rosettacode.org/wiki/Sorting_algorithms/Quicksort#C
void quicksort(int *a, unsigned short n)
{
	_asm
	{
		cmp n, 2
		jl end
		
		// compute-pivot
		movzx eax, n
		mov ebx, 2
		cdq
		idiv ebx
		mov esi, eax
		mov edx, a
		mov ebx, [edx + esi * 4]
		mov eax, ebx

		// For loop
		mov esi, 0
		movzx edi, n
		dec edi

		while1:
			cmp [edx + esi * 4], eax
			jge while2
			inc esi
			jmp while1
			
		while2:
			mov ebx, [edx + edi * 4]
			cmp eax, ebx
			jge condition
			dec edi
			jmp while2

		condition:
			cmp esi, edi
			jae recursion

		// swap
			mov eax, [edx + esi * 4]
			mov ebx, [edx + edi * 4]
			mov [edx + esi * 4], ebx
			mov [edx + edi * 4], eax
			
		// for-condition
			inc esi
			dec edi
			jmp while1

		recursion:
			push esi
			push edx
			call quicksort
			add esp, 8

			movzx ebx, n
			sub ebx, esi
			push ebx
			mov edx, a
			lea ebx, [edx + esi * 4]
			push ebx
			call quicksort
			add esp, 8
		end:
	}
}

void print_array(int a[], int n)
{
	for(int i=0; i<n; i++)
		printf("%d, ", a[i]);
	printf("\n");
}

int _tmain(int argc, _TCHAR* argv[])
{
	int a1[] = {6};
	int a2[] = {8, 3};
	int a3[] = {5, 3, 8};
	int a4[] = {7, 2, 2, 7};
	int a6[] = {5, 6, 2, -3, 8, -5};
	int a7[] = {5, 0, 6, 2, -3, 8, -5};
	int a9[] = {5, 6, 2, 10, 3, 8, -9, 0, 4};

	quicksort(a1, 1);
	quicksort(a2, 2);
	quicksort(a3, 3);
	quicksort(a4, 4);
	quicksort(a6, 6);
	quicksort(a7, 7);
	quicksort(a9, 9);

	print_array(a1, 1);
	print_array(a2, 2);
	print_array(a3, 3);
	print_array(a4, 4);
	print_array(a6, 6);
	print_array(a7, 7);
	print_array(a9, 9);

	system("PAUSE");
	return 0;
}

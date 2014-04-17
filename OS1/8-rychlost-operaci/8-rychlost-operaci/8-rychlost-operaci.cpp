// 8-rychlost-operaci.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>

unsigned long long measure_mov();
unsigned long long measure_add();
unsigned long long measure_mul();
unsigned long long measure_lea_add();
unsigned long long measure_lea_mul();
unsigned long long measure_constant_mul();
unsigned long long measure_constant_shl();
unsigned long long measure_constant_lea();
int strtoint(char *str);


int _tmain(int argc, _TCHAR* argv[])
{
	printf("mov:     %d\n", measure_mov());
	printf("add:     %d\n", measure_add());
	printf("mul:     %d\n", measure_mul());
	printf("lea_add: %d\n", measure_lea_add());
	printf("lea_mul: %d\n", measure_lea_mul());
	printf("-------------\n");
	printf("cst_mul: %d\n", measure_constant_mul());
	printf("cst_shl: %d\n", measure_constant_shl());
	printf("cst_lea: %d\n", measure_constant_lea());
	printf("-------------\n");

	system("PAUSE");
	return 0;
}

unsigned long long measure_mov()
{
	_asm {
		mov ecx, 100

		rdtsc
		push edx
		push eax

		while:
			mov eax, 0
			mov eax, 1
			mov eax, 2
			mov eax, 3
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_add()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		while:
			add eax, ecx
			add eax, ecx
			add eax, ecx
			add eax, ecx
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_mul()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		while:
			mul ecx
			mul ecx
			mul ecx
			mul ecx
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_lea_add()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		while:
			lea eax, [eax + ecx]
			lea eax, [eax + ecx]
			lea eax, [eax + ecx]
			lea eax, [eax + ecx]
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_lea_mul()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		while:
			lea eax, [eax + 8 * ecx]
			lea eax, [eax + 8 * ecx]
			lea eax, [eax + 8 * ecx]
			lea eax, [eax + 8 * ecx]
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_constant_mul()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		mov edx, 9
		while:
			mul edx
			mul edx
			mul edx
			mul edx
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_constant_shl()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		mov edx, 9
		while:
			shl eax, 4
			shl eax, 4
			shl eax, 4
			shl eax, 4
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

unsigned long long measure_constant_lea()
{
	_asm {
		rdtsc
		push edx
		push eax

		mov ecx, 100
		mov eax, 5
		mov edx, 9
		while:
			lea eax, [eax + 8 * edx]
			lea eax, [eax + 8 * edx]
			lea eax, [eax + 8 * edx]
			lea eax, [eax + 8 * edx]
		loop while

		rdtsc
		sub eax, [esp];
		sub edx, [esp + 4]
		add esp, 8
	}
}

int strtoint(char *str)
{
	_asm {
		mov eax, 0
	}
}
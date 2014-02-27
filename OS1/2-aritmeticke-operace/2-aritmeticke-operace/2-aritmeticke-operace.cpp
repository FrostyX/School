// 2-aritmeticke-operace.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>
#include <math.h>

int obvod_obdelnika(int a, int b);
int obsah_obdelnika(int a, int b);
int obvod_ctverce(int a);
int obsah_ctverce(int a);
int obvod_trojuhelnika(int a, int b, int c);
int obvod_trojuhelnika2(int a);
int obsah_trojuhelnika2(int a, int b);
int obsah_trojuhelnika3(int a, int va);
int objem_krychle(int a);
double heron(int a, int b, int c);


int _tmain(int argc, _TCHAR* argv[])
{
	printf("Obvod obdelnika: %d\n", obvod_obdelnika(5, 2));
	printf("Obsah obdelnika: %d\n", obsah_obdelnika(5, 2));
	printf("\n");
	printf("Obvod ctverce: %d\n", obvod_ctverce(5));
	printf("Obsah ctverce: %d\n", obsah_ctverce(5));
	printf("\n");
	printf("Obvod trojuhelnika: %d\n", obvod_trojuhelnika(5, 4, 7));
	printf("Obvod trojuhelnika 2: %d\n", obvod_trojuhelnika2(5));
	printf("Obsah trojuhelnika 2: %d\n", obsah_trojuhelnika2(4, 7));
	printf("Obsah trojuhelnika 3: %d\n", obsah_trojuhelnika3(5, 9));
	printf("\n");
	printf("Objem krychle: %d\n", objem_krychle(2));
	printf("\n");
	printf("Heron: %f\n", heron(2, 2, 2));

	system("PAUSE");
	return 0;
}

int obvod_obdelnika(int a, int b)
{
	_asm {
		mov eax, a
		add eax, b
		add eax, eax
	}
}

int obsah_obdelnika(int a, int b)
{
	_asm {
		mov eax, a
		mul b
	}
}

int obvod_ctverce(int a)
{
	_asm {
		mov eax, a
		add eax, eax
		add eax, eax
	}
}

int obsah_ctverce(int a)
{
	_asm {
		mov eax, a
		mul a
	}
}

int obvod_trojuhelnika(int a, int b, int c)
{
	_asm {
		mov eax, a
		add eax, b
		add eax, c
	}
}

int obvod_trojuhelnika2(int a)
{
	_asm {
		mov eax, a
		add eax, a
		add eax, a
	}
}

int obsah_trojuhelnika2(int a, int b)
{
	// S = a * b / 2
	_asm {
		mov eax, a
		mul b
		mov edx, 0
		mov ebx, 2
		div ebx
	}
}

int obsah_trojuhelnika3(int a, int va)
{
	// S = a * va / 2
	return obsah_trojuhelnika2(a, va);
}

int objem_krychle(int a)
{
	// O = a^3
	_asm {
		mov eax, a
		mul a
		mul a
	}
}

double heron(int a, int b, int c)
{
	// s = (a + b + c) / 2
	// S = sqrt(s * (s-a) * (s-b) * (s-c))

	int s;
	int operand;

	_asm {
		// Výpoèet s
		mov eax, a
		add eax, b
		add eax, c
		mov edx, 0
		mov ebx, 2
		div ebx
		
		// Pøesun s do C promìnné
		mov s, eax

		// Výpoèet operandu pro sqrt()
		/// (s-a)
		mov ebx, s
		sub ebx, a
		mul ebx

		/// (s-b)
		mov ebx, s
		sub ebx, b
		mul ebx

		/// (s-c)
		mov ebx, s
		sub ebx, c
		mul ebx
		
		mov operand, eax
	}

	return sqrt((float) operand);
}
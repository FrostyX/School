// 3-skoky.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <stdlib.h>

int int_avg(int a, int b, int c);
short short_avg(short a, short b, short c);
int sgn(int i);
short max2(char a, char b);
short max3(short a, short b, short c);
int kladne(int a, int b, int c);
unsigned int min4(unsigned int a, unsigned int b, unsigned int c, unsigned d);


int _tmain(int argc, _TCHAR* argv[])
{
	printf("Avg: %i\n", int_avg(4, 5, 6));
	printf("Avg: %i\n", short_avg(4, 5, 6));
	printf("Sgn: %i\n", sgn(-1));
	printf("Max2: %i\n", max2(8, 6));
	printf("Max3: %i\n", max3(3, 5, 1));
	printf("Kladne: %i\n", kladne(3, 5, 0));
	printf("Min4: %i\n", min4(2, 3, 4, 5));

	system("PAUSE");
	return 0;
}


// V�po�et aritmetick�ho pr�m�ru t�� ��sel typu int
int int_avg(int a, int b, int c)
{
	_asm {
		mov eax, a
		add eax, b
		add eax, c
		cdq
		mov ebx, 3
		div ebx
	}
}

// V�po�et aritmetick�ho pr�m�ru t�� ��sel typu short
short short_avg(short a, short b, short c)
{
	_asm {
		mov ax, a
		add ax, b
		add ax, c
		cwd
		mov bx, 3
		div bx
	}
}

// Vrac� hodnoty -1, 0, 1, v z�vislosti na tom, zda-li je hodnota i z�porn�, nulov� nebo kladn�.
int sgn(int i)
{
	_asm {
		mov eax, i
		cmp eax, 0
		jg positive
		jl negative
		jmp end

		negative:
			mov eax, -1
			jmp end
		positive:
			mov eax, 1

		end:
	}
}

// Vrac� nejv�t�� hodnotu
short max2(char a, char b)
{
	_asm {
		mov al, a
		mov bl, b
		cmp al, bl

		jg positive
		jl negative
		jmp end

		negative:
			movsx ax, bl
			jmp end
		positive:
			movsx ax, al

		end:
	}
}

// Vrac� nejv�t�� hodnotu
short max3(short a, short b, short c)
{
	_asm {
		mov ax, a
		mov bx, b
		cmp ax, bx

		jl greater
		jmp next

		greater:
			mov ax, bx

		next:
			mov bx, c
			cmp ax, bx
			jl greater2
			jmp end

		greater2:
			mov ax, bx

		end:
	}
}

// Vrac� 1 pokud jsou v�echny argumenty kladn� a jinak 0
int kladne(int a, int b, int c)
{
	_asm {
		// Ov��� a
		mov eax, a
		cmp eax, 0
		jle negative

		// Ov��� b
		mov eax, b
		cmp eax, 0
		jle negative 

		// Ov��� c
		mov eax, c
		cmp eax, 0
		jle negative

		// V�echny jsou kladn�
		mov eax, 1
		jmp end

		negative: mov eax, 0
		end:
	}
}

// Vrac� nejmen�� hodnotu
unsigned int min4(unsigned int a, unsigned int b, unsigned int c, unsigned d)
{
	__asm {
		mov eax, a
		mov ebx, b
		cmp eax, ebx

		jg less 
		jmp next

		less: mov eax, ebx
		next:
			mov ebx, c
			cmp eax, ebx
			jg less2 
			jmp next2

		less2: mov eax, c
		next2:
			mov ebx, d
			cmp eax, ebx
			jg less3 
			jmp end

		less3: mov eax, d
		end:
	}
}
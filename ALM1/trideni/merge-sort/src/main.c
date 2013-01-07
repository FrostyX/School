/* Zadání: Implementace algoritmu merge-sort
 * Řazení vzestupně
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 *
 * http://www.youtube.com/watch?v=GCae1WNvnZM - Dobré na pochopení
 * Pro implementaci je lepší kouknout do skript od prof. Bělohlávka
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h> // memcpy
#include <limits.h> // Pro zjištění maximální hodnoty intu

int main(int argc, char **argv)
{
	int cisla[] = {8, 9, 7, 3, 2, 6, 10, 5};
	int n = sizeof(cisla)/sizeof(int);
	mergeSort(cisla, 0, n-1);

	int i;
	for(i=0; i<n; i++)
	{
		printf("%i\n", cisla[i]);
	}

	return 0;
}

int mergeSort(int a[], int p, int r)
{
	if(p<r) // Nejmenší pole které má smysl třídit je dvouprvkové
	{
		int q = (p+r)/2;
		mergeSort(a, p, q); // Setřídí levou polovinu pole
		mergeSort(a, q+1, r); // Setřídí pravou polovinu pole
		merge(a, p, q, r); // Sleje obě poloviny pole dohromady
	}
	return 0;
}

// a - pole čísel
// p - index prvního prvku tříděné části
// q - index prostředního prvku tříděné části
// r - index posledního prvku tříděné části
int merge(int a[], int p, int q, int r)
{
	// Zjistíme počet prvků v levé a pravé části
	int n1 = q-p+1;
	int n2 = r-q;

	// Vytvoříme pole pro levou a pravou část a[]
	// Musíme vytvořit každou část o jeden prvek větší
	//     poslední prvek každé části se bude blížit nekonečnu
	int L[n1+1];
	int R[n2+1];

	// Zkopírujeme prvky z pole a[] do pole L[] a R[]
	memcpy(L, a+p, sizeof(int)*n1);
	memcpy(R, a+q+1, sizeof(int)*n2);

	// Nastavíme poslední prvky na "nekonečno"
	L[n1] = INT_MAX;
	R[n1] = INT_MAX;

	// Indexy prvních zatím nepoužitých prvků slučovaných polí
	int Li=0;
	int Ri=0;

	// Projdeme část pole, kterou právě třídíme
	int i;
	for(i=p; i<=r; i++)
	{
		// Postupně ukládáme menší z prvků obou polí
		if(L[Li]<R[Ri])
		{
			a[i] = L[Li];
			Li++;
		}
		else
		{
			a[i] = R[Ri];
			Ri++;
		}
	}
}

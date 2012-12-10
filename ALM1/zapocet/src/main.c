/* Zadání: V jazyce C napište program, který:
 *     1. Vygeneruje pole alespoň 1000 náhodných přirozených čísel menších než 1000
 *     2. Setřídí toto pole pomocí algoritmu Quick Sort a Heapsort.
 *        Tyto algogirtmy implementujte pomocí funkcí (tj. jedna funkce pro jeden algoritmus) tak,
 *        aby návratovou hodnotou byl počet porovnání prvků z tříděného pole,
 *        které daný algoritmus provedl.
 *     3. Na terminál vypište report ve tvaru:
 *
 *            Algoritmus | počet porovnání
 *            ----------------------------
 *            Heapsort   | 1000
 *            Quicksort  | 2000
 *
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <math.h>
#include <stdio.h>
#include <stdlib.h>

int quickSort(int a[], int k, int l);
int maxHeapify(int a[], int i, int n);
int buildMaxHeap(int a[], int n);
int heapSort(int a[], int n);
int *copyArray(int a[], int n);
int *genArrayOfRand(int n, int max);

int main(int argc, char **argv)
{
	int n = 1000;
	int *cisla = genArrayOfRand(n, 1000);
	int *cisla2 = copyArray(cisla, n);

	printf("Algoritmus | Počet porovnání\n");
	printf("----------------------------\n");
	printf("Heapsort   | %i\n", heapSort(cisla, n));
	printf("Quicksort  | %i\n", quickSort(cisla2, 0, n-1));

	return 0;
}

// k - Index začátku části pole která je právě tříděna (0)
// l - Index konce části pole která je právě tříděna (n-1)
int quickSort(int a[], int k, int l)
{
	// Počet porovnání prvků z tříděného pole
	int porovnani = 0;

	int r; // Index prvku uprostřed tříděné části
	int i=k;
	int j=l;

	// Najdeme prvek, který je uprostřed tříděné části pole
	int x=a[(k+l)/2];

	// Ukončení cyklu je závislé na indexech v určité části těla
	while(1)
	{
		while(a[i]<x)
		{
			i++;
			porovnani++;
		}
		while(a[j]>x)
		{
			j--;
			porovnani++;
		}
		if(i>j)
			break;

		// Prohodíme prvky
		int tmp=a[i];
		a[i]=a[j];
		a[j]=tmp;

		i++;
		j--;
	}
	if(k<j)
		porovnani += quickSort(a, k, j);
	if(i<l)
		porovnani += quickSort(a, i, l);

	return porovnani;
}


int maxHeapify(int a[], int i, int n)
{
	int porovnani = 0;
	int iLevy = 2*i+1; // Index levého potomka
	int iPravy = 2*i+2; // Index pravého potomka

	int largest = i;
	if(iLevy<=n)
	{
		if(a[iLevy]>a[largest])
			largest = iLevy;
		porovnani++;
	}
	if(iPravy<=n)
	{
		if(a[iPravy]>a[largest])
			largest = iPravy;
		porovnani++;
	}

	if(largest!=i)
	{
		int tmp = a[i];
		a[i] = a[largest];
		a[largest] = tmp;

		porovnani += maxHeapify(a, largest, n);
	}
	return porovnani;
}

int buildMaxHeap(int a[], int n)
{
	int i, porovnani = 0;
	for(i=(n/2)-1; i>=0; i--)
		porovnani += maxHeapify(a, i, n-1);
	return porovnani;
}

int heapSort(int a[], int n)
{
	int porovnani = 0;
	porovnani = buildMaxHeap(a, n);

	int i;
	for(i=n-1; i>=1; i--)
	{
		int tmp = a[0];
		a[0] = a[i];
		a[i] = tmp;
		porovnani += maxHeapify(a, 0, i-1);
	}
	return porovnani;
}

// Zkopíruje pole a vrátí ukazatel na jeho první prvek
int *copyArray(int a[], int n)
{
	int *b;
	b = (int *) malloc(n);
	int i;
	for(i=0; i<n; i++)
		b[i] = a[i];
	return b;
}

// Vygeneruje pole náhodných čísel a vrátí ukazatel na jeho první prvek
// n = počet prvků
// max = nejvyšší vygenerovatelné číslo
int *genArrayOfRand(int n, int max)
{
	int *a;
	a = (int *) malloc(n*sizeof(int));

	srand(time(0));
	int i;
	for(i=0; i<n; i++)
		a[i] = rand() % max;
	return a;
}

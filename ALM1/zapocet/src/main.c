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

int quickSort(int a[], int k, int l);
int heapSort(int a[], int n);

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	int cisla[] = {1, 3, 7, 6, 9, 4, 3, 5};
	//int cisla[] = {1, 3, 6, 7, 9, 4, 3, 5};
	//int cisla[] = {3, 4, 1};
	//int cisla[] = {5, 3, 4, 1};
	//int cisla[] = {7, 1, 9, 5, 4, 8, 3, 2};
	int n = sizeof(cisla)/sizeof(int);
	quickSort(cisla, 0, n-1);


	int i;
	for(i=0; i<n; i++)
		printf("%i\n", cisla[i]);
	return 0;
}

// k - Index začátku části pole která je právě tříděna (0)
// l - Index konce části pole která je právě tříděna (n-1)
int quickSort(int a[], int k, int l)
{
	int r; // Index prvku uprostřed tříděné části
	int i=k;
	int j=l;

	// Ukončení cyklu je závislé na indexech v určité části těla
	while(1)
	{
		// Najdeme index prvku, který je uprostřed tříděné části pole
		int x=a[(k+l)/2];

		while(a[i]<x)
			i++;
		while(a[j]>x)
			j--;
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
		quickSort(a, k, j);
	if(i<l)
		// Ale proč -1 ?
		quickSort(a, i-1, l);

	return 0;
}

int heapSort(int a[], int n)
{
	return 0;
}
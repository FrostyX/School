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

//int quickSort(int a[], int k, int l);
int heapSort(int a[], int n);

#include <math.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	//int cisla[] = {1, 3, 7, 6, 9, 4, 3, 5};
	//int cisla[] = {17, 1, 3, 28, 8, 7, 4, 2};
	int cisla[] = {41, 67, 34, 0, 69, 24, 78, 58, 62, 64, 5, 45, 81, 27, 61};
	//int cisla[] = {1, 3, 6, 7, 9, 4, 3, 5};
	//int cisla[] = {3, 4, 1};
	//int cisla[] = {5, 3, 4, 1};
	//int cisla[] = {7, 1, 9, 5, 4, 8, 3, 2};
	int n = sizeof(cisla)/sizeof(int);
	//quickSort(cisla, 0, n-1);
	heapSort(cisla, n);


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

	// Najdeme prvek, který je uprostřed tříděné části pole
	int x=a[(k+l)/2];

	// Ukončení cyklu je závislé na indexech v určité části těla
	while(1)
	{
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

/*
 * http://www.algoritmy.net/article/17/Heapsort
 * http://en.wikipedia.org/wiki/Heapsort
 * http://cs.wikipedia.org/wiki/Heapsort
 * http://cs.wikipedia.org/wiki/Bin%C3%A1rn%C3%AD_strom
 * http://www.devbook.cz/algoritmus-heap-sort-trideni-cisel-podle-velikosti
 * http://www.youtube.com/watch?v=6NB0GHY11Iw
 */
int heapSort(int a[], int n)
{
	// Projdeme haldu od kořene k listům
	// @TODO Přepsat podmínku cyklu
	int i;
	int opakovat=0;
	for(i=0; i<n; i++)
	{
		int iLevy = 2*i+1;
		int iPravy = 2*i+2;

		// Pokud existuje levý potomek
		if(iLevy<n)
		{
			// Pokud je potomek větší než rodič
			if(a[iLevy]>a[i])
			{
				int tmp = a[iLevy];
				a[iLevy] = a[i];
				a[i] = tmp;
				opakovat = 1;
			}
		}

		// Pokud existuje pravý potomek
		if(iPravy<n)
		{
			// Pokud je potomek větší než rodič
			if(a[iPravy]>a[i])
			{
				int tmp = a[iPravy];
				a[iPravy] = a[i];
				a[i] = tmp;
				opakovat = 1;
			}
		}

		if(opakovat)
		{
			i=-1; // @TODO Nevracet na začátek ale pouze na předchozí uzel
			opakovat = 0;
			continue;
		}
	}

	/*
	// Zjistíme výšku stromu
	int h = log(n)/log(2);
	*/
	return 0;
}

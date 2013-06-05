/* Zadání:
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

int binarySearch(int A[], int x, int j, int k);
int binarySearchNonRecursive(int A[], int x, int n);

int main(int argc, char **argv)
{
	int A[] = {1, 4, 6, 7, 20, 35, 50};
	int n = sizeof(A)/sizeof(int);
	printf("%i\n", binarySearch(A, 50, 0, n-1));
	printf("%i\n", binarySearchNonRecursive(A, 50, n));
	return 0;
}

int binarySearch(int A[], int x, int k, int l)
{
	if(k>l)
		return -1;

	int s = (k+l)/2;
	if(x>A[s]) return binarySearch(A, x, s+1, l);
	if(x<A[s]) return binarySearch(A, x, k, s-1);
	return s;
}

int binarySearchNonRecursive(int A[], int x, int n)
{
	int k = 0;
	int l = n-1;
	while(k<=l)
	{
		int s = (k+l)/2;
		if(x>A[s])
			k = s+1;
		else if(x<A[s])
			l = s-1;
		else
			return s;
	}
}

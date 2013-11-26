#include <math.h>
#include <stdlib.h>
#include <stdio.h>
#include <iostream>
#include <string>

using namespace std;

class Zlomek
{
	unsigned c,j;
	void nsd()
	{
		if(c==0)
		{
			j=1;
			return;
		}

		unsigned a=c,b=j,r;
		for(;;)
		{
			r=a%b;
			if(r==0)
			{
				c/=b;
				j/=b;
				return;
			}
			a=b;
			b=r;
		}
	}
	public:
		Zlomek()
		{
		}

		Zlomek(unsigned c, unsigned j):c(c),j(j)
		{
			nsd();
		}

		string operator () () const
		{
			return to_string(c) + "/" + to_string(j);
		}

		bool operator > (const Zlomek &z)
		{
			return c*z.j > z.c*j;
		}

};

template<class T>
void bubble_sort(T A[], int n)
{
	for(int i=0; i<n; i++)
	{
		for(int j=1; j<n; j++)
		{
			if(A[j-1] > A[j])
			{
				T temp = A[j];
				A[j] = A[j-1];
				A[j-1] = temp;
			}
		}
	}
}


#include <stdlib.h>
#include <iostream>

class Pole
{
	private:
		const int d, h;
		int *pole;
		unsigned pocet;

	public:
		Pole(int d, int h) :d(d), h(h)
		{
			this->pocet = 0;
			pole = new int[h-d+1];
		}

		~Pole()
		{
			delete [] pole;
		}

		int * operator [] (int x)
		{
			if(x >= h-d)
				return NULL;

			return &pole[x-d];
		}

		bool operator += (int x)
		{
			if(pocet >= h-d)
				return false;

			pole[d+pocet] = x;
			pocet++;
			return true;
		}

		bool operator += (const Pole &p)
		{
			if((h-d) < (p.h - p.d))
				return false;

			for(int i=0; i<=p.pocet; i++)
			{
				this->operator+=(p.pole[i]);
			}
		}

		bool operator -= (int x)
		{
			for(int i=0; i<=h-d; i++)
			{
				if(pole[i] == x)
				{
					//for(int j=i; j=h-d; j++)
					//{
						//pole[j] = pole[j+1];
					//}

					pocet--;
					return true;
				}
			}
			return false;
		}

		bool operator ! ()
		{
			return pocet == 0;
		}

		int operator + ()
		{
			int max = 0;
			for(int i=0; i<=h-d; i++)
			{
				if(pole[i] > max)
					max = pole[i];
			}
			return max;
		}
};


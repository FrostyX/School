#include<iostream>

using namespace std;

class Zlomek
{
	private:
		void nsd()
		{
			if (c==0)
			{
				j=1;
				return;
			}
			unsigned a=c, b=j, r;
			for (;;)
			{
				r=a%b;
				if (r==0)
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
		unsigned c,j;
		Zlomek() { }
		Zlomek(unsigned c, unsigned j):c(c),j(j) { nsd(); }

		void operator () () const
		{
			cout << c << '/' << j << endl;
		}

		bool operator ! ()
		{
			return (c == 0) && (j == 0);
		}

		bool operator == (const Zlomek &z)
		{
			return (z.c == c) && (z.j == j);
		}
	};

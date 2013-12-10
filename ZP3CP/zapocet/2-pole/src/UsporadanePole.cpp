#include<iostream>
using namespace std;

template<class T, unsigned n>
class UsporadanePole
{
	private:
		T pole[n];
		unsigned count = 0;
		int iter = 0;

		void sort()
		{
			for(int j=0; j<count-1; j++)
			{
				for(int i=0; i<count-j-1; i++)
				{
					if((pole[i]>pole[i+1]) || (!pole[i]))
					{
						T tmp = pole[i+1];
						pole[i+1] = pole[i];
						pole[i] = tmp;
					}
				}
			}
		}

	public:
		UsporadanePole & operator << (const T &value)
		{
			if((!najit(value)) && (count < n))
			{
				pole[count] = value;
				count++;
				sort();
			}
			return *this;
		}

		const T *najit(const T &value)
		{
			int k = 0;
			int l = count;
			while(k<=l)
			{
				int s = (k+l) / 2;
				if(pole[s] == value)
					return &pole[s];
				if(pole[s] > value)
					l = s-1;
				else
					k = s+1;
			}
			return nullptr;
		}

		bool operator -= (const T &p)
		{
			const T *del = najit(p);
			if(del)
			{
				del = nullptr;
				count--;
				sort();
			}
		}

		operator unsigned () const
		{
			return count;
		}

		bool prvni()
		{
			iter = 0;
			return iter < count;
		}

		const T & aktual()
		{
			return pole[iter];
		}

		bool dalsi()
		{
			bool r = iter<count;
			if(r)
				iter++;
			return r;
		}
};

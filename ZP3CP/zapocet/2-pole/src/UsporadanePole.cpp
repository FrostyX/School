#include<iostream>
using namespace std;

template<class T, unsigned n>
class UsporadanePole
{
	private:
		T pole[n];
		unsigned count = 0;

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
			for(int i=0; i<=count; i++)
			{
				if(pole[i] == value)
					return &pole[i];
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
		}

		const T & aktual()
		{
		}

		bool dalsi()
		{
		}
};

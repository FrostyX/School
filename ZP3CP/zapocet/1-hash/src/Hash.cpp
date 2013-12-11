#include<iostream>

using namespace std;

template<class T, unsigned n>
class Hash
{
	protected:
		T *table = new T[n]();
		unsigned count = 0;
		unsigned unsaved = 0;
		int iter = 0;

	public:
		virtual unsigned h(const T &) const =0;
		Hash & operator << (const T &value)
		{
			int h0 = h(value);
			for(int i=0; i<n-1; i++)
			{
				int h = (h0 + i*i) % n;
				if(!table[h])
				{
					table[h] = value;
					count++;
					return *this;
				}
			}
			unsaved++;
			return *this;
		}

		unsigned operator + () const
		{
			return count;
		}

		unsigned operator - () const
		{
			return unsaved;
		}

		const T * najit(const T &value)
		{
			int h0 = h(value);
			for(int i=0; i<n-1; i++)
			{
				int h = (h0 + i*i) % n;
				if(!table[h])
					break;
				else if(table[h] == value)
					return &table[h];
			}
			return nullptr;
		}

		bool prvni()
		{
			iter = 0;
			return iter < count;
		}

		const T & aktual()
		{
			return table[iter++];
		}

		bool dalsi()
		{
			return iter<n;
		}
};

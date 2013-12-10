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
			if(najit(value))
				;
			else if(count >= n)
				unsaved++;
			else
			{
				table[h(value)] = value;
				count++;
			}
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
			if(!table[h(value)])
				return nullptr;
			return &table[h(value)];
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

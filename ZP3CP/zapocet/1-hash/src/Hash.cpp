#include<iostream>

using namespace std;

template<class T, unsigned n>
class Hash
{
	private:
		T table[n];
		unsigned count = 0;
		unsigned unsaved = 0;

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
				table[count] = value;
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
			for(int i=0; i<count; i++)
			{
				if(h(table[i]) == h(value))
					return &table[i];
			}
			return NULL;
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

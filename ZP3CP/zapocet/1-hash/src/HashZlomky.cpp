#include "Zlomek.cpp"

template<unsigned n>
class HashZlomky: public Hash<Zlomek, n>
{
	public:
		virtual unsigned h(const Zlomek &z) const
		{
			return ((z.c * 10000) / z.j);
		}
};

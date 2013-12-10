#include "Zlomek.cpp"

template<unsigned n>
class HashZlomky: public Hash<Zlomek, n>
{
	public:
		virtual unsigned h(const Zlomek &z) const
		{
			return (31 * z.c + z.j) % n;
		}
};

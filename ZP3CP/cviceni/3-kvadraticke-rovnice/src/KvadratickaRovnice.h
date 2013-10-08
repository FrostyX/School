#include <math.h>

class KvadratickaRovnice
{
	public:
		KvadratickaRovnice(double a, double b, double c);
		int pocetKorenu();
		double koren1();
		double koren2();

	protected:
		double a;
		double b;
		double c;
		double diskriminant();
};

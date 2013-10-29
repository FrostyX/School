#define _USE_MATH_DEFINES
#include <math.h>

class Teleso
{
	public:
		Teleso(double vyska)
		{
			this->vyska = vyska;
		}
		virtual double objem() =0;

	protected:
		double vyska;
};

class Kvadr : public Teleso
{
	public:
		Kvadr(double a, double b, double c)
			: Teleso(c)
		{
			this->a = a;
			this->b = b;
		}

		double objem()
		{
			return a * b * vyska;
		}

	private:
		double a, b, c;
};

class TelesoSKruhovouPodstavou : public Teleso
{
	public:
		TelesoSKruhovouPodstavou(double polomer, double vyska)
			: Teleso(vyska)
		{
			this->polomer = polomer;
		}

		double obsah()
		{
			return M_PI * polomer * polomer;
		}

	protected:
		double polomer;
};

class Valec : public TelesoSKruhovouPodstavou
{
	public:
		Valec(double polomer, double vyska)
			: TelesoSKruhovouPodstavou(polomer, vyska) {}

		double objem()
		{
			return obsah() * vyska;
		}
};

class Kuzel : public TelesoSKruhovouPodstavou
{
	public:
		Kuzel(double polomer, double vyska)
			: TelesoSKruhovouPodstavou(polomer, vyska) {}

		double objem()
		{
			return obsah() * vyska / 3;
		}
};

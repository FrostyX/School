/* Zadání:
 * 	Napište program, který pro zadané číslo n postupně vytiskne
 * 	na obrazovku kříže, které budou mít 2*i+1 řádků a
 * 	2*i+1 sloupců (i=1,..,n). Maximální hodnota n bude 12
 * 	(součástí programu musí být i test přípustnosti n).
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	// Maximální přípustná hodnota n
	const int max = 12;

	// Číslo n pro které budou vytisknuty kříže
	unsigned int n;
	printf("Zadejte číslo n: ");
	scanf("%i", &n);

	// Ze zadání je přípustná hodnota čísla n omezena
	if((n<1) || (n>max))
	{
		printf("Zadané číslo n musí být v rozmezí 1 až %i\n", max);
		return 0;
	}

	// Zpracování jednotlivých křížů
	int i;
	for(i=1; i<=n; i++)
	{
		// Pozice X na kterém bude x vypsán
		int pozice=2*i;

		// Souradnice Y - výška kříže
		// Přestože začíná od 0 musí být <= kvůli středovému x
		int y;
		for(y=0; y<=2*i; y++)
		{

			// Souradnice X - šířka kříže
			// Přestože začíná od 0 musí být <= kvůli středovému x
			int x;
			for(x=0; x<=2*i; x++)
			{
				// Za předpokladu označení pomyslného čtverce běžným ABCD:
				// 	- První část podmínky reprezentuje úhlopříčku BD
				// 	- Druhá část podmínky reprezentuje úhlopříčku AC
				if((x==y) || (pozice==x))
				{
					printf("x");
				}
				else
					printf("-");
			}

			//
			pozice--;

			// Nový řádek vypisovaného kříže
			printf("\n");
		}

		// Oddělovač mezi jednotlivými obrazci
		// Za posledním obrazcem jej vyposovat nechceme
		if(i<n)
			printf("\n\n\n");
	}
	return 0;
}

/* Zadání: Naprogramujte shell pro frontu
 * Příkazy:
 * 	p n - Přidá n do fronty
 * 	o   - odebere ze fronty
 * 	e   - vypíše zda je fronta prázdná
 * 	t   - vypíše celou frontu
 * 	q   - ukončí shell
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	// Vytvoření fronty
	int fronta[5];

	// Maximální velikost fronty
	int velikost = sizeof(fronta)/sizeof(int);

	// Počet čísel ve frontě
	int count=0;

	// Pozice na kterou byl uložen poslední prvek
	int pozice=-1;

	// Vstup shellu je nekonečný
	while(1)
	{
		// Zadání příkazu shellu
		// Bug - u scanf musí být mezera před %c
		// Kdo mi objasní proč, získává zlatého bludišťáka
		printf("> ");
		char c;
		scanf(" %c", &c);

		// Pro příkaz p - přidání do fronty
		int cislo;

		// Pro příkaz t - výpsání fronty
		int i, j, zacatek;

		// Použití správného příkazu
		switch(c)
		{
			// Ukončení shellu
			case 'q':
				return 0;

			// Přidání hodnoty do zásobníku
			case 'p':
			{
				if(count>=velikost)
					printf("Fronta je plná\n");
				else
				{
					printf("Zadejte číslo, které chcete přidat: ");
					scanf("%i", &cislo);

					// Nastavíme pozici na kterou bude prvek uložen
					// Pokud jsme poslední hodnotu zapsali na konec pole
					//    přesuneme zapisování na začátek
					pozice = pozice == velikost-1 ? 0 : pozice+1;

					fronta[pozice] = cislo;
					count++;
				}
				break;
			}

			// Odebrání hodnodnoty ze zásobníku
			case 'o':
			{
				if(count<=0)
					printf("Fronta je prázdná\n");
				else
					count--;
				break;
			}

			// Ověření, zda je zásobník prázdný
			case 'e':
			{
				printf("Fronta %s prázdná\n", count > 0 ? "není" : "je");
				break;
			}

			// Výpis hodnot v zásobníku
			case 't':
			{
				if(count<=0)
					printf("Fronta je prázdná\n");
				else
				{
					// Zjistíme pozici prvního prvku fronty
					zacatek = pozice-count+1;

					// Pokud poslední hodnota byla přidána do začátku pole
					//     Začátek fronty je v poli za posledním přidaným prvkem
					if(zacatek<0)
						zacatek = velikost+zacatek;

					// j reprezentuje tolik zopakování, kolik je prvků ve frontě
					// i reprezentuje index procházených prvků fronty
					for(j=1, i = zacatek; j<=count; j++, i++)
					{
						// Po projití celého pole jej chceme procházet znovu od začátku
						if(i==velikost)
							i=0;

						printf("%i ", fronta[i]);
					}
					printf("\n");
				}
				break;
			}

			// Neexistující příkaz
			default:
			{
				printf("Command not found \n");
				break;
			}
		}
	}
	return 0;
}


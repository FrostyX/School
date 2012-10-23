/* Zadání: Naprogramujte shell pro zásobník
 * Příkazy:
 * 	p n - Přidá n do zásobníku
 * 	o   - odebere ze zásobníku
 * 	e   - vypíše zda je zásobník prázdný
 * 	t   - vypíše celý zásobník
 * 	q   - ukončit
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 */

#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv)
{
	// Vytvoření zásobníku
	int zasobnik[10];

	// Maximální velikost zásobníku
	int velikost = sizeof(zasobnik)/sizeof(int);

	// Počet čísel v zásobníku
	int count=0;

	while(1)
	{
		// Zadání příkazu shellu
		// Bug - u scanf musí být mezera před %c
		// Kdo mi objasní proč, získává zlatého bludišťáka
		printf("> ");
		char c;
		scanf(" %c", &c);

		// Načtení v příkazu p
		int cislo;

		// Cyklus v příkazu p
		int i;

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
					printf("Zásobník je plný\n");
				else
				{
					printf("Zadejte číslo, které chcete přidat: ");
					scanf("%i", &cislo);
					zasobnik[count] = cislo;
					count++;
				}
				break;
			}

			// Odebrání hodnodnoty ze zásobníku
			case 'o':
			{
				if(count<=0)
					printf("Zásobník je prázdný\n");
				else
					count--;
				break;
			}

			// Ověření, zda je zásobník prázdný
			case 'e':
			{
				printf("Zásobník %s prázdný.\n", count > 0 ? "není" : "je");
				break;
			}

			// Výpis hodnot v zásobníku
			case 't':
			{
				if(count<=0)
					printf("Zásobník je prázdný\n");
				else
				{
					for(i=0; i<count; i++)
						printf("%i ", zasobnik[i]);
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

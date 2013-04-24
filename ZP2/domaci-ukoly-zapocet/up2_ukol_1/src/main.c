/* Zadání: Napište funkce, které pro danou relaci R vytvoří
 *     reflexivní, symetrický a tranzitivní uzávěr.
 *
 * Info: Uzávěr je původní relace, rozšířená o takové dvojice,
 *     aby splňovala danou vlastnost
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

// Uzávěrové funkce
char **reflexivni(char **R, int n);
char **symetricky(char **R, int n);
char **tranzitivni(char **R, int n);

// Pomocné funkce
void kopirujMatici(char **co, char **kam, int n);
void vypisRelaci(char **R, int n);
char **novaMatice(int n);

int main(int argc, char **argv)
{
	// Relace R definovaná pomocí matice
	/*
	char r0[] = {0, 0, 1, 0, 0};
	char r1[] = {0, 0, 1, 0, 0};
	char r2[] = {1, 0, 1, 0, 1};
	char r3[] = {0, 0, 0, 0, 0};
	char r4[] = {0, 0, 0, 0, 0};
	*/

	char r0[] = {0, 0, 1, 0, 0};
	char r1[] = {0, 0, 0, 0, 0};
	char r2[] = {0, 0, 0, 3, 0};
	char r3[] = {0, 0, 0, 0, 1};
	char r4[] = {0, 0, 0, 0, 0};

	char *R[] = {r0, r1, r2, r3, r4};

	// Velikost matice je počítána podle počtu prvků v prvním řádku
	int n = sizeof(r0)/sizeof(char);

	// Zjištění uzávěrů
	char **Ur = reflexivni(R, n);
	char **Us = symetricky(R, n);
	char **Ut = tranzitivni(R, n);

	// Vypsání relace R
	printf("Relace R je: ");
	vypisRelaci(R, n);

	// Vypsání reflexivního uzávěru
	printf("Reflexivní uzávěr relace R je: ");
	vypisRelaci(Ur, n);

	// Vypsání symetrického uzávěru
	printf("Symetrický uzávěr relace R je: ");
	vypisRelaci(Us, n);

	// Vypsání tranzitivního uzávěru
	printf("Tranzitivní uzávěr relace R je: ");
	vypisRelaci(Ut, n);

	return 0;
}



// Všechny (a, a)eR
char **reflexivni(char **R, int n)
{
	// Vytvoříme novou matici a naplníme ji uspořádaními
	//     dvojicemi z relace R
	char **S = novaMatice(n);
	kopirujMatici(R, S, n);

	// Rozšíříme relaci S o dvojice nezbytné k tomu, aby
	//     byla reflexivní
	int i;
	for(i=0; i<n; i++)
	{
		if(R[i][i]!=1)
		{
			S[i][i] = 1;
		}
	}
	return S;
}

// Pokud (a, b)eR pak musí (b, a)eR
char **symetricky(char **R, int n)
{
	// Vytvoříme novou matici a naplníme ji uspořádaními
	//     dvojicemi z relace R
	char **S = novaMatice(n);
	kopirujMatici(R, S, n);

	// Rozšíříme relaci S o dvojice nezbytné k tomu, aby
	//     byla symetrická
	int i, j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(R[i][j])
				S[j][i] = 1;
		}
	}
	return S;
}

// Pokud (a, b)eR a (b, c)eR, pak musí (a, c)eR
char **tranzitivni(char **R, int n)
{
	// Vytvoříme novou matici a naplníme ji uspořádaními
	//     dvojicemi z relace R
	char **S = novaMatice(n);
	kopirujMatici(R, S, n);

	// Rozšíříme relaci S o dvojice nezbytné k tomu, aby
	//     byla tranzitivní
	int a=0, b, c;
	char zmena = 0;
	do
	{
		//zmena = 1;
		for(a=0; a<n; a++)
		//do
		{
			for(b=0; b<n; b++)
			{
				// Našli jsme (a, b)eR
				if(R[a][b])
				{
					for(c=0; c<n; c++)
					{
						// Našli jsme (b, c)eR
						if(R[b][c])
						{
							S[a][c]=1;
							zmena = 0;
						}
					}
				}
			}
			//a++;
		}
	}while(zmena == 0);
	return S;
}

// Vytvoří a vrátí novou matici
// Všechny žádná uspořádaná dvojice nebude nastavena na pravdu
char **novaMatice(int n)
{
	char **M;
	M = malloc(sizeof(char*)*n);

	int i;
	for(i=0; i<n; i++)
		M[i] = malloc(sizeof(char)*n);
	return M;
}

// Kopíruje uspořádané dvojice z jedné matice do druhé
// Původní dvojice cílové matice zůstanou zachovány
void kopirujMatici(char **co, char **kam, int n)
{
	int i, j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(co[i][j])
				kam[i][j] = 1;
		}
	}
}

// Do konzole vypíše relaci ve tvaru
//     {(0, 2), (1, 2), (2, 0), (2, 2), (2, 4), }
void vypisRelaci(char **R, int n)
{
	printf("{");
	int i, j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(R[i][j])
				printf("(%i, %i), ", i, j);
		}
	}
	printf("}\n");
}

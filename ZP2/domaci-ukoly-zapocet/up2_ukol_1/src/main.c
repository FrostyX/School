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
#include <string.h> // memcpy

// Datový typ bool se pro tuto reprezentaci matic hodí lépe
typedef enum { false, true } bool;
//typedef char bool;
//enum { true, false};

// Uzávěrové funkce
// Oproti zadání jsem změnil datový tip char na bool
//     řešení je samozřejmě plně funkční i pro char
bool **reflexivni(bool **R, int n);
bool **symetricky(bool **R, int n);
bool **tranzitivni(bool **R, int n);

// Pomocné funkce
void kopirujMatici(bool **co, bool **kam, int n);
void vypisRelaci(bool **R, int n);
bool **novaMatice(int n);

int main(int argc, char **argv)
{
	// Relace R definovaná pomocí matice
	bool r0[] = {0, 0, 1, 0, 0};
	bool r1[] = {0, 0, 1, 0, 0};
	bool r2[] = {1, 0, 1, 0, 1};
	bool r3[] = {0, 0, 0, 0, 0};
	bool r4[] = {0, 0, 0, 0, 0};
	bool *R[] = {r0, r1, r2, r3, r4};

	// Velikost matice je počítána podle počtu prvků v prvním řádku
	int n = sizeof(r0)/sizeof(bool*);

	// Zjištění uzávěrů
	bool **Ur = reflexivni(R, n);
	bool **Us = symetricky(R, n);
	bool **Ut = tranzitivni(R, n);

	printf("%i", sizeof(bool));
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
bool **reflexivni(bool **R, int n)
{
	// Vytvoříme novou matici a naplníme ji uspořádaními
	//     dvojicemi z relace R
	bool **S = novaMatice(n);
	kopirujMatici(R, S, n);

	// Rozšíříme relaci S o dvojice nezbytné k tomu, aby
	//     byla reflexivní
	int i;
	for(i=0; i<n; i++)
	{
		if(R[i][i]!=1)
			S[i][i] = 1;
	}
	return S;
}

// Pokud (a, b)eR pak musí (b, a)eR
bool **symetricky(bool **R, int n)
{
	// Vytvoříme novou matici a naplníme ji uspořádaními
	//     dvojicemi z relace R
	bool **S = novaMatice(n);
	kopirujMatici(R, S, n);

	// Rozšíříme relaci S o dvojice nezbytné k tomu, aby
	//     byla symetrická
	// @TODO Neprocházet zbytečně celou matici
	int i, j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(R[i][j]==1)
				S[j][i] = 1;
		}
	}
	return S;
}

// Pokud (a, b)eR a (b, c)eR, pak musí (a, c)eR
bool **tranzitivni(bool **R, int n)
{
	// Vytvoříme novou matici a naplníme ji uspořádaními
	//     dvojicemi z relace R
	bool **S = novaMatice(n);
	kopirujMatici(R, S, n);

	// Rozšíříme relaci S o dvojice nezbytné k tomu, aby
	//     byla tranzitivní
	int a, b, c;
	for(a=0; a<n; a++)
	{
		for(b=0; b<n; b++)
		{
			// Našli jsme (a, b)eR
			if(R[a][b]==1)
			{
				for(c=0; c<n; c++)
				{
					// Našli jsme (b, c)eR
					if(R[b][c]==1)
						S[a][c]=1;
				}
			}
		}
	}
	return S;
}

// Vytvoří a vrátí novou matici
// Všechny žádná uspořádaná dvojice nebude nastavena na pravdu
bool **novaMatice(int n)
{
	bool **M;
	M = malloc(sizeof(bool*)*n);

	int i;
	for(i=0; i<n; i++)
		M[i] = malloc(sizeof(bool)*n);
	return M;
}

// Kopíruje uspořádané dvojice z jedné matice do druhé
// Původní dvojice cílové matice zůstanou zachovány
void kopirujMatici(bool **co, bool **kam, int n)
{
	int i, j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(co[i][j]==1)
				kam[i][j] = 1;
		}
	}
}

// Do konzole vypíše relaci ve tvaru
//     {(0, 2), (1, 2), (2, 0), (2, 2), (2, 4), }
void vypisRelaci(bool **R, int n)
{
	printf("{");
	int i, j;
	for(i=0; i<n; i++)
	{
		for(j=0; j<n; j++)
		{
			if(R[i][j]==1)
				printf("(%i, %i), ", i, j);
		}
	}
	printf("}\n");
}


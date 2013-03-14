/* Zadání:
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "Jmena2.h"

struct node
{
	char name[20];
	struct node *left;
	struct node *right;
};

struct node *add(const char *name, struct node **root);
void inOrderWalk(struct node **root);

int main(int argc, char **argv)
{
	struct node *tree = 0;

	int i;
	for(i=0; i<Pocet; i++)
	{
		add(Jmena[i], &tree);
	}
	inOrderWalk(&tree);
	printf("Výška stromu: %i\n", maxHeight(&tree));
	return 0;
}

// Přidá jméno do stromu
struct node *add(const char *name, struct node **root)
{
	// Pokud kořen neexistuje
	if(*root==0)
	{
		*root = malloc(sizeof(struct node));
		strcpy((*root)->name, name);
		(*root)->left = 0;
		(*root)->right = 0;
	}

	// Pokud je vkládané jméno "větší" než kořen
	else if(strcmp(name, (*root)->name) > 0)
		add(name, &(*root)->right);

	// Pokud je vkládané jméno "menší" než kořen
	else if(strcmp(name, (*root)->name) < 0)
		add(name, &(*root)->left);
}

// Projde strom a vypíše jména podle abecedy
void inOrderWalk(struct node **root)
{
	if(*root==0)
		return;

	inOrderWalk(&(*root)->left);
	printf("Jmeno: %s\n", (*root)->name);
	inOrderWalk(&(*root)->right);
}

// Vrátí výšku celého stromu
int maxHeight(struct node **root)
{
	if(*root==0)
		// Nikoliv 0. Je potřeba výsledek zmenšit, protože
		//    výška kořene je 0, nikoliv 1.
		return -1;

	int mLeft = maxHeight(&(*root)->left);
	int mRight = maxHeight(&(*root)->right);
	return (mLeft > mRight? mLeft : mRight) + 1;
}

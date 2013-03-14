/* Zadání: Implementace binárního stromu a funkcí pro práci s ním
 *
 * Info: Symbolem !!! jsou označeny konstrukce, které jsou nezbytné k sestavení stromu
 *
 * Jakub Kadlčík [jakub.kadlcik01@upol.cz]
 *
 * Zdroje:
 *    [0] http://www.cprogramming.com/tutorial/c/lesson18.html
 *
 *    [1] Tree abstract data type
 *        - http://www.youtube.com/watch?v=tJRB-ZPwFEE
 *        - http://www.youtube.com/watch?v=StP3VZlnKc8
 *        - http://www.youtube.com/watch?v=MmOMfsFoRFQ
 *        - http://www.youtube.com/watch?v=SFuDvvo-LaY
 *        - http://www.youtube.com/watch?v=lxVbQPLOA1o
 *    [2] Tree traversal
 *        - http://www.youtube.com/watch?v=d3c6Hc9hNz8
 *        - http://www.youtube.com/watch?v=zZ58RLu0YkE
 *        - http://www.youtube.com/watch?v=DNLb79o0-Kw
 *    [3] Binary trees
 *        - http://www.youtube.com/watch?v=RZOqOdAmqWU
 *        - http://www.youtube.com/watch?v=EAF5Ja_UwuA
 *        - http://www.youtube.com/watch?v=KxyGuXbwzEY
 *
 * Product is under The BSD 3-Clause License
 * Copyright (c) 2012, Jakub Kadlčík
 * All rights reserved.
 */

#include <stdio.h>
#include <stdlib.h>

// Struktura reprezentující jeden uzel stromu
struct node // !!!
{
	int number;
	struct node *left;
	struct node *right;
};

// Funkce pro práci se stromem
struct node *find(int number, struct node **root);
struct node *add(int number, struct node **root); // !!!
struct node *removeMin(int number, struct node **root);

// Průchod a výpis celého stromu
void inOrderWalk(struct node **root);
void preOrderWalk(struct node **root);
void postOrderWalk(struct node **root);

// Výška a hloubka
int height(struct node **root);
int depth(struct node **root);
int maxHeight(struct node **root);

// @TODO dopsat funkci pro vytvoření stromu, aby byl veškerý kód ve funkcích
int main(int argc, char **argv)
{
	struct node *tree = 0;

	add(1, &tree);
	add(2, &tree);
	add(3, &tree);
	add(4, &tree);
	add(5, &tree);
	add(6, &tree);
	add(7, &tree);

	printf("Height: %i\n", maxHeight(&tree));

	printf("inOrderWalk:   ");
	inOrderWalk(&tree);
	printf("\n");

	printf("preOrderWalk:  ");
	preOrderWalk(&tree);
	printf("\n");

	printf("postOrderWalk: ");
	postOrderWalk(&tree);
	printf("\n");
	return 0;
}

// @TODO Zařídit vyvažování stromu tak, aby byl vždy nejefektivnější
//     --> AVL vyvážení
struct node *add(int number, struct node **root)
{
	// Pokud kořen neexistuje
	if(*root==0)
	{
		*root = malloc(sizeof(struct node));
		(*root)->number = number;
		(*root)->left = 0;
		(*root)->right = 0;
	}

	// Pokud je vkládané číslo větší než kořen
	else if(number>(*root)->number)
		add(number, &(*root)->right);

	// Pokud je vkládané číslo menší než kořen
	else if(number<(*root)->number)
		add(number, &(*root)->left);
}

// Vrátí výšku celého stromu
int maxHeight(struct node **root)
{
	if(*root==0)
		return 0;

	int mLeft = maxHeight(&(*root)->left);
	int mRight = maxHeight(&(*root)->right);
	return (mLeft > mRight? mLeft : mRight) + 1;
}

// Projde pole a vypíše čísla vzestupně
void inOrderWalk(struct node **root)
{
	if(*root==0)
		return;

	inOrderWalk(&(*root)->left);
	printf("%i ", (*root)->number);
	inOrderWalk(&(*root)->right);
}

void preOrderWalk(struct node **root)
{
	if(*root==0)
		return;

	printf("%i ", (*root)->number);
	preOrderWalk(&(*root)->left);
	preOrderWalk(&(*root)->right);
}

void postOrderWalk(struct node **root)
{
	if(*root==0)
		return;

	postOrderWalk(&(*root)->left);
	postOrderWalk(&(*root)->right);
	printf("%i ", (*root)->number);
}

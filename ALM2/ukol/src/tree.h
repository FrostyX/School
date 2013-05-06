#ifndef TREE
#define TREE 1

#include <stdlib.h>
#include <stdio.h>
#include <math.h>

typedef struct node
{
	int order;
	int item[3];
	struct node *child[4];
} Node;

Node *CreateNode(int x, Node *v, Node *w);
Node *SplitNode(Node *u, Node *v);
char Insert(Node **T, int x);
int Height(Node *u);
float Podil(Node *u, int n);
void PrvniPosledni(Node *tree, int n);
int Count(Node *u);

#endif

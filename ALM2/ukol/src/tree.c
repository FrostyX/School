#include "tree.h"

int nodes=0; // Globální proměnná uchovávající počet uzlů

Node *CreateNode(int x, Node *v, Node *w)
{
	nodes++;

	Node *u = (Node*)malloc(sizeof(Node));
	u->item[0] = x;
	u->order = 2;
	u->child[0] = v;
	u->child[1] = w;
	u->child[2] = NULL;
	u->child[3] = NULL;
	return u;
}

Node *SplitNode(Node *u, Node *v)
{
	if(v==NULL)
	{
		u->child[0] = CreateNode(u->item[0], u->child[0], u->child[1]);
		u->child[1] = CreateNode(u->item[2], u->child[2], u->child[3]);
		u->order = 2;
		u->item[0] = u->item[1];
		return u;
	}

	int j = v->order-1;
	while((j>0) && (u->item[1] < v->item[j-1]))
	{
		v->item[j] = v->item[j-1];
		v->child[j+1] = v->child[j];
		j--;
	}

	v->item[j] = u->item[1];
	v->child[j] = u;
	u->order = 2;
	v->child[j+1] = CreateNode(u->item[2], u->child[2], u->child[3]);
	v->order++;

	return v;
}

char Insert(Node **T, int x)
{
	Node *u = *T;

	if(u==NULL)
	{
		*T = CreateNode(x, NULL, NULL);
		return 1;
	}

	Node *v = malloc(sizeof(Node));
	v = NULL;
	while(1)
	{
		if(u->order==4)
			u = SplitNode(u, v);
		int i=0;
		while((i<u->order-1) && (x>=u->item[i]))
		{
			if(x==u->item[i])
				return 0;
			i++;
		}

		if(u->child[i]!=NULL)
		{
			v = u;
			u = u->child[i];
		}
		else
		{
			int j = u->order-1;
			while(j>i)
			{
				u->item[j] = u->item[j-1];
				j--;
			}
			u->item[i] = x;
			u->order++;
			return 1;
		}
	}
}

int Height(Node *u)
{
	if(u==NULL)
		return -1;
	return Height(u->child[0])+1;
}

float Podil(Node *u, int n)
{
	int h = Height(u);
	return h/log2(n);
}

void PrvniPosledni(Node *tree, int n)
{
	static int tmp = 0;
	int i = 0;

	if (tree == NULL)
		return;

	PrvniPosledni(tree->child[0], n);

	do
	{
		tmp++;
		if ((tmp <= 10) || (tmp >= n-9))
		{
			if(tmp==1)
				printf("\n\nPrvnich deset prvku:\n");
			if(tmp==n-9)
				printf("\n\nPoslednich deset prvku:\n");

			printf("%i ", tree->item[i]);
		}
		i++;

		PrvniPosledni(tree->child[i], n);
	} while(i<(tree->order-1));
}

int Count(Node *u)
{
	static int count=0;
	if(u==NULL)
		return 0;

	int i=0;
	Count(u->child[0]);

	do{
		count++;
		i++;
		Count(u->child[i]);
	} while(i<u->order-1);
	return count;
}

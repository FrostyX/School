#include "tree.h"

Node *CreateNode(int x, Node *v, Node *w)
{
	Node *u = malloc(sizeof(Node));
	u->item[0] = x;
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

#include <stdio.h>
#include <stdlib.h>

#define YEAR_OFFSET 1980

typedef struct node
{
	int value;
	struct node *next;
} Node;

void int2bits(char *, int);
short encode_date(char day, char month, short year);
void decode_date(short date, int *day, int *month, int *year);
void my_memcpy(void *dest, void *src, size_t size);
void print_nodes(Node *root);
void args_order(int, int, int);


int main(int argc, char **argv)
{
	// 1
	printf("---[1]--------------------\n");
	char a_bin[32];
	int2bits(a_bin, 345);
	printf("%s\n", a_bin);

	// 2
	printf("\n---[2]--------------------\n");
	short encodedDate = encode_date(20, 2, 2014);
	printf("%d\n", encodedDate);

	// 3
	printf("\n---[3]--------------------\n");
	int decodedDay = 0;
	int decodedMonth = 0;
	int decodedYear = 0;
	decode_date(encodedDate, &decodedDay, &decodedMonth, &decodedYear);
	printf("%d. %d. %d\n", decodedDay, decodedMonth, decodedYear);

	// 4
	printf("\n---[4]--------------------\n");
	int size = 3;
	char src[] = {'A', 'B', 'C'};
	char dest[size];
	my_memcpy(dest, src, size);

	int i;
	for(i=0; i<size; i++)
	{
		printf("%c\n", dest[i]);
	}


	// 5
	printf("\n---[5]--------------------\n");
	Node *nodeA = malloc(sizeof(Node*));
	Node *nodeB = malloc(sizeof(Node*));
	Node *nodeC = malloc(sizeof(Node*));

	nodeA->value = 5;
	nodeB->value = 6;
	nodeC->value = 7;

	nodeA->next = nodeB;
	nodeB->next = nodeC;
	nodeC->next = NULL;

	print_nodes(nodeA);

	// 6
	printf("\n---[6]--------------------\n");
	args_order(printf("A"), printf("B"), printf("C"));
	printf("\n");

	return 0;
}

// Převede číslo na textový řetězec představující jeho zápis v binární podobě
void int2bits(char *output, int n)
{
	int i;
	for(i=31; i>=0; i--)
	{
		*output = (1 << i) & n ? '1' : '0';
		output++;
	}
	*output = '\0';
}

// Zakóduje datum do 16bitového čísla následovně: YYYY-YYYM-MMMD-DDDD
short encode_date(char day, char month, short year)
{
	short date = 0;
	date |= (year - YEAR_OFFSET);
	date <<= 4;
	date |= month;
	date <<= 5;
	date |= day;

	return date;
}

void decode_date(short date, int *day, int *month, int *year)
{
	*day = date & 0b11111;
	*month = (date & 0b111100000) >> 5;
	*year = ((date & 0b1111111000000000) >> 9) + YEAR_OFFSET;
}

void my_memcpy(void *dest, void *src, size_t size)
{
	unsigned char *destPtr = (unsigned char*)dest;
	unsigned char *srcPtr = (unsigned char*)src;
	unsigned char *srcEnd = srcPtr + size;
	while (srcPtr != srcEnd){
		*destPtr = *srcPtr;
		*destPtr++;
		*srcPtr++;
	}
}

void print_nodes(Node *root)
{
	Node *node = root;
	while(node->next != NULL)
	{
		printf("%d\n", node->value);
		node = node->next;
	}
	printf("%d\n", node->value);
}

// Zjistí v jakém pořadí jsou vyhodnocovány argumenty.
void args_order(int a, int b, int c) {}

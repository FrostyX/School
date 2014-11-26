#include <iostream>
#include <pthread.h>
#include <fstream>

using namespace std;

typedef struct Param
{
	string file;
	int n;
} param;

bool result1;
bool result2;

void *search(void *arg)
{
	Param *param = (Param*) arg;

	ifstream file (param->file.c_str(), ios::in | ios::binary | ios::ate);
	if(file.is_open())
	{
		int end=file.tellg();
		file.seekg(0);

		unsigned short n;
		while(file.tellg()!=end)
		{
			file.read((char *)&n, sizeof n);
			if(n == param->n)
			{
				if(param->file == "Cisla1") result1 = true; else result2 = true;
				return NULL;
			}
		}
	}
	if(param->file == "Cisla1") result1 = false; else result2 = false;
}

int main()
{
	int n;
	while(n != 0)
	{
		cout << "n: ";
		cin >> n;

		Param param1 = {"Cisla1", n};
		Param param2 = {"Cisla2", n};

		pthread_t thread1, thread2;

		pthread_create(&thread1, NULL, search, (void*) &param1);
		pthread_create(&thread2, NULL, search, (void*) &param2);

		pthread_join(thread1, NULL);
		pthread_join(thread2, NULL);

		cout << "Cisla1: " << (result1 ? "yes" : "no") << endl;
		cout << "Cisla2: " << (result2 ? "yes" : "no") << endl;
	}
	return 0;
}

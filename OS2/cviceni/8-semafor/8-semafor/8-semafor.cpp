// 8-semafor.cpp : main project file.

#include "stdafx.h"
#include <iostream>
#include <fstream>
#include <string>
#include <Windows.h>

using namespace System;
using namespace std;

HANDLE s3 = CreateSemaphore(NULL, 0, 2, NULL);
struct Param
{
	string soubor;
	HANDLE semafor;
	bool eof;
	string jmeno;
};

void printFile(Param *p)
{
	ifstream s(p->soubor);
	while(!s.eof())
	{
		WaitForSingleObject(p->semafor, INFINITE);
		getline(s, p->jmeno);
		ReleaseSemaphore(s3, 1, NULL);
	}
	p->eof = true;
}

int main(array<System::String ^> ^args)
{
	HANDLE s1 = CreateSemaphore(NULL, 0, 1, NULL);
	HANDLE s2 = CreateSemaphore(NULL, 0, 1, NULL);

	Param p1 = {"C:\\Users\\FrostyX\\Desktop\\7\\Jmena1s.txt", s1, false, ""};
	Param p2 = {"C:\\Users\\FrostyX\\Desktop\\7\\Jmena2s.txt", s2, false, ""};

	HANDLE h[2];
	h[0] = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)printFile, &p1, CREATE_SUSPENDED, NULL);
	h[1] = CreateThread(NULL, 0, (LPTHREAD_START_ROUTINE)printFile, &p2, CREATE_SUSPENDED, NULL);
	ResumeThread(h[0]);
	ResumeThread(h[1]);

	int i = 1;
	string name1, name2;

	ReleaseSemaphore(p1.semafor, 1, NULL);
	WaitForSingleObject(s3, INFINITE);
	name1 = p1.jmeno;

	ReleaseSemaphore(p2.semafor, 1, NULL);
	WaitForSingleObject(s3, INFINITE);
	name2 = p2.jmeno;

	while(true)
	{
		if(p1.eof)
		{
			cout << i << ". " << p2.jmeno << endl;
			ReleaseSemaphore(p2.semafor, 1, NULL);
			WaitForSingleObject(s3, INFINITE);
		}
		if(p2.eof)
		{
			cout << i << ". " << p1.jmeno << endl;
			ReleaseSemaphore(p1.semafor, 1, NULL);
			WaitForSingleObject(s3, INFINITE);
		}

		if(!p1.eof && !p2.eof)
		{
			if(name1 < name2)
			{
				cout << i << ". " << name1 << endl;
				ReleaseSemaphore(p1.semafor, 1, NULL);
				WaitForSingleObject(s3, INFINITE);
				name1 = p1.jmeno;
			}
			else if(name1 > name2)
			{
				cout << i << ". " << name2 << endl;
				ReleaseSemaphore(p2.semafor, 1, NULL);
				WaitForSingleObject(s3, INFINITE);
				name2 = p2.jmeno;
			}
			i++;
		}
		else break;
	}

	cin.get();
	return 0;
}

#include "strfce.h"

int pocet_vyskytu(string veta, string slovo)
{
	veta = tolower(veta);
	slovo = tolower(slovo);

	// http://stackoverflow.com/a/8614196/3285282
	int occurrences = 0;
	string::size_type start = 0;

	while ((start = veta.find(slovo, start)) != string::npos)
	{
		++occurrences;
		start += slovo.length(); // see the note
	}

	return occurrences;
}

string tolower(string str)
{
	for(int i=0; i<str.length(); i++)
	{
		if((str[i] > 'A') && (str[i] < 'Z'))
		{
			str[i] += 'a' - 'A';
		}
	}
	return str;
}

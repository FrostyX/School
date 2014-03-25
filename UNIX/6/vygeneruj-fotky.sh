#!/bin/bash

for i in {0001..1000}; do

	day=$(($RANDOM%30 + 1))
	month=$(($RANDOM%11 + 1))
	year=$(($RANDOM%100 + 1970))

	echo $month/$day/$year > soubory/$i
done

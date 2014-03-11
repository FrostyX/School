#!/bin/bash
# Jakub Kadlčík

from=$1
increment=$2
to=$3

if [ "$#" == "2" ]; then
	increment=1
	from=$1
	to=$2
elif [ "$#" == "1" ]; then
	from=1
	to=$1
	increment=1
fi

if [ $from -gt $to ]; then
	for ((i=$from; i>=$to; i=i+$increment)); do
		echo $i;
	done
else
	for ((i=$from; i<=$to; i=i+$increment)); do
		echo $i;
	done
fi

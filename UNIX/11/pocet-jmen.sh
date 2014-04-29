#!/bin/bash

while read name; do
	echo -n $name": "
	cat studenti.txt |awk 'BEGIN { FS = "\t" } ; { print $1 }' |grep "^$name$" |wc -l

done < <(cat studenti.txt |awk 'BEGIN { FS = "\t" } ; { print $1 }' |sort |uniq)

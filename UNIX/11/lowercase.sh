#!/bin/bash

while read name; do
	echo $name |awk '{for (i=0; i<=NF; i++) {sub(".", substr(toupper($i), 1,1) , $i)}} { print }'

done < <(cat studenti.txt |awk 'BEGIN { FS = "\t" } ; { print tolower($2) }')

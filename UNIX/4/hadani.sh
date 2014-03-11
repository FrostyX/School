#!/bin/bash
# Jakub Kadlčík

from=$1
to=$2

if [ "$#" == "1" ]; then
	from=0
	to=$1
elif [ "$#" == "0" ]; then
	from=0
	to=100
fi


echo "Mysli si číslo"

while true; do
	guess=$(((from + to) / 2))
	answer=""
	echo "Myslíš si číslo $guess ? Nebo větší či menší?"
	echo "Uhodl jsi: u  |  Větší: v  |  Menší: m"
	read answer

	case "$answer" in
	"m")
		to=$guess
		;;
	"v")
		from=$guess
		;;
	"u")
		echo "Skvěle"
		break
		;;
	*)
		echo "Taková možnost neexistuje."
		;;
	esac
done

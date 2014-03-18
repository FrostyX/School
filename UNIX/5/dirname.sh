#!/bin/bash

function dirname {
	file=$1

	# When directory, remove last /
	if [ -d $file ] && [ ${#file} -ne 1 ]; then
		file=${file%?}
	fi

	# Parse everything to last /
	dir=`echo $file |tr "/" "\n" |head -n -1 |tr "\n" "/"`

	# Remove last /
	if [ ${#dir} -gt 1 ]; then
		dir=${dir%?}
	fi

	# It have to be in same directory
	if [ "$dir" == "" ]; then
		dir="."
	fi

	echo $dir
}

dirname $1

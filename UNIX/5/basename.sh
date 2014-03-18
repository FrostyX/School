#!/bin/bash

function basename {
	path=$1

	# When directory, remove last /
	if [ -d $path ] && [ ${#path} -ne 1 ] && [ "${path:${#path} - 1}" == "/" ]; then
		path=${path%?}
	fi

	# String after last /
	file=`echo $path |tr "/" "\n" |tail -n 1 |tr "\n" "/"`
	file=${file:0:-1}

	# It have to be /
	if [ "$file" == "" ]; then
		file="/"
	fi

	echo $file
}

basename $1

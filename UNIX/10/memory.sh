#!/bin/bash

declare -A memory

# Find memory usage for every user
while read line; do
	user=`echo $line |awk '{print $1}'`
	mem=`echo $line |awk '{print $6}'`

	cur=${memory[$user]}
	if [ "$cur" == "" ]; then
		cur=0
	fi

	memory[$user]=$(($cur + $mem))
done < <(ps aux |tail -n +2)

# Print it
for user in "${!memory[@]}"; do
	echo $user": "${memory[$user]}
done

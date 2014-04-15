BEGIN {
	file = ARGV[1]

	# Save file as array
	i = 1
	while((getline line<file) > 0)
	{
		split(line, line_a, " ")
		for(j=1; j<=length(line_a); j++)
		{
			f[i][j] = line_a[j]
		}
		i++
	}

	# Rotate into array called t
	for(i=1; i<=length(f); i++)
	{
		for(j=1; j<=length(f[i]); j++)
		{
			t[j][i] = f[i][j]
		}
	}


	# Print the rotated file
	for(i=1; i<=length(f); i++)
	{
		for(j=1; j<=length(t[i]); j++)
		{
			printf t[i][j] " "
		}
		printf "\n"
	}
}

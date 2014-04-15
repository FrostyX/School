BEGIN {
	file = ARGV[1]

	# Counters
	l = 0
	w = 0
	c = 0

	while((getline line<file) > 0)
	{
		split(line, words, " ")

		for(i=1; i<=length(words); i++)
		{
			split(words[i], word, "")
			for(j=1; j<=length(word); j++)
				c++

			w++
		}
		l++
	}

	# Spaces lost on spliting
	c += w

	printf "%d %d %d %s\n", l, w, c, file
}

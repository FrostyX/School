## LaTeX bez IDE

### Struktura
Základní struktura dokumentu zajišťující funkční češtinu

	\documentclass[10pt,a4paper]{article}
	\usepackage[utf8]{inputenc}
	\usepackage[czech]{babel}

	\begin{document}
		Hello world
	\end{document}

### Překlad - GNU/Linux
Pro vygenerování PDF dokumentu bez zbytečných mezikroků, můžeme použít příkaz `pdflatex` z balíčku `texlive-core`:

	pdflatex main.tex

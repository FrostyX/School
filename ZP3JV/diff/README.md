# Diff
## Disclaimer
Skromná implementace UNIXového nástroje `diff`. V žádném případě se nesnaží nahradit původní nástroj, nebo pokrýt jeho plnou funkcionalitu. Tento projekt vznikl jako semestrální práce do předmětu ZP3JV vyučovaném na Univerzitě Palackého.

## Autor
Jakub Kadlčík &lt;[jakub.kadlcik01@upol.cz](mailto:jakub.kadlcik01@upol.cz)&gt;

## Licence
[The BSD 3 lincese](https://github.com/FrostyX/School/blob/master/LICENSE.md "The BSD 3-Clause License")

## Funkcionalita
- Načte dva textové soubory a porovná je
- Výstup do souboru
- Barevný a nebarevný výstup
- Nápověda

## Použití
	# Sestavení
	ant

	# Spuštění
	java -jar diff.jar [možnosti] prvni-soubor druhy-soubor

## Možnosti
	`-nc`, `--no-color`
	    Neobarví výstup
	    (Obarvení výstupu je podporováno pouze pro UNIXové systémy.
	    Na Windows nemá smysl tento přepínač použít.)
	`-l`, `--number-lines`
	    Vypíše čísla řádků
	`-o`, `--output` soubor
	    Zapíše výstup do souboru
	`-h`, `--help`
	    Vypíše nápovědu pro použití programu

## Výstup
Výstup porovnání dvou textových souborů bez použití žádných přepínačů (ve skutečnosti je obarvený)

	$[FrostyX  diff]-> java -jar dist/diff.jar ~/from ~/to
	> This is an important notice! It should
	> therefore be located at the beginning of
	> this document!
	>
	  This part of the document has stayed
	  the same from version to version.
	<
	< This paragraph contains text that is
	< outdated - it will be deprecated '''and'''
	< deleted '''in''' the near future.

	  It is important to spell check this
	< dokument. On the other hand, a misspelled
	< word isn't the end of the world.
	> document. On the other hand, a misspelled
	> word isn't the end of the world. This
	> paragraph contains important new
	> additions to this document.
	>

Výstup porovnání dvou textových souborů s použitím přepínače --number-lines (ve skutečnosti je obarvený)

	$[FrostyX  diff]-> java -jar dist/diff.jar ~/from ~/to --number-lines
	   |  1 | > This is an important notice! It should
	   |  2 | > therefore be located at the beginning of
	   |  3 | > this document!
	   |  4 | >
	1  |  1 |   This part of the document has stayed
	2  |  2 |   the same from version to version.
	3  |    | <
	4  |    | < This paragraph contains text that is
	5  |    | < outdated - it will be deprecated '''and'''
	6  |    | < deleted '''in''' the near future.
	7  |  7 |
	8  |  8 |   It is important to spell check this
	9  |    | < dokument. On the other hand, a misspelled
	10 |    | < word isn't the end of the world.
	   |  9 | > document. On the other hand, a misspelled
	   | 10 | > word isn't the end of the world. This
	   | 11 | > paragraph contains important new
	   | 12 | > additions to this document.
	   | 13 | >

# Zdroje
- <https://github.com/FrostyX/School/blob/master/ZP3JV/diff/An-O(ND)-Difference-Algorithm-and-Its-Variations.pdf>
- <http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_subsequence#Python>
- <http://www.abclinuxu.cz/clanky/navody/unixove-nastroje-8-diff-a-patch>

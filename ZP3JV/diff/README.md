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

# Zdroje
- <https://github.com/FrostyX/School/blob/master/ZP3JV/diff/An-O(ND)-Difference-Algorithm-and-Its-Variations.pdf>
- <http://en.wikibooks.org/wiki/Algorithm_Implementation/Strings/Longest_common_subsequence#Python>
- <http://www.abclinuxu.cz/clanky/navody/unixove-nastroje-8-diff-a-patch>

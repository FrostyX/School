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
- Možnost vypsat kontext změn (-u a -U)
- Nápověda

## Použití
	diff [možnosti] prvni-soubor druhy-soubor

## Možnosti
	`-nc`, `--no-color`
	    Neobarví výstup
	`-o`, `--output` soubor
	    Zapíše výstup do souboru
	`-u`
	    Use the unified output format.
	`-U` lines
	    Use the unified output format, showing lines (an integer) lines of context, or three if lines is not given. For proper operation, patch typically needs at least two lines of context.

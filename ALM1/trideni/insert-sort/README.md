## Insert-sort
### Využití

### Algoritmus
1. Rozdělíme si pole na setříděnou a nesetříděnou část.
	- Do setříděné patří první prvek a do nesetříděné zbytek pole
2. Po prvcích projdeme nesetříděnou část a pokaždé:
	1. Zapamatujeme si první prvek nesetříděné části
	2. Od konce procházíme setříděnou část a v případě, že:
		- je setříděný prvek větší než zapamatovaný, posuneme jej o pozici doprava.
		- není setříděný prvek větší, než zapamatovaný, uložíme na jeho pozici zapamatovaný prvek.

### Příklad
Vstupní pole: 5 1 15 13 20 10

	5 | 1 15 13 20 10
	1 5 | 15 13 20 10
	1 5 15 | 13 20 10
	1 5 13 15 | 20 10
	1 5 13 15 20 | 10
	1 5 10 13 15 20 |


### Složitost
#### Časová složitost
#### Paměťová složitost



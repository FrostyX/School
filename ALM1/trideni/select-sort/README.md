## Select-sort
### Využití

### Algoritmus
1. Rozdělíme si pole na setříděnou a nesetříděnou část.
	- Setříděná část je zatím prázdná a nesetříděná obsahuje celé pole
2. Zopakujeme tolikrát, kolik je v poli prvků:
	1. Po prvcích projdeme nesetříděnou část a pokaždé:
		- Pamatujeme si nejmenší prvek který najdeme
	2. Prohodíme první prvek nesetříděné části s nejmenším nalezeným v nesetříděné části
	3. Posuneme hranici mezi setříděnou a nesetříděnou části

### Příklad
Vstupní pole: 5 3 8 4 2 7

	| 5 3 8 4 2 7
	2 | 3 8 4 5 7
	2 3 | 8 4 5 7
	2 3 4 | 8 5 7
	2 3 4 5 | 8 7
	2 3 4 5 7 | 8

### Složitost
#### Časová složitost
#### Paměťová složitost



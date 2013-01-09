## Shell-sort
### Využití

### Algoritmus
1.Vybíráme různé intervaly z pole
	- zvolíme vzdálenost mezi vybíranými prvky - příkladně n/2
	- vzdálenost postupně půlíme, dokud je větší než 0
2. Na vybraný interval aplikujeme [insert-sort](https://github.com/FrostyX/School/tree/master/ALM1/trideni/insert-sort).

### Příklad
Tříděné pole 5 8 9 4 3 9 6 2

	h=4
	[5] 8 9 4 [3] 9 6 2
	[3] [5] 8 9 4 9 6 2

	h=2
	[3] 5 [8] 9 [4] 9 [6] 2
	[3] 5 [4] [8] 9 9 [6] 2
	[3] 5 [4] [6] [8] 9 9 2

	h=1
	3 | 5 4 6 8 9 9 2
	3 5 | 4 6 8 9 9 2
	3 4 5 | 6 8 9 9 2
	3 4 5 6 | 8 9 9 2
	...
	3 4 5 6 8 9 9 | 2
	2 3 4 5 6 8 9 9 |


### Složitost
#### Časová složitost
#### Paměťová složitost
### Odkazy



## Quick-sort
### Využití

### Algoritmus
1. Zavoláme quicksort na celé pole
2. Zvolíme pivota - prvek uprostřed tříděné části
	- Ideální by bylo zvolit prvek pole, pro které je v daném poli stejný počet větších a menších prvků. To se ale nevyplatí, proto zvolíme prvek na prostřední pozici.
3. Z obou stran současně po prvcích procházíme pole, dokud se nestřetneme
	1. Od konce najdeme prvek, který je menší než pivot.
	2. Od začátku najdeme prvek, který je větší než pivot.
	3. Prohodíme oba nalezené prvky
4. Pokud část prvků menších než je pivot obsahuje alespoň jeden prvek, rekurzivně na ni aplikujeme quicksort.
5. Pokud část prvků větších než je pivot obsahuje alespoň jeden prvek, rekurzivně na ni aplikujeme quicksort.

### Příklad


### Složitost
#### Časová složitost
#### Paměťová složitost
### Odkazy





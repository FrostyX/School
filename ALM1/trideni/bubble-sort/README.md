## Bubble-sort
### Využití
- Nejpomalejší třídící algoritmus, je ale nejintuitivnější.

### Algoritmus
- Zopakujeme tolikrát, kolik je v poli prvků:
	- Po prvcích projdeme celé pole a:
		- Pokud je prvek menší než následující, prohodíme je.


### Příklad
Vstupní pole: 7 3 8 5
	7 3 8 5
	3 7 8 5
	3 7 5 8
	3 5 7 8

### Pseudokód
Vstup:
- a - pole tříděných prvků
- n - velikost pole

	for i <- 0..n
		for j <- 0..n-i
			if(a[j]>a[j+1])
				swap(a[j], a[j+1])

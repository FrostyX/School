## Counting-sort
### Využití
Counting-sort je vhodné použít pro třídění velkého pole prvků, které se skládá pouze z několika-málo různých hodnot.

### Algoritmus
1. Do pomocného pole si uložíme počet výskytů každého prvku obsaženého v poli
	- Předpoklad je, že prvky v poli výskytů jsou seřazeny (seřazení indexů pole 0..n je automatickou záležitostí)
2. K počtu výskytů přičteme počet výskytů předchozích hodnot (získáme něco, čemu můžeme říkat například "koncové pozice")
3. Vytvoříme nové pole o stejné velikosti jako tříděné pole
4. Projdeme vstupní pole a pro každý prvek:
	- Najdeme jeho koncovou pozici a zmenšíme ji o 1
	- Na tuto zmenšenou pozici uložíme prvek do výsledného pole

### Složitost
#### Časová složitost
#### Paměťová složitost
### Odkazy
- http://cs.wikipedia.org/wiki/Counting_sort

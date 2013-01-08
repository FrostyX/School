## Merge-sort
### Využití

### Algoritmus
#### Funkce mergeSort
- Pokud jsou v tříděné části alespoň dva prvky:
	1. Rekurzivně zavoláme setřídění na první polovinu pole
	2. Rekurzivně zavoláme setřídění na druhou polovinu pole
	3. Slejeme (funkce merge) setříděné poloviny dohromady

#### Funkce merge
1. Zjistíme počet prvků v levé a pravé polovině tříděné části pole
2. Vytvoříme pro levou a pravou polovinu tříděné částidvě nové pole
	- Každé pole bude mít o jeden prvek navíc
3. Zkopírujeme do nových polí prvky z tříděné části vstupního pole
4. Na poslední místo obou nových polí vložíme "nekonečno"
6. Projdeme část pole, kterou právě třídíme a:
	- na právě procházený index uložíme menší z prvků obou polí
	- zapamatujeme si, že příště budeme porovnávat následující prvek pole

### Složitost
#### Časová složitost
#### Paměťová složitost
### Odkazy


Na přednáškách zadané domácí úkoly. Nepovinné, ale doporučené umět na zápočet

- [Úkol č.1](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D1)
- [Úkol č.2](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D2)
- [Úkol č.3](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D3)
- [Úkol č.4](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D4)
- [Úkol č.5](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D5)
- [Úkol č.6](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D6)
- [Úkol č.7](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D7)
- [Úkol č.8](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D8)
- [Úkol č.9](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C3%9Akol-%C4%8D9)

### Úkol č.1:
(Přednáška 1)

Přečíst stránky wikipedie:
- History of computing hardware: [http://en.wikipedia.org/wiki/History_of_computing_hardware](http://en.wikipedia.org/wiki/History_of_computing_hardware)
- History of computing hardware (1960s-present): [http://en.wikipedia.org/wiki/History_of_computing_hardware_(1960s-present)](http://en.wikipedia.org/wiki/History_of_computing_hardware_(1960s-present\))
- Computer: [http://en.wikipedia.org/wiki/Computer](http://en.wikipedia.org/wiki/Computer)


### Úkol č.2:
(Přednáška 2) [[řešení](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C5%98e%C5%A1en%C3%AD-%C3%BAkolu-%C4%8D2)]

1. Pro několik čísel zjistěte (hodnotu) čísla ze zápisů ve dvojkové, osmičkové, desítkové a šestnáctkové soustavě.
2. Pro několik čísel zjistěte zápis čísla (dané hodnoty) ve dvojkové, osmičkové, desítkové a šestnáctkové soustavě.


### Úkol č.3:
(Přednáška 2) [[řešení](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C5%98e%C5%A1en%C3%AD-%C3%BAkolu-%C4%8D3)]

Převeďte několik logických funkcí se třemi a více proměnnými do ÚKNF a ÚDNF.


### Úkol č.4:
(Přednáška 2) [[řešení](https://github.com/FrostyX/School/blob/master/UINT/ukoly.md#%C5%98e%C5%A1en%C3%AD-%C3%BAkolu-%C4%8D4)]

Pokuste se minimalizovat logické funkce z předchozího úkolu.


### Úkol č.5:
(Přednáška 2)

Vyjádřete logické operace negace, logický součin, logický součet, implikace ekvivalence a nonekvivalence pomocí (1) Shefferovy funkce a (2) Piercovy funkce.


### Úkol č.6:
(Přednáška 2)

Nakreslete schéma zapojení logického obvodu realizujícího logické operace NOT, AND, OR, implikace, ekvivalence, a XOR pomocí logických členů realizujících operaci (1) NAND a (2) NOR


### Úkol č.7:
(Přednáška 3)

Vytvořte binární reprezentace celých čísel pomocí aditivního, inverzního (jedničkově doplňkového) a (dvojkově doplňkového) kódu.


### Úkol č.8:
(Přednáška 3)

Vytvořte binární reprezentace několika racionálních čísel s fixní i plovoucí řádovou čárkou.


### Úkol č.9:
(Přednáška 3)

Vytvořte binární reprezentace několika českých slov s diakritickými znaky pomocí kódování UTF-8 a UTF-16. K dispozici máte Unicode tabulku znaků (UCS).


--------------------

Moje řešení, které vůbec nemusejí být správná! Pokud najdete chybu, poprosil bych, abyste mi dali vědět.

### Řešení úkolu č.2
#### Část 1)
Převod z libovolné soustavy do desítkové ukázaný na příkladu:

(1024)10 = 1*10^3 + 0*10^2 + 2*10^1 + 4*10^0 = 1000 + 0 + 20 + 4 = 1024

(101)2 = 1*2^2 + 1*2^0 = 4 + 1 = 5

- Zadání ve dvojkové soustavě:
	- 1010010110101 = 2^12 + 2^10 + 2^7 + 2^5 + 2^4 + 2^2 + 2^0 = 5301
	- 101011 = 2^5 + 2^3 + 2^1 + 2^0 = 43
	- 111111111 = 2^9 - 1 = 511
- Zadání v osmičkové soustavě:
	- 7643 = 7*8^3 + 6*8^2 + 4*8^1 + 3 = 3584 + 384 + 32 + 3 = 4003
	- 12345 = 8^4 + 2*8^3 + 3*8^2 + 4*8^1 + 5 = 4096 + 1024 + 192 + 32 + 5 = 5349
	- 353 = 3*8^2 + 5*8^1 + 3 = 192 + 40 + 3 = 235
- Zadání v šestnáctkové soustavě:
	- 14FE = 16^3 + 4*16^2 + 15*16 + 14 = 4096 + 1024 + 240 + 14 = 5374
	- 358C = 3*16^3 + 5*16^2 + 8*16 + 13 = 12288 + 1280 + 128 + 12 = 13708
	- ABCD = 10*16^3 + 11*16^2 + 12*16 + 13 = 40960 + 2816 + 192 + 13 = 43981

#### Část 2)
Převod z desítkové soustavy do libovolné:
Dělíme číslo základem soustavy dokud nedostaneme nulu. Zbytky po dělení napsané od konce jsou výsledkem

- Převod do dvojkové soustavy:
	- 358: 358/2 = 179 (zb 0) / 2 = 89 (zb 1) / 2 = 44 (zb 1) / 2 = 22 (zb 0) / 2 = 11 (zb 0) / 2 = 5 (zb 1) / 2 = 2 (zb 1) / 2 = 1 (zb 0) / 2 = 0 (zb 1)
	      => 101100110
	- 432: 432/2 = 216 (zb 0) / 2 = 108 (zb 0) / 2 = 54 (zb 0) / 2 = 27 (zb 0) / 2 = 13 (zb 1) / 2 = 6 (zb 1) / 2 = 3 (zb 0) / 2 = 1 (zb 1) / 2 = 0 (zb 1)
	      => 110110000
	- 37: 32 + 4 + 1 => 100101
- Převod do osmičkové soustavy:
	- 123: 123/8 = 15 (zb 3) / 8 = 1 (zb 7) / 8 = 0 (zb 1) => 173
	- 5478: 5478/8 = 684 (zb 6) / 8 = 85 (zb 4) / 8 = 10 (zb 5) / 8 = 1 (zb 2) / 8 = 0 (zb 1) => 12546
	- 23: 23/8 = 2 (zb 7) / 8 = 0 (zb 2) => 27
- Převod do šestnáctkové soustavy:
	- 10932 = 10932/16 = 683 (zb 4) / 16 = 42 (zb 11) / 16 = 2 (zb 10) / 16 = 0 (zb 2) => 2AB4
	- 374 = 374/16 = 23 (zb 6) / 16 = 1 (zb 7) / 16 = 0 (zb 1) => 176
	- 1234 = 1234/16 = 77 (zb 2) / 16 = 4 (zb 13) / 16 = 0 (zb 4) => 4D2

### Řešení úkolu č.3
#### První funkce
(p & r) -> q

	p q r V
	0 0 0 1
	0 0 1 1
	0 1 0 1
	0 1 1 1
	1 0 0 1
	1 0 1 0
	1 1 0 1
	1 1 1 1
ÚDNF: (!p & !q & !r) | (!p & !q & r) | (!p & q & !r) | (!p & q & r) | (p & !q & !r) | (p & q & !r) | (p & q & r)

ÚKNF: (!p | q | !r)

#### Druhá funkce
(p | q) <=> (q & r)

	p q r V
	0 0 0 1
	0 0 1 1
	0 1 0 0
	0 1 1 1
	1 0 0 0
	1 0 1 0
	1 1 0 0
	1 1 1 1
ÚDNF: (!p & !q & !r) | (!p & !q & r) | (!p & q & r) | (p & q & r)

ÚKNF: (p | !q | r) & (!p | q | r) & (!p | q | !r) & (!p | !q | r)

### Řešení úkolu č.4

1. Z ÚDNF sestavíme Karnaughovu mapu. (každý součin ÚDNF představuje jedničku v mapě)
2. Najdeme smyčky jedniček
	- Smyčky mohou obsahovat pouze 2^n jedniček
	- Hledáme co největší smyčky (čím je smyčka větší, tím vypadne více proměnných)
3. Napíšeme novou formuli.
	- Formule má tvar stejný jako ÚDNF - součet násobků
	- Každá konjunkce obsahuje pouze prvky, jejichž hodnota se ve smyčce nemění

#### První funkce
ÚDNF: (!p & !q & !r) | (!p & !q & r) | (!p & q & !r) | (!p & q & r) | (p & !q & !r) | (p & q & !r) | (p & q & r)

![Karnaughova mapa](https://raw.github.com/FrostyX/School/master/UINT/img/karn-mapa-prvni.png)

Formule po zjednodušení = !p | q | !r

#### Druhá funkce
ÚDNF: (!p & !q & !r) | (!p & !q & r) | (!p & q & r) | (p & q & r)

![Karnaughova mapa](https://raw.github.com/FrostyX/School/master/UINT/img/karn-mapa-druha.png)

Formule po zjednodušení = (q & r) | (!p & !q)

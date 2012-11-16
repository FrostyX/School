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
(Přednáška 2)

1. Pro několik čísel zjistěte (hodnotu) čísla ze zápisů ve dvojkové, osmičkové, desítkové a šestnáctkové soustavě.
2. Pro několik čísel zjistěte zápis čísla (dané hodnoty) ve dvojkové, osmičkové, desítkové a šestnáctkové soustavě.


### Úkol č.3:
(Přednáška 2)

Převeďte několik logických funkcí se třemi a více proměnnými do ÚKNF a ÚDNF.


### Úkol č.4:
(Přednáška 2)

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

- Převod do dvojkové soustavy:
	- 358
	- 432
	- 37
- Převod do osmičkové soustavy:
	- 123
	- 5478
	- 23
- Převod do šestnáctkové soustavy:
	- 10932
	- 374
	- 1234

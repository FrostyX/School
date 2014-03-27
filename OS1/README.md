# OS1

## Assembler

### Registry
- Datové registry
	- EAX, AX, AH ,AL (Accumulator) - střadač pro násobení a dělení, vstupně-výstupní operace
	- EBX, BX, BH, BL (Base) - nepřímá adresace paměti
	- ECX, CX, CH, CL (Counter) - počitadlo při cyklech, posuvech a rotacích
	- EDX, DX, DH, DL (Data) - nepřímá adresace vstupů/výstupů
- Ukazatele a indexregistry
	- ...

### Numerické operace
- `INC cíl` - cíl++
- `DEC cíl` - cíl--
- `ADD cíl, zdroj` - cíl += zdroj
- `SUB cíl, zdroj` - cíl -= zdroj
- `NEG cíl` - otoč znaménko v cíli
- `MUL zdroj` - registr AL, AX, nebo EAX vynásob (bez znaménka) se zdrojem a výsledek zapiš do AX, DX AX, nebo EDX EAX.
- `IMUL zdroj` - jako MUL, ale násobení se znaménkem
- `IMUL cíl, zdroj` - do cíle vlož součin zdroje a cíle
- `DIV zdroj` - registr AX, (nebo DX AX, nebo EDX EAX) vyděl zdrojem a podíl ulož do AL (nebo AX, nebo EAX) a zbytek po dělení do AH (nebo DX, nebo EDX)
- `IDIV zdroj` - jako DIV ale dělení se znaménkem

### Bitové operace
- `AND cíl, zdroj` - Bitový součin
- `OR cíl, zdroj` - Bitový součet
- `NOT cíl` - Negace po bitech
- `SHR cíl, počet` - Bitový posun doprava (z leva se přidají nuly)
- `SHL cíl, počet` - Bitový posun doleva (z prava se přidají nuly)

### Převod mezi datovými typy
- `MOVSX cíl, zdroj` - rozšíření hodnoty z menší na vetší

### Skoky
- Nepodmíněný skok: `JMP místo` - provede nepodmíněný skok na část kódu uvozenou návěstím `místo`
- Skoky po bezznaménkovém srovnání:
	- `JA návěští` - skok, je-li větší
	- `JAE návěští` - skok, je-li větší nebo rovno
	- `JB návěští` - skok, je-li menš
	- `JBE návěští` - skok, je-li menší nebo rovno
- Skoky po znaménkovém srovnání:
	- `JG návěští` - skok, je-li větší
	- `JGE návěští` - skok, je-li větší nebo rovno
	- `JL návěští` - skok, je-li menší
	- `JLE návěští` - skok, je-li menší nebo rovno

### Adresace paměti



### Zdroje
- <http://phoenix.inf.upol.cz/~krajcap/courses/2014LS/OS1/os1-02.htm>
- <http://phoenix.inf.upol.cz/~krajcap/courses/2014LS/OS1/os1-03.htm>
- <http://phoenix.inf.upol.cz/~krajcap/courses/2014LS/OS1/os1-04.htm>
- <http://phoenix.inf.upol.cz/~krajcap/courses/2014LS/OS1/os1-05.htm>
- <http://phoenix.inf.upol.cz/~krajcap/courses/2014LS/OS1/os1-06.htm>

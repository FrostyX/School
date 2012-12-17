## Výroková logika
### Pojmy
- logika
- logická spojka
- výrok
- pravdivostní hodnota výroku
- symbol logické spojky a pravdivostní funkce logické spojky
- kvantifikátor

### Kontrolní otázky
1. Jaké znáte logický spojky?
2. Co to je klasická a neklasická logika?
3. Jaký je vztah mezi obecným a existenčním kvantifikátorem?
4. Co to je formule výrokové logiky?
5. Vysvětlete, co to je tabulková metoda a k čemu slouží.
6. Vysvětlete pojem sémantické vyplývání.

## Množiny
### Pojmy
- množina
- inkluze
- průnik, sjednocení, rozdíl
- potenční množina

### Kontrolní otázky
1. Může množina obsahovat daný prvek více než jedenkrát? Proč? Jsou množiny {a, b} a {b, a} různé? Proč?
2. Jaké znáte způsoby zápisu množin? Jsou množiny {xeR | x^2 < 0} a {xeN | x^4 < 0} stejné? Je některá z nich rovna {} ?
3. Platí, že když A je podmnožinou(?) B, pak |A| = |B|? Co je to potenční množina dané množiny? Existuje množina, jejíž potenční množina je prázdná?
4. Jaké znáte množinové operace? Jaká je nutná a postačující podmínka pro to, aby A (průnik) X = A? Jaká pro A (sjednoceno) X = A ?

## Relace
### Pojmy
- kartézský součin
- relace, binární relace, inverzní relace, skládání binárních relací, reprezentace binárních relací
- funkce, injekce, surjekce, bijekce
- princip indukce

### Kontrolní otázky
1. Je pravda, že každá neprázdná n-ární relace má alespoň n prvků? Proč?
2. Jaká je inverzní relace k relaci "být otcem" na množině všech lidí (slovně ji popište)? Je-li R výše uvedená relace "být otcem", co je relací R (kolečko) R? Co jsou relace R (šipka doleva) R, R (šipka doprava) R?
3. Jaký je rozdíl mezi tabulkovou a maticovou reprezentací binární relace?
4. Nechť X a Y jsou množiny. Jaký vztah musí platit mezi |X| a |Y| pro to, aby existovala funkce f: X -> Y, která je injekcí, surjekcí, bijekcí?
5. Může být prázdná množina funkcí X do Y? Rozeberte v závislosti na množinách X a Y.

## Čísla a číselné obory
Neobsahuje žádné pojmy ani kontrolní otázky. Text látky je ve skriptu [UvodDoInformatiky.pdf](https://github.com/FrostyX/School/raw/master/UDI/doc/UvodDoInformatiky.pdf) jako kapitola číslo 3 - Čísla (strana 44)

## Kombinatorika a pravděpodobnost
### Pojmy
- pravidla součtu a součinu
- permutace a permutace s opakováním
- variace a variace s opakováním
- kombinace a kombinace s opakováním
- princip inkluze a exkluze
- elementární jev, jev
- pravděpodobnost

### Kontrolní otázky
####První část
1. Co říká pravidlo součtu? Co říká pravidlo součinu?
2. Čím se liší permutace a variace? Čím se liší variace a kombinace?
3. Čím se liší permutace a permutace s opakováním? Čím se liší variace a variace s opakováním? Čím se liší kombinace a kombinace s opakováním?
4. Čím se liší aspekt opakování u permutací s opakováním, variací s opakováním a kombinací s opakováním?

####Druhá část
1. Co říká princip inkluze a exkluze?
2. Jak se zjednoduší vzorec z principu inkluze a exkluze, jsou-li množiny A1, .... An po dvou disjunktní?
3. Jaký je rozdíl mezi pojmy jev a elementární jev?
4. Co je to pravděpodobnost jevu a jak je definována?

## Algoritmy, složitost algoritmů
Zatím jsem nenašel v žádných skriptech kapitolu zabývající se tímto tématem.

## Teorie grafů
### Pojmy
- orientovaný graf, neorientovaný graf, vrchol hrana
- izomorfismus grafů, podgraf
- sled, délka sledu, uzavřený sled, tah, cesta, kružnice, vzdálenost vrcholů
- obohacený graf
- souvislost, komponenta, hledání cest
- stupeň vrcholu, skóre, eulerovský tah

### Kontrolní otázky
1. Vysvětlete rozdíl mezi pojmy orientovaný graf a neorientovaný graf.
2. Je-li graf G izomorfní s grafem G', je jeho podgrafem?
3. Jaký je rozdíl mezi pojmy sled, tah, cesta?
4. Může mít souvislý graf po odstranění jedné hrany tři komponenty?
5. Existuje graf, který má skóre 7, 3, 1?

---
## Odpovědi na kontrolní otázky
! Moje odpovědi, které nemusejí být správné - v tomto případě prosím dejte vědět !

#### Jaké znáte logický spojky?
Konjunkce ^, disjunkce V, implikace ->, ekvivalence <->

#### Co to je klasická a neklasická logika?
Klasickou logikou se rozumí logika, která používá právě dvě pravdivostní hodnoty (pravda a nepravda) a klasické logické spojky (předchozí otázka). Neklasická logika se zabývá dalšími aspekty, používá jiné logické spojky, případně jiné pravdivostní hodnoty.

#### Jaký je vztah mezi obecným a existenčním kvantifikátorem?
Výrokové formy mohou obsahovat proměnné. Dosazením konkrétních čísel vznikají výroky. Kvantifikátory udávají určitou vlastnost těchto proměnných. Obecný kvantifikátor říká, že pro všechna možná x platí následující výrok. Existenční kvantifikátor potom říká, že existuje alespoň jedno x, pro které výrok platí.

#### Co to je formule výrokové logiky?
Formule daného jazyka výrokové logiky je definována následovně:

- Každý výrokový symbol je formule (tzv atomická formule)
- Jsou-li P a Q formule, jsou i výrazy !P, (P & Q), (P | Q), (P => Q), (P <=> Q)

#### Vysvětlete, co to je tabulková metoda a k čemu slouží.
Tabulková metoda představuje jednoduchý způsob, jak zapsat pravdivostní hodnoty dané formule při všech možných ohodnoceních jejích proměnných.

#### Vysvětlete pojem sémantické vyplývání.
Formule Q sémanticky vyplývá z množiny T formulí je pravdivá při každém ohodnocení, při kterém jsou současně pravdivé všechny formule z množiny T. Značí se T |= Q

---

#### Může množina obsahovat daný prvek více než jedenkrát? Proč? Jsou množiny {a, b} a {b, a} různé? Proč?
Ano může, ale množina je dána prvky, které obsahuje. Nemá u nich smysl rozlišovat pořadí, nebo kolikrát je prvek obsažen. Množina jej buďto obsahuje, nebo nikoliv. Uvedené množiny jsou z tohoto důvodu (nezáleží na pořadí) stejné.

#### Jaké znáte způsoby zápisu množin? Jsou množiny {xeR | x^2 < 0} a {xeN | x^4 < 0} stejné? Je některá z nich rovna {} ?
Množiny zapisujeme dvěma základními způsoby. Udáním charakteristické vlastnosti, nebo výčtem jejích prvků. Uvedené množiny nejsou stejné první obsahuje záporná čísla umocněná na poloviny lichých čísel, zatímco druhá je prázdná.

#### Platí, že když A je podmnožinou (býti podmnožinou = inkluze) B, pak |A| = |B|? Co je to potenční množina dané množiny? Existuje množina, jejíž potenční množina je prázdná?
Platí pouze v případě, kdy množina A obsahuje úplně všechny prvky z B. Potenční množina množiny X je množina všech podmnožin množiny X. Množina je podmnožinou sebe samé. Podmnožinou každé množiny je také prázdná množina.

#### Jaké znáte množinové operace? Jaká je nutná a postačující podmínka pro to, aby A (průnik) X = A? Jaká pro A (sjednoceno) X = A ?
Průnik, sjednocení, rozdíl. A (průnik) X = A v případě, kdy X = A. Dále A (sjednoceno) X = A v případě, kdy X = {}

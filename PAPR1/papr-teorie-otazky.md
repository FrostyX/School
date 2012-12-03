## Lekce 1
1. Jaký je rozdíl mezi syntaxí a sémantikou programu?
2. Jak v jazyku scheme zapisujeme seznamy?
3. Z čeho se skládají programy v jazyku scheme?
4. Jak probíhá vyhodnocování symbolických výrazů?
5. Co máme na mysli pod pojmem aplikace procedur?
6. Jaký je rozdíl mezi procedurami a speciálními formami?
7. Proč nemůže být define procedura?
8. Co je aktuální vazba symbolu?
9. Jaké znáte typy chyb a jaký je mezi nimi rozdíl?
10. Co jsou to predikáty?
11. Z jakých částí se skládá REPL?
12. Jaké výhody má prefixová notace výrazů?

## Lekce 2
1. Co to jsou lambda výrazy a jak vypadají?
2. Co vzniká vyhodnocením lambda výrazů?
3. Jaké mají prostředí mezi sebou vazby?
4. Jak vznikají prostředí?
5. Jak se změní vyhodnocování, pokud jej uvažujeme vzhledem k prostředí?
6. Co jsou a jak jsou reprezentovány uživatelsky definovatelné procedury?
7. Jak probíhá aplikace uživatelsky definované procedury?
8. Co jsou to procedury vyšších řádů?
9. Jaký mají vztah procedury a matematické funkce?
10. Co máme na mysli pod pojmem monoidální operace?
11. Co v jazyku scheme považujeme za elementy prvního řádu?
12. Jaký je rozdíl mezi lexikálním a dynamickým rozsahem platnosti?
13. Jaké má výhody lexikální rozsah platnosti?
14. Jaké má nevýhody dynamický rozsah platnosti?
15. Jak by se dal upravit náš interpret tak, aby pracoval s dynamickým rozsahem platnosti?

## Lekce 3
1. Co jsou lokální vazby? K čemu jsou dobré?
2. Proč nemohou být let a let* procedury?
3. Jak se let-bloky přepisují na lambda výrazy?
4. Jak se na ně přepisují let*-bloky?
5. Jak probíhá aplikace formy let?
6. Jak probíhá aplikace formy let*?
7. Jak lze přepsat let*-blok pomocí formy let?
8. Jak jsme rozšířili lambda výrazy?
9. Co jsou interní definice, jak se liší od top-level definic?

## Lekce 4
1. Co je datová abstrakce?
2. Co jsou páry?
3. Jak vypadá externí reprezentace párů?
4. Co je boxová notace?
5. Jak jsme změnili definici S-výrazu?
6. Co je to kvotování?
7. Jaká je zkrácená notace kvotování?
8. Je možné naimplementovat páry pomocí procedur vyšších řádů? Jak?

## Lekce 5
1. Co je prázdný seznam?
2. Jak jsou definovány seznamy?
3. Jak jsme změnili boxovou notaci?
4. Jak jsme změnili tečkovou notaci?
5. Jaký je ve scheme vztah mezi programy a daty?
6. Co je to garbage collector?
7. Popište algoritmy práce garbage collectoru.
8. Jak jsme naprogramovali seznamy pamatující si vlastní délku?
9. Jaký je rozdíl mezi silně a slabě typovaným jazykem?
10. Jaký je rozdíl mezi staticky a dynamicky typovaným jazykem?
11. Jaký je rozdíl mezi bezpečně a nebezpečně typovaným jazykem?

## Lekce 6
1. Jaký je rozdíl mezi implicitní a explicitní aplikací procedury?
2. Jaké argumenty může mít procedura apply a jaký mají význam?
3. V kterých případech je nutné použít apply?
4. Jak se zapisují formální argumenty procedur s nepovinnými argumenty?
5. Jak se zapisují formální argumenty procedur s libovolnými argumenty?
6. Jaké omezení platí při použití nepovinných argumentu?
7. Jaké znáte speciální for a procedury pro práci s prostředím?
8. Jak se provádí explicitní vyhodnocování elementů?

## Lekce 7
1. Čím se od sebe liší foldr a foldr?
2. Jak probíhá aplikace foldr?
3. K čemu slouží terminátory?
4. Jak lze využít foldr k rozšíření procedury dvou argumentů na libovolný počet argumentů?
5. Co pro nás hrálo klíčovou roli při stanovování časové náročnosti procedur?

## Lekce 8
1. K čemu slouží princip rekurze?
2. K čemu byste použili indukci?
3. Jak lze dokázat správnost rekurzivních definic?
4. Jaký je rozdíl mezi matematickou indukcí a strukturální indukcí?
5. Co to znamená, že jeden seznam je strukturálně jednodušší než jiný seznam?
6. Je scheme čistě funkcionální jazyk?
7. Co jsou to rekurzivní procedury?
8. Co říká princip "divide et impera"?
9. Jaký je rozdíl mezi rekurzivními procedurami a procedurami, se kterými jsme pracovali v předchozích lekcích?
10. Jaký je rozdíl mezi rekurzivní procedurou a rekurzivním výpočetním procesem?
11. Jaké typy rekurzivních výpočetních procesů znáte?
12. Co je charakteristické pro lineárně rekurzivní výpočetní proces?
13. Jak je definována koncová pozice?
14. Co je to koncová aplikace a koncově rekurzivní procedura?
15. Jak je ve většině programovacích jazyků realizována iterace?
16. Má každý literárně iterativní výpočetní proces lineární časovou složitost?
17. Co jsou a k čemu slouží čítače?
18. Co jsou a k čemu slouží střadače?
19. V jakých fázích probíhají jednotlivé rekurzivní výpočetní procesy?
20. Z jakého důvodu je v rekurzivních procedurách přítomná limitní podmínka?
21. Co máme na mysli pod pojmem jednorázová aplikace rekurzivní procedury?
22. Jakým způsobem lze vždy nahradit rekurzivní proceduru iterativní procedurou?
23. U kterých výpočetních procesů roste exponenciálně počet prostředí vzniklých během aplikace?
24. Který z rekurzivních výpočetních procesů je možné snadno "zastavit" a "rozběhnout"?

## Lekce 9
1. Jakými způsoby lze zastavit rekurzi?
2. Proč if jako procedura nezastaví rekurzi?
3. Jakou má roli speciální forma define při psaní rekurzivních procedur?
4. Co je to y-kombinátor?
5. Jak se používá speciální forma letrec?
6. Na jaké výrazy se přepisují letrec-bloky?
7. Co je to nedefinovaná hodnota?
8. Co se myslí hloubkovou rekurzí na seznamech?

# Lekce 10
Ve skriptech nejsou žádné kontrolní otázky.

## Lekce 11
1. Co je kvazikvotování? Jak se liší od kvotování?
2. Jak jsme naprogramovali zjednodušování aritmetických výrazů?
3. Jak jsme naprogramovali symbolickou derivaci?
4. Jak jsme naprogramovali převod mezi jednotlivými notacemi?

## Lekce 12
1. Co jsou to generické procedury?
2. Jaké jsou výhody a nevýhody automatického přetypování?
3. Co jsou manifestované typy?
4. Jak jsme v naší implementaci jazyka scheme reprezentovali jednotlivé elementy jazyka?
5. Kolik jsme uvažovali počátečních prostředí a proč?
6. Jaký je rozdíl mezi jazykem/interpretem a metajazykem/metainterpretem?



## Paradigmata programování 1
Kurs je zaměřen na pokročilé partie z funkcionálního programování
(líné vyhodnocování, makra) a dále na problematiku imperativního
programování (stav, změna stavu, vedlejší efekt) a nepreemptivního
paralelního programování (kontext a jeho přepnutí). V kursu jsou
studenti dále seznámeni s pokročilými konstrukcemi, kterými jsou
například nelokální skoky (aktuální pokračování), je vysvětlen jejich
princip a implementace. V závěru kursu jsou studenti seznámeni s
principy konstrukce plnohodnotného interpretu jazyka Scheme. Po
absolvování kursu by studenti měli získat přehled o programování
různými styly, které se v současnosti označuje jako multiparadigmové
programování. Některé ze stylů programování jsou dále rozvíjeny v
navazujících kursech.

1. Imperativní rysy při programování. Stav, vedlejší efekt, legitimní
vedlejší efekt. Příkaz přiřazení. Metody předávání argumentů
procedurám. Procedury udržující si vnitřní stav. Mutace. Mutovatelné
elementy, mutovatelné páry. Konstrukce mutovatelného páru pomocí
procedur vyšších řádů. Destruktivní práce se seznamy. Cyklické
seznamy. Další mutovatelné datové typy: boxy, vektory, struktury.

2. Makra. Dva pohledy na makra: makra jako rozšíření syntaxe; makra
jako uživatelsky definovatelné speciální formy. Expanze maker. Makra
jako šablony na přepisování kódu. Vytváření forem let, let*, letrec
pomocí maker. Kolize jmen symbolů (variable capture). Generované
symboly. Vytváření maker realizující cykly a podmíněné výrazy.
Hygienická makra.

3. Modifikace vyhodnocovacího procesu. Líné vyhodnocování. Přísliby a
jejich vytvoření pomocí maker a procedur vyšších řádů. Interpret s
líným vyhodnocováním. Vyhodnocovací proces s vyrovnávací pamětí.
Ovlivnění výpočtové efektivity. Proudy (streamy). Rozdíly mezi proudy
a seznamy. Vytváření proudů pomocí příslibů. Nekonečné proudy.
Rekurzivní procedury bez limitní podmínky. Implicitní proudy. Využití
proudů při reprezentaci potenciálně nekonečných množin.

4. Aktuální pokračování a únikové funkce. Průběh výpočtu, stav výpočtu
a jeho pokračování. Práce s časem výpočtu jako s daty. Únikové funkce.
Modelové příklady použití únikových funkcí: nelokální skoky, okamžité
opuštění rekurze, návrat z podprogramu. Modelování syntaxe a sémantiky
programovacích jazyků. Rozšiřování interpretru o další konstrukce.
Cykly obsahující break, continue a redo. Metody ošetřování výjimek.

5. Zásobníkový model vyhodnocování programů a paralelismus. Metody
implementace aktuálního pokračování. Zásobníkový model vyhodnocování
elementů. Optimalizace na koncovou rekurzi. Únikové funkce jako
manipulátory se zásobníky. Nedeterminismus a nepreemptivní
paralelismus. Operátor amb. Simulace přepnutí kontextu procedur pomocí
únikových funkcí. Nepreemptivní paralelní systém.

### Vyučující
Přednášky: Mgr. Jan Konečný, Ph.D.

Cvičení: Mgr. Tomáš Kühr

### Zápočet

### Zkouška


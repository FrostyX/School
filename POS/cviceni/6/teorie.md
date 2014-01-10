## Rozsah platnosti IPv6 adres
- Místní linka
	- ::/128 — adresa samých nul je nespecifikovaná adresa a je používána pouze v software.
	- ::1/128 — adresa místní smyčky je adresa pro localhost. Pokud aplikace vyšle paket na tuto adresu, IPv6 paket je vrácen zpět na téhož hosta (odpovídá 127.0.0.1 v IPv4).
	- fe80::/10 — prefix místní linky udává platnost adresy pouze v místní fyzické lince. Analogické autokonfigurační IPv4 adrese 169.254.0.0/16.
- Místní stránka
	- fc00::/7 — unikátní lokální adresy (ULA) jsou směrovatelné pouze v množině spolupracujících stránek. Tyto byly definovány v RFC 4193 jako náhrada za adresy lokální stránky.
- IPv4
	- ::ffff:0:0/96 — tento prefix se používá pro IPv4 mapované adresy.
	- 2002::/16 — pro adresování tunelu IPv6 v IPv4.
- Multicast
	- ff00::/8 — použití pro multicast adresy [20], jak je uvedeno v RFC 4291 - „Architektura adresování v IP verze 6“.

Zdroj: <http://cs.wikipedia.org/wiki/IPv6#Druhy_IPv6_adres>

## Popis IPv6 packetu
Paket IPv6 se skládá ze dvou hlavních částí: hlavičky a těla. Hlavička se nachází v prvních 40 oktetech (320 bitů) paketu a obsahuje:
- Verzi — 4 bity, verze 6
- Dopravní třídu — 8 bitů na prioritu paketu. Úroveň priority se dělí na rozsahy: kde zdroj podporuje kontrolu přetížení a bez podpory kontroly přetížení.
- Pojmenování toku — 20 bitů pro správu QoS. Původně určeno pro speciální obsluhu aplikací reálného času, nyní se nepoužívá.

- Délka těla - Payload length — 16 bitů pro délku těla paketu. Při vynulování se nastaví „jumbo“ tělo (skok za skokem)
- Následující hlavička - Next header — 8 bitů, určuje další vnořený protokol. Hodnoty se shodují s hodnotami definovanými pro IPv4.
- Zdrojová a cílová adresa - Source, Destination — 128 bitů na každou adresu.
- Hop limit — 8 bitů, číselně definuje počet povolených přechodů síťovými prvky. Každý přechod znamená snížení čísla o 1.

Zdroj: <http://cs.wikipedia.org/wiki/IPv6#IPv6_paket>

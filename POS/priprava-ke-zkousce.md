# Příprava ke zkoušce

## Zkouškové otázky
1. Klasifikace (LAN/MAN/WAN) a služby sítí
2. Referenční model ISO OSI a architektura TCP/IP
3. Strukturovaná kabeláž a opakovač
4. Ethernet (přepínaný)
5. Wi-Fi a Bluetooth
6. Modem, sítě xDSL a GSM, protokol PPP
7. Protokol IP(v4): paket, adresa a síťová maska, lokální síť
8. Protokol IP(v4): směrování
9. Protokol IP(v4): IP fragmentace, protokoly ICMP a (R)ARP
10. Protokol IP: IPv4 multicast, IPv6
11. Transportní protokoly: transportní služby a protokol UDP,port,segment/datagram
12. Protokol TCP: navázání a ukončení TCP spojení
13. Protokol TCP: řízení toku dat, zpoždění odpovědi a posuvné okno,řešení zahlcení sítě
14. Systém DNS: jmenné služby, architektura, domény a zóny
15. Systém DNS: řešitel a jmenný server, překlad jména
16. Systém DNS: protokol (operace Query), záznamy/věty
17. Systém DNS: DNSSec, diagnostika, delegace a registrace domén,Internet Registry
18. Protokol DHCP a směrovací protokoly
19. Elektronická pošta (architektura, zpráva, protokoly)
20. Protokoly HTTP a FTP
21. Bezpečnost sítí (napříč vrstvami, útoky, firewall, NA(P)T, IPSec,VPN, SSL/TLS, proxy)

### 1. Klasifikace (LAN/MAN/WAN) a služby sítí
- Klasifikace podle rozlehlosti sítí
	- LAN (Local Area Network)
		- Omezeny rozsahem (jednotky km, nejčastěji v budově, nebo komplexu budov)
		- Často virtuální
		- od 10Mb/s do 1Gb/s
	- MAN (Metropolitan Area Network)
		- Propojení a prodloužení několika LAN
		- V rámci měst(a)
	- WAN (Wide Area Network)
		- Páteřní sítě, telekomunikační sítě
		- Neomezeně rozsáhlé
	- PAN (Personal Area Network)
		- Omezeny dosahem v okolí zařízení (desítky m)
		- např Bluetooth
- Služby sítí
	- Připojení k síti
	- Vzdálený přístup
	- Přenos technických prostředků (tiskárny, disky)
	- Adresářové služby
	- Elektronická pošta
	- Informační služby (www)

### 2. Referenční model ISO OSI a architektura TCP/IP
- Referenční model ISO OSI
	- Snaha o standardizaci sítí.
	- Normy pro propojení dvou zařízení
	- Nepopisuje implementaci, ale uvádí všeobecné principy sedmivrstvé síťové architektury
		- Fyzická vrstva - Přenos signálu (bitů) bez ohledu na význam bitů
		- Linková vrstva - Zajištění výměny dat mezi sousedními zařízeními v dosahu protokolu. Bity mají význam (data).
		- Síťová vrstva - Přenos mezi vzdálenými, nesousedními zařízeními. Jednotka přenosu - paket.
		- Transportní vrstva - Přenos dat mezi aplikacemi v rámci jednoho počítače. Jednotka přenosu - transportní paket (datagram). Protokoly TCP a UDP.
		- Relační vrstva - Zabezpečená organizace výměny dat mezi aplikacemi
		- Prezentační vrstva - Jednotná reprezentace (a zabezpeční) informací, v jaké se přenáší sítí a jsou dostupné aplikacím
		- Aplikační vrstva - Předepisuje aplikační formát dat
- Architektura TCP/IP
	- Použití v síti Internet (dříve ARPANET)
	- Popsána v RFC (Request For Comments) - doporučení, protokoly, konvence
	- Vyvinuta k propojení vojenských pracovišť.
	- Nedodržuje přesný ISO OSI vrstvový model - sloučení několika vrstev

### 3. Strukturovaná kabeláž a opakovač
- Propojovací kabely
	- Koaxiální kabel
	- Kroucená dvojlinka (max 100m)
	- Optická vlákna (dosah v řádu kilometrů nebo až desítek kilometrů)
- Opakovač
	- Propojení několika segmentů (rozbočovač) - HUB
	- Zopakování zeslabeného vstupního signálu

### 4. Ethernet (přepínaný)
- Sdílené přenosové médium, v daném okamžiku ho využívá jeden uzel
- Uzly samostatné, rovnocenné
- Fast Ethernet -> Gigabitový Ethernet -> 10Gigabitový Ethernet

### 5. Wi-Fi a Bluetooth
- Důvody: Mobilita, snadná použitelnost, dostupnost, ...
- Přenos dat pomocí rádiových vln
- Wi-Fi
	- Topologie
		- ad-hoc - přímá komunikace mezi stanicemi (do deseti stanic)
		- s přístupovým bodem (AP) - stanice komunikují prostřednictvím AP
		- s více přístupovými body (roaming) - AP propojeny pevnou sítí, klient se přepojuje k AP s nejlepším signálem
- Bluetooth
	- Odlišná protokolová architektura
	- Master a Slave uzly

### 6. Modem, sítě xDSL a GSM, protokol PPP
- Modem
	- Pro připojení k datové síti pomocí telefonní sítě (modulace, demodulace dat a zvuku)

- xDSL
	- Skupina technologií (např. ADSL, HDSL, SDSL) souhrnně označující se jako xDSL
	- Dosažení maximální rychlosti na telefonní lince

- GSM
	- Bezdrátová síť pro přenos hlasu (telekomunikace)
	- Území rozdělené do buněk, které obsluhuje jedna BTS (Base Transceiver Station)
	- Telefony komunikují s BTS

- PPP (Point to Point Protocol)
	- Komunikační protokol linkové vrstvy
	- Přímé spojení mezi dvěma síťovými uzly (nejčastěji na telefonní lince)

### 7. Protokol IP(v4): paket, adresa a síťová maska, lokální síť

### 8. Protokol IP(v4): směrování

### 9. Protokol IP(v4): IP fragmentace, protokoly ICMP a (R)ARP

### 10. Protokol IP: IPv4 multicast, IPv6

### 11. Transportní protokoly: transportní služby a protokol UDP,port,segment/datagram

### 12. Protokol TCP: navázání a ukončení TCP spojení

### 13. Protokol TCP: řízení toku dat, zpoždění odpovědi a posuvné okno,řešení zahlcení sítě

### 14. Systém DNS: jmenné služby, architektura, domény a zóny

### 15. Systém DNS: řešitel a jmenný server, překlad jména

### 16. Systém DNS: protokol (operace Query), záznamy/věty

### 17. Systém DNS: DNSSec, diagnostika, delegace a registrace domén,Internet Registry

### 18. Protokol DHCP a směrovací protokoly

### 19. Elektronická pošta (architektura, zpráva, protokoly)

### 20. Protokoly HTTP a FTP

### 21. Bezpečnost sítí (napříč vrstvami, útoky, firewall, NA(P)T, IPSec,VPN, SSL/TLS, proxy)


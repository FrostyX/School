Úkol č. 7
- Navrhněte jednoduchou síťovou službu pro sdílení obsahu.
- Naprogramujte ,,server'', kterému při spustění bude zadán adresář a bude nabízet k dispozici soubory v tomto adresáři.
- Komunikace by měla probíhat následovně:
	- Klient se připojí k serveru a příkazem DIR požádá o vylistování souborů v adresáři. Server seznam odešle a uzavře spojení. Server čeká na další požadavek od klienta.
	- Klient se připojí k serveru a příkazem GET název-souboru požádá o obsah souboru. Server soubor zašle klientovi a ukončí spojení. Server čeká na další požadavek od klienta. Pokud soubor neexistuje, ohlásí klientovi chybu.
- Naprogramujte pro daný server adekvátního klienta.

**Vyřešenou úlohu neposílejte emailem, bude zkontrolována v průběhu následujícího semináře.**

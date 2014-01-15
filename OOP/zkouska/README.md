# Zadání

V programovacím jazyku Common LISP vytvořte třídu pro reprezentaci grafického okna, které bude podporovat práci se schránkou. Okno bude vždy již po svém vytvoření obsahovat dvě tlačítka "Copy" a "Paste". Pokud se po kliknutí na tlačítko "Copy" klikne na jakýkoli grafický objekt v okně, dojde k jeho zkopírování do schránky. Naopak po kliknutí na tlačítko "Paste" a následném kliknutí kdekoli do grafického okna by mělo dojít k nakopírování objektu ve schránce na místo, kam uživatel klikl.

Objekt ve schránce i rozkopírované objekty by měly být nezávislé na původním objektu. Pokud například dojde ke změně barvy původního grafického objektu, neměl by tímto být ovlivněn obsah schránky, ani existující kopie tohoto objektu.

# MarkdownWebServer
## Disclaimer
Skromná implementace http serveru, servírujícího zdrojové soubory ve formátu markdown přeložené do jazyka html. Server umožňuje tvorbu virtualhostů a tím distribuci více adresářů současně. K dispozici je grafický nástroj pro tvorbu konfiguračního souboru.

## Autor
Jakub Kadlčík &lt;[jakub.kadlcik01@upol.cz](mailto:jakub.kadlcik01@upol.cz)&gt;

## Licence
[The BSD 3 lincese](https://github.com/FrostyX/School/blob/master/LICENSE.md "The BSD 3-Clause License")

## Otestování
Aplikace předpokládají výchozí konfigurační soubor `/tmp/MarkdownWebServer/config.xml`, který uvádí ukázkovou konfiguraci na adresářích `/tmp/MarkdownWebServer/data/tracer.wiki` a `/tmp/MarkdownWebServer/data/neo4j-browser.wiki`, proto je nutné následující:

	mkdir /tmp/MarkdownWebServer
	cp -r config.xml data /tmp/MarkdownWebServer

## Použití
	java -jar MarkdownWebServer.jar [config]

## Výstup
Očekávaný výstup serveru by měl vypadat takto

	$[FrostyX  MarkdownWebServer]-> java -jar server/dist/MarkdownWebServer.jar
	Starting MarkdownWebServer
	Starting VHost: tracer
	Starting VHost: neo4j-browse

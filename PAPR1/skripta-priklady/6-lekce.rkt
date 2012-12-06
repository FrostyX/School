#!/usr/bin/racket
#lang racket

;
; Cvičení
;

; 1. Naprogramujte proceduru na zpracování seznamu podle vzoru. Vzorem se myslí seznam o stejném počtu prvků, který obsahuje:
;	- symbol del, pak je prvek na odpovídající pozici smazán
;	- symbol ins, pak je prvek na odpovídající pozici ponechán
;	- procedury, pak je prvek seznamu na odpovídající pozici nahrazen aplikací této procedury na tento prvek.
; Viz příklad volání (format `(1 2 3 4 5) (list `del even? even? `ins (lambda (x) (+ x 1))))


; 2. Naprogramujte proceduru vracející seznam mocnin čísla k (od 0-té až po (n-1)-té).


; 3. Naprogramujte proceduru konverze binary->decimal, které binární číslo, reprezentované číselným seznamem obsahujícím 0 a 1, převede na číslo (Scheme-ovské).


; 4. Implementujte pro reprezentaci množin uvedenou v sekci 6.5:
;	- operaci symetrického rozdílu
;	- predikát rovnosti množin
;	- predikát zjišťující, zda je zadaný seznam množinou

;
; Úkoly k textu
;

; 1. Naprogramujte relace reprezentované páry (univerzum) . (vlastnost). Kde (universum) je seznam, a (vlastnost) je predikát představující funkci příslušnosti.


; 2. Implementujte reprezentace zobrazení jako speciálních relací.


; Naprogramujte predikáty anisymetric?, ireflexive? a complete? pro zjištění antisymetrie, ireflexivity a úplnosti relace.
; Relace R je
;	- antisymetrická, pokud pro každé x a y platí, že pokud (x,y)eR a (y,x)eR, pak x = y.
;	- ireflexivní, pokud pro každé x platí (x,x)eR
;	- úplná, pokud pro každé x a y platí, (x,y)eR nebo (y,x)eR

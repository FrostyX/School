#!/usr/bin/racket
#lang racket

;
; Cvičení
;

; 1. V sekci 7.3 jsme rozšířili proceduru min2 na proceduru libovolného počtu argumentů. Stejným způsobem rozšiřte proceduru na výběr čísla s extrémní absolutní hodnotou abs-min.


; 2. Napište predikát, který pro posloupnost zjistí, zda je neklesající. Posloupnost bude reprezentovaná seznamem, jehož prvky jsou čísla. Viz příklady aplikace:
;	(nondecreasing? `()) => #t
;	(nondecreasing? `(1 2 3 4)) => #t
;	(nondecreasing? `(1 2 4 3)) => #f
;	(nondecreasing? `(1 4 2 3)) => #f
;	(nondecreasing? `(4 1 2 3)) => #f

3. Napište procedury after a before, jejichž argumenty budou element <elem> a seznam <l>. Procedura after bude vracet seznam prvků za posledním výskytem prvku <elem> (včetně) v seznamu <l>. Procedura before zase seznam prvků před prvním výskytem prvku <elem> (včetně) v seznamu <l>. Viz příklady použití:
;	(after 10 '(1 2 3 4 3 5 6))   => ()
;	(after 3 '(1 2 3 4 3 5 6))    => (3 5 6)
;	(after 6 '(1 2 3 4 3 5 6))    => (6)
;	(before 10 '(1 2 3 4 3 5 6))  => (1 2 3 4 5 6)
;	(before 1 '(1 2 3 4 3 5 6))   => (1)
;	(before 3 '(1 2 3 4 3 5 6))   => (1 2 3)

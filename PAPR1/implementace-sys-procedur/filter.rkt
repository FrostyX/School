#!/usr/bin/racket
#lang racket

; Vybere podmnožinu prvků, které splňují danou vlastnost
(define filter
	(lambda (proc seznam)
		(apply append (map
			(lambda (x)
				(if (proc x)
					(list x)
					`()))
			seznam))))

; Vybere podmnožinu prvků, které nesplňují danou vlastnost
(define remove
	(lambda (proc seznam)
		(filter (lambda (x) (not (proc x))) seznam)))

; Zjistí, zda je prvek obsažen v seznamu
(define member?
	(lambda (prvek seznam)
		(if (null? (filter (lambda (x) (equal? prvek x)) seznam)) #f #t)))


; Příklady použití procedur
(filter even? `(1 2 3))
(remove even? `(1 2 3))
(member? `a `(1 2 3))

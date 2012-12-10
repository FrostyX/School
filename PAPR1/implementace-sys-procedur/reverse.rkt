#!/usr/bin/racket
#lang racket

; Zřetězení dvou argumentů, ale ten první v opačném pořadí
(define rev-append
	(lambda (a b)
		(if (null? a)
			b
			(rev-append
				(cdr a)
				(cons (car a) b)))))

(define reverse
	(lambda (a)
		(rev-append a `())))


(rev-append `(a b c) `(1 2 3))
(reverse `(a b c 1 2 3))

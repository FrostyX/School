#!/usr/bin/racket
#lang racket

(define append
	(lambda (a b)
		(if (null? a)
			b
			(cons (car a) (append (cdr a) b)))))

(append `(1 2 3) `(a b))

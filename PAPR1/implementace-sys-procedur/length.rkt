#!/usr/bin/racket
#lang racket

(define length
	(lambda (l)
		(if (null? l)
			0
			(+ 1 (length (cdr l))))))

(length `(1 2 3 a b))

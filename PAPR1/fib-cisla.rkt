#!/usr/bin/racket
#lang racket

(define fib-cislo
	(lambda (n)
		(cond
			((= n 0) 0)
			((= n 1) 1)
			(else
				(+ (fib-cislo(- n 1))	(fib-cislo(- n 2)))))))

(fib-cislo 7)

#!/usr/bin/racket
#lang racket

(define map
	(lambda (proc seznam)
		(if (null? seznam)
		`()
		(cons (proc (car seznam))
			(map proc (cdr seznam))))))

(map (lambda (x) (* 2 x)) `(1 2 3 4))

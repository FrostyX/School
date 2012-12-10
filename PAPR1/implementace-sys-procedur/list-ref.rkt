#!/usr/bin/racket
#lang racket

(define list-ref
	(lambda (seznam i)
		(if (= 0 i)
			(car seznam)
			(list-ref (cdr seznam) (- i 1)))))

(list-ref `(a b c d e) 3)

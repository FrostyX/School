#!/usr/bin/racket
#lang racket

; 1
(/
	(+ (log 2.71828) 1)
	(+ (sqrt 4) (/ 10 (+ -1 6))))

; 2
(define x +)
(define y 1)
(define z 2)
(define w -)

; 3
(define pyramid
	(lambda (d v)
		(define a (* (/ (sqrt 2) 2) d))
		(* a (+ a (sqrt (+ (* 4 v v) (* a a)))))))

; 4
(define my-negative?
	(lambda (c)
		(< c 0)))

; 5
(define my-proc
	(lambda (x)
		(cond
			((> x 0)(+ x 2))
			((< x 0)(- x 2))
			(else x))))


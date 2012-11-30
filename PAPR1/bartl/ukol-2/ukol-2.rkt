#!/usr/bin/racket
#lang racket

; 1
(define my-cons
	(lambda (a b)
		(lambda (predikat)
			(if predikat a b))))

(define my-car
	(lambda (par)
		(par #t)))

(define my-cdr
	(lambda (par)
		(par #f)))

; 2
(define switch
	(lambda (par)
		(my-cons (my-cdr par) (my-car par))))

; 3
(define make-c cons)
(define real car)
(define imag cdr)

(define conj
	(lambda (par)
		(cons (car par) (- (cdr par)))))

(define c+
	(lambda (a b)
		(make-c (+ (real a) (real b)) (+ (imag a) (imag b)))))

(define c*
	(lambda (a b)
		(make-c
			(- (* (real a) (real b)) (* (imag a) (imag b)))
			(+ (* (real a) (imag b)) (* (imag a) (real b))))))

; 4
(define singletons
	(lambda (seznam)
		(map list seznam)))

; 5
(define roots-of-unity
	(lambda (n)
		(build-list n
			(lambda (k)
				(make-rectangular
						(upresni (cos (/ (* 2 pi k) n)))
						(upresni (* 1 (sin (/ (* 2 pi k) n)))))))))

(define upresni
	(lambda (x)
		(rationalize (inexact->exact x) 1/10)))

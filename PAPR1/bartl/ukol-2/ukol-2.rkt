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

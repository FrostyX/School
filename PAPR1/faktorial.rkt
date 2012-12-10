#!/usr/bin/racket
#lang racket

(define faktorial
	(lambda (n)
		(if (<= n 1)
			1
			(* n (faktorial (- n 1))))))

(faktorial 5)

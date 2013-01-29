#!/usr/bin/racket
#lang racket

(define approx=
	(lambda (a b . x)
		(let ((x (if (null? x)
				1/1000 ; Předdefinovaná odchylka
				(car x)))) ; car, protože x je seznam obsahující nepoviné argumenty
		(<= (abs (- a b)) x))))

(approx= 5 5.00001 1/10)
(approx= 5 5.10)

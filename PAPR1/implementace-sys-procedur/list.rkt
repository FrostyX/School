#!/usr/bin/racket
#lang racket

; K čemu je tu dobrá rekurze?

(define list?
	(lambda (x)
		(if (null? x)
		#t
		(if
			(and (pair? x)
			(pair? (cdr x))) #t #f))))

(define list-z-prednasky?
	(lambda (x)
		(if (null? x)
			#t
			(and (pair? x)
				(list-z-prednasky? (cdr x))))))

(define list-elegantni?
	(lambda (x)
		(or (null? x)
			(and (pair? x)
				(list-elegantni? (cdr x))))))

(define l `(1 2 3))
(list? l)
(list-z-prednasky? l)
(list-elegantni? l)

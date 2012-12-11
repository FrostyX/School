#!/usr/bin/racket
#lang racket

(define forall?
	(lambda (proc seznam)
		(or (null? seznam)
			(and (proc (car seznam))
				(forall? proc (cdr seznam))))))

(define exists?
	(lambda (proc seznam)
		(and (not (null? seznam))
			(or (proc (car seznam))
				(exists? proc (cdr seznam))))))

(forall? even? `(2 4 8))
(exists? even? `(3 5 2))


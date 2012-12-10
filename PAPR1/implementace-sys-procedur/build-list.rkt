#!/usr/bin/racket
#lang racket

(define build-list1
	(lambda (n proc)
		(let build-next ((i 0))
			(if (= i n)
				`()
				(cons (proc i) (build-next (+ i 1)))))))

(build-list1 5 (lambda (x) (* 2 x)))

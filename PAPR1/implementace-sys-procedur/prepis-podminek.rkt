#!/usr/bin/racket
#lang racket

(define x 4)
(define y 3)

; Přepis if na or + and
(if (= x 3) 10 20)
(or (and (= x 3) 10) 20)

; Přepis cond na if
(cond ((= x 3) 30)
      ((= x 4) 40) 
      (50))

(if (= x 3)
    30
    (if (= x 4) 40 50))

; Přepis cond na or + and
(cond ((= x 3) 60)
      ((= x 4) 70) 
      (80))

(or (and (= x 3) 60)
    (or (and (= x 4) 70) 80))

; Přepis and na if
(and (= x 3) (= y 3))
(if (= x 3) (if (= y 3) #t #f) #f)

; Přepis or na if
(or (= x 3) (= y 3))
(if (= x 3) #t (if (= y 3) #t #f))

; Přepis or a and na if
(or (and (= x 3) 10) (or (and (= x 4) 20) 30))

(if (= x 3)
    10
    (if (= x 4) 20 30))

; Přepis if na cond
(if (= x 3) 
    90
    (if (= x 4) 100 110))

(cond ((= x 3) 90)
      ((= x 4) 100)
      (110))

; Přepis and na or
(and (= x 3) (= y 3))
(not (or (not (= x 3)) (not (= y 3))))

; Přepis or na and
(or (= x 3) (= y 3))
(not (and (not (= x 3)) (not (= y 3))))
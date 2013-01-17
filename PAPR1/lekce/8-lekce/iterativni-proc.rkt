#!/usr/bin/racket
#lang racket

(define faktorial-iter
  (lambda (n accum)
    (if (<= n 1)
        accum
        (faktorial-iter (- n 1) (* accum n)))))

; Není tato procedura už není zapotřebí, 
;    ale je běžné pro získání faktorialu použít pouze jeden vstupní parametr
(define faktorial
  (lambda (n)
    (faktorial-iter n 1)))

(faktorial 5)
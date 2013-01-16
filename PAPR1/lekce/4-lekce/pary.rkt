#!/usr/bin/racket
#lang racket

; 5
(define map-pair
  (lambda (proc par)
    (cons (proc (car par)) (proc (cdr par)))))

; 6
; Implementaci lze upravit na čistější funkcionální řešení
; Podmínku nahradit voláním projekce a v selektorech místo pravdivostní hodnoty
;    předávat předpis projekce
(define my-cons
  (lambda (a b)
    (lambda (k)
      (if k a b))))

(define my-car
  (lambda (par)
    (par #t)))
    
(define my-cdr
  (lambda (par)
    (par #f)))
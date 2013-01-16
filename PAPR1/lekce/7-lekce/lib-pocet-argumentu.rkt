#!/usr/bin/racket
#lang racket

; Zadání: Procedura pro libovolný počet argumentů

; Nejdříve vytvoříme proceduru, která operaci provádí pouze s dvěma argumenty
(define plus-2
  (lambda (a b)
      (+ a b)))

; Následovně proceduru dvou argumentů aplikujeme na všechny
(define plus
  (lambda args
    (foldr plus-2 0 args)))

(plus 1 2 7)
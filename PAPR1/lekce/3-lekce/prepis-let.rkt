#!/usr/bin/racket
#lang racket

; 2.1 Zadání
(let* ((x 10)
       (y (* x x))
       (z (- y x)))
  (/ z y))

; 2.1 Pouze let
(let ((x 10))
  (let ((y (* x x)))
    (let ((z (- y x)))
      (/ z y))))

; 2.1 Pouze lambda
((lambda (x)
   ((lambda (y)
      ((lambda (z)
         (/ z y))
       (- y x)))
    (* x x)))
   10)


; 2.2 Zadání
(let ((a 3)
      (b 4)
      (c (let* ((a 10)
                (b 20))
           (+ a b 10))))
  (+ a b c))

; 2.2 Pouze let
(let ((a 3)
      (b 4)
      (c (let ((a 10))
           (let ((b 20))
             (+ a b 10)))))
	(+ a b c))

; 2.2 Pouze lambda
((lambda (a)
   ((lambda (b)
      ((lambda (c)
         (+ a b c))
       ((lambda (a)
          ((lambda (b)
             (+ a b 10))
           20))
        10)))
    4))
 3)

; 2.3 Zadání
(let* ((x (let ((x 10))
            (define x 2)
            (+ x x)))
       (y (+ x x)))
  (+ x y))

; 2.3 Pouze let
(let ((x (let ((x 10))
           (define x 2)
           (+ x x))))
  (let ((y (+ x x)))
    (+ x y)))

; 2.3 Pouze lambda
((lambda (x)
   ((lambda (y)
      (+ x y))
    (+ x x)))
 ((lambda (x)
    (define x 2)
    (+ x x))
  10))
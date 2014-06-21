;
; Líné vyhodnocování
;

(define-macro delay
  (lambda exprs
    `(let ((result (lambda ()
                     (begin ,@exprs)))
           (evaluated #f))
       (lambda ()
         (begin
           (if (not evaluated)
               (begin
                 (set! result (result))
                 (set! evaluated #t)))
           result)))))

(define force
  (lambda (promise)
    (promise)))


;(define p (delay (+ 1 2)))
;p
;(thaw p)


;
; Streamy (proudy)
; Neformálně - líně vyhodnocované seznamy
; Prázdný seznam je proud, každý pár (e . p) kde p je příslib proudu, je proud
;

(define-macro stream-cons
  (lambda (a b)
    `(cons ,a (delay ,b))))

(define stream-car car)

(define stream-cdr
  (lambda (stream)
    (force (cdr stream))))


;
; Nekonečné proudy
; Konstruují se pomocí rekurzivní procedury bez limitní podmínky
;

(define naturals-stream
  (lambda ()
    (let iter ((i 1))
      (stream-cons i (iter (+ i 1))))))

;(force (cdr (force (cdr (naturals-stream)))))


(define integers-stream
  (lambda ()
    (stream-cons
     0
     (let iter ((i 0))
       (if (>= i 0)
           (set! i (+ i 1)))
       (stream-cons i (iter (* i -1)))))))
  
;(integers-stream)
;(force (cdr (integers-stream)))
;(force (cdr (force (cdr (integers-stream)))))
;(force (cdr (force (cdr (force (cdr (integers-stream)))))))
;(force (cdr (force (cdr (force (cdr (force (cdr (integers-stream)))))))))
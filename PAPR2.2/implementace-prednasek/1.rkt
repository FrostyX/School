;
; Foreach
;

(define for-each
  (lambda (f l)
    (if (not (null? l))
        (begin
          (f (car l))
          (for-each f (cdr l))))))

;(for-each print '(1 2 3))


;
; Procedury generující procedury s vnitřním stavem
;

(define make-inc
  (lambda ()
    (let ((value 0))
      (lambda (x)
        (set! value (+ value x))
        value))))

;(define x (make-inc))
;(define y (make-inc))

;(y 1)
;(y 1)
;(y 2)
;(x 1)
;(x 3)

;
; Reenantní procedury = procedury, které mohou být přerušeny během jejich vykonávání
;     a vyvolány znovu předtím, než to předchozí vykonávání skončí
;

;
; OOP
;

(define make-stack
  (lambda ()
    (let ((stack '())
          (depth 0))
      
      (define push
        (lambda (x)
          (format "push ~a" x)))
      
      (define pop
        (lambda ()
          (print "pop")))
      
      (lambda (signal . args)
        (cond ((equal? signal 'push) (push (car args)))
              ((equal? signal 'pop) (pop))
              (else (error "Unknown signal")))))))

;(define stack (make-stack))
;(stack 'push 5)
;(stack 'push 6)
;(stack 'pop)
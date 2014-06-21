;
; Makra
; Ladění - Za kvazikvótování přidat kvótování: `'(...)
;

; Místo nedefinované else větve vrací #f
(define-macro new-if
  (lambda (test expr . alt)
    `(if ,test
         ,expr
         (begin #f ,@alt))))

;(new-if #f 2)

; Anaforický if - Na proměnnou $result naváže výsledek vyhodnocení podmínky
(define-macro if*
  (lambda (test expr . alt)
    `(let (($result ,test))
       (if $result
           ,expr
           ,@alt))))

;(if* #f 1)
;(if* #f 1 $result)

(define-macro and2
  (lambda args
    (if (null? args)
        #t
        (if (null? (cdr args))
            (car args)
            `(if ,(car args)
                 (and2 ,@(cdr args))
                 #f)))))

;(and2 1 2 3)

(define-macro or2
  (lambda args
    (if (null? args)
        #f
        `(if ,(car args)
             ,(car args)
             (or2 ,@(cdr args))))))

;(or #f 2 3)


;
; Procedury bez lambda
;

(define (f x y . args)
  (list x y args))

;(f 1 2 3 4)

(define-macro def
  (lambda (name body)
    `(define ,name (lambda ,@body))))
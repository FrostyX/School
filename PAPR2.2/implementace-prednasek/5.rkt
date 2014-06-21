;
; Cykly
;

(define-macro while
  (lambda (condition . body)
    `(let iter ()
      (if ,condition 
          (begin 
            ,@body 
            (iter))))))


(let ((i 0))
  (while (< i 5)
         (display i)
         (newline)))

;
; Hygienická makra
;     - Makra jsou definována pomocí (několika) přepisovacích pravidel
;     - Výhody:
;          - Odpadají kvazikvótované výrazy
;          - Odpadá problém se symbol-capture
;          
;

(define-syntax and
  (syntax-rules ()
    ((and) #t)
    ((and arg1) arg1)
    ((and arg1 arg2 ...)
     (if arg1 (and arg2 ...) #f))))

;(and 1 2 3)
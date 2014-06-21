;
; Mutovatelné páry
;

(define cons2
  (lambda (x y)
    
    (define set-x! (lambda (value) (set! x value)))
    (define set-y! (lambda (value) (set! y value)))
    
    (lambda (signal)
      (cond ((equal? signal 'car) x)
            ((equal? signal 'cdr) y)
            ((equal? signal 'set-car!) set-x!)
            ((equal? signal 'set-cdr!) set-y!)
            (else "Unknown signal!")))))

(define car2
  (lambda (pair)
    (pair 'car)))
              
(define cdr2
  (lambda (pair)
    (pair 'cdr)))

(define set-car2!
  (lambda (pair value)
    ((pair 'set-car!) value))) ; Proč se value předává takto?

(define set-cdr2!
  (lambda (pair value)
    ((pair 'set-cdr!) value)))


;
; Destruktivní seznamy
;

(define list-insert!
  (lambda (list index value)
    (if (= index 0) ; Vkládání na začátek
        (begin
          (set-cdr! list (cons (car list) (cdr list))) ; Nešlo by (set-cdr! list list) ?
          (set-car! list value))
        (let iter ((l list)
                   (i index))
          (if (= i 1)
              (set-cdr! l (cons value (cdr l)))
              (iter (cdr l) (- i 1)))))))

;
; Cyklické seznamy
;

(define cycle!
  (lambda (l)
    (let iter ((aux l))
      (if (null? (cdr aux))
          (set-cdr! aux l)
          (iter (cdr aux))))))

(define cyclic?
  (lambda (l)
    (let test ((rest (if (null? l) '() (cdr l))))
      (cond (((null? rest) #f)
             ((eq? rest l) #t)
             (else test (cdr rest)))))))

(define uncycle!
  (lambda (l)
    (let iter ((aux l))
      (if (eq? (cdr aux) l)
          (set-cdr! aux '())
          (iter (cdr aux))))))
              
(define depth-cyclic?
  (lambda (l)
    (let ((found '()))
      (let test ((l l))
        (if (pair? l)
            (if (memq l found)
                #t
                (begin
                  (set! found (cons l found))
                  (or (test (car l))
                      (test (cdr l)))))
            #f)))))


;
; Obousměrné seznamy
; Struktura ve tvaru (elem . (ptr_prev . ptr_next))
;

(define cons-dlist
  (lambda (elem dlist)
    (let ((new-cell (cons elem 
                          (cons '() dlist))))
      (if (not (null? dlist))
          (set-car! (cdr dlist) new-cell)
      new-cell))))
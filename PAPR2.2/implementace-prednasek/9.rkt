(define list-product
  (lambda (l)
    (call/cc
     (lambda (exit)
       (let iter ((l l))
         (cond ((null? l) 1)
               ((= (car l) 0) (exit 0))
               (else (* (car l) (iter (cdr l))))))))))

(list-product '(1 2 3 4 5))
(list-product '(1 2 0 3 4 5))
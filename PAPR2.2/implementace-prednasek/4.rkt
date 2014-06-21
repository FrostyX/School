(define-macro let
  (lambda (assign . body)
    `'((lambda ,(map car assign)
        (begin ,@body))
      ,@(map cadr assign))))

(let ((a 1)
      (b 2)
      (c 3))
  (+ a b c))
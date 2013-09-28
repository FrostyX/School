(defvar *numbers* '(5 8 4 9 3 2 1 7 6 9 4))


; 1. Napsat funkci select-sort
(defun select-sort (l)
  (if (eq l '()) '()
    (let ((min (apply #'min l)))
      (cons min (select-sort (remove min l :count 1))))))

; Příklad č.1 je pouze speciálním případem příkladu č.3,
; je tedy možné ho implementovat pomocí něj.
(defun select-sort3 (l)
  (select-sort-f l #'<))

; Použití:
;(select-sort *numbers*)
;(select-sort3 *numbers*)



; 2. Napsat funkci select-sort destruktivně (modifikovat stávající seznam)
(defun select-sort-destructive (l)
  (let ((n (length l)))
    (labels ((iter (i)
               (cond ((= i (- n 1)) l)
                     ((eq l '()) '())
                     (t (let* ((min (apply #'min (nthcdr i l)))       ; Nejmenší prvek nesetříděné části
                               (min-pos (position min l :start i)))   ; Pozice nejmenšího prvku v nesetříděné části
                          (print l)
                          (print i)
                          (print min-pos)
                          (switch i min-pos)
                          (print l)
                          (print "---")
                          (iter (+ i 1))))))

             ; Prohodí dva prvky seznamu na daných indexech
             (switch (i j)
               (let ((tmp (nth i l)))
                 (setf (nth i l) (nth j l))
                 (setf (nth j l) tmp)))) 

      (iter 0)))) 

; Použití: 
;(select-sort-destructive *numbers*)



; 3. Napsat funkci select-sort která bude prvky porovnávat podle předané funkce
(defun select-sort-f (l f)
  (labels 
      ; Nalezení 'nejmenšího' prvku dle daného kritéria (funkce)
      ((find-next-sorted (l next) 
         (if (eq l '()) next
           (find-next-sorted (cdr l) (if (funcall f (car l) next) (car l) next)))))

    ; Setřídění
    (if (eq l '()) '()
      (let ((next (find-next-sorted (cdr l) (car l))))
        (cons next (select-sort-f (remove next l :count 1) f)))))) ; Proč f místo #'f ?

; Použití: 
;(select-sort-f *numbers* #'>)
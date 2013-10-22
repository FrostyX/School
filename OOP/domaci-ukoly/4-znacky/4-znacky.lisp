(load (current-pathname "micro-graphics.lisp"))

;;
;; Bez dědičnosti nemá smysl definovat třídy TRIANGLE, WARNING-SIGN, STOP-SIGN, ..
;;

(defun triangle (A B C width color)
  (let ((tr (make-instance 'polygon)))
    (set-items tr (list A B C))
    (set-thickness tr width)
    (set-color tr color)
    tr))

(defun warning-sign (A B C)
  (let ((black-border (triangle A B C 1 :black))
        (white-border (triangle (shift A 0 -1) (shift B 1 0) (shift C -1 0) 2 :white))
        (red-border (triangle (shift A 0 15) (shift B 15 -8) (shift C -15 -8) 10 :red)))
    (list black-border white-border red-border)))

(defun cross-sign (A B C)
  (let* ((warning-sign (warning-sign A B C))
         (sign (make-instance 'polygon))
         (l1 (make-instance 'polygon))
         (l2 (make-instance 'polygon)))
    
    (set-color l1 :black)
    (set-thickness l1 15)
    (set-items l1 (list (make-point 100 90) (make-point 140 130)))

    (set-color l2 :black)
    (set-thickness l2 15)
    (set-items l2 (list (make-point 100 130) (make-point 140 90)))
  
    (append warning-sign (list l1 l2))))

(defun arrows-sign (A B C)
  (let ((warning-sign (warning-sign A B C))
        (l1 (make-instance 'polygon))
        (l2 (make-instance 'polygon))
        (arrow1 (make-instance 'polygon))
        (arrow2 (make-instance 'polygon)))

    ; Levá strana
    (set-color l1 :black)
    (set-thickness l1 13)
    (set-items l1 (list (make-point 107 76) (make-point 107 118)))

    
    (set-color arrow1 :black)
    (set-filledp arrow1 t)
    (set-items arrow1 (list (make-point 120 118)
                            (make-point 95 118)
                            (make-point 107 138)))
    
    ; Pravá strana
    (set-color l2 :black)
    (set-thickness l2 13)
    (set-items l2 (list (make-point 132 90) (make-point 132 138)))
    
    (set-color arrow2 :black)
    (set-filledp arrow2 t)
    (set-items arrow2 (list (make-point 120 96)
                            (make-point 144 96)
                            (make-point 132 76)))

    (append warning-sign (list l1 l2 arrow1 arrow2))))

(defun bottleneck-sign (A B C)
  (let ((warning-sign (warning-sign A B C))
        (l1 (make-instance 'polygon))
        (l2 (make-instance 'polygon)))
    (set-color l1 :black)
    (set-thickness l1 15)
    (set-closedp l1 nil)
    (set-items l1 (list (make-point 95 139)
                        (make-point 95 115)
                        (make-point 107 105)
                        (make-point 107 80)))
    (set-color l2 :black)
    (set-thickness l2 15)
    (set-closedp l2 nil)
    (set-items l2 (list (make-point 145 139)
                        (make-point 145 115)
                        (make-point 133 105)
                        (make-point 133 80)))

    (append warning-sign (list l1 l2))))

(defun lights-sign (A B C)
  (let ((warning-sign (warning-sign A B C))
        (red (make-instance 'circle))
        (yellow (make-instance 'circle))
        (green (make-instance 'circle))
        (red-border (make-instance 'circle))
        (yellow-border (make-instance 'circle))
        (green-border (make-instance 'circle))
        (radius 10))
    
    (set-x (center red) 120)
    (set-x (center red-border) 120)
    (set-x (center yellow) 120)
    (set-x (center yellow-border) 120)
    (set-x (center green) 120)
    (set-x (center green-border) 120)

    (set-y (center yellow) 102)
    (set-y (center yellow-border) 102)
    (set-y (center green) 127)
    (set-y (center green-border) 127)
    (set-y (center red) 77)
    (set-y (center red-border) 77)

    (set-radius red radius)
    (set-radius red-border radius)
    (set-radius yellow radius)
    (set-radius yellow-border radius)
    (set-radius green radius)
    (set-radius green-border radius)

    (set-color red :red)
    (set-color yellow :yellow)
    (set-color green :green)

    (set-filledp red t)
    (set-filledp yellow t)
    (set-filledp green t)

    (append warning-sign (list red-border yellow-border green-border red yellow green))))


(defun no-entry-sign ()
  (let ((border (make-instance 'circle))
        (bg (make-instance 'circle))
        (sign (make-instance 'polygon))
        (radius 80))

     (set-x (center border) 120)
     (set-y (center border) 90)
     (set-radius border radius)

     (set-x (center bg) 120)
     (set-y (center bg) 90)
     (set-radius bg (- radius 4))
     (set-filledp bg t)
     (set-color bg :red)

     (set-color sign :white)
     (set-thickness sign 20)
     (set-items sign (list (make-point 65 90)
                            (make-point 175 90)))
    (list sign border bg)))



; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let* ((A (make-point 120 17))
       (B (make-point 35 155))
       (C (make-point 203 155))
       (pic (make-instance 'picture)))
 
  ; Odkomentujte jednen z následujících řádků:
  (set-items pic (append (cross-sign A B C)))
  ;(set-items pic (append (arrows-sign A B C)))
  ;(set-items pic (append (bottleneck-sign A B C)))
  ;(set-items pic (append (lights-sign A B C)))
  ;(set-items pic (append (no-entry-sign)))  

  (set-shape *win* pic)
  (redraw *win*))

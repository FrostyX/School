(load (current-pathname "micro-graphics.lisp"))

;; CELL
(defclass cell (picture) 
  ((value :initform 0))) ; Value: -1 0 1

; Vytvoří objekt reprezentující políčko piškvorek
(defun make-cell (value)
  (let ((cell (make-instance 'cell)))
    (set-value cell value)
    cell))

; @override
(defmethod draw ((cell cell))
  (let ((value-image (cond ((= (value cell) -1) (cross cell))
                           ((= (value cell) +1) (circle cell))
                           (t (make-instance 'empty-shape)))))
    (set-items cell (list value-image (border cell)))
    (call-next-method)))

; Vrátí objekt reprezentující rámeček okolo políčka
(defmethod border ((cell cell))
  (let ((border (make-instance 'polygon))
        (A (make-point 0 0))
        (B (make-point 0 50))
        (C (make-point 50 50))
        (D (make-point 50 0)))

    (set-items border (list A B C D))
    border))

; Vrátí objekt reprezentující značku prvního hráče - křížek
(defmethod cross ((cell cell))
  (let* ((cross (make-instance 'picture))
         (l1 (make-instance 'polygon))
         (l2 (make-instance 'polygon)))
    
    (set-thickness l1 5)
    (set-items l1 (list (make-point 10 10) (make-point 40 40)))
    (set-thickness l2 5)
    (set-items l2 (list (make-point 10 40) (make-point 40 10)))

    (set-color cross :red)
    (set-propagate-color-p cross t)
    (set-items cross (list l1 l2))
    cross))

; Vrátí objekt reprezentující značku druhého hráče - kolečko
(defmethod circle ((cell cell))
  (let ((c (make-instance 'circle))
        (s (make-point 25 25)))

    (set-center c s)
    (set-radius c 15)
    (set-thickness c 5)
    (set-color c :green)
    c))

; Gettery & Settery
(defmethod value ((cell cell))
  (slot-value cell 'value))

(defmethod set-value ((cell cell) value) 
  (unless (and (>= value -1) (<= value 1))
    (error "Hodnota policka musi byt -1, 0, nebo 1"))
  (setf (slot-value cell 'value) value)
  cell)



;; POUŽITÍ

; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let* ((cell (make-cell -2)))
  (set-shape *win* cell)
  (redraw *win*))

(load (current-pathname "micro-graphics.lisp"))

;; FIELD
(defclass field (picture) 
  ((value :initform 0))) ; Value: -1 0 1

; Vytvoří objekt reprezentující políčko piškvorek
(defun make-field (value)
  (let ((field (make-instance 'field)))
    (set-value field value)
    field))

; @override
(defmethod draw ((field field))
  (let ((value-image (cond ((= (value field) -1) (cross field))
                           ((= (value field) +1) (circle field))
                           (t (make-instance 'empty-shape)))))
    (set-items field (list value-image (border field)))
    (call-next-method)))

; Vrátí objekt reprezentující rámeček okolo políčka
(defmethod border ((field field))
  (let ((border (make-instance 'polygon))
        (A (make-point 0 0))
        (B (make-point 0 50))
        (C (make-point 50 50))
        (D (make-point 50 0)))

    (set-items border (list A B C D))
    border))

; Vrátí objekt reprezentující značku prvního hráče - křížek
(defmethod cross ((field field))
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
(defmethod circle ((field field))
  (let ((c (make-instance 'circle))
        (s (make-point 25 25)))

    (set-center c s)
    (set-radius c 15)
    (set-thickness c 5)
    (set-color c :green)
    c))

; Gettery & Settery
(defmethod value ((field field))
  (slot-value field 'value))

(defmethod set-value ((field field) value) 
  (unless (and (>= value -1) (<= value 1))
    (error "Hodnota policka musi byt -1, 0, nebo 1"))
  (setf (slot-value field 'value) value)
  field)



;; POUŽITÍ

; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let* ((field (make-field -2)))
  (set-shape *win* field)
  (redraw *win*))

(load (current-pathname "micro-graphics.lisp"))

; Konfigurace
(defvar *field-width* 50)
(defvar *fields-in-row* 3)
(defvar *gap-x* 45)
(defvar *gap-y* 15)

;; FIELD
(defclass field (picture) 
  ; Hodnoty:
  ;    -1 = Křížek
  ;     0 = Neutrální
  ;    +1 = Kolečko
  ((value :initform 0)))

; Vytvoří objekt reprezentující políčko piškvorek
(defun make-field (value)
  (let ((field (make-instance 'field)))
    (set-value field value)
    field))

; Gettery & Settery
(defmethod value ((field field))
  (slot-value field 'value))

(defmethod set-value ((field field) value) 
  (labels 
      ; Vrátí objekt reprezentující rámeček okolo políčka
      ((border ()
         (let ((border (make-instance 'polygon))
               (A (make-point 0 0))
               (B (make-point 0 *field-width*))
               (C (make-point *field-width* *field-width*))
               (D (make-point *field-width* 0)))

           (set-items border (list A B C D))
           border))

       ; Vrátí objekt reprezentující značku prvního hráče - křížek
       (cross ()
         (let* ((cross (make-instance 'picture))
                (l1 (make-instance 'polygon))
                (l2 (make-instance 'polygon)))

           (let* ((a (/ *field-width* 5))
                  (b (- *field-width* a)))
             (set-items l1 (list (make-point a a) (make-point b b)))
             (set-items l2 (list (make-point a b) (make-point b a)))
      
             (set-thickness l1 a)
             (set-thickness l2 a))

           (set-color cross :red)
           (set-propagate-color-p cross t)
           (set-items cross (list l1 l2))
           cross))

       ; Vrátí objekt reprezentující značku druhého hráče - kolečko
       (circle ()
         (let* ((c (make-instance 'circle))
                (half (/ *field-width* 2))
                (tenth (/ *field-width* 10))
                (s (make-point half half)))

           (set-center c s)
           (set-radius c (/ (+ half tenth) 2))
           (set-thickness c tenth)
           (set-color c :green)
           c)))

  (unless (and (>= value -1) (<= value 1))
    (error "Hodnota policka musi byt -1, 0, nebo 1"))
  
  (let ((value-image (cond ((= value -1) (cross))
                           ((= value +1) (circle))
                           (t (make-instance 'empty-shape)))))

    (set-items field (list value-image (border)))
    (setf (slot-value field 'value) value)
    field)))



;; POUŽITÍ

; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let ((p (make-instance 'picture))
      (fields (list (make-field -1)
                    (make-field 0)
                    (make-field 1)
                    (make-field 1)
                    (make-field -1)
                    (make-field 0)
                    (make-field 0)
                    (make-field 1)
                    (make-field -1)))
      (x 0)
      (y 0))

  (dolist (field fields)
    (move field 
          (* *field-width* x)
          (* *field-width* y))

    (setq x (+ x 1))
    (if (= x *fields-in-row*)
        (progn
          (setq x 0)
          (setq y (+ y 1)))))
          
  (let ((center (make-point (+ *gap-x* (* *fields-in-row* (/ *field-width* 2)))
                            (+ *gap-y* (* y (/ *field-width* 2))))))
    
    (set-items p fields)
    (move p *gap-x* *gap-y*)
    (scale p 1.1 center)
    (set-shape *win* p)
    (redraw *win*)))

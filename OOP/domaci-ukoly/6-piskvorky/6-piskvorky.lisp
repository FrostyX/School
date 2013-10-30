(load (current-pathname "micro-graphics.lisp"))

;; CELL
(defclass cell (picture) 
  ((value :initform 0))) ; Value: -1 0 1

(defun make-cell (value)
  value)

(defmethod draw ((cell cell))
  ; Tu podle value přidat buď cross nebo circle
  (set-items cell (list (border cell)))
  (call-next-method))

(defmethod border ((cell cell))
  (let ((border (make-instance 'polygon))
        (A (make-point 0 0))
        (B (make-point 0 50))
        (C (make-point 50 50))
        (D (make-point 50 0)))

    (set-items border (list A B C D))
    border))

(defmethod cross ((cell cell))
  0)

(defmethod circle ((cell cell))
  0)

(defmethod value ((cell cell))
  (slot-value cell 'value))

(defmethod set-value ((cell cell) value) 
  (setf (slot-value cell 'value) value)
  cell)

; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let* ((cell (make-instance 'cell)))
  (set-shape *win* cell)
  (redraw *win*))

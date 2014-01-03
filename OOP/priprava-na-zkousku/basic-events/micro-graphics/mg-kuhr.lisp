;; auxiliary functions

(defun colorp (color)
  (if (find color (color:get-all-color-names)) t nil))




;; class CIRCLE

(defmethod set-center ((c circle) value)
  (setf (slot-value c 'center) value)
  c)



; functions for easy construction

(defun make-point (x y)
  (let ((point (make-instance 'point)))
    (set-x point x)
    (set-y point y)
    point))

(defun make-circle (center radius)
  (let ((circle (make-instance 'circle)))
    (set-center circle center)
    (set-radius circle radius)
    circle))

;(defclass circle (shape)
;  ((center :initform (make-instance 'point))
;   (radius :initform 1)))

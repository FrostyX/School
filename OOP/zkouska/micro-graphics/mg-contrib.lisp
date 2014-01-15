;; auxiliary functions

(defun colorp (color)
  (if (find color (color:get-all-color-names)) t nil))




;; class CIRCLE
;(defclass circle (shape)
;  ((center :initform (make-instance 'point))
;   (radius :initform 1)))


(defmethod set-center ((c circle) value)
  (setf (slot-value c 'center) value)
  c)



;; functions for easy construction

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

(defun make-picture ()
  (make-instance 'picture))

(defun make-text (value)
  (let ((text (make-instance 'text-shape)))
    (set-text text value)
    (move text 0 10)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;
;;; text-shape - ukázka definice přímého potomka třídy shape
;;;

(defclass text-shape (shape)
  ((text :initform "")
   (position :initform (make-instance 'point))))

(defmethod text ((shape text-shape))
  (slot-value shape 'text))

(defmethod text-position ((shape text-shape))
  (slot-value shape 'position))

(defmethod initialize-instance ((shape text-shape) &key)
  (call-next-method)
  (let ((pos (text-position shape)))
    (set-events pos '(ev-changing ev-change))
    (set-delegate pos shape))
  shape)

(defmethod ev-changing ((shape text-shape) sender msg &rest msg-args)
  (changing shape 'ev-changing sender msg msg-args))

(defmethod ev-change ((shape text-shape) sender msg &rest msg-args)
  (change shape 'ev-change sender msg msg-args))

(defmethod set-text ((shape text-shape) value)
  (with-change (shape 'set-text value)
    (setf (slot-value shape 'text) value)))

(defmethod do-move ((shape text-shape) dx dy)
  (move (text-position shape) dx dy))

(defmethod do-scale ((shape text-shape) coeff center)
  (scale (text-position shape) coeff center))

(defmethod do-rotate ((shape text-shape) angle center)
  (rotate (text-position shape) angle center))

(defmethod left ((shape text-shape))
  (+ (first (mg:get-string-extent (shape-mg-window shape)
                                  (text shape)))
     (x (text-position shape))))

(defmethod top ((shape text-shape))
  (+ (second (mg:get-string-extent (shape-mg-window shape)
                                   (text shape)))
     (y (text-position shape))))

(defmethod right ((shape text-shape))
  (+ (third (mg:get-string-extent (shape-mg-window shape)
                                  (text shape)))
     (x (text-position shape))))

(defmethod bottom ((shape text-shape))
  (+ (fourth (mg:get-string-extent (shape-mg-window shape)
                                   (text shape)))
     (y (text-position shape))))

(defmethod contains-point-p ((shape text-shape) point)
  (and (<= (left shape) (x point) (right shape))
       (<= (top shape) (y point) (bottom shape))))


(defmethod do-draw ((shape text-shape))
  (mg:draw-string (shape-mg-window shape)
                  (text shape)
                  (x (text-position shape))
                  (y (text-position shape)))
  shape)

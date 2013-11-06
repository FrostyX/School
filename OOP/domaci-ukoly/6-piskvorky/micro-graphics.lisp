(load (current-pathname "micro-graphics/load.lisp"))

;; auxiliary functions

(defun colorp (color)
  (if (find color (color:get-all-color-names)) t nil))


;; class SHAPE

(defclass shape ()
  ((color :initform :black)
   (thickness :initform 1)
   (filledp :initform nil)
   (window :initform nil)))

; get/set slots

(defmethod window ((shape shape))
  (slot-value shape 'window))

(defmethod set-window ((shape shape) value)
  (unless (typep value 'window)
    (error "The value should be a window. "))
  (setf (slot-value shape 'window) value)
  shape)

(defmethod shape-mg-window ((shape shape))
  (when (window shape)
    (mg-window (window shape))))

(defmethod color ((shape shape))
  (slot-value shape 'color))

(defmethod set-color ((shape shape) value)
  (unless (colorp value)
    (error "The value is not a correct representation of a color. "))
  (setf (slot-value shape 'color) value)
  shape)

(defmethod thickness ((shape shape))
  (slot-value shape 'thickness))

(defmethod set-thickness ((shape shape) value)
  (unless (and (typep value 'integer) (< 0 value))
    (error "The value should be a positive integer. "))
  (setf (slot-value shape 'thickness) value)
  shape)

(defmethod filledp ((shape shape))
  (slot-value shape 'filledp))

(defmethod set-filledp ((shape shape) value)
  (setf (slot-value shape 'filledp) value)
  shape)

; transformations

(defmethod move ((shape shape) dx dy)
  shape)

(defmethod rotate ((shape shape) angle center)
  shape)

(defmethod scale ((shape shape) coeff center)
  shape)

; drawing

(defmethod set-mg-params ((shape shape))
  (let ((mgw (shape-mg-window shape)))
    (mg:set-param mgw :foreground (color shape))
    (mg:set-param mgw :filledp (filledp shape))
    (mg:set-param mgw :thickness (thickness shape)))
  shape)

(defmethod do-draw ((shape shape))
  shape)

(defmethod draw ((shape shape))
  (set-mg-params shape)
  (do-draw shape))


;; class POINT

(defclass point (shape)
  ((x :initform 0)
   (y :initform 0)))

; get/set slots

(defmethod x ((point point))
  (slot-value point 'x))

(defmethod y ((point point))
  (slot-value point 'y))

(defmethod set-x ((point point) value)
  (unless (typep value 'number)
    (error "X coordinate of a point should be a number. "))
  (setf (slot-value point 'x) value)
  point)

(defmethod set-y ((point point) value)
  (unless (typep value 'number)
    (error "Y coordinate of a point should be a number. "))
  (setf (slot-value point 'y) value)
  point)

; polar coordinates

(defmethod r ((point point))
  (let ((x (slot-value point 'x))
        (y (slot-value point 'y)))
    (sqrt (+ (* x x) (* y y)))))

(defmethod phi ((point point))
  (let ((x (slot-value point 'x))
        (y (slot-value point 'y)))
    (cond ((> x 0) (atan (/ y x)))
          ((< x 0) (+ pi (atan (/ y x))))
          (t (* (signum y) (/ pi 2))))))

(defmethod set-r-phi ((point point) r phi)
  (setf (slot-value point 'x) (* r (cos phi))
        (slot-value point 'y) (* r (sin phi)))
  point)

(defmethod set-r ((point point) r)
  (set-r-phi point r (phi point)))

(defmethod set-phi ((point point) phi)
  (set-r-phi point (r point) phi))

; transformations

(defmethod move ((pt point) dx dy)
  (set-x pt (+ (x pt) dx))
  (set-y pt (+ (y pt) dy))
  pt)

(defmethod rotate ((pt point) angle center)
  (let ((cx (x center))
        (cy (y center)))
    (move pt (- cx) (- cy))
    (set-phi pt (+ (phi pt) angle))
    (move pt cx cy)
    pt))

(defmethod scale ((pt point) coeff center)
  (let ((cx (x center))
        (cy (y center)))
    (move pt (- cx) (- cy))
    (set-r pt (* (r pt) coeff))
    (move pt cx cy)
    pt))

; drawing

(defmethod set-mg-params ((pt point))
  (call-next-method)
  (mg:set-param (shape-mg-window pt) :filledp t)
  pt)

(defmethod do-draw ((pt point))
  (mg:draw-circle (shape-mg-window pt)
                  (x pt)
                  (y pt)
                  (thickness pt))
  pt)



;; class CIRCLE

(defclass circle (shape)
  ((center :initform (make-instance 'point))
   (radius :initform 1)))

; get/set slots

(defmethod radius ((c circle))
  (slot-value c 'radius))

(defmethod set-radius ((c circle) value)
  (when (< value 0)
    (error "Radius should be a non-negative number. "))
  (setf (slot-value c 'radius) value)
  c)

(defmethod center ((c circle))
  (slot-value c 'center))

(defmethod set-center ((c circle) value)
  (setf (slot-value c 'center) value)
  c)

; transformations

(defmethod move ((c circle) dx dy)
  (move (center c) dx dy)
  c)

(defmethod rotate ((c circle) angle center)
  (rotate (center c) angle center)
  c)

(defmethod scale ((c circle) coeff center)
  (scale (center c) coeff center)
  (set-radius c (* (radius c) coeff))
  c)

; drawing

(defmethod do-draw ((c circle))
  (mg:draw-circle (shape-mg-window c)
                  (x (center c))
                  (y (center c))
                  (radius c))
  c)



;; class COMPOUND-SHAPE

(defclass compound-shape (shape)
  ((items :initform '())))

; get/set slots

(defmethod items ((shape compound-shape))
  (copy-list (slot-value shape 'items)))

(defmethod send-to-items ((shape compound-shape)
			  message
			  &rest arguments)
  (dolist (item (items shape))
    (apply message item arguments))
  shape)

(defmethod check-item ((shape compound-shape) item)
  (error "Method check-item has to be rewritten"))

(defmethod check-items ((shape compound-shape) item-list)
  (dolist (item item-list)
    (check-item shape item))
  shape)

(defmethod set-items ((shape compound-shape) value)
  (check-items shape value)
  (setf (slot-value shape 'items) (copy-list value))
  (if (window shape) (send-to-items shape #'set-window (window shape)))
  shape)

; transformations

(defmethod move ((shape compound-shape) dx dy)
  (send-to-items shape #'move dx dy)
  shape)

(defmethod rotate ((shape compound-shape) angle center)
  (send-to-items shape #'rotate angle center)
  shape)

(defmethod scale ((shape compound-shape) coeff center)
  (send-to-items shape #'scale coeff center)
  shape)



;; class PICTURE

(defclass picture (compound-shape)
  ((propagate-color-p :initform nil)))

; get/set slots

(defmethod propagate-color-p ((p picture))
  (slot-value p 'propagate-color-p))

(defmethod send-to-items-set-color ((p picture) color)
  (send-to-items p #'set-color color)
  p)

(defmethod set-propagate-color-p ((p picture) value)
  (setf (slot-value p 'propagate-color-p) value)
  (when value
    (send-to-items-set-color p (color p)))
  p)

(defmethod set-color ((p picture) color)
  (call-next-method)
  (when (propagate-color-p p)
    (send-to-items-set-color p (color p)))
  p)

(defmethod set-items ((p picture) items)
  (call-next-method)
  (when (propagate-color-p p)
    (send-to-items-set-color p (color p)))
  p)

(defmethod check-item ((pic picture) item)
  (unless (typep item 'shape)
    (error "Invalid picture element type."))
  pic)

(defmethod set-window ((pic picture) value)
  (send-to-items pic #'set-window value)
  (call-next-method))

; drawing

(defmethod draw ((pic picture))
  (dolist (item (reverse (items pic)))
    (draw item))
  pic)



;; class POLYGON

(defclass polygon (compound-shape)
  ((closedp :initform t)))

; get/set slots

(defmethod check-item ((p polygon) item)
  (unless (typep item 'point)
    (error "Invalid polygon element type."))
  p)

(defmethod closedp ((p polygon))
  (slot-value p 'closedp))

(defmethod set-closedp ((p polygon) value)
  (setf (slot-value p 'closedp) value)
  p)

; drawing

(defmethod set-mg-params ((poly polygon))
  (call-next-method)
  (mg:set-param (shape-mg-window poly)
                :closedp
                (closedp poly))
  poly)

(defmethod do-draw ((poly polygon))
  (let (coordinates)
    (dolist (point (reverse (items poly)))
      (setf coordinates (cons (y point) coordinates))
      (setf coordinates (cons (x point) coordinates)))
    (mg:draw-polygon (shape-mg-window poly)
                     coordinates))
  poly)


;; class EMPTY-SHAPE

(defclass empty-shape (shape)
  ())



;; class FULL-SHAPE

(defclass full-shape (shape)
  ())

(defmethod set-mg-params ((shape full-shape))
  (mg:set-param (shape-mg-window shape)
		:background
		(color shape))
  shape)

(defmethod do-draw ((shape full-shape))
  (mg:clear (shape-mg-window shape))
  shape)



;; class WINDOW

(defclass window ()
  ((mg-window :initform (mg:display-window))
   (shape :initform nil)
   (background :initform :white)))

; get/set slots

(defmethod mg-window ((window window))
  (slot-value window 'mg-window))

(defmethod shape ((w window))
  (slot-value w 'shape))

(defmethod set-shape ((w window) shape)
  (unless (typep shape 'shape)
    (error "The value shape is not of desired type."))
  (set-window shape w)
  (setf (slot-value w 'shape) shape)
  w)

(defmethod background ((w window))
  (slot-value w 'background))

(defmethod set-background ((w window) color)
  (unless (colorp color)
    (error "The value color is not a correct representation of a color. "))
  (setf (slot-value w 'background) color)
  w)

; redrawing

(defmethod redraw ((window window))
  (let ((mgw (slot-value window 'mg-window)))
    (mg:set-param mgw :background (background window))
    (mg:clear mgw)
    (when (shape window)
      (draw (shape window))))
  window)



; functions for easy construction

(defun make-point (x y)
  (let ((point (make-instance 'point)))
    (set-x point x)
    (set-y point y)
    point))

(load (current-pathname "micro-graphics/init.lisp"))

(defmethod ev-mouse-down ((circle circle) sender origin button position)
  (print "FOO")
  (set-color sender :red))

(defmethod set-value ((circle circle) value) 
  (with-change (circle 'set-value value)
    (set-color circle :red)
    circle))


; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let ((circle (make-circle (make-point 50 50) 30)))
  (set-shape *win* circle)
  (redraw *win*))

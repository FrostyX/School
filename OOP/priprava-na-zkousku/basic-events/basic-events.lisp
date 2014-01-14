(load (current-pathname "micro-graphics/init.lisp"))

; Klikat lze pouze na obrázky!

(defmethod ev-mouse-down ((picture picture) sender origin button position)
  (set-color sender :red))


; WINDOW musí být globální!
(defvar *win*)
(setf *win* (make-instance 'window))

(let* ((picture (make-picture))
       (circle (make-circle (make-point 50 50) 30)))

  (set-thickness circle 10)
  (set-filledp circle t)
  (set-items picture (list circle))
  (set-shape *win* picture)
  (redraw *win*))

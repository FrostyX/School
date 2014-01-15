;; -*- mode: lisp; encoding: utf-8; -*-
(in-package "MICRO-GRAPHICS")

(defun set-properties (plist source-plist)
  "Destruktivně nastaví vlastnosti plistu plist z source-plist a nový plist vrátí"
  (cond (source-plist (setf (getf plist (first source-plist)) (second source-plist))
                      (set-properties plist (cddr source-plist)))
        (t plist)))

#|
(set-properties (list :b 2 :c 4) (list :a 1 :c 3))
|#         

(defun %get-subset (set num)
  (loop for bit below (integer-length num)
        for elem in set
        when (logbitp bit num) collect elem))

#|
(%get-subset '(a b c d e) #b101)
|#

(defmacro do-subsets ((var set-form &optional result-form) &body body)
  (lw:rebinding (set-form)
    (lw:with-unique-names (num)
      `(let (,var)
         (loop for ,num below (expt 2 (length ,set-form))
               do (progn (setf ,var (%get-subset ,set-form ,num))
                    ,@body)
               finally return ,result-form)))))

#|
(do-subsets (a (list 1 2 3 4))
  (print a))

(do-subsets (a nil)
  (print a))
|#

(defun map-subsets (function set)
  (let (res)
    (do-subsets (subset set (reverse res))
      (push (funcall function subset) res))))

(defun mouse-callbacks (what)
  (mapcan (lambda (but lcr)
            (map-subsets (lambda (subset)
                           `((,but ,what ,@subset) call-mouse-callback ,what ,lcr ,subset))
                         '(:shift :control :meta)))
          '(:button-1 :button-2 :button-3)
          '(:left :center :right)))

(defclass mg-offscreen-window ()
  ((pane :reader pane :initarg :pane)
   (%filledp :initform nil :accessor filledp)
   (%closedp :initform nil :accessor closedp)))

(defmethod print-object ((w mg-offscreen-window) stream)
  (print-unreadable-object (w stream :type nil :identity t)
    (princ "MG-OFFSCREEN-WINDOW" stream)))

(capi:define-interface mg-interface ()
  ((%callbacks :initform '() :initarg :callbacks)
   (%filledp :initform nil :accessor filledp)
   (%closedp :initform nil :accessor closedp))
  (:panes (pane capi:output-pane 
                :reader pane
                :display-callback 'call-display-callback
                :resize-callback 'pane-resize-callback
                :input-model (list* '(:motion call-mouse-callback :motion nil nil)
                                    '(:character call-char-callback)
                                    (append (mouse-callbacks :press)
                                            (mouse-callbacks :release)
                                            (mouse-callbacks :second-press)
                                            (mouse-callbacks :motion)))))
  (:layouts 
   (main capi:column-layout '(pane) :default t))
  (:default-initargs :destroy-callback 'call-destroy-callback
                     :activate-callback 'call-activate-callback))

(defmethod pane ((pane gp:graphics-port-mixin))
  pane)

(defmethod print-object ((w mg-interface) stream)
  (print-unreadable-object (w stream :type nil :identity t)
    (princ "MG-WINDOW" stream)))

(defun call-back (pane-or-intf callback-type &rest args)
  (let* ((intf (capi:element-interface (pane pane-or-intf)))
         (fun (getf (get-callbacks intf) callback-type)))
    (when fun (apply fun intf args))))

(defun pane-resize-callback (pane x y w h)
  (declare (ignore x y))
  (call-back pane :resize w h))

(defun call-display-callback (pane &rest args)
  (declare (ignore args))
  (call-back pane :display))

(defun call-destroy-callback (intf &rest args)
  (declare (ignore args))
  (call-back intf :destroy))

(defun call-activate-callback (intf actp)
  (call-back intf :activate actp))

(defun call-mouse-callback (pane x y what button modifiers)
  (declare (ignore modifiers))
  (call-back pane
             (cdr (find what '((:press . :mouse-down) (:second-press . :double-click)
                               (:release . :mouse-up) (:motion . :mouse-move))
                        :key #'car))
             button x y))

(defun call-char-callback (pane x y char)
  (declare (ignore x y))
  (call-back pane :character char))

(defun display-window (&key callbacks 
                            (display-callback (getf callbacks :display))
                            (mouse-down-callback (getf callbacks :mouse-down))
                            (mouse-up-callback (getf callbacks :mouse-up))
                            (mouse-move-callback (getf callbacks :mouse-move))
                            (double-click-callback (getf callbacks :double-click))
                            (character-callback (getf callbacks :character))
		            (destroy-callback (getf callbacks :destroy))
		            (activate-callback (getf callbacks :activate))
                            (resize-callback (getf callbacks :resize))
		            (always-on-top-p t)
                            (height 200)
                            (width 250))
  (let ((result (make-instance 'mg-interface 
                               :title "micro-graphics window"
                               :height height
                               :width width
                               :callbacks `(:display ,display-callback :destroy ,destroy-callback
                                            :activate ,activate-callback
                                            :mouse-down ,mouse-down-callback
                                            :mouse-up ,mouse-up-callback :mouse-move ,mouse-move-callback
                                            :double-click ,double-click-callback
                                            :resize ,resize-callback
                                            :character ,character-callback)
                               :window-styles `(:always-on-top ,always-on-top-p))))
    (capi:display result)
    ;; Nastavime rucne, na nekterych platformach zrejme jinak drawing-state vraci
    ;; interni data misto nazvu barev:
    (set-param result :foreground :black)
    (set-param result :background :white)
    result))

(defun make-offscreen-window (from-window width height)
  "Vytvoří offscreen-window, čili okno, které se nezobrazí na obrazovce, ale je uloženo v paměti. Pro kreslení do tohoto okna je možné použít stejné funkce jako na kreslení do obyčejného okna vytvořeného funkcí mg:display-window. Jako první parametr je třeba zadat okno, do kterého se obsah offscreen-window bude případně kreslit. Parametry width a height udávají rozměry v pixelech. Obsah offscreen-window lze vykreslit do obyčejného okna funkcí draw-window. Po ukončení práce je třeba okno smazat funkcí close-window."
  (make-instance 'mg-offscreen-window 
                 :pane (gp:create-pixmap-port (pane from-window) width height)))

(defmethod apply-in-p-p ((pane capi:simple-pane) fun &rest args)
  (apply 'capi:apply-in-pane-process pane fun args))

(defmethod apply-in-p-p ((pane mg-offscreen-window) fun &rest args)
  (apply fun args))

(defmethod close-window ((window capi:simple-pane))
  (apply-in-p-p window #'capi:quit-interface window)
  nil)

(defmethod close-window ((window mg-offscreen-window))
  (gp:destroy-pixmap-port (pane window))
  nil)

(defun get-param (window param)
  (let ((state (gp:get-graphics-state (pane window))))
    (case param
      (:thickness (gp:graphics-state-thickness state))
      (:foreground (gp:graphics-state-foreground state))
      (:background (gp:graphics-state-background state))
      (:transform (gp:graphics-state-transform state))
      (:filledp (filledp window))
      (:closedp (closedp window)))))

(defun set-param (window param value)
  (cond 
   ((eql param :filledp) (setf (filledp window) value))
   ((eql param :closedp) (setf (closedp window) value))
   (t (apply-in-p-p 
       window 
       (lambda ()
         ;(gp:clear-graphics-port-state (pane window))
         (gp:set-graphics-state (pane window) param value)))))
  nil)

(defun get-callbacks (window)
  (slot-value window '%callbacks))

(defun set-callbacks (window &rest args &key display mouse-down mouse-up mouse-move double-click 
                                             character
		                             destroy activate resize)
  (declare (ignore mouse-down mouse-up mouse-move double-click character destroy activate resize))
  (apply-in-p-p
   window
   (lambda (a)
     (setf (slot-value window '%callbacks) 
           (set-properties (slot-value window '%callbacks) a)))
   args)
  (when display
    (invalidate window))
  nil)

(defun get-callback (window callback-name)
  (getf (get-callbacks window) callback-name))

(defun set-callback (window callback-name function)
  (set-callbacks window callback-name function)
  nil)

(defun invalidate (window)
  (apply-in-p-p window #'gp:invalidate-rectangle (pane window))
  nil)

(defun clear (window)
  (apply-in-p-p window #'gp:clear-graphics-port (pane window))
  nil)

(defun draw-circle (window x y r)
  (apply-in-p-p 
   window 
   #'gp:draw-circle (pane window) x y r :filled (filledp window))
  nil)

(defun draw-polygon (window points)
  (apply-in-p-p 
   window 
   #'gp:draw-polygon (pane window) points :closed (closedp window) :filled (filledp window))
  nil)

(defun get-string-extent (window string)
  (multiple-value-bind (l tp r b) (gp:get-string-extent (pane window) string)
    (list l tp r b)))

(defun draw-string (window string x y)
  (apply-in-p-p 
   window 
   #'gp:draw-string (pane window) string x y)
  nil)

(defparameter *images* (make-hash-table :test #'equalp :weak-kind :key))

(defun get-image (file pane)
  (let ((result (gethash file *images*)))
    (unless result
      (setf result (gp:load-image pane file))
      (setf (gethash file *images*) result))
    result))

(defun draw-image (window file x y)
  (apply-in-p-p
   window
   #'gp:draw-image
   (pane window) (get-image file (pane window)) x y))

(defun get-image-extent (window file)
  (let ((image (get-image file (pane window))))
    (list 0
          0
          (gp:image-width image)
          (gp:image-height image))))

(defun draw-window (window offscreen-window)
  "Vykreslí offscreen-window do window."
  (let ((off-pane (pane offscreen-window)))
    (apply-in-p-p
     window
     'gp:copy-pixels (pane window) off-pane 0 0 (gp:port-width off-pane) (gp:port-height off-pane) 0 0)))

#|

(defun paint-flag (w)
    (mg:set-param w :background :skyblue)
    (mg:set-param w :foreground :white)
    (mg:clear w)
    (mg:set-param w :filledp t)
    (mg:set-param w :closedp t)
    (mg:draw-polygon w '(20 20 200 20 200 140 20 140))
    (mg:set-param w :foreground :black)
    (mg:draw-polygon w '(20 20 200 20 200 140 20 140))
    (mg:set-param w :foreground :red)
    (mg:draw-circle w 110 80 30))

(defun display-flag ()
  (mg:display-window :callbacks (list :display (lambda (w) (paint-flag w))
                                      :mouse-down (lambda (w butt x y)
                                                    (format t "~%:mouse-down ~s ~s ~s" butt x y))
                                      :mouse-up (lambda (w butt x y)
                                                    (format t "~%:mouse-up ~s ~s ~s" butt x y))
                                      :double-click (lambda (w butt x y)
                                                    (format t "~%:double-click ~s ~s ~s" butt x y))
                                      :mouse-move (lambda (w butt x y)
                                                    (format t "~%:mouse-move ~s ~s ~s" butt x y))
                                      :character (lambda (w char)
                                                   (format t "~%:character ~s" char))
                                      :resize (lambda (w width height)
                                                (format t "~%resize... ~s ~s" width height))
                                      :destroy (lambda (w)
                                                 (format t "~%Window ~s destroyed" w))
                                      :activate (lambda (w actp)
                                                  (if actp
                                                      (format t "~%Window ~s activated" w)
                                                    (format t "~%Window ~s deactivated" w))))))

(setf w (display-flag))
(get-callbacks w)
(set-callbacks w :display (lambda (w) (paint-flag w)))

|#

#|
(setf w (mg:display-window))
(mg:set-param w :transform '(2 0 0 1 0 0))
(mg:draw-circle w 50 50 20)
|#


#|
(setf w1 (mg:display-window))
(setf w2 (mg:make-offscreen-window w1 250 255))
(mg:set-param w2 :foreground :blue)
(mg:set-param w2 :filledp t)
(mg:draw-circle w2 100 100 50)
(mg:draw-window w1 w2)
(mg:close-window w2)
|#

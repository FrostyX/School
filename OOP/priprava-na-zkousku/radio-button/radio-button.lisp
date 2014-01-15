(load (current-pathname "micro-graphics/init.lisp"))

;; RADIOBUTTON

(defclass radiobutton (picture)
  ((checked :initform nil)
   (text :initform (make-text ""))
   (input :initform (make-circle (make-point 7 7) 7))))

(defmethod initialize-instance ((radiobutton radiobutton) &key)
  (call-next-method)
  (add-event radiobutton 'ev-mouse-down))

(defun make-radiobutton (text checked)
  (let ((radiobutton (make-instance 'radiobutton)))

    (set-text radiobutton text)
    (set-checked radiobutton checked)

    ; Zarovnání textu vedle inputu
    (move (text radiobutton) 20 2)

    ; Výchozí hodnoty pro input (vyplněné šedé pozadí)
    (set-filledp (slot-value radiobutton 'input) t)
    (set-color (slot-value radiobutton 'input) :grey)

    ; Při vykreslení radiobuttonu vykreslit jeho komponenty
    (set-items radiobutton (list (text radiobutton) (slot-value radiobutton 'input)))
    
    radiobutton))

(defmethod mouse-down ((radiobutton radiobutton) button position)
  ; Zavolá metodu ev-mouse-down nadřazeného objektu
  (send-event radiobutton 'ev-mouse-down radiobutton button position))
 
(defmethod text ((radiobutton radiobutton))
  (slot-value radiobutton 'text))

(defmethod set-text ((radiobutton radiobutton) text)
  (setf (slot-value radiobutton 'text) (make-text text)))

(defmethod chcecked ((radiobutton radiobutton))
  (slot-value radiobutton 'value))

(defmethod set-checked ((radiobutton radiobutton) checked)
  (setf (slot-value radiobutton 'checked) checked))

;; RADIO-GROUP

(defclass radio-group (picture)
  ((text :initform (make-text ""))
   (radiobuttons :initform '())))

(defmethod initialize-instance ((radio-group radio-group) &key)
  (call-next-method)
  (set-items radio-group (list (text radio-group))))

(defun make-radio-group ()
  (let ((group (make-instance 'radio-group)))
    group))

(defmethod text ((radio-group radio-group))
  (slot-value radio-group 'text))

(defmethod set-text ((radio-group radio-group) text)
  (setf (slot-value radio-group 'text) (make-text text))
  (setf (car (items radio-group)) text)
  (print (items radio-group)))

(defmethod radiobuttons ((radio-group radio-group))
  (slot-value radio-group 'radiobuttons))

(defmethod set-radiobuttons ((radio-group radio-group) radiobuttons)
  (setf (slot-value radio-group 'radiobuttons) radiobuttons)
  (set-items radio-group (list (text radio-group)))
  (dolist (radiobutton (radiobuttons radio-group))
    (set-items radio-group (append 
                            (items radio-group) 
                            (list (move radiobutton 5 (* 20 (length (items radio-group)))))))))

(defmethod ev-mouse-down ((radio-group radio-group) sender origin button position)
  (if (typep origin 'radiobutton)
      (progn
        ; Odkliknutí všech radiobuttonu ve skupině
        (dolist (radiobutton (radiobuttons radio-group))
          (set-color (slot-value radiobutton 'input) :grey)
          (set-checked radiobutton nil))
        
        ; Zakliknutí nového radiobuttonu
        (set-checked origin t)
        (set-color (slot-value origin 'input) :black))))


;; MAIN

(defvar *win*)
(setf *win* (make-instance 'window))

(let* ((picture (make-picture))
       (question-os (make-radio-group))
       (question-sex (make-radio-group)))
  
  ; Otázka - Operační systém
  (set-text question-os "What OS do you use?")
  (set-radiobuttons question-os (list
                                 (make-radiobutton "Linux" nil)
                                 (make-radiobutton "Mac" nil)
                                 (make-radiobutton "Windows" nil)))

  ; Otázka - Pohlaví
  (set-text question-sex "Sex")
  (set-radiobuttons question-sex (list
                                  (make-radiobutton "Male" nil)
                                  (make-radiobutton "Female" nil)))
  (move question-sex 0 90)
  
  
  ; Vykreslení okna
  (set-items picture (list question-os question-sex))
  (set-shape *win* picture)
  (redraw *win*))


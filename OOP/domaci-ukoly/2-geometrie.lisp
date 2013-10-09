;; class POINT
(defclass point ()
  ((x :initform 0)
   (y :initform 0)))

; http://cs.wikipedia.org/wiki/Pol%C3%A1rn%C3%AD_soustava_sou%C5%99adnic
; Přednáška
(defmethod set-r-phi ((point point) r phi)
  (setf (slot-value point 'x) (* r (cos phi))
        (slot-value point 'y) (* r (sin phi)))
  point)

; Přednáška
(defmethod set-r ((point point) r)
  (set-r-phi point r (phi point)))

; Přednáška
(defmethod set-phi ((point point) phi)
  (set-r-phi point (r point) phi))

; Přednáška
(defmethod r ((point point))
  (let ((x (slot-value point 'x))
        (y (slot-value point 'y)))
    (sqrt (+ (* x x) (* y y)))))

; Přednáška
(defmethod phi ((point point))
  (let ((x (slot-value point 'x))
        (y (slot-value point 'y)))
    (cond ((> x 0) (atan (/ y x)))
          ((< x 0) (+ pi (atan (/ y x))))
          (t (* (signum y) (/ pi 2))))))

; Posune bod o dané délky
(defmethod shift ((point point) x y)
  (setf (slot-value point 'x) (+ x (slot-value point 'x))
        (slot-value point 'y) (+ y (slot-value point 'y))))

; Vrátí vzdálenost mezi dvěma body
(defmethod distance ((from point) to)
    (sqrt (+ (expt (- (slot-value to 'x) (slot-value from 'x)) 2)
             (expt (- (slot-value to 'y) (slot-value from 'y)) 2))))

; Rotuje bod po kružnici o daný úhel
(defmethod rotate ((point point) angle)
  (let ((phi (phi point))
        (r (r point)))
    (set-r-phi point r (+ phi angle))))


;; class SEGMENT
(defclass segment ()
  ((a :initform (make-instance 'point))
   (b :initform (make-instance 'point))))

; Vrátí délku úsečky
(defmethod size ((segment segment))
  (distance (slot-value segment 'a) 
            (slot-value segment 'b)))


;; class TRIANGLE
(defclass triangle ()
  ((a :initform (make-instance 'point))
   (b :initform (make-instance 'point))
   (c :initform (make-instance 'point))))

; Vrátí obvod trojúhelníku
(defmethod perimeter ((tr triangle))
  (let ((A (slot-value tr 'a))
        (B (slot-value tr 'b))
        (C (slot-value tr 'c)))
    (+ (distance A B) (distance A C) (distance B C))))

; Vrátí obsah trojúhelníku
(defmethod area ((tr triangle))
  (let* ((o (perimeter tr))
         (s (/ o 2))
         (a (distance (slot-value tr 'b) (slot-value tr 'c)))
         (b (distance (slot-value tr 'a) (slot-value tr 'c)))
         (c (distance (slot-value tr 'a) (slot-value tr 'b))))
    
    ; Heronův vzorec
    ; http://cs.wikipedia.org/wiki/Heron%C5%AFv_vzorec
    (sqrt (* s (- s a) (- s b) (- s c)))))

; Vrátí seznam těžnic trojúhelníku
(defmethod medians ((tr triangle))
  (let* ((a (distance (slot-value tr 'b) (slot-value tr 'c)))
         (b (distance (slot-value tr 'a) (slot-value tr 'c)))
         (c (distance (slot-value tr 'a) (slot-value tr 'b)))
         (ta (* 1/2 (sqrt (- (* 2 (+ (expt b 2) (expt c 2))) (expt a 2)))))
         (tb (* 1/2 (sqrt (- (* 2 (+ (expt a 2) (expt c 2))) (expt b 2)))))
         (tc (* 1/2 (sqrt (- (* 2 (+ (expt a 2) (expt b 2))) (expt c 2))))))

    ; http://cs.wikipedia.org/wiki/Troj%C3%BAheln%C3%ADk#T.C4.9B.C5.BEnice
    (list ta tb tc)))

; Vráti těžiště trojúhelníku
(defmethod center ((tr triangle))
  (let* ((A (slot-value tr 'a))
         (B (slot-value tr 'b))
         (C (slot-value tr 'c))
         (p (make-instance 'point)))

    ; http://www.matematika.cz/teznice-trojuhelniku    
    (setf (slot-value p 'x) (/ (+ (slot-value A 'x) 
                                    (slot-value B 'x) 
                                    (slot-value C 'x)) 3))
    (setf (slot-value p 'y) (/ (+ (slot-value A 'y) 
                                    (slot-value B 'y) 
                                    (slot-value C 'y)) 3))
    p))

; Zarotuje trojúhelník kolem těžiště
; Hint: Posunout do počátku -> zarotovat kolem počátku -> posunout zpátky
(defmethod rotate ((tr triangle) angle)
  0)


(defvar *a*)
(setf *a* (make-instance 'point))
(shift *a* 0 0)

(defvar *b*)
(setf *b* (make-instance 'point))
(shift *b* 0 3)
(slot-value *b* 'y)

(defvar *c*)
(setf *c* (make-instance 'point))
(shift *c* 3 0)

(distance *a* *b*)
(rotate *a* 1/2)

(defvar *s*)
(setf *s* (make-instance 'segment))
(setf (slot-value *s* 'a) *a*)
(setf (slot-value *s* 'b) *b*)
(size *s*)

(defvar *abc*)
(setf *abc* (make-instance 'triangle))
(setf (slot-value *abc* 'a) *a*)
(setf (slot-value *abc* 'b) *b*)
(setf (slot-value *abc* 'c) *c*)
(perimeter *abc*)
(area *abc*)
(medians *abc*)
(slot-value (center *abc*) 'x)

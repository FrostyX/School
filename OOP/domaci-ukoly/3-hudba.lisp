;; ALBUM
(defclass album () 
  (name author tracks))

; Vytvoří objekt třídy ALBUM
(defmethod make-album (name author tracks)
  (let ((album (make-instance 'album)))
    (setf (slot-value album 'name) name)
    (setf (slot-value album 'author) author)
    (setf (slot-value album 'tracks) tracks)
    album))

(defmethod track-count ((album album))
  (length (slot-value album 'tracks)))

(defmethod total-time ((album album))
  (let ((tracks (slot-value album 'tracks)))
;    (print (get-sec (slot-value (car tracks) 'time)))
    0))


(defmethod print-album ((album album))
  (format t "~x - ~x:~%" (slot-value album 'author) (slot-value album 'name))
  (mapcar #'print-track (slot-value album 'tracks))
  (format t "(~x tracks | ~x)~%" (track-count album) (total-time album)))


;; TRACK
(defclass track ()
  (title time))

; Vytvoří objekt třídy TRACK
(defmethod make-track (title length-seconds)
  (let ((track (make-instance 'track)))
    (setf (slot-value track 'title) title)
    (setf (slot-value track 'time) (make-time length-seconds))
    track))

; Vypíše název a délku songu
(defmethod print-track ((track track))
  (format t "~x " (slot-value track 'title))
  (print-time (slot-value track 'time)))

;; TIME
(defclass my-time () (sec))

; Vytvoří objekt třídy TIME
(defmethod make-time (sec)
  (let ((time (make-instance 'my-time)))
    (setf (slot-value time 'sec) sec)
    time))

; Vrátí tečkový pár reprezentují minuty a sekundy: (m . s)
(defmethod mins-secs ((time my-time))
  (cons (floor (/ (slot-value time 'sec) 60))
        (mod (slot-value time 'sec) 60)))

; Vypíše čas ve formátu: [m:s]
(defmethod print-time ((time my-time))
  (let ((x (mins-secs time)))
    (format t "[~s:~s]~%" (car x) (cdr x))))

(defmethod get-sec ((time my-time))
  (slot-value time 'sec))



;; POUŽITÍ
(let* ((t1 (make-track "Tears Dont Fall" 348))
       (t2 (make-track "Hit The Floor" 210))
       (t3 (make-track "The End" 407))
       (a (make-album "The Poison" "Bullet For My Valentine" (list t1 t2 t3))))
  (print-album a))

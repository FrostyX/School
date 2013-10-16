;; ALBUM
(defclass album () 
  (name author tracks))

; Vytvoří objekt třídy ALBUM
(defun make-album (name author tracks)
  (let ((album (make-instance 'album)))
    (setf (slot-value album 'name) name)
    (setf (slot-value album 'author) author)
    (setf (slot-value album 'tracks) tracks)
    album))

; Vrátí počet skladeb v albu
(defmethod track-count ((album album))
  (length (slot-value album 'tracks)))

; Vrátí celkový čas alba v sekundách
(defmethod total-time ((album album))
  (labels ((iter (tracks)
             (if (eq tracks `()) 0 
               (+ (get-sec (get-time (car tracks))) (iter (cdr tracks))))))   
    (iter (slot-value album 'tracks))))

; Vypíše autora, název alba, jeho skladby, jejich počet a celkovou délku
(defmethod print-album ((album album))
    (format t "~x - ~x:~%" (slot-value album 'author) (slot-value album 'name))
    (mapcar #'print-track (slot-value album 'tracks))
    (format t "-> ~x tracks | " (track-count album))
    (print-time (make-time (total-time album))))

; Vrátí název alba
(defmethod get-name ((album album))
  (slot-value album 'name))

; Nastaví název alba
(defmethod set-name ((album album) name)
  (setf (slot-value album 'name) name))

; Vrátí autora alba
(defmethod get-author ((album album))
  (slot-value album 'author))

; Nastaví autora alba
(defmethod set-author ((album album) author)
  (setf (slot-value album 'author) author))

; Vrátí seznam songů (objektů třídy TRACK) v albu
(defmethod get-tracks ((album album))
  (slot-value album 'tracks))

(defmethod set-tracks ((album album) tracks)
  (if (not (listp tracks)) 
      (error "Seznam songu musi byt seznam")
    (setf (slot-value album 'tracks) tracks)))


;; TRACK
(defclass track ()
  (title time))

; Vytvoří objekt třídy TRACK
(defun make-track (title length-seconds)
  (let ((track (make-instance 'track)))
    (setf (slot-value track 'title) title)
    (setf (slot-value track 'time) (make-time length-seconds))
    track))

; Vypíše název a délku songu
(defmethod print-track ((track track))
  (format t "~x " (slot-value track 'title))
  (print-time (slot-value track 'time)))

; Vrátí délku songu jako objekt třídy TIME
(defmethod get-time ((track track))
  (slot-value track 'time))

; Nastaví délku songu
(defmethod set-time ((track track) (time my-time))
  (setf (slot-value track 'time) time))

; Vrátí název songu
(defmethod get-title ((track track))
  (slot-value track 'title))

; Nastaví název songu
(defmethod set-title ((track track) title)
  (setf (slot-value track 'title) title))


;; TIME
(defclass my-time () (sec))

; Vytvoří objekt třídy TIME
(defun make-time (sec)
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

; Vrátí čas v sekundách
(defmethod get-sec ((time my-time))
  (slot-value time 'sec))

; Nastaví čas v sekundách
(defmethod set-sec ((time my-time) sec)
  (if (not (numberp sec)) 
      (error "Zadejte prosim cislo")
    (setf (slot-value time 'sec) sec)))


;; POUŽITÍ
(let* ((t1 (make-track "Tears Dont Fall" 348))
       (t2 (make-track "Hit The Floor" 210))
       (t3 (make-track "The End" 407))
       (a (make-album "The Poison" "Bullet For My Valentine" (list t1 t2 t3))))
  (print-album a))

;; DATABAZE-OBCHODU
(defclass databaze-obchodu ()
  ((seznam-zbozi :initform '())))

(defun make-databaze-obchodu (seznam-zbozi)
  (let ((db (make-instance 'databaze-obchodu)))
    (set-seznam-zbozi db seznam-zbozi)
    db))

(defmethod print-databaze-obchodu ((db databaze-obchodu))
  (format t "Zboží: ~D kusů - ~D kč~%  => " (length (seznam-zbozi db)) (celkova-cena db))
  (print-seznam-zbozi db))

(defmethod print-seznam-zbozi ((db databaze-obchodu))
  (labels ((iter (seznam)
             (if (eq seznam `()) `()
               (progn 
                 (format t (if (eq (cdr seznam) '()) "~x" "~x | ") (nazev (car seznam)))
                 (iter (cdr seznam))))))
    (iter (seznam-zbozi db))))


; Vrátí celkovou cenu všeho zboží v obchodě
(defmethod celkova-cena ((o databaze-obchodu))
  (labels ((iter (seznam)
             (if (eq seznam `()) 0 
               (+ (cena (car seznam)) (iter (cdr seznam))))))
    (iter (seznam-zbozi o))))

; Gettery & Settery
(defmethod seznam-zbozi ((o databaze-obchodu))
  (slot-value o 'seznam-zbozi))

(defmethod set-seznam-zbozi ((o databaze-obchodu) s)
  (setf (slot-value o 'seznam-zbozi) s))


;; ZBOZI
(defclass zbozi ()
  ((nazev :initform "")
   (autor :initform "")
   (cena :initform 0)
   (rok :initform 1970)))

(defun make-zbozi (nazev autor cena rok)
  (let ((zbozi (make-instance 'zbozi)))
    (set-nazev zbozi nazev)
    (set-autor zbozi autor)
    (set-cena zbozi cena)
    (set-rok zbozi rok)
    zbozi))

; Gettery & Settery
; Nazev
(defmethod nazev ((zbozi zbozi))
  (slot-value zbozi 'nazev))

(defmethod set-nazev ((zbozi zbozi) nazev)
  (setf (slot-value zbozi 'nazev) nazev))

; Autor
(defmethod autor ((zbozi zbozi))
  (slot-value zbozi 'autor))

(defmethod set-autor ((zbozi zbozi) autor)
  (setf (slot-value zbozi 'autor) autor))

; Cena
(defmethod cena ((zbozi zbozi))
  (slot-value zbozi 'cena))

(defmethod set-cena ((zbozi zbozi) cena)
  (setf (slot-value zbozi 'cena) cena)) 

(defmethod rok ((zbozi zbozi))
  (slot-value zbozi 'rok))

(defmethod set-rok ((zbozi zbozi) rok)
  (setf (slot-value zbozi 'rok) rok)) 


;; KNIHA
(defclass kniha (zbozi)
  ((stran :initform 0)))

(defun make-kniha (nazev autor cena rok stran)
  (let ((kniha (make-instance 'kniha)))
    (set-nazev kniha nazev)
    (set-autor kniha autor)
    (set-cena kniha cena)
    (set-rok kniha rok)
    (set-stran kniha stran)
    kniha))

(defmethod print-kniha ((k kniha))
  ; http://psg.com/~dlamkins/sl/chapter24.html
  (format t "~x - ~x: ~D stran ~%" (autor k) (nazev k) (stran k)))

; Gettery & Settery
(defmethod stran ((kniha kniha))
  (slot-value kniha 'stran))

(defmethod set-stran ((kniha kniha) stran)
  (setf (slot-value kniha 'stran) stran))


;; MULTIMEDIALNI-SOUBOR
(defclass multimedialni-soubor (zbozi)
  ((delka :initform (make-time 0))))

(defun make-multimedialni-soubor (nazev autor cena rok delka)
  (let ((soubor (make-instance 'multimedialni-soubor)))
    (set-nazev soubor nazev)
    (set-autor soubor autor)
    (set-cena soubor cena)
    (set-rok soubor rok)
    (set-delka soubor delka)
    soubor))

; Gettery & Settery
(defmethod delka ((s multimedialni-soubor))
  (slot-value s 'delka))

(defmethod set-delka ((s multimedialni-soubor) delka)
  (setf (slot-value s 'delka) (make-time delka)))


;; SKLADBA
(defclass skladba (multimedialni-soubor)
  ((autor-hudby :initform "")
   (autor-textu :initform "")))

(defun make-skladba (nazev autor cena rok delka autor-hudby autor-textu)
  (let ((skladba (make-instance 'skladba)))
    (set-nazev skladba nazev)
    (set-autor skladba autor)
    (set-cena skladba cena)
    (set-rok skladba rok)
    (set-delka skladba delka)
    (set-autor-hudby skladba autor-hudby)
    (set-autor-textu skladba autor-textu)
    skladba))

; Gettery & Settery
; Autor-hudby
(defmethod autor-hudby ((skladba skladba))
  (slot-value skladba 'autor-hudby))

(defmethod set-autor-hudby ((skladba skladba) autor)
  (setf (slot-value skladba 'autor-hudby) autor))

; Autor-textu
(defmethod autor-textu ((skladba skladba))
  (slot-value skladba 'autor-textu))

(defmethod set-autor-textu ((skladba skladba) autor)
  (setf (slot-value skladba 'autor-textu) autor))


;; VIDEO
(defclass video (multimedialni-soubor)
  ((herci :initform '())
   (jazyk :initform "cz")))

(defun make-video (nazev autor cena rok delka herci jazyk)
  (let ((video (make-instance 'video)))
    (set-nazev video nazev)
    (set-autor video autor)
    (set-cena video cena)
    (set-rok video rok)
    (set-delka video delka)
    (set-herci video herci)
    (set-jazyk video jazyk)
    video))

; Gettery & Settery
; Herci
(defmethod herci ((video video))
  (slot-value video 'herci))

(defmethod set-herci ((video video) herci)
  (setf (slot-value video 'herci) herci))

; Jazyk
(defmethod jazyk ((video video))
  (slot-value video 'jazyk))

(defmethod set-jazyk ((video video) jazyk)
  (setf (slot-value video 'jazyk) jazyk))


;; KOLEKCE-MULTIMEDII
(defclass kolekce-multimedii (zbozi)
  ((soubory :initform '())))

(defun make-kolekce-multimedii (nazev autor cena rok soubory)
  (let ((kolekce (make-instance 'kolekce-multimedii)))
    (set-nazev kolekce nazev)
    (set-autor kolekce autor)
    (set-cena kolekce cena)
    (set-rok kolekce rok)
    (set-soubory kolekce soubory)
    kolekce))

(defmethod print-kolekce-multimedii ((k kolekce-multimedii))
  (format t "~x - ~x: ~x soubory " (autor k) (nazev k) (pocet-souboru k))
  (print-time (celkova-delka k)))

(defmethod pocet-souboru ((k kolekce-multimedii))
  (length (soubory k)))

; Vrátí celkový čas kolekce v sekundách
(defmethod celkova-delka ((k kolekce-multimedii))
  (labels ((iter (soubory)
             (if (eq soubory `()) 0 
               (+ (get-sec (delka (car soubory))) (iter (cdr soubory))))))   
    (make-time (iter (soubory k)))))

; Gettery & Settery
(defmethod soubory ((k kolekce-multimedii))
  (slot-value k 'soubory))

(defmethod set-soubory ((k kolekce-multimedii) soubory)
  (setf (slot-value k 'soubory) soubory))


;; ALBUM
(defclass album (kolekce-multimedii) ())

(defun make-album (nazev autor cena rok soubory)
  (let ((album (make-instance 'album)))
    (set-nazev album nazev)
    (set-autor album autor)
    (set-cena album cena)
    (set-rok album rok)
    (set-soubory album soubory)
    album))

(defmethod print-album ((album album))
  (print-kolekce-multimedii album))


;; SERIAL
(defclass serial (kolekce-multimedii) ())

(defun make-serial (nazev autor cena rok soubory)
  (let ((serial (make-instance 'serial)))
    (set-nazev serial nazev)
    (set-autor serial autor)
    (set-cena serial cena)
    (set-rok serial rok)
    (set-soubory serial soubory)
    serial))

(defmethod print-serial ((serial serial))
  (print-kolekce-multimedii serial))


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
(let* ((s1 (make-skladba "Tears Dont Fall" "BFMW" 50 2005 348 "Matt Tuck" "Matt Tuck"))
       (s2 (make-skladba "Hit The Floor" "BFMW" 30 2005 210 "Matt Tuck" "Matt Tuck"))
       (s3 (make-skladba "The End" "BFMW" 75 2005 407 "Matt Tuck" "Matt Tuck"))
       (a (make-album "The Poison" "BFMW" 99 2005 (list s1 s2 s3)))
 
       (h1 (list "Johnny Galecki" "Jim Parsons" "Kaley Cuoco"))
       (v1 (make-video "01x01 - Pilot" "James Burrows" 40 2007 1380 h1 "en"))
       (v2 (make-video "01x02 - The Big Bran Hypothesis" "James Burrows" 40 2007 1380 h1 "en"))
       (v3 (make-video "01x03 - The Fuzzy Boots Corollary" "James Burrows" 40 2007 1380 h1 "en"))
       (s (make-serial "Big Bang Theory, The" "James Burrows" 99 2007 (list v1 v2 v3)))

       (k (make-kniha "Carrie" "Stephen King" 85 1973 171))
       (db (make-databaze-obchodu (list a s k))))

  (format t "~%")
  (print-album a)
  (print-serial s)
  (print-kniha k)
  (print-databaze-obchodu db))
;; DATABAZE-OBCHODU
(defclass databaze-obchodu ()
  ((seznam-zbozi :initform '())))

(defun make-databaze-obchodu (seznam-zbozi)
  (let ((db (make-instance 'databaze-obchodu)))
    (set-seznam-zbozi db seznam-zbozi)))

(defmethod celkova-cena ((o databaze-obchodu))
  0)

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
    (set-rok zbozi rok)))

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
    (set-stran kniha stran)))

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
    (set-delka soubor delka)))

; Gettery & Settery
(defmethod delka ((s multimedialni-soubor))
  (slot-value s 'delka))

(defmethod set-delka ((s multimedialni-soubor) delka)
  (setf (slot-value s 'delka) delka))

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
    (set-autor-textu skladba autor-textu)))

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
    (set-jazyk video jazyk)))

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
    (set-soubory kolekce soubory)))

(defmethod pocet-souboru ((k kolekce-multimedii))
  0)

(defmethod celkova-delka ((k kolekce-multimedii))
  0)

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
    (set-soubory album soubory)))


;; SERIAL
(defclass serial (kolekce-multimedii) ())

(defun make-serial (nazev autor cena rok soubory)
  (let ((serial (make-instance 'serial)))
    (set-nazev serial nazev)
    (set-autor serial autor)
    (set-cena serial cena)
    (set-rok serial rok)
    (set-soubory serial soubory)))


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


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;  CISTE FUNKCIONALNI FRAGMENT SCHEME VE SCHEME, PARADIGMATA PROGRAMOVANI 1
;;;;;  http://vychodil.inf.upol.cz/kmi/pp1/
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;
;;;;  SYSTEM MANIFESTOVANYCH TYPU
;;;;

;; vytvor element jazyka s manifestovanym typem
(define curry-make-elem
  (lambda (type-tag)
    (lambda (data)
      (cons type-tag data))))

;; vrat visacku s typem
(define get-type-tag car)

;; vrat data
(define get-data cdr)

;; test daneho typu
(define curry-scm-type
  (lambda (type)
    (lambda (elem)
      (equal? type (get-type-tag elem)))))

;;;;
;;;;  ZAKLADNI ELEMENTY JAZYKA SCHEME
;;;;

;;;; CISLA
;;;;;;;;;;;;;;;;;;;;

;; konstruktor cisla a test zda-li se jedna o cislo
(define make-number (curry-make-elem 'number))
(define scm-number? (curry-scm-type 'number))

;;;; SYMBOLY
;;;;;;;;;;;;;;;;;;;;

;; konstruktor symbolu a test zda-li se jedna o symbol
(define make-symbol (curry-make-elem 'symbol))
(define scm-symbol? (curry-scm-type 'symbol))

;;;; TECKOVE PARY 
;;;;;;;;;;;;;;;;;;;;

;; konstruktor paru CONS
(define make-pair
  (let ((make-physical-pair (curry-make-elem 'pair)))
    (lambda (head tail)
      (make-physical-pair (cons head tail)))))

;; test datoveho typu
(define scm-pair? (curry-scm-type 'pair))

;; selektor paru CAR
(define pair-car
  (lambda (pair)
    (if (scm-pair? pair)
	(car (get-data pair))
	(error "; Car: argument must be a pair"))))

;; selektor paru CDR
(define pair-cdr
  (lambda (pair)
    (if (scm-pair? pair)
	(cdr (get-data pair))
	(error "; Cdr: argument must be a pair"))))

;; obecny konvertor seznamu na scm-seznam a obracene
(define convert-list
  (lambda (a-null? a-car a-cdr b-cons b-nil f l)
    (if (a-null? l)
	b-nil
	(b-cons (f (a-car l))
		(convert-list a-null? a-car a-cdr b-cons b-nil f
			      (a-cdr l))))))

;;;; PROSTREDI
;;;;;;;;;;;;;;;;;;;;;;;;;

;; preved asociacni seznam na tabulku prostredi
(define assoc->env
  (lambda (l)
    (if (null? l)
	the-empty-list
	(make-pair (make-pair
		    (make-symbol (caar l))
		    (cdar l))
		   (assoc->env (cdr l))))))

;; konstruktor prostredi
(define make-env
  (let ((make-physical-env (curry-make-elem 'environment)))
    (lambda (pred table)
      (make-physical-env
       (cons pred table)))))

;; test datoveho typu
(define scm-env? (curry-scm-type 'environment))

;; konstruktor globalniho prostredi
(define make-global-env
  (lambda (alist-table)
    (make-env scm-false (assoc->env alist-table))))

;; vrat tabulku
(define get-table
  (lambda (elem)
    (if (scm-env? elem)
	(cdr (get-data elem))
	(error "; Get-table: argument must be an environment"))))

;; vrat predka
(define get-pred
  (lambda (elem)
    (if (scm-env? elem)
	(car (get-data elem))
	(error "; Get-pred: argument must be an environment"))))

;; je globalni prostredi?
(define global?
  (lambda (elem)
    (and (scm-env? elem)
	 (equal? scm-false (get-pred elem)))))

;; hledani vazeb v asociacnim poli
(define scm-assoc
  (lambda (key alist)
    (cond ((scm-null? alist) scm-false)
	  ((equal? key (pair-car (pair-car alist))) (pair-car alist))
	  (else (scm-assoc key (pair-cdr alist))))))

;; vyhledej vazbu v prostredi env, nebo vrat not-found
(define lookup-env
  (lambda (env symbol search-nonlocal? not-found)
    (let ((found (scm-assoc symbol (get-table env))))
      (cond ((not (equal? found scm-false)) found)
	    ((global? env) not-found)
	    ((not search-nonlocal?) not-found)
	    (else (lookup-env (get-pred env) symbol #t not-found))))))

;;;; PRIMITIVNI PROCEDURY
;;;;;;;;;;;;;;;;;;;;;;;;;

;; konstruktor primitivni procedury a test zda-li se jedna o proceduru
(define make-primitive (curry-make-elem 'primitive))
(define scm-primitive? (curry-scm-type 'primitive))

;; vytvareni primitivnich procedur pomoci wrapperu
(define wrap-primitive
  (lambda (proc)
    (make-primitive
     (lambda arguments
       (expr->intern (apply proc (map get-data arguments)))))))

;;;; UZIVATELSKE PROCEDURY
;;;;;;;;;;;;;;;;;;;;;;;;;;

;; konstruktor procedury
(define make-procedure
  (let ((make-physical-procedure (curry-make-elem 'procedure)))
    (lambda (env args body)
      (make-physical-procedure (list env args body)))))

;; selektory
(define procedure-environment (lambda (proc) (car (get-data proc))))
(define procedure-arguments (lambda (proc) (cadr (get-data proc))))
(define procedure-body (lambda (proc) (caddr (get-data proc))))

;; test zda-li se jedna o proceduru
(define scm-user-procedure? (curry-scm-type 'procedure))

;; test zda-li se jedna o primitivni/uzivatelskou proceduru
(define scm-procedure?
  (lambda (elem)
    (or (scm-primitive? elem)
	(scm-user-procedure? elem))))

;;;; PRIMITIVNI SPECIALNI FORMY
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; konstruktor primitivni spec. formy a test zda-li se jedna o spec. formu
(define make-specform (curry-make-elem 'specform))
(define scm-specform? (curry-scm-type 'specform))

;; test zda-li se jedna o primitivni/uzivatelskou formu
(define scm-form?
  (lambda (elem)
    (or (scm-specform? elem))))

;;;; SPECIALNI ELEMENTY JAZYKA
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;; pravdivostni hodnoty
(define scm-false ((curry-make-elem 'boolean) #f))
(define scm-true  ((curry-make-elem 'boolean) #t))
(define scm-boolean? (curry-scm-type 'boolean))

;; prazdny seznam
(define the-empty-list ((curry-make-elem 'empty-list) '()))
(define scm-null? (lambda (elem) (equal? elem the-empty-list)))

;; nedefinovana hodnota
(define the-undefined-value ((curry-make-elem 'undefined) '()))
(define scm-undefined? (lambda (elem) (equal? elem the-undefined-value)))

;;;;
;;;;  READER (nacteni vyrazu a prevedeni do interni representace)
;;;;

;; prevedeni vyrazu do interni formy
(define expr->intern
  (lambda (expr)
    (cond ((symbol? expr) (make-symbol expr))
	  ((number? expr) (make-number expr))
	  ((and (boolean? expr) expr) scm-true)
	  ((boolean? expr) scm-false)
	  ((null? expr) the-empty-list)
	  ((pair? expr) (make-pair (expr->intern (car expr))
				   (expr->intern (cdr expr))))
	  ((eof-object? expr) #f)
	  (else (error "; Syntactic error.")))))

;; nacti vstupni vyraz do interni formy
(define scm-read
  (lambda ()
    (expr->intern (read))))

;;;;
;;;;  PRINTER (vytisteni vyrazu v jeho externi reprez. -- pokud je to mozne)
;;;;

;; pouze pouzije display -- vypise syrovou reprezentaci
(define scm-print
  (lambda (elem)
    (display elem)))

;; prevedeni elementu do externi formy
(define intern->expr
  (lambda (expr)
    (cond ((scm-pair? expr) (cons (intern->expr (pair-car expr))
				  (intern->expr (pair-cdr expr))))
	  ((scm-env? expr) "#<environment>")
	  ((scm-primitive? expr) "#<primitive-procedure>")
	  ((scm-user-procedure? expr) "#<user-defined-procedure>")
	  ((scm-specform? expr) "#<special-form>")
	  ((scm-undefined? expr) "#<undefined>")
	  (else (get-data expr)))))

;; o neco ,,chytrejsi printer''
(define scm-print
  (lambda (elem)
    (display (intern->expr elem))))

;;;;
;;;;  EVALUATOR
;;;;

;; map pres elementy tvorici scm-seznam, vysledkem je klasicky seznam
(define map-scm-list->list
  (lambda (f scm-l)
    (convert-list scm-null? pair-car pair-cdr cons '() f scm-l)))

;; preved scm-seznam na klasicky seznam
(define scm-list->list
  (lambda (scm-l)
    (convert-list scm-null? pair-car pair-cdr cons '() (lambda (x) x) scm-l)))

;; preved klasicky seznam na scm-seznam
(define list->scm-list
  (lambda (l)
    (convert-list null? car cdr make-pair the-empty-list (lambda (x) x) l)))

;; vyhodnot vyraz v danem prostredi
(define scm-eval
  (lambda (elem env)

    ;; vyhodnocovani elementu podle jejich typu
    (cond

     ;; symboly se vyhodnocuji na svou aktualni vazbu
     ((scm-symbol? elem)
      (let* ((binding (lookup-env env elem #t #f)))
	(if binding
	    (pair-cdr binding)
	    (error "; EVAL: Symbol not bound"))))

     ;; vyhodnoceni seznamu
     ((scm-pair? elem)

      ;; nejprve vyhodnotime prvni prvek seznamu
      (let* ((first (pair-car elem))
	     (args (pair-cdr elem))
	     (f (scm-eval first env)))

	;; podle prvniho prvku rozhodni o co se jedna
	(cond

	 ;; pokud se jedna o proceduru, vyhodnot argumenty a aplikuj
	 ((scm-procedure? f)
	  (scm-apply f (map-scm-list->list
			(lambda (elem)
			  (scm-eval elem env))
			args)))

	 ;; pokud se jedna o formu, aplikuj s nevyhodnocenymi argumenty
	 ((scm-form? f)
	  (scm-form-apply env f (scm-list->list args)))
	 
	 ;; na prvnim miste stoji nepripustny prvek
	 (error "; EVAL: First element did not eval. to procedure"))))

     ;; vse ostatni (cisla, boolean, ... se vyhodnocuje na sebe sama)
     (else elem))))

;; vytvor tabulku vazeb: formalni argument -- argument
(define make-bindings
  (lambda (formal-args args)
    (cond ((scm-null? formal-args) the-empty-list)
	  ((scm-symbol? formal-args)
	   (make-pair (make-pair formal-args (list->scm-list args))
		      the-empty-list))
	  (else (make-pair
		 (make-pair (pair-car formal-args) (car args))
		 (make-bindings (pair-cdr formal-args) (cdr args)))))))

;; aplikuj proceduru, predka nastav na ``env''
(define scm-env-apply
  (lambda (proc env args)
    (cond ((scm-primitive? proc) (apply (get-data proc) args))
	  ((scm-user-procedure? proc)
	   (scm-eval (procedure-body proc)
		     (make-env env
			       (make-bindings (procedure-arguments proc)
					      args))))
	  (else (error "; APPLY: Expected procedure")))))

;; aplikuj proceduru s lexikalnim predkem
(define scm-apply
  (lambda (proc args)
    (cond ((scm-primitive? proc) (scm-env-apply proc #f args))
	  ((scm-user-procedure? proc)
	   (scm-env-apply proc (procedure-environment proc) args))
	  (else (error "; APPLY: Expected procedure")))))

;; aplikuj spec. formu
(define scm-form-apply
  (lambda (env form args)
    (cond ((scm-specform? form) (apply (get-data form) env args))
	  (else (error "; APPLY: Expected special form")))))

;;;;
;;;;  NOVY REPL
;;;;

;;;;
;;;;  POMOCNE PROCEDURY
;;;;

;; pro obecny typ volani apply sestavi seznam argumentu
(define apply-collect-arguments
  (lambda (args)
    (cond ((null? args) (error "; APPLY: argument missing"))
	  ((and (not (null? args)) (null? (cdr args)))
	   (scm-list->list (car args)))
	  (else (cons (car args) (apply-collect-arguments (cdr args)))))))

;;;;
;;;;  TOPLEVEL ENVIRONMENT
;;;;

;; vytvor prostredi, ktere je nejvys v hierarchii
(define scheme-toplevel-env
  (make-global-env
   `(
     ;; specialni forma IF
     (if . ,(make-specform
	     (lambda (env condition expr . alt-expr)
	       (let ((result (scm-eval condition env)))
		 (if (equal? result scm-false)
		     (if (null? alt-expr)
			 the-undefined-value
			 (scm-eval (car alt-expr) env))
		     (scm-eval expr env))))))

     ;; specialni forma AND
     (and . ,(make-specform
	      (lambda (env . exprs)
		(let and-eval ((exprs exprs))
		  (cond ((null? exprs) scm-true)
			((null? (cdr exprs)) (scm-eval (car exprs) env))
			(else (let ((result (scm-eval (car exprs) env)))
				(if (equal? result scm-false)
				    scm-false
				    (and-eval (cdr exprs))))))))))
     
     ;; specialni forma OR
     (or . ,(make-specform
	     (lambda (env . exprs)
	       (let or-eval ((exprs exprs))
		 (cond ((null? exprs) scm-false)
		       ((null? (cdr exprs)) (scm-eval (car exprs) env))
		       (else (let ((result (scm-eval (car exprs) env)))
			       (if (equal? result scm-false)
				   (or-eval (cdr exprs))
				   result))))))))

     ;; specialni forma LAMBDA (zjednodusena: v tele je jen jeden vyraz)
     (lambda . ,(make-specform
		 (lambda (env args body)
		   (make-procedure env args body))))

     ;; specialni forma the-environment
     (the-environment . ,(make-specform (lambda (env) env)))

     ;; specialni forma quote
     (quote . ,(make-specform (lambda (env elem) elem)))

     ;; specialni forma dyn-apply
     ;; prepisuje vyraz ve tvaru (dyn-apply proc a1 a2 ... (argumenty ...))
     ;; na vyraz (env-apply (the-environment) proc a1 a2 ... (argumenty ...))
     ;; a vyhodnoti jej v aktualnim prostredi
     (dyn-apply . ,(make-specform
		    (lambda (env proc . rest)
		      (scm-eval
		       (make-pair (make-symbol 'env-apply)
				  (make-pair
				   proc
				   (make-pair
				    env
				    (let iter ((ar rest))
				       (if (null? (cdr ar))
					   (make-pair (car ar) the-empty-list)
					   (make-pair (car ar)
						      (iter (cdr ar))))))))
		       env))))

     ;; konstanty
     (pi . ,(make-number (* 4 (atan 1))))

     ;; procedury pro praci s cisly (zakomentovane procedury jsou v R5RS, ale
     ;; nejsou implementovany ve vsech dostupnych interpretech Scheme)
     (* . ,(wrap-primitive *))
     (+ . ,(wrap-primitive +))
     (- . ,(wrap-primitive -))
     (/ . ,(wrap-primitive /))
     (< . ,(wrap-primitive <))
     (<= . ,(wrap-primitive <=))
     (= . ,(wrap-primitive =))
     (> . ,(wrap-primitive >))
     (>= . ,(wrap-primitive >=))
     (abs . ,(wrap-primitive abs))
     (acos . ,(wrap-primitive acos))
;     (angle . ,(wrap-primitive angle))
     (asin . ,(wrap-primitive asin))
     (atan . ,(wrap-primitive atan))
     (ceiling . ,(wrap-primitive ceiling))
     (cos . ,(wrap-primitive cos))
;     (denominator . ,(wrap-primitive denominator))
     (even? . ,(wrap-primitive even?))
     (exact->inexact . ,(wrap-primitive exact->inexact))
     (exact? . ,(wrap-primitive exact?))
     (exp . ,(wrap-primitive exp))
     (expt . ,(wrap-primitive expt))
     (floor . ,(wrap-primitive floor))
     (gcd . ,(wrap-primitive gcd))
;     (imag-part . ,(wrap-primitive imag-part))
     (inexact->exact . ,(wrap-primitive inexact->exact))
     (inexact? . ,(wrap-primitive inexact?))
     (lcm . ,(wrap-primitive lcm))
     (log . ,(wrap-primitive log))
;     (magnitude . ,(wrap-primitive magnitude))
;     (make-polar . ,(wrap-primitive make-polar))
;     (make-rectangular . ,(wrap-primitive make-rectangular))
     (max . ,(wrap-primitive max))
     (min . ,(wrap-primitive min))
     (modulo . ,(wrap-primitive modulo))
     (negative? . ,(wrap-primitive negative?))
;     (numerator . ,(wrap-primitive numerator))
     (odd? . ,(wrap-primitive odd?))
     (positive? . ,(wrap-primitive positive?))
     (quotient . ,(wrap-primitive quotient))
;     (rationalize . ,(wrap-primitive rationalize))
;     (real-part . ,(wrap-primitive real-part))
     (remainder . ,(wrap-primitive remainder))
     (round . ,(wrap-primitive round))
     (sin . ,(wrap-primitive sin))
     (sqrt . ,(wrap-primitive sqrt))
     (tan . ,(wrap-primitive tan))
     (truncate . ,(wrap-primitive truncate))
     (zero? . ,(wrap-primitive zero?))

     ;; prace s pary
     (null? . ,(make-primitive
		(lambda (l)
		  (expr->intern (equal? l the-empty-list)))))
     (cons . ,(make-primitive make-pair))
;       (pair? . ,(make-primitive scm-pair?))
     (car . ,(make-primitive pair-car))
     (cdr . ,(make-primitive pair-cdr))

     ;; negace
     (not . ,(make-primitive
	      (lambda (elem)
		(if (equal? elem scm-false)
		    scm-true
		    scm-false))))
     
     ;; sektoru uzivatelskych procedur
     (procedure-environment . ,(make-primitive procedure-environment))
     (procedure-arguments . ,(make-primitive procedure-arguments))
     (procedure-body . ,(make-primitive procedure-body))

     ;; procedura environment-parent (vrat lexikalniho predka daneho prostredi)
     (environment-parent . ,(make-primitive get-pred))

     ;; procedura environment->list (vrat tabulku s vazbami v danem prosredi)
     (environment->list . ,(make-primitive
			    (lambda (elem)
			      (if (equal? elem scm-false)
				  scm-false
				  (get-table elem)))))

     ;; procedura eval (dvou argumentu, druhy je vzdy povinny)
     (eval . ,(make-primitive
	       (lambda (elem env)
		 (scm-eval elem env))))

     ;; procedura apply
     (apply . ,(make-primitive
		(lambda (proc . rest)
		  (scm-apply proc (apply-collect-arguments rest)))))

     ;; procedura apply, ktere lze predat prostredi predka
     (env-apply . ,(make-primitive
		    (lambda (proc env . rest)
		      (scm-env-apply proc
				     env
				     (apply-collect-arguments rest)))))
     )))

;; prostredi zabudovanych uzivatelskych definic, jelikoz nas jazyk nedisponuje
;; specialni formou ,,define'', musime v tomto prostredi zavadet rekurzivni
;; procedury pomoci Y-kombinatoru
(define scheme-midlevel-env
  (make-env
   scheme-toplevel-env
   (assoc->env
    `(
      ;; uzivatelska procedura sgn (signum)
      (sgn . ,(make-procedure
	       scheme-toplevel-env		; lexikalni predek
	       (expr->intern '(x))		; seznam argumentu
	       (expr->intern '(if (= x 0)	; vlastni telo
				  0
				  (if (> x 0)
				      1
				      -1)))))

      ;; uzivatelska procedury length (pomoci Y-kombinatoru)
      (length .
	      ,(make-procedure
		scheme-toplevel-env
		(expr->intern '(l))
		(expr->intern
		 '((lambda (y)
		     (y y l))
		   (lambda (length l)
		     (if (null? l)
			 0
			 (+ 1 (length length (cdr l)))))))))
     
      ;; uzivatelska procedura map (pomoci Y-kombinatoru)
      (map .
	   ,(make-procedure
	     scheme-toplevel-env
	     (expr->intern '(f l))
	     (expr->intern
	      '((lambda (y)
		  (y y l))
		(lambda (map l)
		  (if (null? l)
		      ()
		      (cons (f (car l)) (map map (cdr l)))))))))
      ))))

;; vrat nazev typu jako retezec s prvnim pismenem velkym
(define type-name
  (lambda (elem)
    (let ((strl (string->list (symbol->string (get-type-tag elem)))))
      (list->string (cons (char-upcase (car strl)) (cdr strl))))))

;; cyklus REPL
(define scm-repl
  (lambda ()
    (let ((glob-env (make-env scheme-midlevel-env the-empty-list)))
      (let loop ()
	(display "]=> ")
	(let ((elem (scm-read)))
	  (if (not elem)
	      'bye-bye
	      (let ((result (scm-eval elem glob-env)))
		(newline)
		(display (string-append "; " (type-name result)	": "))
		(scm-print result)
		(newline)
		(newline)
		(loop))))))))

;; spust REPL
(scm-repl)

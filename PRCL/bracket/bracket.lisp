(defvar *bracket-packages*)
(setf *bracket-packages* "/home/frostyx/docs/skola/upol/PRCL/bracket-packages/")

(defvar *posix-argv*)
(setf *posix-argv* '(bracket show "foo"))

;;; Bracket functions
(defun bracket-show (pkg-name)
  "Return informations about package specified by its name"
  (load (format nil "~a~a.lisp" *bracket-packages* pkg-name))
  *pkg*)

;;; Renderers
(defun render-show-package ()
  (print (bracket-show (caddr *posix-argv*))))

(defun render-help ()
  (print "Usage:")
  (print "  bracket install <pkgname>")
  (print "  bracket remove <pkgname>")
  (print "")
  (print "  bracket show <pkgname>")
  (print "  bracket installed")
  (print "  bracket available")
  (print "")
  (print "  bracket log")
)

;;; Router
(let ((renderer (case (cadr *posix-argv*)
                  ('show     #'render-show-package)
                  ('help     #'render-help)
                  (otherwise #'render-help))))
  (funcall renderer))

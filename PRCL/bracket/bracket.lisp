;;; TODO - Use real argv instead
(defvar *posix-argv*)
(setf *posix-argv* '(bracket show "foo"))

(defvar *bracket-packages*)
(setf *bracket-packages* "/home/frostyx/docs/skola/upol/PRCL/bracket-packages/")

(defvar *prefix*)
(setf *prefix* "/home/frostyx/docs/skola/upol/PRCL/prefix")


;;; Bracket functions
(defun bracket-show (pkg-name)
  "Return informations about package specified by its name"
  (let ((spec (format nil "~a~a.lisp" *bracket-packages* pkg-name)))
    (if (not (probe-file spec))
        nil
      (progn (load spec) *pkg*))))


;;; Renderers
(defun render-show-package ()
  (let* ((pkg-name (caddr *posix-argv*))
         (pkg (bracket-show pkg-name)))

    (if (not pkg)
        (format nil "Package ~a not found" pkg-name)
      (progn
        (format t "*  ~a~%" pkg-name)
        (format t "      Version: ~a~%" (getf pkg :version))
        (format t "      Description: ~a~%" (getf pkg :description))
        (format t "      Homepage: ~a~%" (getf pkg :homepage))
        (format t "      License: ~a~%" (getf pkg :license))))))

(defun render-help ()
  (print "Usage:")
  (print "  bracket install <pkgname>")
  (print "  bracket remove <pkgname>")
  (print "")
  (print "  bracket show <pkgname>")
  (print "  bracket installed")
  (print "  bracket available")
  (print "")
  (print "  bracket log"))


;;; Router
(let ((renderer (case (cadr *posix-argv*)
                  ('show     #'render-show-package)
                  ('help     #'render-help)
                  (otherwise #'render-help))))
  (funcall renderer))

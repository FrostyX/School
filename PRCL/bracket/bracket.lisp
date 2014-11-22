;;; TODO - Use real argv instead
(defvar *posix-argv*)
(setf *posix-argv* 
      ;'(bracket show "foo")
      '(bracket available)
)

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

(defun bracket-available ()
  "Return a names of available packages"
  (remove-duplicates 
   (mapcar (lambda (file) (pathname-name file)) 
           (directory *bracket-packages*)) 
   :test #'equal))


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

(defun render-available ()
  (format t "Available packages:~%")
  (dolist (pkg-name (bracket-available))
    (let ((pkg (bracket-show pkg-name)))
      (format t "  ~a  -  ~a~%" pkg-name (getf pkg :description)))))

(defun render-help ()
  (format t "Usage:~%")
  (format t "  bracket install <pkgname>     Install specified package to the system~%")
  (format t "  bracket remove <pkgname>      Remove specified package from the system~%~%")
  (format t "  bracket show <pkgname>        Show informations about the package~%")
  (format t "  bracket installed             List all installed packages~%")
  (format t "  bracket available             List all available packages~%~%")
  (format t "  bracket log                   Show bracket log~%"))


;;; Router
(let ((renderer (case (cadr *posix-argv*)
                  ('show       #'render-show-package)
                  ('available  #'render-available)
                  ('help       #'render-help)
                  (otherwise   #'render-help))))
  (funcall renderer))

;;; TODO - Use real argv instead
(defvar *posix-argv*)
(setf *posix-argv*
      '(bracket help)
      ;'(bracket show "foo")
      ;'(bracket available)
      ;'(bracket installed)
)

(defvar *bracket-packages*)
(setf *bracket-packages* "/home/frostyx/docs/skola/upol/PRCL/bracket-packages/")

(defvar *installed*)
(setf *installed* "/home/frostyx/docs/skola/upol/PRCL/bracket/installed")

(defvar *prefix*)
(setf *prefix* "/home/frostyx/docs/skola/upol/PRCL/prefix")


;;; Bracket functions
(defun bracket-show (pkg-name)
  "Return informations about package specified by its name"
  (let ((spec (format nil "~a~a.lisp" *bracket-packages* pkg-name)))
    (if (not (probe-file spec))
        nil
      (progn (setf *pkg* '()) (load spec :verbose nil) *pkg*))))

(defun bracket-available ()
  "Return a names of available packages"
  (remove-duplicates 
   (mapcar (lambda (file) (pathname-name file)) 
           (directory *bracket-packages*)) 
   :test #'equal))

(defun bracket-installed ()
  (labels
   ((parse (file)
     "Load list of installed packages and their versions"
     "Format: ((foo 1.0) (bar 2.1) (baz 0.2))"
     "Inspired by https://groups.google.com/d/msg/comp.lang.lisp/dh_oJ8jJ6l4/CHKLqTJHA0oJ"
     (with-open-file (in file)
       (loop for line = (read-line in nil nil)
             :while line
             :collect (read-from-string (concatenate 'string "(" line ")"))))))

  (parse *installed*)))


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
  (render-list-oneline-packages (bracket-available)))

(defun render-installed ()
  (format t "Installed packages:~%")
  (render-list-oneline-packages (mapcar #'car (bracket-installed))))

(defun render-list-oneline-packages (packages)
  (dolist (pkg-name packages)
    (let ((pkg (bracket-show pkg-name)))
      (format t "  ~a  -  ~a~%" pkg-name (getf pkg :description)))))

(defun render-help ()
  (format t "Bracket~%")
  (format t "  The source based package manager written in Lisp~%~%")
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
                  ('installed  #'render-installed)
                  ('help       #'render-help)
                  (otherwise   #'render-help))))
  (funcall renderer))

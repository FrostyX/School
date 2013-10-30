(in-package "CL-USER")

(defsystem micro-graphics ()
  :members ("package" "micro-graphics")
  :rules ((:compile :all 
           (:requires (:load :previous)))))

(compile-system 'micro-graphics :load t)
(in-package "CL-USER")

(defpackage "MICRO-GRAPHICS" 
  (:nicknames "MG")
  (:export "DISPLAY-WINDOW" "CLOSE-WINDOW" 
           "GET-CALLBACK" "SET-CALLBACK"
           "GET-PARAM" "SET-PARAM" 
           "INVALIDATE"
           "CLEAR" "DRAW-POLYGON" "DRAW-CIRCLE" 
           "GET-STRING-EXTENT" "DRAW-STRING" 
           "GET-IMAGE-EXTENT" "DRAW-IMAGE"
           "MAKE-OFFSCREEN-WINDOW" "DRAW-WINDOW"))
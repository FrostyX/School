(list
 :name "foo"
 :version 1.0
 :description "This is the foo package"
 :homepage "http://foo.example.com"
 :license "GPLv2"

 :dependencies (list "bar" "baz")

 :files (list
         "/etc/foo/foo.conf"
         "/usr/bin/foo"
         "/usr/share/foo/README.md")

 :prepare (lambda ()
            nil)

 :install (lambda ()
            nil)

 :remove (lambda ()
           nil)

)

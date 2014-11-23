(list

             :name "baz"
             :version 0.2
             :description "This is the baz package"
             :homepage "http://baz.example.com"
             :license "GPLv3"

             :dependencies '()

             :files (list
                     "/etc/baz/baz.cnf"
                     "/usr/bin/baz")

             :prepare (lambda ()
                        nil)

             :install (lambda ()
                        nil)

             :remove (lambda ()
                       nil)

)
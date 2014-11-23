(list

             :name "bar"
             :version 1.3
             :description "This is the bar package"
             :homepage "http://bar.example.com"
             :license "GPLv2"

             :dependencies '()

             :files (list
                     "/etc/bar/config"
                     "/usr/bin/bar")

             :prepare (lambda ()
                        nil)

             :install (lambda ()
                        nil)

             :remove (lambda ()
                       nil)

)

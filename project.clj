(defproject comely "0.1.0-SNAPSHOT"
  :description "A web application for learning the guitar"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [ring/ring-core "1.3.1"]
                 [ring/ring-jetty-adapter "1.3.1"]
                 [com.stuartsierra/component "0.2.2"]
                 [compojure "1.1.9"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.8.11"]]
  :ring {:handler comely.handler/app}
  :main ^:skip-aot comely.system
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

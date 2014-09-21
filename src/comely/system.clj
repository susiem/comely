(ns comely.system
    (:gen-class)
    (:require [com.stuartsierra.component :as component]
              [comely.handler :refer [new-web-server]])
    )

(defn system []
      (component/system-map
        :webserver (component/using (new-web-server) [])))

(defn -main
      [& args]
      (component/start-system (system)))


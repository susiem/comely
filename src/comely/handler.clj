(ns comely.handler
    (:require [ring.adapter.jetty :refer [run-jetty]]
              [ring.middleware.resource :refer [wrap-resource]]
              [ring.middleware.file :refer [wrap-file]]
              [comely.view :refer :all]
              [compojure.core :refer :all]
              [com.stuartsierra.component :as component]
              [compojure.route :as route]
              [compojure.handler :as handler]
              [hiccup.middleware :refer [wrap-base-url]]))

(defroutes main-routes
           (GET "/" [] (index-page))
           (route/resources "/")
           (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)
      (wrap-resource "public")
      ))

(defrecord WebServer [server]
           component/Lifecycle
           (start [this]
             (if server
               this
               (assoc this :server (run-jetty app {:port 3000}))))
           (stop [this]
             (if (not server)
               this
               (do (.stop server)
                   (assoc this :server nil)))))

(defn new-web-server []
      (map->WebServer {}))

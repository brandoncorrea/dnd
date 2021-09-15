(ns dnd-server.core
  (:require 
    [org.httpkit.server :as server]
    [compojure.core :refer :all]
    [compojure.route :as route]
    [ring.middleware.defaults :refer :all]
    [clojure.pprint :as pp]
    [clojure.string :as str]
    [clojure.data.json :as json]
    [dnd-server.routes.example :refer :all]
    [dnd-server.routes.hello :refer :all]
    [dnd-server.routes.home :refer :all]
    )
  (:gen-class))

(defroutes app-routes
  (GET "/" [] home-route)
  (GET "/request" [] example-route)
  (GET "/hello" [] hello-route)
  (route/not-found "Error, page not found!"))

(defn -main
  [& args]
  (let [port (Integer/parseInt (or (System/getenv "PORT") "3000"))]
    ; Run the server with Ring.defaults middleware
    (server/run-server (wrap-defaults #'app-routes site-defaults) {:port port})
    (println (str "Running webserver at http:/127.0.0.1:" port "/"))))

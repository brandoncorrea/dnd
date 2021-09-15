(ns dnd-server.routes.hello)

(defn hello-route
  [request]
  {
    :status 200
    :headers { "Content-Type" "text/html" }
    :body (str "Hello, " (get-in request [:params :name]))
  })


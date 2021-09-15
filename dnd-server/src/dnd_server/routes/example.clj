(ns dnd-server.routes.example)

(defn example-route
  [request]
  {
    :status 200
    :headers { "Content-Type" "text/html" }
    :body (str "Request Object: " request)
  })
(ns dnd-server.routes.home)

(defn home-route
  [request]
  {
    :status 200
    :headers { "Content-Type" "text/html" }
    :body "<h1>Home</h1>"
  })
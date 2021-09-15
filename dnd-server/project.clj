(defproject dnd-server "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main dnd-server.core
  :dependencies [
    [org.clojure/clojure "1.8.0"]
    [compojure "1.6.1"]
    [http-kit "2.3.0"]
    [ring/ring-defaults "0.3.2"]
    [org.clojure/data.json "0.2.6"]]
  :profiles {:dev {:dependencies [[speclj "3.3.2"]]}}
  :plugins [[speclj "3.3.2"]]
  :test-paths ["spec"])

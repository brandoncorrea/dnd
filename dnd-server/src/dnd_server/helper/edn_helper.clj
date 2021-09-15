(ns dnd-server.helper.edn-helper
  (:require 
    [clojure.edn :as edn]))

(defn edn->pretty
  "Converts an object to a 'pretty' EDN string"
  [data]
  (let [out (java.io.StringWriter.)]
    (clojure.pprint/pprint data out)
    (.toString out)))

(defn read-edn
  "Reads EDN data from a file"
  ; Opt for a default value
  ([filename val]
    (or (read-edn) filename val))
  ; Returns edn data from the file or nil
  ([filename]
  (try
    (edn/read-string (slurp filename))
  (catch Exception e 
    nil))))

(defn write-edn
  "Writes an object to an EDN file"
  [filename data]
  (spit filename (edn->pretty data)))

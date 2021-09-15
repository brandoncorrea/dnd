(ns dnd-server.data.characters
  (:require [dnd-server.helper.edn-helper :refer :all]))
(use 'clojure.string)

; The file name of the repository
(def filename "characters.edn")

(defn new-id
  "Generate a new id based on existing ids"
  [store]
  (if (empty? store)
    1
    ((comp inc #(apply max %) keys) store)))

(defn initialize
  "Initializes a character with required properties"
  [character]
  (assoc character 
    :attributes (merge {
      :intelligence 0
      :dexterity 0
      :strength 0
      :constitution 0
      :charisma 0
      :wisdom 0
    } (:attributes character))))

(defn is-valid-character
  "Returns true if the hashmap has valid character data"
  [character]
  (let [
    name (:name character)
    attributes (:attributes character)]
    (and
      (or (not name) (< 0 (count (trim name))))
      (or (not attributes) (not (some neg? (vals attributes)))))))

(defn write-character
  ([id character]
    (write-character id character (read-edn filename)))
  ([id character store]
    (write-edn filename (assoc store id character))))

(defn create
  "Create a new character"
  [character]
    (if (and (:name character) (is-valid-character character))
      (let [
        store (read-edn filename)
        id (new-id store)
        created (initialize character)]
      (write-character id created store)
      (assoc created :id id))))

(defn find-by-id
  "Returns a character by its id"
  [id]
  (if-let [character (get (read-edn filename) id)]
    (assoc character :id id)))

(defn update
  "Updates existing characters"
  [character]
  (if (is-valid-character character)
    (if-let [found (find-by-id (:id character))]
      (let [merged (merge found character)]
        (write-character (:id character) (dissoc merged :id))
        merged
        character))))
      
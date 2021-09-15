(ns dnd-server.data.characters_spec
  (:require
    [speclj.core :refer :all]
    [dnd-server.data.characters :as characters]))

(def required-attributes [
  :intelligence
  :dexterity
  :strength
  :wisdom
  :charisma
  :constitution
])

(describe "Character Repository Tests"
  (describe "Create Character Tests"
    (it "Create two characters"
      (doseq [name ["First Guy" "Second Guy"]]
        (let [created (characters/create { :name name })]
          (should= 
            created
            (characters/find-by-id (:id created)))
          (should=
            {
              :intelligence 0
              :dexterity 0
              :strength 0
              :wisdom 0
              :charisma 0
              :constitution 0
            }
            (:attributes created)))))
    
    (it "Returns nil when name is missing"
      (should= nil (characters/create { })))
    (it "Returns nil when name is whitespace"
      (should= nil (characters/create { :name "  " })))
    (it "Returns nil when an attribute is negative"
      (doseq [attribute required-attributes]
        (should= 
          nil 
          (characters/create { 
            :name "Bob" 
            :attributes { attribute -1 }}))))
  )

  (describe "Find by id tests"
    (it "Returns character"
    (let [
      character {
        :name "Billy Bob"
        :attributes {
          :intelligence 1
          :dexterity 3
          :strength 2
          :wisdom 5
          :charisma 7
          :constitution 9
        }
      }
      created (characters/create character)]
      (should=
        (assoc character :id (:id created))
        (characters/find-by-id (get created :id)))))
  
    (it "Returns nil when id is nil"
      (should= nil (characters/find-by-id nil)))
    (it "Returns nil when id not found"
      (should= nil (characters/find-by-id -1)))
  )

  (describe "Update tests"
    (it "Updates existing characters"
      (let [
        created (characters/create {
          :name "Updating this dude"
          :attributes {
            :intelligence 1
            :dexterity 2
            :strength 3
            :wisdom 4
            :charisma 5
            :constitution 6
          }})
        update-info {
          :id (:id created)
          :name "Some other name"
          :attributes {
            :intelligence 11
            :dexterity 12
            :strength 13
            :wisdom 14
            :charisma 15
            :constitution 16
          }}]
          (should= 
            (characters/update update-info)
            (characters/find-by-id (:id created)))
          ))

    (it "Returns nil when attributes are negative"
      (let [
        created (characters/create { :name "Nil Updates" })
        id (:id created)]
        (doseq [attribute required-attributes]
          (should= nil  
            (characters/update { 
              :id id 
              :attributes { attribute -1 } 
            })))))

    (it "Returns nil when name is whitespace"
      (let [created (characters/create { :name "Name Nil Updates" })]
        (should= nil 
          (characters/update { 
            :id (:id created)
            :name "  "
          }))))

    (it "Returns nil when id not found"
      (should= nil (characters/update { :id -1 })))
    (it "Returns nil when id is nil"
      (should= nil (characters/update { :id nil })))
  )
)
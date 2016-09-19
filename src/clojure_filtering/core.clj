(ns clojure-filtering.core
  (:require [clojure.string :as str] [clojure.data.json :as json])
  (:gen-class))

(defn -main []
  (println "What category would you like to filter by...")
  (let
    [purchases (slurp "purchases.csv")
     purchases (str/split-lines purchases)
     purchases (map (fn [line]
                      (str/split line #","))
                    purchases)
     header (first purchases)
     purchases (rest purchases)
     purchases (map (fn [line]
                      (zipmap header line))
                    purchases)
     category (read-line)
     purchases (filter (fn [line]
                         (= (get line "category") category))
                       purchases)
     JSONfile (json/write-str purchases)]
     (spit "new-purchases.json" JSONfile))
  )

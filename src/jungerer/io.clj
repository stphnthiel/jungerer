(ns jungerer.io
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [jungerer.graph :as g])
  (:import edu.uci.ics.jung.io.GraphMLWriter))

(defn load-pairs!
  [f graph]
  (with-open [r (io/reader f)]
    (doseq [edge (->> (line-seq r)
                      (map #(string/split % #"\t")))]
      (g/add-edge! graph edge)))
  nil)

(defn save-as-pairs
  [f graph]
  (with-open [w (io/writer f)]
    (doseq [[a b] (g/edges graph)]
      (.write w (str a \tab b \newline)))))

(defn save-as-graphml
  [f graph]
  (.save (GraphMLWriter.) graph (io/writer f)))

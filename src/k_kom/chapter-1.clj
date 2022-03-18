(ns k-kom.chapter-1
  (:require [clojure.pprint :as pprint]))

(defn v-length [v]
  (Math/sqrt (reduce #(+ %1 (Math/pow %2 2)) 0 v)))

(defn dot-product [v1 v2]
  (if (= (count v1) (count v2))
    (reduce + (map * v1 v2))
    (throw
     (Exception.
      "could not calculate dot product from vectors with different counts."))))

(defn cosine-similarity
  [doc query]
  (/ (dot-product doc query)
     (* (v-length doc) (v-length query))))

;; EXERCISE 1:
;; Calculate cosine similarity between each pair of vectors: A=[4,3], B=[5,5] and C=[1,10]. Which ones are closest (most similar) to each other?

(let [a [4 3] b [5 5] c [1 10]]
  [[a b] (cosine-similarity a b)
   [a c] (cosine-similarity a c)
   [b c] (cosine-similarity b c)])

;; "What temperature does water boil at?"
;; content words: #{"temperature" "water" "boil"}
;; function words: #{"what" "do" "at"}

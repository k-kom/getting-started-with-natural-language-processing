(ns k-kom.chapter-1)

(defn v-length [v]
  (Math/sqrt (reduce #(+ %1 (Math/pow %2 2)) 0 v)))

(defn dot-product [v1 v2]
  (if (= (count v1) (count v2))
    (reduce + (map * v1 v2))
    (throw
     (Exception.
      "could not calculate dot product from vectors with different counts."))))

(defn cosine-similarity
  [query doc]
  (/ (dot-product query doc)
     (* (v-length query) (v-length doc))))

;; EXERCISE 1:
;; Calculate cosine similarity between each pair of vectors: A=[4,3], B=[5,5] and C=[1,10]. Which ones are closest (most similar) to each other?

(comment
  (let [a [4 3] b [5 5] c [1 10]]
    [[a b] (cosine-similarity a b)
     [a c] (cosine-similarity a c)
     [b c] (cosine-similarity b c)])
  #_[[[4 3] [5 5]]
     0.9899494936611665
     [[4 3] [1 10]]
     0.6766252893427926
     [[5 5] [1 10]]
     0.773957299203321])

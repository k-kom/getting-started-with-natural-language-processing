^{:nextjournal.clerk/visibility #{:hide-ns :hide}}
(ns k-kom.chapter-3)

;; ## Information retrieval
;; difference between computers and us
;; - computers don't have a notion of what a word is
;; - similar words (management manager managerial...)
;; - focus to important words
;; - relevance

;; we treat documents and queries equally in terms of information retrieval

;; ### Boolean search algorithm
;; steps
;; 1. tokenize query and documents
;; 2. label documents true or false (check if a document contains a word of the query)

;; word for word comparison returns too many unrelated documents

;; ### Stopwords removal

;; - stopwrods = less meaningful words
;; - nltk has a stopwords list in `nltk.corpus.stopwords`

;; ### Morphological processing

;; - try to match "systems" to "system"
;; - morphology contains modality?
;; - map morphological form(?) to base form (systems->system, sung->sing)

;; Exercise 4
;; "A computer program have be write and use which simulate the several-year operation of a information system and compute estimate of the cost as well as the amount of equipment and personell require during that time period"

;; - stemming extract shared leading sequence from related words
;;   - this leads to a non word stem like "continu"
;; - nltk has LancasterStemmer and other stemming tools
;; - applications of different different rules sometimes produces unexpected results
;;   - [organ organic organizing organize] becomes [org org org org]

;; ## Information weighing
;; How much are the documents relevant to an query?

;; - boolean search checks if a word is present in a document or not
;; - _term frequency_ counts frequency of occurence like below
;;   - more frequent = more relevant

{:query {:much 1 :information 1 :retrieval 1 :dissemination 1 :system 1 :cost 1}
 :doc-x {:much 0 :information 2 :retrieval 1 :dissemination 2 :system 3 :cost 1}
 :doc-y {:much 0 :information 1 :retrieval 1 :dissemination 1 :system 2 :cost 0}}

;; ### Weighing words with inverse document frequency

;; - when searching documents about information science, `"information"` or `"system"` may occur more frequently than other words
;; - treating frequent words (`"information"`, `"system"`) less valuable serves higher relevance calculation quality
;; - inverse document frequency (higher value means more important)
;;   - documents contains `"inform"`:   0.45, idf("inform")   = 1/0.45 ~ 2.22
;;   - documents contains `"dissemin"`: 0.05, idf("dissemin") = 1/0.05 ~ 20

(defn idf [m n]
  ;; log10 tones down the differences in absolute counts
  (Math/log10 (/ n
                 ;; inc avoid divided by 0
                 (inc m))))

;; Exercise 5.
(let [n 1460 system 531 us 800 retriev 287 cost 137]
  [(idf n system) (idf n us) (idf n retriev) (idf n cost)])

;; ## Practical use of the search algorithm

;; copied and pasted from ch-1
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


;; ### Evaluation of the reusults
;; - precision@k
;; - mean reciprocal rank

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

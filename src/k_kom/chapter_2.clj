(ns kkom.chapter-2
  (:require [clojure.string :as string])
  (:import java.io.FileInputStream
           [opennlp.tools.tokenize TokenizerModel TokenizerME]))

;; steps
;; - define classes
;;   - which data represents spam class or ham class
;; - split into words
;; - extract features
;; - train classifier
;; - test & evaluate

;; Tokenize text to treat it as a feature

;; Exercise 2
;; split by whitespaces.

(def sentence
  "Define which data represents \"ham\" class and which data represents \"spam\" class for the machine-learning algorithm.")

(defn split-string
  [text punctuations]
  (loop [text text current [] words []]
    (if-let [s (first text)]
      (cond
        (= \space s)
        (recur (rest text)
               []
               (if (empty? current)
                 words
                 (conj words (string/join current))))

        (punctuations s)
        (recur (rest text)
               []
               (if (empty? current)
                 (conj words (str s))
                 (conj words (string/join current) (str s))))

        :else
        (recur (rest text)
               (conj current s)
               words))
      (if (empty? current) words (conj words (string/join current))))))

;; Exercise 3
;; “What's the best way to split a sentence into words?”
;; -> ["What" "'s" "the" ... "?"]
;; tokenizer just split a text into tokens ("'s" does not become "is")

;; Exercise 4
;; 1. 3/5
;; 2. slightly better than random guess?
;; 3.

;; accuracy and distribution matter (baseline)

;; tokenize by OpenNLP:
;;   - https://opennlp.apache.org/docs/1.9.4/manual/opennlp.html#tools.tokenizer
;;   - https://opennlp.apache.org/docs/1.9.4/apidocs/opennlp-tools/index.html
(defn tokenize
  [^String text]
  (with-open [model-in (FileInputStream. "./en-token.bin")]
    (let [model (TokenizerModel. model-in)
          tokenizer (TokenizerME. model)]
      (.tokenize tokenizer (.toLowerCase text)))))

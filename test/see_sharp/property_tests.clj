(ns see-sharp.property-tests
  (:require
   [clojure.test :as test]
   [clojure.test.check :as chk]
   [clojure.test.check.clojure-test :as clj-test]
   [clojure.test.check.generators :as gen]
   [clojure.test.check.properties :as prop]
   [see-sharp.core :refer [fselect-keys]]))


(def n-checks 1000)


(def gen-keyseqs
  (gen/let [m (gen/map gen/small-integer #_gen/any-printable-equatable
                       gen/large-integer)
            sub-seq (if (not-empty m)
                      (gen/vector-distinct (gen/elements (keys m)))
                      (gen/vector gen/any 0))]
    {:sub-seq sub-seq
     :m m}))


;;;; input/output relationships


;; return hashmap's keys and values always in the original


(comment ;; demonstration
  (let [key-seq [:a :c :e]]
    (every? (set key-seq) (keys (select-keys {:a 11 :b 22 :c 33 :d 44 :e 55} key-seq)))) ;; true
  )


(def keys-in-original?
  (prop/for-all
   [info gen-keyseqs]
   (let [{m :m
          sub-seq :sub-seq} info]
     (every? (set (keys m)) (keys (fselect-keys m sub-seq))))))


#_(chk/quick-check 1000 keys-in-original?)


(clj-test/defspec
  test-keys-in-original?
  n-checks
  keys-in-original?)


;; returned hashmap's keys are from key-sequence


(comment ;; demonstration
  (let [key-seq [:a :c :e]]
    (every? (set key-seq) (keys (select-keys {:a 11 :b 22 :c 33 :d 44 :e 55} key-seq)))) ;; true
  )


(def keys-in-keyseq?
  (prop/for-all
   [info gen-keyseqs]
   (let [{m :m
          sub-seq :sub-seq} info]
     (every? (set sub-seq) (keys (fselect-keys m sub-seq))))))


#_(chk/quick-check 1000 keys-in-keyseq?)


(clj-test/defspec
  test-keys-in-keyseq?
  n-checks
  keys-in-keyseq?)


;;;; oracle tests: compare to core `select-keys`


(comment ;; demonstration
  (let [m {:a 11 :b 22 :c 33 :d 44 :e 55}
        key-seq [:a :c :e]]
    (= (select-keys m key-seq)
       (fselect-keys m key-seq))) ;; true
  )


(def matches-core-implementation?
  (prop/for-all
   [info gen-keyseqs]
   (let [{m :m
          sub-seq :sub-seq} info]
     (= (select-keys m sub-seq)
        (fselect-keys m sub-seq)))))


#_(chk/quick-check 1000 matches-core-implementation?)


(clj-test/defspec
  test-matches-core-implementation?
  n-checks
  matches-core-implementation?)


#_(test/run-tests)


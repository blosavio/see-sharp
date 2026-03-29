(ns see-sharp.performance.select-keys
  (:require
   [clojure.math :as math]
   [clojure.test :refer [are
                         deftest
                         is
                         run-test
                         run-tests
                         testing]]
   [fastester.define :refer [defbench]]
   [fastester.display :refer [generate-documents]]
   [fastester.measure :refer [range-pow-10
                              run-benchmarks
                              run-one-defined-benchmark]]
   [fastester.options :refer [project-version-lein]]
   [see-sharp.select-keys-variants :refer :all]))


(def small-maps-max 16)
(def large-maps-max 6)


(defn rand-map
  {:UUIDv4 #uuid "bfdff217-b347-4ff6-8252-6bbd4f1f842c"}
  [n offset]
  (zipmap  (range offset (+ n offset)) (repeatedly n #(rand 99))))


(def small-maps
  (reduce
   (fn [m x] (assoc m x {:map (rand-map x 0)
                         :all-keys (into [] (range x))
                         :half-keys (into [] (range (math/round (* x 0.5))
                                                    (math/round (* x 1.5))))
                         :none-keys (into [] (range x (* x 2)))}))
   {}
   (range (inc small-maps-max))))


(def large-maps
  (reduce
   (fn [m x] (assoc m x {:map (rand-map x 0)
                         :all-keys (into [] (range x))
                         :half-keys (into [] (range (math/round (* x 0.5))
                                                    (math/round (* x 1.5))))
                         :none-keys (into [] (range x (* x 2)))}))
   {}
   (range-pow-10 large-maps-max)))


(def select-keys-tactics
  {"select-keys" select-keys
   "fselect-keys-1" fselect-keys-1
   "fselect-keys-2" fselect-keys-2
   "fselect-keys-3" fselect-keys-3
   "fselect-keys-4" fselect-keys-4
   "fselect-keys-5" fselect-keys-5
   "fselect-keys-6" fselect-keys-6})


(defn select-keys-tactic
  {:UUIDv4 #uuid "8c7e4047-45d3-4f05-8131-ce791598c671"}
  []
  (-> (project-version-lein)
      select-keys-tactics))


(defbench
  select-all-keys-small
  "Measure `select-keys` variants, contains all keys"
  (fn [n] ((select-keys-tactic)
           (get-in small-maps [n :map])
           (get-in small-maps [n :all-keys])))
  (range (inc small-maps-max)))


(defbench
  select-all-keys-large
  "Measure `select-keys` variants, contains all keys"
  (fn [n] ((select-keys-tactic)
           (get-in large-maps [n :map])
           (get-in large-maps [n :all-keys])))
  (range-pow-10 large-maps-max))


(defbench
  select-half-keys-small
  "Measure `select-keys` variants, contains half keys"
  (fn [n] ((select-keys-tactic)
           (get-in small-maps [n :map])
           (get-in small-maps [n :half-keys])))
  (range (inc small-maps-max)))


(defbench
  select-half-keys-large
  "Measure `select-keys` variants, contains half keys"
  (fn [n] ((select-keys-tactic)
           (get-in large-maps [n :map])
           (get-in large-maps [n :half-keys])))
  (range-pow-10 large-maps-max))


(defbench
  select-none-keys-small
  "Measure `select-keys` variants, contains no keys"
  (fn [n] ((select-keys-tactic)
           (get-in small-maps [n :map])
           (get-in small-maps [n :none-keys])))
  (range (inc small-maps-max)))


(defbench
  select-none-keys-large
  "Measure `select-keys` variants, contains no keys"
  (fn [n] ((select-keys-tactic)
           (get-in large-maps [n :map])
           (get-in large-maps [n :none-keys])))
  (range-pow-10 large-maps-max))


(comment
  #_(def options-filename "resources/select_keys_options.edn")
  #_(run-one-defined-benchmark select-none-keys-small :lightning)
  #_(run-benchmarks options-filename)
  #_(generate-documents options-filename)
  )


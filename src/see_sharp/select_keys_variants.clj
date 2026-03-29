(ns see-sharp.select-keys-variants
  (:require
   [see-sharp.core :as core]))


(defn fselect-keys-1
  "Possibly faster `select-keys`. Does *not* check for `nil`.

  Implemented with `reduce` and `assoc`."
  {:UUIDv4 #uuid "f1dff156-b53f-42b7-8450-2bf411ca6e4d"
   :no-doc true}
  [m ks]
  (reduce #(assoc %1 %2 (get m %2)) {} ks))


(defn fselect-keys-2
  "Possibly faster `select-keys`. Does *not* check for `nil`.

  Implemented with `reduce`, `assoc!` and transients."
  {:UUIDv4 #uuid "990d9850-1399-4977-810a-844569e4ed17"
   :no-doc true}
  [m ks]
  (persistent! (reduce #(assoc! %1 %2 (get m %2)) (transient {}) ks)))


(defn fselect-keys-3
  "Possibly faster `select-keys`. Does *not* check for `nil`.

  Implemented with `reduce` and `conj`."
  {:UUIDv4 #uuid "cbd2ce49-d1fe-4307-9ca7-c5e2c5148ef3"
   :no-doc true}
  [m ks]
  (reduce #(conj %1 (find m %2)) {} ks))


(defn fselect-keys-4
  "Possibly faster `select-keys`. Does *not* check for `nil`.

  Implemented with `reduce`, `conj!`, and transients."
  {:UUIDv4 #uuid "97beb4a0-c0aa-47d5-a872-82d3318693e7"
   :no-doc true}
  [m ks]
  (persistent! (reduce #(conj! %1 (find m %2)) (transient {}) ks)))


(defn fselect-keys-5
  "Possibly faster `select-keys`. Checks for `nil`!

  Implemented with `reduce` and `conj`."
  {:UUIDv4 #uuid "1a9fcfa9-5280-479c-8f9e-7f59189cc147"
   :no-doc true}
  [m ks]
  (reduce #(if-let [entry (find m %2)] (conj %1 entry) %1) {} ks))


(def fselect-keys-6 core/fselect-keys)


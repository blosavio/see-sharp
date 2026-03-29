(ns see-sharp.core
  "A slightly faster version of Clojure's `select-keys`.")


(defn fselect-keys
  "5–20% faster implementation of Clojure's core [`select-keys`](https://clojure.github.io/clojure/clojure.core-api.html#clojure.core/select-keys).
  When given hashmap `m` and *key-sequence* `ks`, returns a hashmap containing
  only those entries in `m` whose key is in `ks`.

  Example:
  ```Clojure
  (fselect-keys {:a 11 :b 22 :c 33} [:a :c]) ;; => {:a 11, :c 33}
  ```"
  {:UUIDv4 #uuid "710d3124-76ff-4f5f-bf38-ccda0fff4157"}
  [m ks]
  (with-meta
    (persistent!
     (reduce (fn [tm x] (if-let [entry (find m x)]
                          (conj! tm entry)
                          tm))
             (transient {})
             ks))
    (meta m)))


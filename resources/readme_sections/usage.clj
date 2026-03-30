[:section#usage
 [:h2 "Motivation & usage"]

 [:p "Clojure's "
  [:a {:href "https://clojure.github.io/clojure/clojure.core-api.html#clojure.core/select-keys"}
   [:code "select-keys"]]
  " is "
  [:a {:href "https://github.com/clojure/clojure/blob/8ae9e4f95e2fbbd4ee4ee3c627088c45ab44fa68/src/clj/clojure/core.clj#L1555-L1568"}
   "implemented"]
  " with a recursive "
  [:code "first/next"]
  " idiom. I wondered if an alternative implementation using some other "
  [:a {:href "https://ask.clojure.org/index.php/1913/use-transients-with-select-keys-if-possible"}
   "standard"]
  " Clojure idiom could perform measurably better across a wide range of inputs."]

 [:p "Overall, I "
  [:a {:href "https://blosavio.github.io/see-charp/select_keys_performance.html"}
   "observed"]
  " that an idiomatic "
  [:a {:href "https://github.com/blosavio/see-sharp/blob/7b002b87516286bcc0a0d43122ae15a6d477e2e3/src/see_sharp/core.clj#L17-L22"}
   "variant"]
  " composed of "
  [:code "reduce"]
  ", "
  [:code "conj!"]
  ", and transients performs 5–20% faster than Clojure's "
  [:code "select-keys"]
  " on hashmaps containing up to one-million entries."]

 [:p "Here's how we use it. We supply a hashmap and a "
  [:a {:href "#key-sequence"}
   [:em "key sequence"]]
  "."]

 [:pre (print-form-then-eval "(fselect-keys {:a 11 :b 22 :c 33 :d 44 :e 55} [:a :c :e])")]

 [:p "Exactly like "
  [:code "select-keys"]
  ", "
  [:code "fselect-keys"]
  " returns a hashmap with only those entries, but a smidge faster."]

 [:p "More "
  [:a {:href "https://clojuredocs.org/clojure.core/select-keys"}
   "examples"]
  "."]

 [:h4 "Commentary"]

 [:p "It probably only makes sense to use this variant if we're in some
 unusually performance-sensitive context where 5–20% is a significant.
 Otherwise, stick with Clojure's version."]

 [:p "Also: It's easy to have an idea; proving that the idea is good takes
 time and effort. And ultimately, the idea may be merely okay."]]


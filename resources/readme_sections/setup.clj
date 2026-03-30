[:section#setup

 [:h2 "Setup"]

 [:h3 "Leiningen/Boot"]

 [:pre [:code (str "["
                   *project-group*
                   "/"
                   *project-name*
                   " \""
                   *project-version*
                   "\"]")]]

 [:h3 "Clojure CLI/deps.edn"]

 [:pre [:code (str *project-group*
                   "/"
                   *project-name*
                   " {:mvn/version \""
                   *project-version*
                   "\"}")]]

 [:h3 "Require"]

 [:pre (print-form-then-eval "(require '[see-sharp.core :refer [fselect-keys]])")]

 [:h3 "Recommended"]

 [:p "Feel free to directly copy-paste the "
  [:a {:href "https://github.com/blosavio/see-sharp/blob/7b002b87516286bcc0a0d43122ae15a6d477e2e3/src/see_sharp/core.clj#L5-L23"}
   "code"]
  " ("
  [:a {:href "https://github.com/blosavio/see-sharp/blob/main/LICENSE"}
   "license"]
  " terms apply)."]]


(defproject com.sagevisuals/see-sharp "0-SNAPSHOT0"
  :description "A slightly faster implementation of Clojure's `select-keys`"
  :url "https://github.com/blosavio/see-sharp"
  :license {:name "MIT License"
            :url "https://opensource.org/license/mit"
            :distribution :repo}
  :dependencies [[org.clojure/clojure "1.12.4"]
                 [com.sagevisuals/extended-extend-protocol "0"]]
  :repl-options {:init-ns see-sharp.core}
  :main see-sharp.core
  :plugins []
  :profiles {:dev {:dependencies [[com.clojure-goes-fast/clj-async-profiler "2.0.0-beta1"]
                                  [com.clojure-goes-fast/clj-memory-meter "0.4.0"]
                                  [com.hypirion/clj-xchart "0.2.0"]
                                  [com.sagevisuals/chlog "5"]
                                  [com.sagevisuals/fastester "1"]
                                  [com.sagevisuals/readmoi "6"]
                                  [criterium "0.4.6"]
                                  [bsless/clj-fast "0.0.11"]
                                  [dev.weavejester/medley "1.9.0"]
                                  [org.flatland/useful "0.11.6"]
                                  [clojure-deep-merge "0.1.2"]
                                  [puppetlabs/kitchensink "3.4.0"]
                                  [tech.droit/fset "0.1.1"]
                                  [com.sagevisuals/brokvolli "0-SNAPSHOT0"]]
                   :plugins [[dev.weavejester/lein-cljfmt "0.12.0"]
                             [lein-codox "0.10.8"]]
                   :jvm-opts ["-Djdk.attach.allowAttachSelf"
                              "-XX:+UnlockDiagnosticVMOptions"
                              "-XX:+DebugNonSafepoints"
                              "-Dclj-async-profiler.output-dir=./resources/profiler_data/"]}
             :benchmark {:jvm-opts ["-XX:+TieredCompilation"
                                    "-XX:TieredStopAtLevel=4"]}
             :repl {}}
  :aliases {"readmoi" ["run" "-m" "readmoi-generator"]
            "chlog" ["run" "-m" "chlog-generator"]}
  :codox {:metadata {:doc/format :markdown}
          :namespaces [#"^see-sharp\.(?!scratch)(?!experimental)(?!merge-variants)(?!select-keys-variants)(?!set-operation-variants)"]
          :target-path "doc"
          :output-path "doc"
          :doc-files []
          :source-uri "https://github.com/blosavio/see-sharp/blob/main/{filepath}#L{line}"
          :html {:transforms [[:div.sidebar.primary] [:append [:ul.index-link [:li.depth-1 [:a {:href "https://github.com/blosavio/see-sharp"} "Project Home"]]]]]}
          :project {:name "see-sharp" :version "version 0"}}
  :scm {:name "git" :url "https://github.com/blosavio/see-sharp"})


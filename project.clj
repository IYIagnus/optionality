(defproject optionality "0.1.0-SNAPSHOT"
  :description "API for fetching option data"
  :url "https://github.com/IYIagnus/optionality"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.10.0"]
                 [environ "1.1.0"]
                 [org.jsoup/jsoup "1.13.1"]
                 [clj-http "3.10.1"]
                 [cheshire "5.10.0"]
                 [clojure.java-time "0.3.2"]]
  :repl-options {:init-ns optionality.core}
  :plugins [[lein-environ "1.1.0"]]
  :profiles {:dev [:project/dev :profiles/dev]
             :test [:project/test :profiles/test]
             :profiles/dev  {}
           :profiles/test {}
           :project/dev {}
           :project/test {}})

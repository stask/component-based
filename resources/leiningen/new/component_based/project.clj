(defproject {{raw-name}} "0.1.0-SNAPSHOT"
  :description "TODO: FIXME"
  :url "TODO: FIXME"
  :min-lein-version "2.5.0"

  :dependencies [[org.clojure/clojure        "1.6.0"]
                 [org.clojure/core.async     "0.1.346.0-17112a-alpha"]
                 [com.stuartsierra/component "0.2.2"]
                 [cc.qbits/jet               "0.5.0-beta3"]
                 [ring/ring-json             "0.3.1"]
                 [compojure                  "1.2.1"]
                 {{#with-om}}
                 ;; client
                 [org.clojure/clojurescript "0.0-2371" :scope "provided"]
                 [om                        "0.7.3"]
                 [secretary                 "1.2.1"]
                 [sablono                   "0.2.22"]
                 [cljs-ajax                 "0.3.3"]
                 {{/with-om}}
                 ]

  :source-paths ["src/clj"]
  :main ^:skip-aot {{namespace}}.core
  :target-path "target/%s"
  :uberjar-name "{{name}}-standalone.jar"
  {{#with-om}}
  :plugins [[lein-cljsbuild "1.0.3"]]
  {{/with-om}}

  :repl-options {:init-ns user}

  {{#with-om}}
  :cljsbuild {:builds
              {:app {:source-paths ["src/cljs"]
                     :compiler {:output-to     "resources/public/js/app.js"
                                :output-dir    "resources/public/js/out"
                                :source-map    "resources/public/js/app.js.map"
                                :optimizations :none
                                :pretty-print  true}}}}
  {{/with-om}}

  :profiles {:uberjar {:aot :all
                       :omit-source true
                       {{#with-om}}
                       :cljsbuild {:builds
                                   {:app
                                    {:compiler
                                     {:output-to     "resources/public/js/app.min.js"
                                      :optimizations :advanced
                                      :pretty-print  false}}}}
                       {{/with-om}}
                       }
             :dev {:dependencies [[org.clojure/tools.namespace "0.2.7"]]
                   :source-paths ["dev"]}})

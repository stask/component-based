(ns leiningen.new.component-based
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files
                                             project-name sanitize-ns]]
            [leiningen.core.main     :as main]
            [clojure.string          :as string]))

(defn component-based
  "Creates project with optional om support."
  ([name] (component-based name :noop))
  ([name opts]
     (let [render (renderer "component-based")
           main-ns (sanitize-ns name)
           data {:raw-name       name
                 :name           (project-name name)
                 :namespace      main-ns
                 :cljs-namespace (string/replace main-ns \- \_)
                 :sanitized      (name-to-path main-ns)
                 :with-om        (= opts "with-om")}
           base [["src/clj/{{sanitized}}/core.clj" (render "core.clj" data)]
                 ["src/clj/{{sanitized}}/system.clj" (render "system.clj" data)]
                 ["src/clj/{{sanitized}}/web_server.clj" (render "web_server.clj" data)]
                 ["dev/user.clj" (render "user.clj" data)]
                 ["project.clj" (render "project.clj" data)]]
           om   [["src/cljs/{{sanitized}}/core.cljs" (render "core.cljs" data)]
                 ["resources/public/index-dev.html" (render "index-dev.html" data)]]]
       (main/info "Generating fresh 'lein new' component-based project.")
       (apply ->files data
              (case opts
                "with-om" (concat base om)
                base)))))

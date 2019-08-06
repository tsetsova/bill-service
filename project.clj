(defproject bill-service "0.1.0-SNAPSHOT"
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler bill-service.core/handler}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [liberator "0.15.3"]
                 [compojure "1.6.1"]
                 [ring/ring-core "1.7.1"]])

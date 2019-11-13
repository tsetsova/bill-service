(defproject bill-service "0.1.0-SNAPSHOT"
  :plugins [[lein-ring "0.12.5"]]
  :ring {:handler bill-service.core/handler
         :init bill-service.db/init}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [liberator "0.15.3"]
                 [compojure "1.6.1"]
                 [ring/ring-core "1.7.1"]
                 [ring/ring-mock "0.3.2"]
                 [org.postgresql/postgresql "42.2.5"]
                 [com.layerware/hugsql "0.4.9"]
                 [org.postgresql/postgresql "42.2.5"]
                 [org.flywaydb/flyway-core "5.2.1"]]
  :profiles {:kaocha {:dependencies [[lambdaisland/kaocha "0.0-529"]]}}
  :aliases {"kaocha" ["with-profile" "+kaocha" "run" "-m" "kaocha.runner"]})

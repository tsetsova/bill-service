(ns bill-service.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY]]))

(defroutes app
  (ANY "/" [] (resource :available-media-types ["text/html"]
                        :handle-ok "<html>Hello, Internet.</html>")))

(def handler
  (-> app
    wrap-params))
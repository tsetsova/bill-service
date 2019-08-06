(ns bill-service.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes GET]]
            [clojure.data.json :as json]))

(def bill-resource (resource
                     :available-media-types ["application/json"]
                     :handle-ok (fn [_] (json/write-str {}))))

(defroutes app
  (GET "/bills" [] bill-resource))

(def handler
  (-> app
    wrap-params))
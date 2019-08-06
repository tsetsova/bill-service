(ns bill-service.core
  (:require [liberator.core :refer [resource defresource]]
            [ring.middleware.params :refer [wrap-params]]
            [compojure.core :refer [defroutes ANY GET]]
            [clojure.data.json :as json]))

(def bills (atom []))

(defn find-bill [id]
  (first (filter (fn [bill] (= (:id bill) id)) @bills)))

(defn total [amount tip-percentage]
  (let
    [amount (float amount)]
    (+ amount (* (/ tip-percentage 100) amount))))

(defresource bill-resource [id]
  :allowed-methods [:get]
  :available-media-types ["application/json"]
  :exists? (fn [_]
             (let [bill (find-bill id)]
               (if-not (nil? bill)
                 {::bill bill})))
  :handle-ok ::bill)

(defresource bills-resource
  :allowed-methods [:post :get]
  :available-media-types ["application/json"]
  :handle-ok (fn [_] (json/write-str @bills))
  :handle-created {:created "ok"}
  :post! (fn [context]
           (let [body (-> context
                        (get-in [:request :body])
                        (slurp)
                        (json/read-str :key-fn keyword))
                 amount (:amount body)
                 tip-percentage (:tip-percentage body)]
             (swap! bills conj
               {:id             (inc (count @bills))
                :amount         amount
                :tip-percentage tip-percentage
                :total          (total amount tip-percentage)}))))

(defroutes app
  (ANY "/bills/:id" [id]
    (bill-resource (read-string id)))
  (ANY "/bills" []
    bills-resource))

(def handler
  (-> app
    wrap-params))

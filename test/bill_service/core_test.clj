(ns bill-service.core-test
  (:require [clojure.test :refer :all]
            [bill-service.core :refer :all]
            [ring.mock.request :as ring]))

(deftest bills-resource-GET-on-success
  (testing "Get bills"
    (is (= (:body (bill-resource (ring/request :get "/bills"))) "{}"))))

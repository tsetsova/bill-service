(ns bill-service.core-test
  (:require [clojure.test :refer :all]
            [bill-service.core :refer :all]
            [ring.mock.request :as ring]
            [clojure.data.json :as json]))

(deftest bills-resource-GET-on-success
  (testing "Get /bills"
    (reset! bills [])
    (is (= "[]" (:body (bills-resource (ring/request :get ""))))))
  (testing "Get all bills"
    (reset! bills [])
    (bills-resource (ring/request :post ""
                      (json/write-str {:amount 10.00 :tip-percentage 10})))
    (bills-resource (ring/request :post ""
                      (json/write-str {:amount 25.00 :tip-percentage 20})))
    (is (= (json/write-str [{:id             1
                             :amount         10.00
                             :tip-percentage 10
                             :total          11.00},
                            {:id             2
                             :amount         25.00
                             :tip-percentage 20
                             :total          30.00}])
          (:body (bills-resource (ring/request :get "")))))))

(deftest bills-resource-GET-on-success-by-id
  (testing "Get bills/:id"
    (reset! bills [])
    (bills-resource (ring/request :post ""
                      (json/write-str {:amount 10.00 :tip-percentage 10})))
    (is (= (json/write-str {:id             1
                            :amount         10.00
                            :tip-percentage 10
                            :total          11.00})
          (:body ((bill-resource 1) (ring/request :get "")))))))

(deftest bills-resource-GET-on-failure-by-id
  (testing "Get bills/:id"
    (reset! bills [])
    (is (= "Resource not found."
          (:body ((bill-resource 1) (ring/request :get "")))))))

(deftest bills-resource-POST-on-success
  (reset! bills [])
  (testing "Post /bills"
    (is (= (json/write-str {:created "ok"})
          (:body (bills-resource
                   (ring/request :post ""
                     (json/write-str {:amount 10.00 :tip-percentage 10}))))))))

(ns unit.bills-test
  (:require [clojure.test :refer :all]
            [bill-service.core :refer :all]))

(deftest calculates-total-bill-correctly
  (testing "Calculate total bill"
    (is (= 11.00
          (total 10.00 10)))
    (is (= 11.00
          (total 10 10)))))

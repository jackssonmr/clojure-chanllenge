(ns invoice-item-test
  (:require [clojure.test :refer [deftest is]]
            [invoice-item :as invoice-item]))

(deftest test-subtotal-no-discount
  (let [item {:precise-quantity 2.5
              :precise-price 10.0}]
    (is (= (invoice-item/subtotal item) 25.0))))

(deftest test-subtotal-with-discount
  (let [item {:precise-quantity 5
              :precise-price 20.0
              :discount-rate 15}]
    (is (= (invoice-item/subtotal item) 85.0))))

(deftest test-subtotal-zero-quantity
  (let [item {:precise-quantity 0
              :precise-price 50.0
              :discount-rate 10}]
    (is (= (invoice-item/subtotal item) 0.0))))

(deftest test-subtotal-zero-price
  (let [item {:precise-quantity 3
              :precise-price 0
              :discount-rate 5}]
    (is (= (invoice-item/subtotal item) 0.0))))

(deftest test-subtotal-negative-discount-rate
  (let [item {:precise-quantity 4
              :precise-price 15.0
              :discount-rate -8}]
    (is (= (invoice-item/subtotal item) 60.0))))

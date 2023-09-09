(ns invoice-validator
  (:require
    [clojure.spec.alpha :as s]
    [cheshire.core :as json]
    [invoice-spec :as spec]))

(def invoice
  {
   :invoice/issue-date (java.util.Date. 1631472000000)
   :invoice/customer {:customer/name "ANDRADE RODRIGUEZ MANUEL ALEJANDRO"
                      :customer/email "cgallegoaecu@gmail.com"}
   :invoice/items
   [
    {:invoice-item/price 10000.0
     :invoice-item/quantity 2.0
     :invoice-item/sku "SUPER-1"
     :invoice-item/taxes [{:tax/category :iva
                           :tax/rate 0.05}]}
    {:invoice-item/price 20000.0
     :invoice-item/quantity 1.0
     :invoice-item/sku "SUPER-2"
     :invoice-item/taxes [{:tax/category :iva
                           :tax/rate 0.19}]}
    {:invoice-item/price 30000.0
     :invoice-item/quantity 1.0
     :invoice-item/sku "SUPER-3"
     :invoice-item/taxes [{:tax/category :iva
                           :tax/rate 0.19}]}
    ]
   :invoice/retentions
   [
    {:tax/category :ret_fuente
     :tax/rate 0.15}
    {:tax/category :ret_iva
     :tax/rate 0.15}
    ]
   :invoice/order-reference "PEDID_0001"
   :invoice/payment-date (java.util.Date. 1631472000000)
   :invoice/payment-means "DEBIT_CARD"
   :invoice/payment-means-type "DEBITO"
   :invoice/number "1"
   })

(defn run [opts]
  (println "Problem 2 Thread-last Operator ->>")
  ;(s/valid? ::spec/invoice invoice)
  (if (s/valid? ::spec/invoice invoice)
    (println (json/generate-string invoice))
    nil))

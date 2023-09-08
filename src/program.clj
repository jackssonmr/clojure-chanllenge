(ns program)

(def invoice (clojure.edn/read-string (slurp "invoice.edn")))

(defn filter-invoice-items [invoice]
  (->> (:invoice/items invoice) ; Extract the items from the invoice
       (group-by #(if (some (fn [param1] (= 19 (:tax/rate param1))) (:taxable/taxes %))
                    :iva
                    :ret_fuente)) ; Group items by condition
       (vals) ; Get the values (groups)
       (filter (fn [group]
                 (cond
                   (= :iva (first group)) ; Check if it's an :iva group
                   (some #(= 1 (:retention/rate %)) (mapcat :retentionable/retentions group))
                   (= :ret_fuente (first group)) ; Check if it's a :ret_fuente group
                   (some #(= 19 (:tax/rate %)) (mapcat :taxable/taxes group))
                   :else false)))))

(defn run [opts]
  (println "Problem 1 Thread-last Operator ->>")
  (println filter-invoice-items invoice))

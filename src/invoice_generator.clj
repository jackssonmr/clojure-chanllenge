(ns invoice-generator
  (:require [cheshire.core :as json]
            [clojure.spec.alpha :as s]
            [clojure.java.io :as io]
            [invoice_spec :as invoice-spec]))

(defn validate-json-file [file-path]
  (let [json-data (with-open [reader (io/reader file-path)]
                    (json/parse reader))
        valid? (s/valid? ::invoice json-data)]
    (if valid?
      json-data
      (throw (ex-info "Invalid invoice data" {:errors (s/explain-data ::invoice json-data)})))))

(defn -main [file-name]
  (try
    (let [invoice-map (validate-json-file file-name)]
      (println "Valid invoice:")
      (println invoice-map))
    (catch Exception e
      (println "Error:" (.getMessage e)))))

(defn run [opts]
  (println "Problem 1 Thread-last Operator ->>")
  (println (-main "invoice.json")))

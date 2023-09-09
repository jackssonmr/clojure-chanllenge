(ns invoice-validation
  (:require
    [clojure.spec.alpha :as s]
    [cheshire.core :as json]
    [invoice-spec :as spec]))

;;(def invoice (clojure.edn/read-string (slurp "invoice_problem2.edn")))

;; load json function
(defn validate-json-file [file-path]
  (try
    (let [json-content (clojure.edn/read-string (slurp file-path))]
      ;; Apply Spec validations
      (if (s/valid? ::spec/invoice json-content)
        json-content ;; if valid return clojure map
        nil))
    (catch Exception e
      (println "Error loading and validating JSON:" (.getMessage e))
      nil))) ; nil when error occurred

(defn load-json-file [file-path]
  (try
    (let [json-content (json/parse-string (slurp file-path) true)]
      ;; check if clojure map
      (if (map? json-content)
        ;;json-content ;; return map
        (if (s/valid? ::spec/invoice json-content)
          json-content ;; if valid return clojure map
          nil)
        (throw (ex-info "The file content is no valid clojure map" {:content json-content}))))
    (catch Exception e
      (println "Error:" (.getMessage e))
      nil))) ;

;;(def json-file-path "invoice_problem2.edn")
;; (def invoice-map (validate-json-file json-file-path))
(def json-file-path "invoice_generated.json")
(def invoice-map (load-json-file json-file-path))

(defn run [opts]
  (println invoice-map) ;; print a clojure map
  )
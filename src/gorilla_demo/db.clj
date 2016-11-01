(ns gorilla-demo.db
  (:require [datascript.core :as d]))


(def conn (d/create-conn (read-string (slurp "resources/population-schema.edn"))))

(defn transact [datum]
  @(d/transact conn datum))

(defn loadDatabase []
  (let [db-tx (read-string (slurp "resources/population-db.edn"))
        population-tx (read-string (slurp "resources/population-load.edn"))]
    (do (apply #(transact %) db-tx)
        (apply #(transact %) population-tx))))

(loadDatabase)

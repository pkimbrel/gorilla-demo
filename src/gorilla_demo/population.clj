;; gorilla-repl.fileformat = 1

;; **
;;; # Population Retrieval
;;; 
;;; US Population as Datums
;;; 
;;; This will retrieve each county individually into a vector of maps
;; **

;; @@
(ns gorilla-demo.us-population
  (:require [gorilla-plot.core :as plot]
            [gorilla-demo.db :as db]
            [datascript.core :as d]))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-nil'>nil</span>","value":"nil"}
;; <=

;; @@
(defn demungeGeoid [geoid] (subs geoid 1))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;gorilla-demo.us-population/demungeGeoid</span>","value":"#'gorilla-demo.us-population/demungeGeoid"}
;; <=

;; @@
(defn retrievePopulation [year] (map #(do {:id (demungeGeoid (first %)) :value (second %) :name (get % 2)}) (sort-by first (d/q '[:find ?c-geoid ?p-amount ?c-name
 :in $ ?year
 :where
 [?c-id :map.county/geoid ?c-geoid]
 [?c-id :map.county/name ?c-name]
 [?p-id :map.population/map.county ?c-id]
 [?p-id :map.population/year ?year]
 [?p-id :map.population/amount ?p-amount]
] (d/db db/conn) year))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;gorilla-demo.us-population/retrievePopulation</span>","value":"#'gorilla-demo.us-population/retrievePopulation"}
;; <=

;; @@
(defn retrievePopulationByState [state year] (map #(do {:id (demungeGeoid (first %)) :value (second %) :name (get % 2)}) (sort-by first (d/q '[:find ?c-geoid ?p-amount ?c-name
 :in $ ?state ?year
 :where
 [?s-id :map.state/name ?state]
 [?c-id :map.county/map.state ?s-id]
 [?c-id :map.county/geoid ?c-geoid]
 [?c-id :map.county/name ?c-name]
 [?p-id :map.population/map.county ?c-id]
 [?p-id :map.population/year ?year]
 [?p-id :map.population/amount ?p-amount]
] (d/db db/conn) state year))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;gorilla-demo.us-population/retrievePopulationByState</span>","value":"#'gorilla-demo.us-population/retrievePopulationByState"}
;; <=

;; @@
(defn retrievePopulationByCounty [state county year] (first (first (d/q '[:find ?p-amount
 :in $ ?state ?county ?year
 :where
 [?s-id :map.state/name ?state]
 [?c-id :map.county/map.state ?s-id]
 [?c-id :map.county/name ?county]
 [?c-id :map.county/geoid ?c-geoid]
 [?p-id :map.population/map.county ?c-id]
 [?p-id :map.population/year ?year]
 [?p-id :map.population/amount ?p-amount]
] (d/db db/conn) state county year))))
;; @@
;; =>
;;; {"type":"html","content":"<span class='clj-var'>#&#x27;gorilla-demo.us-population/retrievePopulationByCounty</span>","value":"#'gorilla-demo.us-population/retrievePopulationByCounty"}
;; <=

;; **
;;; # Unit Tests
;; **

;; @@
(retrievePopulationByState "Illinois" 2011)
;; @@

;; @@
(comment retrievePopulationByCounty "Illinois" "McLean County" 2011)
;; @@

;; @@
(retrievePopulation 2011)
;; @@

;; @@

;; @@

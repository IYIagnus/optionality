(ns optionality.time
  (:require [java-time])
  (:import (java.time Duration)))


(defn local-date
  "Maps jave-time/local-date to a collection"
  [format coll]
  (map #(java-time/local-date format %) coll))


(defn days-between
  [start end]
  (.toDays
    (Duration/between
      (.atStartOfDay start)
      (.atStartOfDay end))))


(defn get-ttm
  "Returns time-to-maturity for seq of expiration dates
   with format yyyyMMdd"
  [expiration-dates]
  (let [date-objects (local-date "yyyyMMdd" expiration-dates)]
    (map #(days-between (java-time/local-date) %) date-objects)))

(ns optionality.iexcloud
  (:require [clojure.string :as str])
  (:require [environ.core :refer [env]])
  (:require [clj-http.client :as client])
  (:require [optionality.time :as time]))


(def iexcloud-api-key
  (env :iexcloud-api-key))

(def iexcloud-base-url "https://cloud.iexapis.com/stable/stock/")


(defn get-url
  [symbol request]
  (str/join [iexcloud-base-url
             symbol
             "/"
             request
             "?token="
             iexcloud-api-key]))


(defn request-iexcloud
  [request]
  (:body (client/get request {:as :json})))


(defn get-expiration-dates
  [symbol]
  (request-iexcloud (get-url symbol "options")))


(defn get-quote
  [symbol]
  (request-iexcloud (get-url symbol "quote")))


(defn get-option-price
  [symbol cp expiration]
  (request-iexcloud
      (get-url symbol
        (str/join ["options"
                   "/"
                   expiration
                   "/"
                   cp]))))


(defn in-range? [start end x]
  (assert (and (= (type x) (type start)) (= (type x) (type end))))
  (cond
    (number? x) (and
                  (<= start x)
                  (<= x end))
    (string? x) (and
                  (<= (Integer/parseInt start) (Integer/parseInt x))
                  (<= (Integer/parseInt x) (Integer/parseInt end)))))


(defn get-prices
  "Retrieves prices from IEX Cloud based on info about the underlying
   asset, call or put, and a range for ttm"
  [symbol cp start-ttm end-ttm]
  (let [expiration-dates
        (filter #(in-range? start-ttm end-ttm %)
          (get-expiration-dates symbol))]
    (zipmap
      (map keyword expiration-dates)
      (map #(get-option-price symbol cp %) expiration-dates))))

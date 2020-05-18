(ns optionality.core
  (:require [optionality.iexcloud :as iexcloud]))


(defn get-prices
  [symbol cp start-ttm end-ttm source]
  (cond
    (= source "iexcloud") (iexcloud/get-prices symbol cp start-ttm end-ttm)
    :else nil))

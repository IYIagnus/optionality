(ns optionality.core
  (:require [optionality.iexcloud :as iexcloud]))


(defn get-prices
  [source symbol cp start-ttm end-ttm]
  (cond
    (= source "iexcloud") (iexcloud/get-prices symbol cp start-ttm end-ttm)
    :else nil))

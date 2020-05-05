(ns optionality.core
  (:import [org.jsoup Jsoup]))


(def YAHOO-PATH "https://finance.yahoo.com/quote")


(defn join-url
  "Joins components of a url to a proper url"
  [& components]
  (let [url (clojure.string/join "/" components)]
    url))


(defn get-prices-yahoo
  "Retrieves prices from Yahoo Finance based on info about the underlying
   asset, call or put, and a range for strike prices"
  [underlying cp strike-range]
  )


(defn get-price-table
  "Gets table from html page (calls / puts)"
  [page cp]
  (cond
    (= cp "c") (.select page "table.calls")
    (= cp "p") (.select page "table.puts")
    :else nil))


(defn get-page-yahoo
  "Retrieves Yahoo page with Jsoup based on ticker symbol"
  [ticker]
  (let [page (.get (Jsoup/connect (join-url YAHOO-PATH ticker "options")))]
    page))


(defn -main
  (prinln
    (get-prices-yahoo "SPX" "p" [2000 3000])))

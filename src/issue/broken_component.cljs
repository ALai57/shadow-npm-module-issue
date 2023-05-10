(ns issue.broken-component
  (:require [reagent.core :as r]
            ["./js/Example" :refer [example]]
            ))

(defn my-component
  []
  [:div
   [:div "Hello"]
   [example]])

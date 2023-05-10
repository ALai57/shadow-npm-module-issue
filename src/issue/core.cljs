(ns issue.core
  (:require [goog.dom :as gdom]
            ["react-dom/client" :as react-dom]
            [issue.component :refer [my-component]]
            [reagent.core :as r]))

;; Support for React 18
(defonce root
  (react-dom/createRoot (gdom/getElement "app")))

(defn ^:export main
  []
  (.render root (r/as-element [my-component])))

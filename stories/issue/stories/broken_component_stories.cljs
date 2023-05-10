(ns issue.stories.broken-component-stories
  (:require [issue.broken-component :refer [my-component]]
            [issue.stories.helper :as helper]))

(def ^:export default
  (helper/->default-story {:title     "My Component"
                           :component my-component}))

(def ^:export Broken-component-story
  (clj->js {}))

(ns issue.stories.component-stories
  (:require [issue.component :refer [my-component]]
            [issue.stories.helper :as helper]))

(def ^:export default
  (helper/->default-story {:title     "My Component"
                           :component my-component}))

(def ^:export Main-component-story
  (clj->js {}))

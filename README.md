# Shadow CLJS compilation issue reproduction

From the project root directory `shadow-npm-module-issue`:

Install deps
``` sh
npm install
```

Transpile JSX to JS
``` sh
npx babel src/issue-js --out-dir src/issue --watch
```

Start shadow build. This will emit `:npm-module`s, where each module is a storybook story.
``` sh
npm run watch
```

Start storybook

``` sh
npm run storybook
```


Navigate to storybook (`localhost:6006`) to see that there is a working story
component (In storybook, it is called "Working Component Story" and corresponds
to `issue.component`), and a broken component `issue.broken-component` ![Image
of the console showing the error leading to the broken
stories](./storybook-ui-showing-broken-component.png)

The only difference between the working story component and the broken component
is that the broken component requires the transpiled Javascript directly using `ns`.

``` clojure
(ns issue.broken-component
  (:require [reagent.core :as r]
            ;; This seems to break the `:npm-module`
            ["./js/Example" :refer [example]]))
```


The shadow output from the file
`resources/public/js/compiled/shareable_stories/module$issue$js$Example.js` seems to be broken because the module cannot load React.

  * [ ] Here's a snippet from the `:npm-module`'s output from
`resources/public/js/compiled/shareable_stories/module$issue$js$Example.js` that
illustrates the issue. The `(require("shadow.js.shim.module$react"))` statement
doesn't return the module, so the `_react` var doesn't have an actual link to
the React module, and breaks upon loading. 

``` javascript
shadow$provide.module$issue$js$Example = function(global, require, module, exports) {
    Object.defineProperty(exports, "__esModule", {
        value: !0
    });
    exports.example = function() {
        return _react.default.createElement("h1", null, "This was compiled from JSX")
    };
    var _react = function(obj) {
        return obj && obj.__esModule ? obj : {
            default: obj
        }
    }(require("shadow.js.shim.module$react"))
}
```

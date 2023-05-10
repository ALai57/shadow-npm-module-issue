# Shadow CLJS compilation issue reproduction


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


Navigate to storybook to see that there is a working component `issue.component`, and a broken component `issue.broken-component`


The output from the file
`resources/public/js/compiled/shareable_stories/module$issue$js$Example.js` seems to be broken because the module cannot load React.

Here's a snippet from the file that illustrates the issue. The `(require("shadow.js.shim.module$react"))` statement doesn't return the module, so the `_react` var doesn't have an actual link to the React module. 

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

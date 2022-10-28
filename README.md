# electron-cljs

Electron App Example using shadow-cljs.

## Build Instructions

```
npm install
npx shadow-cljs watch main renderer
npx electron .
```

## Implementation Notes

Two builds are used. The `:renderer` build is a generic `:browser` build, used for the "BrowserWindow" parts which act like a normal browser. They are launched by the Electron "main" process. The `:main` build produces the output for the "main" as well as the Electron "preload". They are both node-ish contexts, so we can use a single `:npm-module` build to produce the necessary outputs.

The `app/main.js` and `app/preload.js` files act as the entry point which load and call the actual code. These are not strictly necessary, but I prefer it over having code that unconditionally runs when the namespace is loaded.


This requires using the helper files:

```
(ns demo.main)

(defn ^:export init []
  (do-something))
```

This would not, and you would just point your `package.json` `"main"` directly at `app/js/demo.main.js` instead.

```
(ns demo.main)

(do-something)
```

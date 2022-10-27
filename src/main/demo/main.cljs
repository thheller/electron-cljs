(ns demo.main
  (:require
    [shadow.cljs.modern :refer (js-await)]
    ["electron" :as e]
    ["path" :as path]))

(defonce main-window nil)

(defn create-window []
  (set! main-window
    (e/BrowserWindow.
      (clj->js
        {:width 800
         :height 600
         :webPreferences
         {:nodeIntegration true
          :preload (path/join js/__dirname ".." "/preload.js")}
         ;; :transparent true
         :backgroundColor "#dddddd"})))

  (.loadFile main-window (path/join js/__dirname ".." "public" "index.html"))

  (.on main-window "closed" #(set! main-window nil))

  (e/ipcMain.on "set-title"
    (fn [event title]
      (.setTitle main-window title))))

(defn init []
  (js-await [_ (e/app.whenReady)]
    (create-window)
    (e/app.on "activate"
      (fn []
        (when (zero? (alength (e/BrowserWindow.getAllWindows)))
          (create-window))))

    (e/app.on "window-all-closed"
      (fn []
        (when-not (= js/process.platform "darwin")
          (e/app.quit))))))

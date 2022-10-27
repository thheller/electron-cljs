(ns demo.preload
  (:require ["electron" :as e]))

(defn init []
  (e/contextBridge.exposeInMainWorld
    "electronAPI"
    #js {:setTitle
         (fn [title]
           (e/ipcRenderer.send "set-title" (str "via preload: " title)))}))
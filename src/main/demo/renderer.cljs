(ns demo.renderer)

(defn init []
  (-> (js/document.getElementById "test")
      (.addEventListener "input"
        (fn [^js e]
          (js/window.electronAPI.setTitle (-> e .-target .-value))))))

(ns expy-driver.core
  (:gen-class)
  (:require [clj-webdriver.taxi :as taxi])
  (:require [clojure.edn :as edn]))

(defn config []
  (clojure.edn/read-string (slurp (java.lang.System/getProperty "config.edn" "config.edn"))))

(defn visit-home []
  (taxi/set-driver! {:browser :firefox} "http://expy.jp")
  (taxi/implicit-wait (:wait (config))))

(defn login [username password]
  (taxi/click {:xpath "//div[@id='left-login']//img[@alt='J-WEST CARD 会員ログイン']"})
  (taxi/input-text "#user_id2" username)
  (taxi/input-text "#password2" password)
  (taxi/click {:xpath "//div[@id='left-login']//div[@class='accordion-box'][2]//input[@alt='ログイン']"}))

(defn -main
  [& args]
  (visit-home)
  (login (:username (config)) (:password (config)))

  (taxi/switch-to-frame {:xpath "//frame[@name='main']"})
  (taxi/switch-to-frame {:xpath "//frame[@name='main']"})
  (taxi/click {:xpath "//img[@alt='列車名を指定して予約']"})

  (taxi/select-by-value {:xpath "//select[@name='go_search_date']"} (:date (config)))
  (taxi/select-by-text {:xpath "//select[@name='go_search_dep_st1']"} (:from (config)))
  (taxi/select-by-text {:xpath "//select[@name='go_search_train_name1']"} (:train-type (config)))
  (taxi/input-text {:xpath "//input[@name='go_search_train_no1']"} (:train-number (config)))
  (taxi/select-by-text {:xpath "//select[@name='go_search_arv_st1']"} (:to (config)))
  (taxi/click {:xpath "//img[@alt='次へ']"})

  (taxi/select-by-text {:xpath "//select[@name='request_equip']"} "グリーン車 禁煙")
  (taxi/click {:xpath "//img[@alt='次へ']"})

  (taxi/click {:xpath "//table/tbody/tr[4]//img[@alt='選択']"})
  ;; (taxi/click {:xpath "//img[@alt='確定']"})

  )

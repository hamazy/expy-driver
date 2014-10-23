(defproject expy-driver "0.1.0-SNAPSHOT"
  :description "Make a reservation on expy.jp"
  :url "http://github.com/hamazy/expy-driver"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [clj-webdriver "0.6.1"]]
  :main ^:skip-aot expy-driver.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

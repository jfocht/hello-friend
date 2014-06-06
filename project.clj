(defproject hello-friend "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring "0.8.7"]]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.cemerick/friend "0.2.1"]]
  :ring {:handler hello-friend.core/secure-handler}
)

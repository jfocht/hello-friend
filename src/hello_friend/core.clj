(ns hello-friend.core
  (:require [cemerick.friend :as friend]
            (cemerick.friend [workflows :as workflows]
                             [credentials :as creds])))


(def users {"root" {:username "root"
                    :password (creds/hash-bcrypt "admin_password")
                    :roles #{::admin}}
            "jane" {:username "jane"
                    :password (creds/hash-bcrypt "user_password")
                    :roles #{::user}}})


(defn handler [request]
    {:status 200
     :headers {"Content-Type" "text/html"}
     :body (str request) })


(defn admin-handler [request]
  (friend/authorize #{::admin}
    (handler request)))

(def realm "API")

(def secure-handler
  (->
    admin-handler
    (friend/authenticate {:credential-fn (fn [{:keys [username password]}]
                                           (creds/bcrypt-credential-fn
                                             users
                                             {:username username :password password}))
                          :workflows [(workflows/http-basic)
                                      (partial workflows/http-basic-deny realm)]})
))

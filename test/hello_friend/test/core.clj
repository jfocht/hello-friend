(ns hello-friend.test.core
  (:use [hello-friend.core]
        [clojure.test]
        [ring.mock.request :only [request header body]]))

(deftest test-auth
  (are [auth status]
    (let [req (header (request :get "/") "Authorization" (str "Basic " auth))
          rsp (secure-handler req)]
      (= status (:status rsp)))
      "cm9vdDphZG1pbl9wYXNzd29yZAo=" 200
       "amFuZTp1c2VyX3Bhc3N3b3JkCg==" 403))

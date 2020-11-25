(ns parentheses.core-test
  (:require [clojure.test :refer :all]
            [parentheses.core :refer :all]))

(deftest test-when-let+
  (testing "it evaluates all bindings"
    (is (= 333 (when-let+ [a 111 b 222 c (+ a b)] c))))
  (testing "it short circuits and return nil"
    (is (= nil (when-let+ [a 3 b nil c (+ a b)] c)))))

(deftest test-if-let+
  (testing "it evaluates all bindings"
    (is (= 333 (if-let+ [a 111 b 222 c (+ a b)] c))))
  (testing "it short circuits and return the else form"
    (is (= 222 (if-let+ [a "aaa" b nil] 111 222))))
  (testing "it short circuits and return nil if there is no else form"
    (is (= nil (if-let+ [a "aaa" b nil] 111)))))




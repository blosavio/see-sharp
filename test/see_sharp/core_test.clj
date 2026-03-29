(ns see-sharp.core-test
  (:require [clojure.test :refer [are
                                  deftest
                                  is
                                  run-test
                                  run-tests
                                  testing]]
            [see-sharp.core :refer :all]))


(deftest fselect-keys-tests
  (testing "empty key-sequence"
    (are [m ks result] (= result
                          (select-keys m ks)
                          (fselect-keys m ks))
      {} [] {}
      {:a 11} [] {}
      {:a 11 :b 22 :c 33} [] {}))

  (testing "empty hashmap"
    (are [m ks result] (= result
                          (select-keys m ks)
                          (fselect-keys m ks))
      {} [] {}
      {} [:a] {}
      {} [:a :b :c] {}))

  (testing "non-empty key-sequence, no matches"
    (are [m ks result] (= result
                          (select-keys m ks)
                          (fselect-keys m ks))
      {:a 11 :b 22 :c 33} [:d] {}
      {:a 11 :b 22 :c 33} [:d :e :f] {}))

  (testing "non-empty key-sequence, some matches"
    (are [m ks result] (= result
                          (select-keys m ks)
                          (fselect-keys m ks))
      {:a 11 :b 22 :c 33} [:a] {:a 11}
      {:a 11 :b 22 :c 33} [:a :b] {:a 11 :b 22}
      {:a 11 :b 22 :c 33} [:a :b :c] {:a 11 :b 22 :c 33}))

  (testing "metadata"
    (are [m ks] (= (meta m)
                   (meta (select-keys m ks)))
      (with-meta {} {:test "foo"}) []
      (with-meta {:a 11} {:test "foo"}) [:a])))


#_(run-tests)


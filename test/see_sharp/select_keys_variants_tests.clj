(ns see-sharp.select-keys-variants-tests
  (:require
   [clojure.test :refer [are
                         deftest
                         is
                         run-test
                         run-tests
                         testing]]
   [see-sharp.select-keys-variants :refer :all]))


(deftest select-keys-tests
  (testing "basic selection"
    (are [m ks result] (= result
                          (select-keys m ks)
                          (fselect-keys-1 m ks)
                          (fselect-keys-2 m ks)
                          (fselect-keys-3 m ks)
                          (fselect-keys-4 m ks)
                          (fselect-keys-5 m ks)
                          (fselect-keys-6 m ks))
      {} [] {}
      {:a 11} [] {}
      {:a 11} [:a] {:a 11}
      {:a 11 :b 22 :c 33} [:a] {:a 11}
      {:a 11 :b 22 :c 33} [:a :b] {:a 11 :b 22}
      {:a 11 :b 22 :c 33} [:a :b :c] {:a 11 :b 22 :c 33}))

  (testing "hashmap does not contain key"
    (are [m ks result] (= result
                          (select-keys m ks)
                          (fselect-keys-5 m ks)
                          (fselect-keys-6 m ks))
      {} [:a] {}
      {} [:a :b :c] {})))


#_(run-tests)


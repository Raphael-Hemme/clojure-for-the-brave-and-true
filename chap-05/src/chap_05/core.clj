(ns chap-05.core
  (rquire [clojure.set :as set])
  (:gen-class))

(declare successful-move prompt-move game-over query-rows)

(def board-structure
  {1 {:pegged true, :connections {6 3, 4 2}},
   2 {:pegged true, :connections {9 5, 7 4}},
   3 {:pegged true, :connections {10 6, 8 5}},
   4 {:pegged true, :connections {13 8, 11 7, 6 5, 1 2}},
   5 {:pegged true, :connections {14 9, 12 8}},
   6 {:pegged true, :connections {15 10, 13 9, 4 5, 1 3}},
   7 {:pegged true, :connections {9 8, 2 4}},
   8 {:pegged true, :connections {10 9, 3 5}},
   9 {:pegged true, :connections {7 8, 2 5}},
   10 {:pegged true, :connections {8 9, 3 6}},
   11 {:pegged true, :connections {13 12, 4 7}},
   12 {:pegged true, :connections {14 13, 5 8}},
   13 {:pegged true, :connections {15 14, 11 12, 6 9, 4 8}},
   14 {:pegged true, :connections {12 13, 5 9}},
   15 {:pegged true, :connections {13 14, 6 10}},
   :rows 5
   })

(defn tri*
  "Generates a lazy sequence of triangular numbers"
  ([] (tri* 0 1))
  ([sum n]
   (let [new-sum (+ sum n)]
     (cons new-sum (lazy-seq (tri* new-sum (inc n)))))))

(def tri (tri*))

(defn triangular?
  "Tests if the number is triangular: e.g. 1, 3, 6, 10, 15, etc"
  [n]
  (= n (last (take-while #(>= n %) tri))))

(defn row-tri
  "The triangular number at the end of row n"
  [n]
  (last (take n tri)))

(defn row-num
  "Returns row number the position belongs to: pos 1 in row 1,
   pos 2 and 3 in row 2 etc."
  [pos]
  (inc (count (take-while #(> pos %) tri))))

(defn connect
  "Form a mutual connection between two positions"
  [board max-pos pos neighbor destination]
  (if (<= destination max-pos)
    (reduce (fn [new-board [p1 p2]]
              (assoc-in new-board [p1 :connections p2] neighbor))
            board
            [[pos destination] [destination pos]])
    board))



(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

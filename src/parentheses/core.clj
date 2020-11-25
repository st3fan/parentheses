(ns parentheses.core)

;; Taken from clojure.core where it is private

(defmacro ^{:private true} assert-args
  [& pairs]
  `(do (when-not ~(first pairs)
         (throw (IllegalArgumentException.
                 (str (first ~'&form) " requires " ~(second pairs) " in " ~'*ns* ":" (:line (meta ~'&form))))))
       ~(let [more (nnext pairs)]
          (when more
            (list* `assert-args more)))))

(defmacro if-let+
  ([bindings then]
   `(if-let+ ~bindings ~then nil))
  ([bindings then else]
   `(assert-args
    (vector? bindings) "a vector for its binding")
   (if (seq bindings)
     `(if-let [~(first bindings) ~(second bindings)]
        (if-let+ ~(drop 2 bindings) ~then ~else)
        ~else)
     then)))

(defmacro when-let+
  [bindings & body]
  `(if-let+ ~bindings (do ~@body) nil))

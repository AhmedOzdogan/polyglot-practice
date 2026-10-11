# Lambdas and streams

Lambdas, functional interfaces, closures, stream pipelines. Files: `LambdasAndStreams.java`, `lambdasAndStreams.ts`, `lambdas_and_streams.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Sort a list of strings by length using a lambda.
2. From 1-20, keep the even numbers and square them (stream / array methods / comprehension).
3. Sum a list with `reduce` in each language.
4. **[Java]** Store a lambda in a variable and note that it needs a functional interface type. Use `Function`, `Predicate`, `Supplier`, `Consumer`, and `BiFunction` once each. Write your own `@FunctionalInterface`.
5. **[Java]** Increment a local variable from inside a lambda and read the "effectively final" error. Compare with closures in TS and Python (`nonlocal`).
6. Replace lambdas with method references (`String::toUpperCase`, `System.out::println`, a constructor reference). Compare with passing functions directly in TS and Python.
7. Group a list of words by first letter (`Collectors.groupingBy`, `Object.groupBy` (Node 21+) or `reduce`, `defaultdict`).
8. **[Java]** Reuse a stream after a terminal operation and read the exception. Then add a print inside `map` and run the pipeline without a terminal operation to observe laziness. Compare with Python generators.
9. Compose two functions with `andThen` and `compose` in Java; write a `compose` helper in TS and Python.
10. Given a list of orders (customer, amount), compute total per customer and print the top 3 in descending order, using a single pipeline per language.

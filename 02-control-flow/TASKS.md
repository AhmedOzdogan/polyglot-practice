# Control flow

Conditionals, loops, switch/match, pattern matching. Files: `ControlFlow.java`, `controlFlow.ts`, `control_flow.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Print 1-20, replacing multiples of 3 with "Fizz", of 5 with "Buzz", and of both with "FizzBuzz".
2. Iterate over a list of strings twice: once with an index-based loop, once with an enhanced-for / `for...of` / `for...in`.
3. Halve a number in a `while` loop until it drops below 1 and print the iteration count. Do it again with `do...while` where the language has one.
4. **[Java]** Use the integer `count` directly as an `if` condition and read the compile error. List the falsy values of TS and Python in NOTES.md.
5. **[Java]** Write an old-style `switch` statement with a missing `break` and observe fall-through. Rewrite it with arrow labels. Compare with TS `switch` and Python `match`.
6. **[Java]** Write a `switch` expression that maps a day name to "weekday"/"weekend" and assign its result to a variable. Use `yield` in a block branch.
7. Exit a nested loop early when a pair of numbers summing to a target is found. Use a labeled `break` in Java and TS; find the idiomatic Python alternative.
8. Given a mixed collection (`Object[]` / `unknown[]` / `list`) of ints, strings, and lists, print a different message per type. Use `instanceof` with a binding variable in Java, `typeof`/`Array.isArray` narrowing in TS, and `isinstance` in Python.
9. **[Java]** Redo the previous task with a `switch` using type patterns and a `when` guard (e.g. strings longer than 5). Redo it in Python with `match` class patterns and a guard.
10. Print a 1-9 multiplication table with right-aligned columns using `String.format`/`printf`, `padStart`, and f-string format specs.

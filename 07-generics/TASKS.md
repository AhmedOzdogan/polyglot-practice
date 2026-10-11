# Generics

Generic functions and classes, bounds, variance, erasure. Files: `Generics.java`, `generics.ts`, `generics.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Write a generic function returning the first element of a list, or an "empty" result when the list is empty.
2. Create a generic `Box<T>` class with `get` and `set`. In Python, use `TypeVar` + `Generic` (or 3.12 syntax if available).
3. Create a generic `Pair<A, B>` and a function that swaps it into a `Pair<B, A>`.
4. Write a generic `max` that only accepts comparable types (`T extends Comparable<T>`, a TS constraint, a Python `TypeVar` bound).
5. **[Java]** Declare a `List<int>`, read the error, then use `List<Integer>`. Note where autoboxing happens.
6. **[Java]** Inside a generic class, try `new T()` and `new T[10]` and read the errors. Print `getClass()` of a `List<String>` and a `List<Integer>`. Explain type erasure in NOTES.md and compare with TS (types erased) and Python (hints ignored at runtime).
7. **[Java]** Assign a `List<Integer>` to a `List<Number>` and read the error. Then write a sum method taking `List<? extends Number>` and an add method taking `List<? super Integer>`. Try the same assignment with TS arrays and note the unsoundness.
8. Write a generic `Stack<T>` with `push`, `pop`, `peek`, and `isEmpty`. Popping an empty stack must fail with a clear error.
9. Write a generic `map` function taking a list of `T` and a function `T -> R`, returning a list of `R`.
10. Introduce a deliberate type error into the Python version and catch it with `mypy` or `pyright`. Compare with when Java and TS report the same error.

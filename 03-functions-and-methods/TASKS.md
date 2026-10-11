# Functions and methods

Signatures, overloading, parameters, recursion, static vs instance. Files: `FunctionsAndMethods.java`, `functionsAndMethods.ts`, `functions_and_methods.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Write a function that returns the greater of two integers.
2. Write a function with an optional parameter that has a default value. Java has no default parameters: emulate it with overloading.
3. **[Java]** Overload a method `area` for a circle (one `double`) and a rectangle (two `double`s). Achieve the same in TS with overload signatures and in Python with defaults or `functools.singledispatch`.
4. Write a variadic function that sums any number of ints (varargs, rest parameter, `*args`). In Java, also call it with an existing array; in TS and Python, spread/unpack an existing list into it.
5. Python only: call a function using keyword arguments in a different order. Find what Java and TS do instead and note it.
6. **[Java]** Pass an `int` and an `int[]` to a method that modifies both, then print both in the caller. Repeat in TS and Python. Explain "pass by value of the reference" in NOTES.md.
7. **[Java]** Write one `static` and one instance method in the same class. Call the instance method directly from `main` and read the error, then fix it.
8. Return two values from a function: a tuple in Python, a tuple type or object in TS, and a `record` in Java.
9. Write a recursive factorial. Compute 20! and 25! and observe what happens with Java `long`, then switch to `BigInteger`. Compare with TS `number`, TS `bigint`, and Python `int`.
10. Write a function that takes another function and a value and applies the function twice. In Java, type the parameter with an interface from `java.util.function`.

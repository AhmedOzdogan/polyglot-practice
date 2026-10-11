# Exceptions

Throwing, catching, checked exceptions, resources, chaining. Files: `Exceptions.java`, `exceptions.ts`, `exceptions.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Divide by zero with ints and with floats in each language and note what happens.
2. Parse "abc" as an integer and handle the failure. Note that TS does not throw here.
3. Use `finally` to print a message whether or not an exception occurred, including when the `try` block returns early.
4. Create a custom `InsufficientFundsException` that carries the missing amount, throw it, and read the amount in the handler.
5. **[Java]** Throw a checked exception from a method without declaring it and read the error. Fix it with `throws`, then call the method from `main` without handling it. Record checked vs unchecked in NOTES.md.
6. **[Java]** Write a class implementing `AutoCloseable` that prints when closed, use it in try-with-resources, and throw inside the block. Compare with a Python context manager and TS `try/finally`.
7. Catch two different exception types in one handler (multi-catch, tuple, `instanceof` check).
8. Wrap a low-level exception in a higher-level one while preserving the cause. Print the stack trace and find the "Caused by" part (Java constructor cause, Python `raise ... from`, TS `{ cause }`).
9. **[Java]** In TS, throw a string and catch it; note the `unknown` type of the catch variable under strict mode. In Java, try to throw something that is not a `Throwable`.
10. Write a `retry` helper that calls a function up to N times and rethrows the last failure if all attempts fail.

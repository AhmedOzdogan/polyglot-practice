# Null handling

Null, undefined, None, Optional, safe access. Files: `NullHandling.java`, `nullHandling.ts`, `null_handling.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Declare a variable that may hold no value in each language.
2. Call a method on a null/undefined/None value and read the error in each language.
3. **[Java]** Assign `null` to a `String` in Java and to a `string` in TS strict mode. Note which compiler stops you.
4. Use TS optional chaining (`?.`) and nullish coalescing (`??`). Find the closest Java and Python equivalents. Note how Python `or` differs from `??` for falsy values.
5. **[Java]** Return `Optional<String>` from a lookup method. Consume it with `orElse`, `orElseThrow`, `map`, and `ifPresent`, never with an unchecked `get()`.
6. **[Java]** Call `Optional.of(null)` and read the exception, then use `ofNullable`. Try `Optional<int>` and find the primitive variant.
7. Guard a constructor against null arguments (`Objects.requireNonNull` in Java) and do the equivalent in TS and Python.
8. Distinguish "key missing" from "key present with null value" in a Java `HashMap`, a TS `Map`, and a Python `dict`.
9. Look up a user by id in a map, then their address, then their city, where any step may be missing. Chain it safely in each language and return a default city.
10. Annotate the Python version with `str | None` and use `mypy` or `pyright` to catch a missing None check.

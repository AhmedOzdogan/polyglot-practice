# Variables and types

Declarations, primitive types, numeric limits, strings. Files: `VariablesAndTypes.java`, `variablesAndTypes.ts`, `variables_and_types.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Create a variable holding your name as a string and print it.
2. Create a constant that cannot be reassigned and try to reassign it. Note what each language does (compile error, type error, or nothing).
3. Create an integer, add 1 to the maximum int value, and print the result. Compare Java `int`, TS `number`, and Python `int`.
4. Divide 7 by 2 with integer operands, then with floating-point operands. Compare the results across languages.
5. **[Java]** Declare one local variable with `var` and one with an explicit type. Try assigning a value of a different type to each. Compare with TS inference and Python's dynamic typing.
6. **[Java]** Assign a `long` larger than `Integer.MAX_VALUE` to an `int` without a cast, read the error, then add an explicit cast and print the result.
7. **[Java]** Build two equal strings at runtime (e.g. via concatenation in a method) and compare them with `==` and `.equals()`. Do the equivalent comparisons in TS and Python and note which compare by reference.
8. **[Java]** Compare two `Integer` objects holding 127 with `==`, then two holding 128. Record the reason for the difference in NOTES.md.
9. Build a multi-line string with a Java text block, a TS template literal, and a Python triple-quoted string. Interpolate a variable wherever the language supports it; find the Java alternative (`formatted`, `String.format`).
10. Print `0.1 + 0.2` in each language, then compute it exactly with `BigDecimal` (Java) and `Decimal` (Python). Find out what TS offers natively.

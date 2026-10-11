# Enums

Enums, enum fields and methods, enum-keyed collections. Files: `Enums.java`, `enums.ts`, `enums.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Define a `Direction` enum with NORTH, EAST, SOUTH, WEST and print all values.
2. Convert a string to a `Direction` and back. Handle an invalid string without crashing.
3. Switch/match over a `Direction` value, covering every case.
4. Add a `turnRight()` method returning the next direction, wrapping around from WEST to NORTH.
5. **[Java]** Give an enum fields and a constructor: `Planet` with mass and radius, plus a method computing surface gravity. Reproduce it in Python with a tuple-valued `Enum` and in TS with whatever fits best.
6. **[Java]** Create an `Operation` enum (PLUS, MINUS, TIMES) where each constant provides its own `apply(a, b)` body. Compare with how TS and Python can attach behavior per member.
7. Compare a TS numeric enum, a TS string enum, and a union of string literals. Print a numeric enum object to see the reverse mapping. Note Java's `ordinal()` and why relying on it is fragile.
8. **[Java]** Count occurrences of each `Direction` in a list using `EnumMap`, and collect a subset using `EnumSet`. Compare with Python `Counter` and a TS `Record<Direction, number>`.
9. Model file permissions (READ, WRITE, EXECUTE) as combinable flags: Python `enum.Flag`, Java `EnumSet`, TS numeric enum with bitwise operators. Check whether a combination contains WRITE.
10. Implement a traffic light state machine where each enum state knows its next state and its duration. Simulate 10 transitions and print the total elapsed time.

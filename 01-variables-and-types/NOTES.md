# Notes

## Differences I noticed

- **Constants:** Java `final` and TS `const` are compile errors on reassignment. Python `Final` is only checked by pyright; the code still runs.
- **Integer limits:** Java `int` (32-bit) silently wraps on overflow (`MAX_VALUE + 1` → `MIN_VALUE`). Python `int` has no limit. TS has no int type: `number` is a double, exact only up to `Number.MAX_SAFE_INTEGER`; `bigint` has no limit.
- **Division:** Java `int / int` is integer division (truncates toward zero). Python `/` always gives a float; `//` is integer division but floors (`-7 // 2` = -4, Java gives -3). TS has no integer division; `Math.trunc` matches Java.
- **Casting position matters in Java:** `(double) a / b` → 3.5, `(double) (a / b)` → 3.0.
- **Narrowing:** Java refuses `long` → `int` without an explicit cast. The cast keeps the low 32 bits (`(int) Long.MAX_VALUE` = -1).
- **`var`:** Java `var` infers the type once and it is fixed. Not related to JS `var` (use `let` in TS). Python variables can change type freely.
- **Equality:**
  - Java `==` compares references for objects, `.equals()` compares content. Always use `.equals()` for `String` and wrapper types.
  - Python `==` is value, `is` is identity.
  - TS `===` on strings/numbers compares values (primitives); objects compare by reference.
- **String pooling:** equal string literals are one shared object in Java, so `==` is true. `new String(...)` forces a separate object.
- **Integer cache:** Java caches `Integer` -128..127, so `Integer 127 == 127` is true and `128 == 128` is false. Python caches -5..256 and also shares equal literals within a file, so `is` showed "same" for 128 too.
- **Primitives vs objects (Java only):** `int` is a raw value (no null, no methods, not allowed in generics). `Integer` is an object (nullable, has methods, used in `List<Integer>`). Autoboxing converts between them; unboxing `null` throws `NullPointerException`.
- **Multi-line strings / interpolation:**
  - Python f-string and TS template literal interpolate directly.
  - Java text blocks (`"""`) cannot interpolate: use `%s` + `.formatted(...)` or `String.format`.
  - Text blocks strip common indentation and trailing spaces.
- **Floating point:** `0.1 + 0.2` = `0.30000000000000004` in all three. Exact: Java `BigDecimal`, Python `Decimal` (both created from a string, not a float). TS has nothing native: `toFixed` only rounds for display; use integer cents or a library.
- **Types at runtime:** TS types are erased; `tsx` runs code with type errors. Run `npm run typecheck` (TS) and `npm run typecheck:py` (Python) to see them.

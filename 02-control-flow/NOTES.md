# Notes

## Differences I noticed

- **Conditions / truthiness:**
  - Java: an `if` condition must be a `boolean`. `if (count)` → `incompatible types: int cannot be converted to boolean`; write `if (count != 0)`, `if (name != null)`, `if (!list.isEmpty())`.
  - Python falsy: `False`, `None`, `0`, `0.0`, `0j`, `Decimal(0)`, `Fraction(0)`, `""`, `()`, `[]`, `{}`, `set()`, `range(0)`.
  - TS falsy: `false`, `0`, `-0`, `0n`, `""`, `null`, `undefined`, `NaN`. Everything else is truthy, **including `[]` and `{}`** (opposite of Python).
- **Loops over values:**

  | Java | TS | Python |
  |---|---|---|
  | `for (String x : list)` | `for (const x of list)` | `for x in list:` |

  - TS `for...in` gives keys (array indices as strings); avoid it on arrays.
  - Index + value: Python `enumerate`; Java and TS use a classic index loop.
  - Java `List` uses `size()`/`get(i)`; arrays use `length`/`[i]`.
- **do...while:** Java and TS have it; Python does not (use `while True:` + `break`). The body always runs at least once.
- **switch fall-through:**
  - Java old-style `case X:` and TS `switch` fall through into the next case without `break`.
  - Java arrow `case X ->` never falls through, no `break` needed.
  - Python `match` never falls through; only the first matching case runs.
  - This repo's tsconfig has `noFallthroughCasesInSwitch`: a case with code and no `break` is a type error. Empty stacked cases are allowed.
- **switch as a value (Java only):** a switch expression returns a value: `String t = switch (day) { ... };`.
  - `case X -> value;` returns directly.
  - `case X -> { ...; yield value; }` — `yield` is required in a `{ }` block, in any case, not only `default`.
  - Must cover all values, so `default` is required for `String`/`int`.
  - Python `match` and TS `switch` cannot return a value.
- **Exiting nested loops:** labeled `break outer;` in Java and TS. Python has no labels: put the loops in a function and `return`, or use a flag.
- **Type checks:**
  - Java: `obj instanceof String s` → `s` is a `String` inside the branch (binding variable).
  - TS: `typeof x === "string"` narrows `x` itself; arrays need `Array.isArray` (`typeof []` is `"object"`, so is `typeof null`).
  - Python: `isinstance(x, str)`. `bool` is a subclass of `int`, so `isinstance(True, int)` is `True`.
- **Pattern switch:**
  - Java: `case String s when s.length() > 5 ->`. Python: `case str() if len(item) > 5:`. TS has no guards.
  - Java: the guarded case must come before the plain `case String s`, or it is a compile error (dominated label).
  - Java: a pattern switch on `Object` must be exhaustive → `default` required. Python/TS silently skip unmatched values.
  - Java: when switching on `Object`, constant labels (`case 0`, `case ""`) are not allowed; use `case Integer n when n == 0`. `case null` is allowed.
  - TS `switch` uses `===`, so `NaN` can never match a case (`NaN === NaN` is false).
- **Types in a mixed list:**
  - Java: `Object[]` or `List<Object>`; primitives are boxed (`Integer`, `Boolean`, `Character`).
  - TS: `unknown[]` (forces narrowing) or a union like `(number | string)[]`. Avoid `any[]`. `object` covers arrays and objects; `{}` as a type means "anything except null/undefined".
  - Java `'a'` is a `char`, `"a"` is a `String`. TS and Python have no char type.
- **Variable scope:** Java does not allow two variables with the same name in the same method scope (loop counters, pattern variables like `case Integer i`). Python just overwrites.
- **Printing on one line:**

  | | Same line | New line | Right-align width 3 |
  |---|---|---|---|
  | Python | `print(x, end="")` | `print()` | `f"{x:>3}"` |
  | Java | `System.out.print(x)` / `printf` | `System.out.println()` | `printf("%3d", x)` |
  | TS | build a string, then `console.log` once | `console.log` always ends the line | `String(x).padStart(3)` |

## Mistakes I made

- Python: wrote that Python's `for...in` is not like `for...of`; it is (TS's `for...in` is the odd one).
- Python: used `list.index(x)` inside a loop to get the index (slow, wrong with duplicates); use `enumerate`.
- Python: compared floats with `==` in the nested-loop task; the condition could never be true.
- TS: started FizzBuzz at 0.
- TS: `switch (value)` against `mixedList.every(...)` — compared each value to a boolean.
- TS: put `unknown` inside a union (it swallows the whole union), then `{}` failed against a union without `object`.
- Java: used `List.of(...)` with `null` → `NullPointerException`; `List.of` rejects null.
- Java: used constant labels (`case 0`, `case ""`) when switching on `Object`; forgot `default` in a pattern switch.
- Java: named a pattern variable the same as the loop variable (`case String obj`).
- Java: extra brace `}};` after a switch expression closed the `for` loop, so the variable "cannot be resolved".
- Java: stray text after a `}` gave `';' expected`.
- VS Code Java extension was checking with an older Java version and flagged pattern switches; fixed with `java.configuration.runtimes` set to JDK 21.

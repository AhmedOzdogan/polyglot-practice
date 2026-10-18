# Notes

## Differences I noticed

- **Type checks on parameters:**
  - Java: wrong argument types are a compile error, so no runtime check is needed.
  - TS: the type checker rejects wrong types, but `tsx` still runs the code. `NaN` is a `number`, so `Math.max(NaN, 10)` is `NaN`.
  - Python: type hints are not enforced at runtime. Check with `isinstance`, and exclude `bool` because it is a subclass of `int`.
- **Default parameters:**
  - TS and Python: `function f(a, b = 10)` / `def f(a, b=10)`.
  - Java has none: overload instead, and let the short version call the full one (`multiply(n)` → `multiply(n, 10)`).
  - Python evaluates a default once, when the function is defined, so a list default is shared between calls. TS creates the default fresh on every call.
- **Overloading:**
  - Java: real overloading, with several methods of the same name. Java picks one by the number and types of the arguments.
  - TS: overload *signatures* only describe the allowed calls. There is one implementation, which checks the arguments itself (`b === undefined`).
  - Python: `@overload` is for the type checker only; just the last `def` runs. `functools.singledispatch` picks by the type of the first argument, not by the number of arguments.
- **Variadic functions:**

  | | Declare | Pass an existing list |
  |---|---|---|
  | Java | `int... numbers` (an `int[]` inside) | pass the array directly: `mySum(arr)` |
  | TS | `...numbers: number[]` | spread: `mySum(...arr)` |
  | Python | `*args` (a tuple inside) | unpack: `my_sum(*lst)` |

  - Python also has `**kwargs`, which collects extra keyword arguments into a dict.
- **Keyword arguments:**
  - Python: arguments can be passed by name, in any order (`describe_person(city="Munich", age=30, name="Ahmed")`). Positional arguments must come before keyword ones.
  - TS: no keyword arguments. Pass one object and destructure it in the parameter list, with defaults.
  - Java: no keyword arguments. Use a parameter object (a `record`, still positional) or a builder (`.name("Ahmed").age(30).build()`).
- **Pass by value of the reference:**
  - All three languages pass a *copy* of what the variable holds.
  - For an `int`/`number`, that copy is the value itself, so changing it inside the function does not affect the caller.
  - For an array/list, that copy is a *reference* to the same array. Changing an element (`array[0] = 0`, `pop()`) changes the caller's array too.
  - Reassigning the parameter (`array = new int[3]`) only changes the local copy, not the caller's variable. That is why this is called "pass by value of the reference" and not "pass by reference".
  - Python: `num += 5` makes `num` point to a new `int`, because ints are immutable.
- **static vs instance (Java):**
  - A `static` method belongs to the class, so `main` (also static) can call it directly.
  - An instance method belongs to an object. Calling it from `main` gives `non-static method sayHello(String) cannot be referenced from a static context`. Create an object first: `new FunctionsAndMethods().sayHello("Ahmed")`.
  - Field and parameter with the same name: `this.name` is the field, `name` is the parameter.
- **Returning two values:**
  - Python: `return name, age` builds a tuple. Unpack it with `a, b = f()`.
  - TS: a tuple type `[string, number]` (brackets required: `return a, b` returns only `b`), or an object type with named fields. Destructure with `const [a, b]` / `const { name, age }`.
  - Java: no tuples. Declare a `record Identity(String name, int age)` and read it with `me.name()`. Java 21 record patterns can destructure it: `instanceof Identity(String n, int a)`.
- **Big numbers (25!):**

  | | 25! | Why |
  |---|---|---|
  | Java `long` | `7034535277573963776` (wrong) | overflows past `Long.MAX_VALUE` and wraps around silently |
  | TS `number` | `1.5511210043330986e+25` (rounded) | exact only up to 2^53, then loses precision |
  | Java `BigInteger` / TS `bigint` / Python `int` | `15511210043330985984000000` | arbitrary size |

  - The math must stay in the big type the whole way. Converting to `bigint` at the end is too late, because the precision is already lost.
  - `bigint` literals end in `n` (`1n`), and `bigint` cannot be mixed with `number` without `BigInt(x)`.
  - `BigInteger` has no operators: use `.multiply()`, `.add()`, and `BigInteger.ONE`.
- **Recursion:** a base case (`n <= 1` → `1`) plus a call with a smaller input (`n * factorial(n - 1)`). Without the base case: `StackOverflowError` (Java), `RangeError` (TS), `RecursionError` (Python, default limit about 1000 calls).
- **Passing functions as values:**

  | | Parameter type | Call it | Pass a named function | Inline function |
  |---|---|---|---|---|
  | Java | `Function<Integer, Integer>` | `f.apply(x)` | `FunctionsAndMethods::addTen` | `x -> x + 10` |
  | TS | `(x: number) => number` | `f(x)` | `addTen` | `(x) => x + 10` |
  | Python | `Callable[[int], int]` | `f(x)` | `add_ten` | `lambda x: x + 10` |

  - Java has no function types: a lambda fits any interface with exactly one abstract method (`java.util.function`). `UnaryOperator<Integer>` is the same as `Function<Integer, Integer>`. `IntUnaryOperator` avoids boxing and is called with `applyAsInt`.

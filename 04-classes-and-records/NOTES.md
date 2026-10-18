# Notes

## Differences I noticed

- **Declaring fields:**
  - Python: `self.x = x` in `__init__` creates the field. Anything in the class body is shared (a class variable).
  - TS: fields are declared in the class body (`x: number;`) and assigned in the constructor. With `strict`, a declared field that is never assigned is an error (`has no initializer and is not definitely assigned`).
  - TS shortcut: `constructor(public x: number)` declares and assigns in one step (also `private`, `readonly`). `#x` fields cannot use it.
  - Java: fields are declared in the class body with types. No shortcut, so every field needs `this.x = x`.
  - Java: field initializers run *before* the constructor body, so `int total = price * quantity;` as a field is always `0`.
- **Constructors:**
  - Java: same name as the class, no return type. Writing `void Point(...)` makes it an ordinary method.
  - Creating objects: `new Point(3, 4)` in Java and TS; Python has no `new`.
  - Arguments are matched by position in Java and TS. A missing argument shifts the rest (`new Book("Dune", 412, 18, 3)` put `412` into `name`). Python can pass them by name.
- **Privacy:**

  | | Blocked by | Way around it |
  |---|---|---|
  | Python `_x` | nothing (convention) | just read `_x` |
  | Python `__x` | name mangling | `obj._Class__x` |
  | TS `private` | compiler only | `(obj as any).x`; it also shows up in `console.log` |
  | TS / JS `#x` | the JS runtime | none; `(obj as any)["#x"]` is `undefined` |
  | Java `private` | compiler | only reflection |

  - "Outside" means any code not inside the class body. Getters work because they run inside the class.
  - Pylance flags `obj._Class__x` because it does not model name mangling; Python runs it fine.
- **Getters and setters:**
  - Python: `@property` is read like a field (`p.balance`). No `@x.setter` means read-only (`property 'balance' has no setter`).
  - TS: `get balance()` / `set balance(v)`, also read like a field. A getter without a setter throws on assignment at runtime (`Cannot set property balance ... which has only a getter`).
  - Java: no property syntax, so methods: `getBalance()`, `setBalance()`.
  - A setter is only worth writing when it checks something. A setter that just assigns is no better than a public field.
- **Static / class members:**
  - Python: a variable in the class body is shared. Update it through the class name (`Point.count += 1`). `self.count += 1` creates a new instance variable on that one object and leaves the class count at `0`.
  - A bare `count += 1` inside a method is an `UnboundLocalError`: methods don't see class-body names.
  - TS / Java: `static count = 0`, updated as `Point.count++`. Everything in the class body *without* `static` is per object.
  - `static` (who owns it) and `private` / `#` (who can see it) are independent: `static #count` is shared *and* hidden.
- **Printing an object:**

  | | Method | Default output | Called by |
  |---|---|---|---|
  | Java | `@Override public String toString()` | `Point@1b6d3586` | `println`, `"" + obj` |
  | Python | `__repr__` (developers), `__str__` (users) | `<__main__.Point object at 0x...>` | `print` uses `__str__`, falls back to `__repr__`; lists and the shell use `__repr__` |
  | TS | `toString()` (no `override` keyword without `extends`) | `[object Object]` | template literals, `+`, `String()`; **not** `console.log(obj)`, which shows Node's inspector view |

  - Java `toString` must be `public`. `@Override` catches typos like `tostring()`.
  - Python f-strings: `{x!r}` uses `repr` (adds quotes to strings).
- **Equality and hashing:**
  - All three compare objects by identity by default, so two `Point(3, 4)` objects are different.
  - A hash set first uses the hash to pick a bucket, then equality to compare inside it. Equal objects **must** have equal hashes, so both methods must use the same fields.
  - Java: override `equals(Object o)` (the parameter must be `Object`, or it is an overload that `HashSet` never calls) and `hashCode()` (`Objects.hash(...)`). Mismatched fields compile fine and fail silently.
  - Python: `__eq__` + `__hash__` (hash a tuple of the fields). Defining `__eq__` alone makes the class unhashable.
  - TS: a `Set` always uses identity for objects, with no hook to override. Workaround: a `Map` keyed by a string or number (`` `${x},${y}` ``, an id).
  - Java strings: `==` compares objects, not text. `"   ".trim() == ""` happened to be `true`, `new String("").trim() == ""` was `false`. Use `.equals()` / `.isBlank()`.
- **Records, dataclasses and readonly classes:**
  - Java `record Book(int id, String name)`: `private final` fields, the constructor, accessors `id()` / `name()` (no `get`), `equals`, `hashCode` and `toString`. Records are `final` and can't have extra instance fields, so derived values are methods (`totalPrice()`).
  - Python `@dataclass(frozen=True)`: `__init__`, `__repr__`, `__eq__`, and `__hash__` (only because it is frozen; a plain `@dataclass` sets `__hash__ = None`). `order=True` adds `<`, `>` etc.
    - `field(compare=False)` removes a field from `__eq__`, the hash, and ordering. `hash=False` alone is rare (unhashable or expensive fields). Never `compare=False` with `hash=True`.
    - `field(init=False)` + `object.__setattr__` in `__post_init__` for computed fields on a frozen class.
  - TS: nothing generated. A class with `readonly` fields plus a hand-written `toString`.
  - A record compares *all* fields; my Python `Book` compared only `id`.
- **Immutability:**

  | | Keyword | Stopped at | Notes |
  |---|---|---|---|
  | Java | `final` / record | compile time | from outside the record the error is `has private access`; inside it is `cannot assign a value to final variable` |
  | Python | `frozen=True` | runtime | `FrozenInstanceError: cannot assign to field 'id'` |
  | TS | `readonly` | `tsc` only | the write really happens at runtime; `Object.freeze` blocks it (throws in strict mode) |

  - All three are shallow: a `final` / `readonly` list can still be changed inside.
  - A frozen object can't have a working setter, so mutable things (a bank account) stay normal classes with controlled methods.
- **"Changing" an immutable object (`withX`):** return a new instance and leave the original alone. It goes through the constructor, so validation still applies.
  - Python: `dataclasses.replace(book, price=25)` does it for any field.
  - Java 21 and TS: no built-in, write a method. TS `{ ...obj }` gives a plain object, not a class instance.
- **Validation at construction:**
  - Python: `__post_init__` in a dataclass, raise `ValueError`.
  - Java record: compact constructor `Book { ... }` (no parameter list; fields are assigned after it runs; `this.x = x` is a compile error). Throw `IllegalArgumentException`.
  - TS: check the parameters at the top of the constructor, `throw new Error(...)`.
  - Check the *parameter*, not the field. Validate first, then assign.
  - TS: `NaN` and `Infinity` are `number`s and pass `x <= 0`. Use `!Number.isFinite(x)` (also covers `NaN`) and `Number.isInteger(x)`.
  - Java: check `null` before calling methods on a `String`. `int` overflow wraps silently into a negative number; `Math.addExact` throws instead. Python's `int` never overflows.
  - Money as integers (whole units or cents), never floats.
- **Nested classes (Java):**
  - `static class Address` inside `Library`: belongs to the class, created with `new Library.Address(...)`, can't read `Library`'s instance fields (`non-static variable name cannot be referenced from a static context`).
  - Inner `class Shelf`: tied to one `Library` object, created with `central.new Shelf(...)`, can read the outer object's fields, even private ones (`Library.this.name`). Without an outer object: `an enclosing instance that contains Library.Shelf is required`.
  - Default to `static`; every inner object keeps a reference to its outer object.
  - Python and TS nested classes always behave like Java's static nested classes.
- **One file, many classes (Java):** only one `public` top-level class per file, named like the file. Others go below it without `public`.

## Mistakes I made

- Python: `print(p.distance_to_origin)` without `()` printed the method object instead of calling it.
- Python: `repr(bob)` on its own line printed nothing; a script only shows what is passed to `print`.
- Python: counted students with a class variable in `__init__` instead of putting them in a set, so `__eq__` / `__hash__` were never used. Also reused one variable name for all four students.
- Python: dataclass flags inverted (`compare=False` on `id`, the only field that should be compared). The set kept all 10 books.
- Python: `len(self.name) < 0` can never be true.
- Python: `deposit` used `self._balance = amount` instead of `+=`. A single deposit from 0 hid the bug.
- TS: wrote fields like Python (`this.x = x`) without declaring them or typing the parameters.
- TS: tried `#z` as a constructor parameter.
- TS: checked `this.id` before assigning it, so the check always threw.
- TS: removed the `this.name = name` assignments while adding validation.
- TS: made `totalPrice` a constructor parameter with a default, so callers could pass a wrong total.
- TS: reused `book1` in Q8 after the Q6 demo had set its price to 0.
- TS: `Number.isFinite(amount)` without `!` rejected every normal amount and let `Infinity` through; misplaced parentheses put it inside `isNaN(...)`.
- Java: `Students.add(Student('jack', 10))`: missing `new`, single quotes are a `char`, missing `;`, uppercase variable name.
- Java: `Student.studentId` (the class) instead of `student.studentId` (the variable).
- Java: `equals` compared only `studentId` but `hashCode` also hashed `name`, so duplicates landed in different buckets.
- Java: `% s` in `String.format`; the space is a flag and threw `FormatFlagsConversionMismatchException`.
- Java: added `totalPrice` to the constructor parameters and then to the record header.
- Java: `name.trim() == ""` (twice). Use `isBlank()`.
- Java: `boolean[] isSame = {a == b}` made an array, which printed as `[Z@...`.
- Java: declared `Library` inside `BookRecord`'s braces.

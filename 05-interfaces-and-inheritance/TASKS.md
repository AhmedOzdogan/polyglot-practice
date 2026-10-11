# Interfaces and inheritance

Interfaces, abstract classes, overriding, sealed types. Files: `InterfacesAndInheritance.java`, `interfacesAndInheritance.ts`, `interfaces_and_inheritance.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Define a `Shape` interface with `area()` and `perimeter()` and implement `Circle` and `Rectangle`. In Python, use `abc.ABC`.
2. Store several shapes in a list typed as `Shape` and print the total area.
3. Create an abstract class `Animal` with an abstract `sound()` and a concrete `describe()` that uses it. Extend it twice and try to instantiate `Animal` directly.
4. Override a method in a subclass and call the parent implementation via `super`.
5. **[Java]** Add a `default` method to an interface. Then make a class implement two interfaces with conflicting default methods and resolve the conflict.
6. **[Java]** Try to make a class extend two classes and read the error. Compare with Python multiple inheritance and print a class's MRO.
7. **[Java]** In TS, create an object that satisfies `Shape` without `implements` and pass it where a `Shape` is expected. Try the same in Java. In Python, redo it with `typing.Protocol`. Note nominal vs structural typing in NOTES.md.
8. Mark a method with `@Override` and misspell its name. Compare with TS `override` (this repo enables `noImplicitOverride`) and Python `typing.override` (3.12+).
9. Mark a class `final` in Java and try to extend it. Find the closest TS and Python equivalents and whether they are enforced at runtime.
10. **[Java]** Make `Shape` a `sealed` interface permitting only your implementations. Write a `switch` over a `Shape` with no `default` branch, then add a new permitted shape and observe the error. Model the same closed set in TS with a discriminated union and exhaustiveness check.

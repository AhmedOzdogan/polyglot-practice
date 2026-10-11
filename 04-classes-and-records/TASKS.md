# Classes and records

Classes, encapsulation, equality, records, nested classes. Files: `ClassesAndRecords.java`, `classesAndRecords.ts`, `classes_and_records.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Create a `Point` class with `x` and `y`, a constructor, and a method returning the distance to the origin.
2. Make the fields private and add getters. Access a private field from outside and note what Java, TS (`private` vs `#field`), and Python (`_x`, `__x`) each do.
3. Add a static counter that tracks how many `Point` instances have been created.
4. Override `toString` / `__repr__` so printing a `Point` shows its coordinates. Note what TS prints for a plain class instance.
5. **[Java]** Put two equal points into a `HashSet` without overriding `equals`/`hashCode`, print the size, then override both and repeat. Compare with Python `__eq__`/`__hash__` and a TS `Set`.
6. **[Java]** Rewrite `Point` as a `record`. List in NOTES.md everything the compiler generates. Compare with a Python `@dataclass(frozen=True)` and a TS class with `readonly` fields.
7. Reject negative coordinates at construction: use a compact constructor in the Java record, `__post_init__` in Python, and the constructor in TS.
8. Add a `withX` method that returns a new instance instead of mutating. Try to mutate an existing instance in each language and note what stops you.
9. **[Java]** Inside one class, declare a `static` nested class and a non-static inner class. Instantiate both from `main` and note the syntax difference.
10. Create a `BankAccount` class with `deposit` and `withdraw` that never allows a negative balance. Invalid operations must fail loudly.

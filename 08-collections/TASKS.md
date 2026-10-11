# Collections

Lists, maps, sets, queues, iteration, immutability. Files: `Collections.java`, `collections.ts`, `collections.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Create a list of 5 numbers, then add, remove, and read elements by index.
2. **[Java]** Create lists with `List.of`, `Arrays.asList`, and `new ArrayList<>(...)`. Try `add` and `set` on each and record which ones throw. Compare with Python tuple vs list and TS `ReadonlyArray`.
3. **[Java]** Create an `int[]` and print it directly, then with `Arrays.toString`. Try to grow it. Note the array vs `List` split compared to TS and Python.
4. Count word occurrences in a sentence using a map (`HashMap` + `merge`/`getOrDefault`, TS `Map`, Python `dict`/`Counter`).
5. Print the entries of maps built from the same insertions: `HashMap`, `LinkedHashMap`, `TreeMap`, TS `Map`, Python `dict`. Note the iteration order of each.
6. Create a set from a list with duplicates and compute union, intersection, and difference with a second set.
7. Sort a list of people by age, then by name for equal ages (`Comparator.comparing(...).thenComparing(...)`, TS comparator, Python `key` with a tuple).
8. Use one structure as both a stack and a queue (`ArrayDeque`, `collections.deque`, TS array). Note the cost of removing from the front of a TS array.
9. **[Java]** Remove elements from an `ArrayList` inside a for-each loop and observe `ConcurrentModificationException`. Fix it with `Iterator.remove` and with `removeIf`. Try the same loop in TS and Python and note the behavior.
10. Implement an LRU cache with capacity 3: Java `LinkedHashMap` with access order, TS `Map` insertion order, Python `OrderedDict`. Verify the least recently used key is evicted.

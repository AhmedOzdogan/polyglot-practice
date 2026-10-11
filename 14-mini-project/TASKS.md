# Mini project

A CLI task tracker using everything so far. Build it in all three languages. Files: `MiniProject.java`, `miniProject.ts`, `mini_project.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Model a `Task` with id, title, priority enum (LOW, MEDIUM, HIGH), status enum (TODO, DONE), and an optional due date.
2. Define a `TaskRepository` interface with add, find by id, list, and delete. Provide an in-memory implementation.
3. Parse commands from program arguments: `add`, `list`, `done <id>`, `delete <id>`. An unknown command prints usage and exits with code 1.
4. Persist tasks to `tasks.csv` and load them at startup. A missing file means an empty list; a malformed line raises a custom exception with the line number.
5. Make `list` support `--status` filtering and sort by priority, then due date (tasks without a due date last).
6. Return lookups by id as `Optional`/`T | undefined`/`T | None` and handle the missing case in every command.
7. Add a `stats` command printing counts per status and per priority using grouping.
8. **[Java]** Keep the Java version runnable with `java MiniProject.java` while using multiple top-level types in one file. Note which type must come first and why.
9. **[Java]** Add an `import <file>...` command that loads several CSV files concurrently using virtual threads and merges them without losing tasks.
10. Write a self-check (Java `assert` run with `-ea`, plain TS assertions, Python `assert`) that exercises add, done, delete, and persistence round-tripping.

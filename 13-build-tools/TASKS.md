# Build tools

Maven/Gradle, npm, Python packaging. Java here runs through a build tool, not single-file mode. Files: `BuildTools.java`, `buildTools.ts`, `build_tools.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Create a Maven project in a `maven/` subfolder targeting Java 21. Move `BuildTools.java` into `src/main/java` under a package of your choice.
2. Build it with `mvn package` and run the jar with `java -jar`. Configure the main class so that works.
3. Add a JSON library (e.g. Jackson or Gson) as a dependency and serialize an object to JSON.
4. Add JUnit 5, write one passing and one failing test, and run `mvn test`.
5. **[Java]** Print the dependency tree (`mvn dependency:tree`) and find where Maven stores downloaded jars. Compare with `npm ls` / `node_modules` and `pip list` / `site-packages`.
6. Repeat tasks 1-4 with Gradle (Kotlin DSL) in a `gradle/` subfolder. Compare build file size and commands with Maven.
7. **[Java]** Produce a single runnable jar that includes its dependencies (e.g. `maven-shade-plugin`). Compare with how TS (bundling) and Python (wheels, `zipapp`) ship code.
8. Python: create a venv in this folder, add a `pyproject.toml`, install a dependency, and run one `pytest` test.
9. TS: add npm scripts to the root `package.json` for type-checking and running a file, then add a dependency that needs `@types/*`.

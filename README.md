# polyglot-practice

Practice the same topics in Java, TypeScript, and Python side by side. Focus: learning Java by contrast with TS and Python.

Each topic folder has `TASKS.md` (instructions), one stub per language, and `NOTES.md` for your observations. Tasks marked **[Java]** highlight where Java differs.

## Requirements

- Java 21 (JDK)
- Node.js 20+ (run `npm install` once)
- Python 3.10+

## Running

Java (single-file source mode, no compile step; except `13-build-tools`):

```sh
java 01-variables-and-types/VariablesAndTypes.java
```

TypeScript (strict mode):

```sh
npx tsx 01-variables-and-types/variablesAndTypes.ts
npm run typecheck   # tsx does not type-check; run this to see type errors
```

Python:

```sh
python3 01-variables-and-types/variables_and_types.py
```

## Topics

- [ ] [01-variables-and-types](01-variables-and-types/TASKS.md)
- [ ] [02-control-flow](02-control-flow/TASKS.md)
- [ ] [03-functions-and-methods](03-functions-and-methods/TASKS.md)
- [ ] [04-classes-and-records](04-classes-and-records/TASKS.md)
- [ ] [05-interfaces-and-inheritance](05-interfaces-and-inheritance/TASKS.md)
- [ ] [06-enums](06-enums/TASKS.md)
- [ ] [07-generics](07-generics/TASKS.md)
- [ ] [08-collections](08-collections/TASKS.md)
- [ ] [09-lambdas-and-streams](09-lambdas-and-streams/TASKS.md)
- [ ] [10-exceptions](10-exceptions/TASKS.md)
- [ ] [11-null-handling](11-null-handling/TASKS.md)
- [ ] [12-concurrency](12-concurrency/TASKS.md)
- [ ] [13-build-tools](13-build-tools/TASKS.md)
- [ ] [14-mini-project](14-mini-project/TASKS.md)

# Concurrency

Threads, executors, async, synchronization. TS is single-threaded: use async/Promises. Files: `Concurrency.java`, `concurrency.ts`, `concurrency.py`.

**[Java]** = highlights how Java differs from TS/Python.

1. Start two threads that each print their name 5 times and observe interleaving. In TS, simulate it with two async functions using `setTimeout`.
2. Increment a shared counter from 4 threads, 100,000 times each, without synchronization. Print the result. Fix it with `synchronized`, then with `AtomicInteger`. In Python, try it with and without `threading.Lock`.
3. Run 10 tasks that return results on a fixed thread pool (`ExecutorService` + `Future`, `concurrent.futures`, `Promise.all`).
4. **[Java]** Start 10,000 tasks that each sleep 1 second using virtual threads, measure the total time, and compare with a fixed pool of platform threads. Compare with Python `asyncio.gather` and TS `Promise.all`.
5. **[Java]** Chain async work with `CompletableFuture` (`supplyAsync`, `thenApply`, `thenCombine`, `exceptionally`). Rewrite it with `async/await` in TS and Python.
6. Implement a producer-consumer with a bounded queue (`BlockingQueue`, `queue.Queue` or `asyncio.Queue`). In TS, build a minimal async queue yourself.
7. **[Java]** Count words from multiple threads into a `HashMap`, then into a `ConcurrentHashMap` using `merge`. Compare the results.
8. Run 5 tasks with random durations and cancel those that exceed 500 ms (`orTimeout`/`Future.cancel`, `asyncio.wait_for`, `Promise.race` + `AbortController`).
9. Create a deadlock with two locks acquired in opposite order. Inspect it with `jstack` (Java) and then fix it with consistent lock ordering.

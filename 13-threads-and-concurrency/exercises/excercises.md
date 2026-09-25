# Practical Exercises

Try these before looking at the answer key.

## Concurrency exercises

### Exercise 1 — Start two threads

Create two threads:
- First prints numbers 1 to 5.
- Second prints letters A to E.
- Start both from `main`.

Questions:
1. Does output always appear in the same order?
2. Why or why not?
3. What is the difference between `start()` and `run()`?

### Exercise 2 — Runnable vs Callable

1. Create a `Runnable` that prints `"Processing order"`.
2. Create a `Callable<Integer>` that returns `100 + 200`.
3. Submit the `Callable` to a single-thread executor.
4. Retrieve the result using `Future.get()`.
5. Shut down the executor.

### Exercise 3 — Fix a race condition

Create two threads that each increment this counter 100,000 times:

```java
class Counter {
    private int count;
    public void increment() {
        count++;
    }
    public int getCount() {
        return count;
    }
}
```

Fix it using:
1. `synchronized`
2. `AtomicInteger`

Expected final result: `200000`.

### Exercise 4 — Producer and consumer

Use a `BlockingQueue<String>`:
1. A producer adds three job names.
2. A consumer takes and prints each job.
3. Explain what happens when `take()` is called on an empty queue.

### Exercise 5 — CompletableFuture pipeline

Create an asynchronous pipeline that:
1. Supplies `10`.
2. Multiplies it by `5`.
3. Adds `20`.
4. Prints the final result.

Expected result: `70`.

### Exercise 6 — Identify the deadlock

Two threads acquire two locks in opposite orders.

Questions:
1. Why can this cause deadlock?
2. What is one way to prevent it?
3. Which diagnostic tool can help confirm a deadlock in a running JVM?
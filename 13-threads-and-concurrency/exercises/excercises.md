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

## Concurrency answers

### Exercise 1

```java
public class TwoThreadsExample {
    public static void main(String[] args) {
        Thread numbers = new Thread(() -> {
            for (int i = 1; i <= 5; i++) System.out.println(i);
        });

        Thread letters = new Thread(() -> {
            for (char c = 'A'; c <= 'E'; c++) System.out.println(c);
        });

        numbers.start();
        letters.start();
    }
}
```

Order is not guaranteed because the scheduler decides when each thread runs. `start()` starts a new thread; `run()` called directly does not.

### Exercise 2

```java
import java.util.concurrent.*;

public class RunnableCallableAnswer {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Processing order");
        Callable<Integer> callable = () -> 100 + 200;

        ExecutorService executor = Executors.newSingleThreadExecutor();
        try {
            executor.submit(runnable);
            Future<Integer> future = executor.submit(callable);
            System.out.println(future.get()); // 300
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException exception) {
            System.out.println("Task failed: " + exception.getCause());
        } finally {
            executor.shutdown();
        }
    }
}
```

### Exercise 3 — `synchronized`

```java
class SynchronizedCounter {
    private int count;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

Call `getCount()` after both worker threads complete using `join()`.

### Exercise 3 — `AtomicInteger`

```java
import java.util.concurrent.atomic.AtomicInteger;

class AtomicCounter {
    private final AtomicInteger count = new AtomicInteger();

    public void increment() {
        count.incrementAndGet();
    }

    public int getCount() {
        return count.get();
    }
}
```

### Exercise 4

```java
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerAnswer {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new LinkedBlockingQueue<>();

        Thread producer = new Thread(() -> {
            try {
                queue.put("Email");
                queue.put("Report");
                queue.put("Invoice");
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Processing: " + queue.take());
                }
            } catch (InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();
        producer.join();
        consumer.join();
    }
}
```

`take()` waits until an item becomes available if the queue is empty.

### Exercise 5

```java
CompletableFuture<Integer> result =
        CompletableFuture.supplyAsync(() -> 10)
                .thenApply(number -> number * 5)
                .thenApply(number -> number + 20);

System.out.println(result.join()); // 70
```

### Exercise 6

Each thread can hold one lock while waiting for the other, creating circular wait. Acquire locks in a consistent order to prevent this. A thread dump can help identify the deadlock.

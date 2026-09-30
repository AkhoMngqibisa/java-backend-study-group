# Concurrency

## 1. What is concurrency?

**Concurrency** means structuring a program so multiple tasks can make progress during overlapping periods of time. **Parallelism** means multiple tasks are literally executing at the same instant, usually on different CPU cores.

- Concurrency is about managing multiple tasks.
- Parallelism is about simultaneous execution.
- A concurrent program is not necessarily parallel; one CPU core can switch between tasks.

**Analogy:** A restaurant has several orders in progress. Chefs and waiters are like threads. Shared ingredients are shared resources. If two chefs update the same stock count without coordination, the result may be wrong.

## 2. Processes vs threads

A **process** is an executing program with its own address space and operating-system resources. A **thread** is an execution path within a process. Threads in one process share heap memory and other process resources, but each thread has its own stack and execution state.

| Feature | Process | Thread |
|---|---|---|
| Memory | Separate address space | Shares process memory |
| Communication | Often needs inter-process communication | Can use shared objects |
| Overhead | Generally heavier | Generally lighter |
| Isolation | Stronger failure isolation | A thread failure can affect its process |
| Java example | Separate JVM process | `Thread` inside a JVM |

## 3. Creating a thread with `Thread`

```java
public class ThreadExample {
    public static void main(String[] args) {
        Thread worker = new Thread(() ->
                System.out.println("Running on: " +
                        Thread.currentThread().getName())
        );

        worker.start();

        System.out.println("Running on: " +
                Thread.currentThread().getName());
    }
}
```

- `start()` asks the JVM to start a new thread; that thread later invokes `run()`.
- Calling `run()` directly is an ordinary method call on the current thread. It does not start a new thread.
- Thread scheduling is not deterministic. Do not assume a fixed output order without coordination.

## 4. Thread lifecycle

Java's `Thread.State` defines these states:

| State | Meaning |
|---|---|
| `NEW` | Created, but `start()` has not been called |
| `RUNNABLE` | Eligible to run or currently running |
| `BLOCKED` | Waiting to acquire an intrinsic monitor lock |
| `WAITING` | Waiting indefinitely for another action |
| `TIMED_WAITING` | Waiting for a limited time |
| `TERMINATED` | Execution has completed |

```text
             new Thread()
                  |
                  v
                 NEW
                  |
                start()
                  |
                  v
              RUNNABLE
             /    |     \
       lock wait  wait()  sleep()/timed wait
           |        |          |
           v        v          v
        BLOCKED  WAITING  TIMED_WAITING
             \      |        /
              \ eligible   /
                  |
             run() ends
                  |
                  v
              TERMINATED
```

A thread in `RUNNABLE` may be executing or waiting for CPU time. Java does not expose a separate `RUNNING` state.

## 5. `Runnable` vs `Callable`

| Feature | `Runnable` | `Callable<V>` |
|---|---|---|
| Main method | `run()` | `call()` |
| Returns a result | No | Yes, type `V` |
| Can declare checked exceptions | No | Yes |
| Typical use | Task with no result | Task that returns a result |

```java
Runnable task = () -> System.out.println("Processing order");
new Thread(task).start();
```

```java
import java.util.concurrent.Callable;

Callable<Integer> calculation = () -> 10 + 20;
try {
    Integer result = calculation.call();
    System.out.println(result); // 30
} catch (Exception exception) {
    exception.printStackTrace();
}
```

In applications, a `Callable` is usually submitted to an executor rather than calling `call()` directly.

## 6. `ExecutorService` and thread pools

An **executor** separates task submission from thread-management details. A **thread pool** reuses a managed group of worker threads instead of creating a new thread for every task.

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        try {
            for (int i = 1; i <= 5; i++) {
                int taskNumber = i;
                executor.submit(() ->
                        System.out.println("Task " + taskNumber +
                                " on " + Thread.currentThread().getName())
                );
            }
        } finally {
            executor.shutdown();
        }
    }
}
```

- `newFixedThreadPool(2)` creates a pool with two worker threads.
- `submit()` schedules a task.
- `shutdown()` rejects new tasks but allows submitted tasks to finish.
- `shutdownNow()` attempts to interrupt active tasks and returns tasks that never started; it cannot guarantee every task stops.

**Best practice:** Ensure executors are shut down. The `try/finally` pattern is portable in Java 17.

## 7. `Future`

A `Future<V>` represents the result of an asynchronous computation that may not have completed.

```java
import java.util.concurrent.*;

public class FutureExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        try {
            Future<Integer> future = executor.submit(() -> {
                Thread.sleep(500);
                return 42;
            });

            System.out.println("Task submitted...");
            Integer result = future.get(); // waits for completion
            System.out.println("Result: " + result);
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

Useful methods:
- `get()` waits and returns the result.
- `isDone()` checks completion.
- `cancel(true)` attempts cancellation and may interrupt the task.
- `get()` can throw `InterruptedException` or `ExecutionException`.

**Limitation:** Calling `get()` too early blocks the calling thread. `Future` does not provide a rich chain of dependent operations.

## 8. `CompletableFuture`

`CompletableFuture` supports asynchronous pipelines and combining results.

```java
import java.util.concurrent.CompletableFuture;

public class CompletableFutureExample {
    public static void main(String[] args) {
        CompletableFuture<Integer> future =
                CompletableFuture.supplyAsync(() -> 20)
                        .thenApply(number -> number * 2)
                        .thenApply(number -> number + 5);

        System.out.println(future.join()); // 45
    }
}
```

| Method | Purpose |
|---|---|
| `supplyAsync()` | Runs a task that returns a value |
| `runAsync()` | Runs a task with no result |
| `thenApply()` | Transforms a successful result |
| `thenAccept()` | Consumes a successful result |
| `thenCompose()` | Chains another asynchronous operation |
| `thenCombine()` | Combines two independent results |
| `exceptionally()` | Recovers from an error with a fallback |
| `handle()` | Handles either a result or an error |

Combining tasks:

```java
CompletableFuture<Integer> price =
        CompletableFuture.supplyAsync(() -> 100);
CompletableFuture<Integer> delivery =
        CompletableFuture.supplyAsync(() -> 25);

CompletableFuture<Integer> total =
        price.thenCombine(delivery, Integer::sum);

System.out.println(total.join()); // 125
```

Handling a failure:

```java
CompletableFuture<Integer> result =
        CompletableFuture.supplyAsync(() -> {
            throw new IllegalStateException("Service unavailable");
        }).exceptionally(exception -> 0);

System.out.println(result.join()); // 0
```

Many async methods use the common ForkJoin pool by default. For blocking I/O or application-specific workloads, consider supplying an appropriately configured executor.

## 9. Race conditions

A **race condition** occurs when correctness depends on the unpredictable timing or interleaving of concurrent operations.

`count++` is not one indivisible operation. It involves reading, adding, and writing. Two threads can read the same old value and overwrite one another's updates.

```java
public class RaceConditionExample {
    static class Counter {
        int count = 0;
        void increment() {
            count++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        Thread first = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) counter.increment();
        });
        Thread second = new Thread(() -> {
            for (int i = 0; i < 100_000; i++) counter.increment();
        });

        first.start();
        second.start();
        first.join();
        second.join();

        System.out.println("Expected: 200000");
        System.out.println("Actual: " + counter.count);
    }
}
```

The actual result may be less than 200,000. It is nondeterministic.

## 10. Synchronization and `synchronized`

Synchronization coordinates access to shared mutable state. A `synchronized` method or block uses an object's intrinsic monitor. Only one thread at a time can hold that same monitor.

```java
class SafeCounter {
    private int count;

    public synchronized void increment() {
        count++;
    }

    public synchronized int getCount() {
        return count;
    }
}
```

A synchronized block can protect a smaller critical section:

```java
synchronized (lockObject) {
    // access shared state safely
}
```

All threads must use the **same lock object** to coordinate access to the same state. Synchronization also provides memory-visibility guarantees between threads that release and subsequently acquire the same monitor.

## 11. Locks

The `java.util.concurrent.locks` package provides explicit locking tools.

```java
import java.util.concurrent.locks.ReentrantLock;

class LockedCounter {
    private int count;
    private final ReentrantLock lock = new ReentrantLock();

    public void increment() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }
}
```

Unlock in `finally` so an exception does not accidentally leave the lock held.

| `synchronized` | `ReentrantLock` |
|---|---|
| Language keyword | Explicit lock API |
| Released automatically when block exits | Must call `unlock()` |
| Simple and concise | More advanced operations |
| No timed `tryLock()` | Supports `tryLock()` and timed acquisition |

Use the simplest mechanism that meets the requirement.








## Concurrency

| Concept | Remember |
|---|---|
| Process | Independent running program |
| Thread | Execution path inside a process |
| `Runnable` | Task with no return value |
| `Callable` | Task that returns a value and can throw checked exceptions |
| `ExecutorService` | Manages task execution |
| Thread pool | Reuses worker threads |
| `Future` | Represents a pending or completed result |
| `CompletableFuture` | Supports asynchronous pipelines |
| Race condition | Result depends on timing/interleaving |
| `synchronized` | Uses an object's intrinsic monitor |
| `Lock` | Explicit locking API |
| Deadlock | Threads wait on locks held by each other |
| Atomic variable | Thread-safe atomic operations on a variable |
| Concurrent collection | Collection designed for concurrent access |
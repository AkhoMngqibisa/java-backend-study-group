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
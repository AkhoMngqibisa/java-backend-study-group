# Concurrency

## 1. What is concurrency?

**Concurrency** means structuring a program so multiple tasks can make progress during overlapping periods of time. **Parallelism** means multiple tasks are literally executing at the same instant, usually on different CPU cores.

- Concurrency is about managing multiple tasks.
- Parallelism is about simultaneous execution.
- A concurrent program is not necessarily parallel; one CPU core can switch between tasks.

**Analogy:** A restaurant has several orders in progress. Chefs and waiters are like threads. Shared ingredients are shared resources. If two chefs update the same stock count without coordination, the result may be wrong.

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
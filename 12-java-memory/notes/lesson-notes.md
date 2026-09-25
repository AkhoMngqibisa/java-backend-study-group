# JVM (Java Virtual Machine)

## 1. What is the JVM?

The **Java Virtual Machine** is the runtime environment that loads and executes Java bytecode.

The Java compiler (`javac`) compiles source code (`.java`) into bytecode (`.class`). The JVM loads and executes that bytecode.

```text
Java source (.java)
        |
      javac
        |
        v
Java bytecode (.class)
        |
    Class Loader
        |
 Bytecode verification
        |
 Runtime execution
 Interpreter + JIT compiler
        |
 Operating system / hardware
```

The JVM is a specification; different vendors provide JVM implementations.

## 2. JVM architecture

Three major areas:

1. **Class Loader Subsystem** — loads class definitions.
2. **Runtime Data Areas** — memory used during execution.
3. **Execution Engine** — executes bytecode using an interpreter and Just-In-Time (JIT) compilation.

The JVM can also interact with native libraries through the Java Native Interface (JNI).

```text
                  JVM
  ┌─────────────────────────────────────┐
  │          Class Loader               │
  │ Loading → Linking → Initialization │
  ├─────────────────────────────────────┤
  │          Runtime Data Areas          │
  │ Heap | Java Stacks | Metaspace      │
  │ PC Registers | Native Method Stacks │
  ├─────────────────────────────────────┤
  │          Execution Engine            │
  │ Interpreter | JIT Compiler | GC     │
  └─────────────────────────────────────┘
```

## 3. Class loading

The class loader subsystem loads class definitions into the JVM.

Main stages:
1. **Loading** — finds class bytecode and creates its runtime representation.
2. **Linking** — verifies bytecode, prepares class-level structures, and resolves symbolic references as needed.
3. **Initialization** — executes static initializers and assigns explicit static field values.

Common built-in class loaders:
- Bootstrap Class Loader
- Platform Class Loader
- Application (System) Class Loader

Java uses delegation: a class loader normally asks its parent to load a class before attempting to load it itself.

```java
public class ClassLoaderExample {
    public static void main(String[] args) {
        Class<?> type = java.util.ArrayList.class;
        System.out.println(type.getName());
        System.out.println(type.getClassLoader());
    }
}
```

For classes loaded by the bootstrap loader, `Class.getClassLoader()` returns `null`.

## 4. JVM memory areas

### Heap

The **heap** is the runtime area from which objects and arrays are generally allocated. It is shared across threads and managed by the garbage collector.

```java
Customer customer = new Customer();
```

The `Customer` object is generally allocated on the heap. The local variable `customer` is a reference held in the current method's frame.

### Java stack

Each thread has its own Java stack. Each method invocation creates a stack frame containing information such as local variables, intermediate computations, and return information.

```java
public static void main(String[] args) {
    int age = 28;
    calculate(age);
}

static void calculate(int value) {
    int result = value * 2;
}
```

Each method call uses a stack frame. When the method returns, its frame is removed. Deep or infinite recursion can cause `StackOverflowError`.

### Metaspace

Metaspace stores class metadata and uses native memory rather than the Java heap. It replaced PermGen in Java 8. Class-loader leaks or repeatedly loading classes can cause excessive Metaspace use.

### Other runtime areas

- **PC Register:** Each Java thread has its own register tracking the current JVM instruction for that thread.
- **Native Method Stack:** Supports execution of native methods.

| Area | Stores | Shared? |
|---|---|---|
| Heap | Objects and arrays | Shared |
| Java stack | Per-thread method frames | No; each thread has its own |
| Metaspace | Class metadata | Shared JVM area |
| PC register | Current instruction state | Per thread |

**Important:** Do not assume every local variable is always physically stored on the stack or every object always remains on the heap. JIT optimisations can change physical allocation while preserving observable Java behaviour.


## 5. Garbage collection (GC)

Garbage collection automatically reclaims heap memory occupied by objects that are no longer reachable.

```java
public class GarbageCollectionExample {
    public static void main(String[] args) {
        Customer customer = new Customer();
        customer = null; // original object may now be eligible for GC
    }
}
class Customer { }
```

Setting a reference to `null` does not force collection. The object is eligible if no other reachable references keep it alive.

`System.gc()` is only a request or suggestion to the JVM; it does not guarantee immediate collection.

## 6. Generational garbage collection

Many collectors use the observation that many objects become unreachable soon after creation.

Simplified conceptual model:

```text
                 Heap
        ┌────────────────────┐
        │    Young Gen       │
        │  Eden + Survivors  │
        ├────────────────────┤
        │     Old Gen        │
        └────────────────────┘
```

- New objects are commonly allocated in the young generation.
- Objects that survive collections may be promoted to the old generation.
- A young-generation collection is often called a minor collection.
- Collections involving old-generation space are often called major or old-generation collections; terminology and behavior vary by collector.

This is a conceptual model. Exact heap organization depends on the collector.

## 7. GC algorithms and collectors

| Collector / approach | General characteristics |
|---|---|
| Serial GC | Uses one GC thread; useful for smaller heaps or simple workloads |
| Parallel GC | Uses multiple threads to increase throughput |
| G1 GC | Divides heap into regions and aims for predictable pause targets |
| ZGC | Low-latency collector designed for very short pauses |
| Shenandoah | Low-pause collector that performs much work concurrently |

Algorithm concepts:
- **Mark:** Identify reachable objects.
- **Sweep:** Reclaim memory occupied by unreachable objects.
- **Compact:** Move live objects together to reduce fragmentation.
- **Copying:** Copy live objects from one region to another.

Choose a collector based on measured throughput, latency, heap use, and pause times—not its name alone.

## 8. JVM tuning

JVM tuning means configuring runtime settings to meet an application's performance and resource requirements.

| Option | Meaning |
|---|---|
| `-Xms` | Initial/minimum heap size |
| `-Xmx` | Maximum heap size |
| `-Xss` | Thread stack size |
| `-XX:+UseG1GC` | Select G1 GC |
| `-Xlog:gc*` | Enable detailed GC logging on modern JVMs |

Example:

```bash
java -Xms512m -Xmx2g -XX:+UseG1GC -Xlog:gc* -jar app.jar
```

This sets a 512 MB initial heap, a 2 GB maximum heap, selects G1, and enables detailed GC logs.

These are examples, not universal recommendations. Choose settings based on workload and container limits. The JVM also needs native memory for thread stacks, Metaspace, code cache, and other structures; `-Xmx` is not total process memory.

## 9. Profiling

**Profiling** measures application runtime behaviour to locate performance problems.

Investigate:
- High CPU usage.
- Slow methods.
- Excessive object allocation.
- Long GC pauses.
- Lock contention.
- Thread bottlenecks.

Common tools:
- **Java Flight Recorder (JFR):** Low-overhead event recording built into modern JDKs.
- **Java Mission Control (JMC):** Analyses JFR recordings.
- **VisualVM:** Inspects CPU, memory, threads, and heap.
- **JDK profilers:** Help analyse runtime behaviour.

Measure first, identify the bottleneck, change one thing, and measure again.

## 10. Memory leaks

A Java memory leak occurs when an application unintentionally keeps references to objects it no longer needs. Because they remain reachable, the garbage collector cannot reclaim them.

Common causes:
- Static collections that grow without bounds.
- Caches without eviction policies.
- Event listeners that are never removed.
- Long-lived objects retaining large object graphs.
- Incorrect `ThreadLocal` lifecycle management.

Example:

```java
import java.util.ArrayList;
import java.util.List;

public class MemoryLeakExample {
    private static final List<byte[]> CACHE = new ArrayList<>();

    public static void addData() {
        CACHE.add(new byte[1024 * 1024]); // about 1 MB per call
    }
}
```

The static list retains every array added. If it grows continuously, heap use can grow until the application struggles or throws `OutOfMemoryError`.

Investigate by checking whether heap use keeps growing after GC, capturing a heap dump, and examining dominator trees and paths to GC roots.

## 11. Thread dumps

A **thread dump** is a snapshot of thread states and stack traces at a particular moment.

Useful for diagnosing:
- Deadlocks.
- Threads blocked on locks.
- Threads waiting for work.
- High-CPU threads.
- Application hangs.

Commands:

```bash
jcmd <pid> Thread.print
```

Alternative:

```bash
jstack <pid>
```

A dump can show `RUNNABLE`, `BLOCKED`, and `WAITING` states, stack traces, and lock information. For a high-CPU issue, take multiple dumps a short time apart and compare stacks. One dump is only a snapshot.

## 12. Heap dumps

A **heap dump** is a snapshot of objects in the Java heap and their references.

Useful for diagnosing:
- Memory leaks.
- Unexpectedly large collections.
- Objects retaining other objects.
- `OutOfMemoryError` problems.

```bash
jcmd <pid> GC.heap_dump /path/to/heapdump.hprof
```

Heap dumps can be large and may contain sensitive application data. Store and share them securely, and ensure enough disk space is available.

VisualVM and Eclipse MAT (Memory Analyzer Tool) can help analyse heap dumps.

### Thread dump vs heap dump

| Thread dump | Heap dump |
|---|---|
| Snapshot of threads | Snapshot of heap objects |
| Shows states and stack traces | Shows objects and references |
| Useful for deadlocks and hangs | Useful for memory leaks |
| Usually smaller | Can be very large |

---

## JVM

| Concept | Remember |
|---|---|
| JVM | Loads and executes Java bytecode |
| Class loader | Loads class definitions |
| Heap | Stores objects and arrays |
| Stack | Holds per-thread method frames |
| Metaspace | Stores class metadata |
| Garbage collection | Reclaims unreachable objects |
| JIT | Compiles frequently executed bytecode to native code |
| Profiling | Measures runtime behaviour |
| Memory leak | Unneeded objects remain reachable |
| Thread dump | Snapshot of threads and stack traces |
| Heap dump | Snapshot of heap objects and references |
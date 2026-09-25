# Practical Exercises

Try these before looking at the answer key.

## JVM exercises

### Exercise 7 — Identify the memory area

Match each item to Heap, Java Stack, Metaspace, or PC Register:

1. An object created using `new Customer()`.
2. A method's local primitive variable.
3. Class metadata.
4. The current bytecode instruction for a thread.
5. A method invocation's stack frame.

### Exercise 8 — Garbage collection

```java
Customer first = new Customer();
Customer second = first;
first = null;
```

Questions:
1. Is the object eligible for GC after `first = null`?
2. What changes if `second = null` is also executed and no other references exist?
3. Does `System.gc()` guarantee immediate collection?

### Exercise 9 — Diagnose a memory leak

A static `ArrayList` stores every request object and never removes old entries.

Questions:
1. Why might GC not reclaim these objects?
2. What evidence would you look for in a heap dump?
3. Suggest two design fixes.

### Exercise 10 — Thread dump or heap dump?

Choose the more appropriate artifact:
1. Application is hanging and threads appear blocked.
2. Heap usage grows continuously and the app throws `OutOfMemoryError`.
3. You need to inspect which objects retain a large collection.
4. You need thread stack traces and lock ownership.

### Exercise 11 — JVM options

Explain:

```bash
-Xms512m
-Xmx2g
-Xss1m
-XX:+UseG1GC
-Xlog:gc*
```

### Exercise 12 — Tool selection

Choose a suitable tool or command:
1. Print thread information for a running JVM.
2. Capture a heap dump.
3. Record runtime events for performance investigation.
4. Analyse a heap dump for retained objects and paths to GC roots.

---


## JVM answers

### Exercise 7

1. Object — Heap.
2. Local primitive variable — normally represented in the method's Java stack frame (subject to JIT optimisation).
3. Class metadata — Metaspace.
4. Current instruction — PC Register.
5. Method invocation frame — Java Stack.

### Exercise 8

1. No. `second` still refers to the object.
2. If no other reachable references exist, the object becomes eligible for collection.
3. No. `System.gc()` does not guarantee immediate collection.

### Exercise 9

The static list remains reachable and retains its elements. In a heap dump, inspect the list's retained size, entries, and paths to GC roots. Fixes include bounded caches with eviction, removing completed request objects, and avoiding unnecessary static references.

### Exercise 10

1. Thread dump.
2. Heap dump (GC logs are also useful).
3. Heap dump.
4. Thread dump.

### Exercise 11

- `-Xms512m`: initial/minimum Java heap size.
- `-Xmx2g`: maximum Java heap size.
- `-Xss1m`: thread stack size.
- `-XX:+UseG1GC`: selects G1 GC.
- `-Xlog:gc*`: enables detailed GC logging on modern JVMs.

### Exercise 12

1. `jcmd <pid> Thread.print` or `jstack <pid>`.
2. `jcmd <pid> GC.heap_dump /path/to/dump.hprof`.
3. Java Flight Recorder (JFR).
4. Eclipse MAT (Memory Analyzer Tool), or another heap-dump analyser.

---

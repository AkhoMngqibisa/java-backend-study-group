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

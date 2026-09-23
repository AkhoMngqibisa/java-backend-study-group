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
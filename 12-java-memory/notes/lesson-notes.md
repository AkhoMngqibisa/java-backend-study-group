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
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

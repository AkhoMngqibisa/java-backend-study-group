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
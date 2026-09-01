# Lesson 01 — Java Ecosystem, JVM, JDK & JRE

## Learning Objectives

By the end of this lesson, we should be able to explain:

* What Java is
* What the Java ecosystem consists of
* What the JVM is
* What the JDK is
* What the JRE is
* The difference between JVM, JDK and JRE
* How Java source code becomes executable
* What bytecode is
* Why Java is considered platform-independent
* What happens when we run a Java application

## Topics

### 1. What is Java?

* History of Java
* Java's main characteristics
* Java platform independence
* Java applications
* Java's role in backend development

### 2. JVM

* What is the JVM?
* JVM architecture
* Class Loader
* Runtime Data Areas
* Execution Engine
* Garbage Collector
* Native Method Interface

### 3. JDK

* What is the JDK?
* Development tools
* `javac`
* `java`
* `javadoc`
* `jar`
* JShell

### 4. JRE

* What is the JRE?
* Runtime environment
* Relationship between JRE and JVM

### 5. JVM vs JDK vs JRE

```text
JDK
│
├── Development Tools
│
└── Runtime Environment
     │
     └── JVM
```

### 6. How Java Code Runs

```text
Java Source Code
       │
       │ javac
       ↓
   Bytecode
   (.class)
       │
       ↓
      JVM
       │
       ↓
   Machine Code
       │
       ↓
    Computer
```

## Practical

Create and run a simple Java application.

```java
public class HelloWorld {

    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

Compile:

```bash
javac HelloWorld.java
```

Run:

```bash
java HelloWorld
```

## Discussion Questions

1. Why doesn't Java compile directly to machine code?
2. What is bytecode?
3. What makes Java platform-independent?
4. What is the difference between the JDK and JVM?
5. Can the JVM run without the JDK?
6. What happens when we execute `java HelloWorld`?
7. What role does the garbage collector play?

## Challenge

Install a JDK and verify your environment.

Run:

```bash
java --version
```

and:

```bash
javac --version
```

Record the Java version you are using.

## Expected Outcome

Everyone should be able to explain the following without looking at notes:

> **JDK → provides the tools to develop Java applications.**

> **JRE → provides the environment required to run Java applications.**

> **JVM → executes Java bytecode.**

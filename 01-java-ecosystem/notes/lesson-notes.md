# Lesson 01 - Java Ecosystem, JVM, JDK & JRE

## 1.1 What is Java?
```text
Java is a high level, object oriented programming language that runs on the Java Virtual Machine (JVM). It is designed to be portable which means Write once, run any where.
```

## 1.2 Why would you use Java?
### Core benefits of using Java
```text
Platform independence: Java’s philosophy is “Write Once, Run Anywhere” (WORA). The Java Virtual Machine (JVM) executes compiled Java code (bytecode) on any device or operating system that has a JVM installed. This saves time and effort by eliminating the need to write separate codebases for different platforms.

Performance and scalability: The Jun-In-Time (JIT) compiler on the JVM allows java to execute at very high speeds. This makes it suitable for high-performance applications that require speed and efficiency. Java is also highly scalable, capable of powering large-scale systems for enterprise-level demands.

Robust and secure: Java was designed with security and robustness. It uses automatic memory management (garbage collection) to prevent memory leaks and eliminates the use of pointers to reduce security vulnerabilities common in languages like C and C++. Its built-in security features also include bytecode verification and a sandbox environment for running untrusted code.

Rich ecosystem and community: Java boasts one of the largest and most mature ecosystems in programming, with a vast number of open-source libraries, frameworks, and a huge developer community. This means developers have a wealth of resources, support forums, and existing solutions to draw upon, enabling faster development.

Object-oriented: As an object-oriented language, Java allows developers to create modular, reusable code with classes and objects. This provides a clean, organized structure for tackling large, complex software projects, which is especially beneficial for large development teams.
```

## 1.3 Common applications built with Java
```text
Java's versatility means it is used to develop a diverse array of applications and services:

• Enterprise Applications: It is the backbone for many large-scale banking, ERP, and CRM systems.
• Mobile Development: Java is a primary language for Android app development.
• Big Data & Cloud: Powerhouse technologies like Apache Hadoop and Apache Kafka are built using Java.
• Internet of Things (IoT): Used in everything from smart TVs to industrial sensors due to its portability.
• Server-Side Web: Utilizes frameworks like Spring and Hibernate for robust backend development.

```

## 1.4 Java Platform Components
```text
• JDK (Java Development Kit): is a software development kit that provides the tools, libraries, and documentation needed to build, compile, debug, and run Java applications.
• Java Compiler (javac): Translates Java source code (.java files) into Java bytecode (.class files) that the JVM can understand.
• Java Runtime Environment (JRE): Contains the Java Virtual Machine (JVM) and the core Java class libraries necessary to run Java applications.
• Java Virtual Machine (JVM): The engine that executes Java bytecode, making Java a platform-agnostic language.

```

## 1.5 How Java works

```text
Java works through a two-stage process of compilation and interpretation, designed to achieve "Write Once, Run Anywhere" (WORA) portability across different operating systems.
```

### 1.5.1 The Core Execution Process
```text
• Source Code Creation: A developer writes code in a human-readable format using the Java language and saves it with a .java extension.
•	Compilation to Bytecode: The Java Compiler (javac) translates the source code into bytecode, stored in .class files. Bytecode is a platform-neutral intermediate language that is not specific to any hardware.
•	The Java Virtual Machine (JVM): When the program runs, the JVM loads the bytecode. Because there are specific JVM implementations for Windows, Mac, and Linux, the same bytecode can run on any of them without modification.
•	Runtime Execution: The JVM's execution engine processes the bytecode using two methods:
-	Interpreter: Translates and executes bytecode line-by-line.
-	Just-In-Time (JIT) Compiler: Identifies "hot spots" (frequently executed code) and compiles them directly into native machine code to significantly boost performance.

```
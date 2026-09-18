# Lambdas and Functional Interfaces

## 1. What is a functional interface?

A **functional interface** is an interface with exactly one abstract method. It can still contain default methods, static methods, and methods inherited from `Object`.

The `@FunctionalInterface` annotation is optional, but it asks the compiler to check that the interface meets the rule.

```java
@FunctionalInterface
interface Greeting {
    void sayHello();
}
```

**Non-technical analogy:** A functional interface is like a job description with one main responsibility. Different people can perform that responsibility in different ways.

## 2. What is a lambda expression?

A **lambda expression** is a concise way to provide an implementation of a functional interface.

General syntax:

```java
(parameters) -> expression
```

or

```java
(parameters) -> {
    // statements
}
```

Example:

```java
Greeting greeting = () -> System.out.println("Hello, team!");
greeting.sayHello();
```

Output:

```text
Hello, team!
```

The lambda supplies the implementation of `sayHello()` without requiring a separate class.
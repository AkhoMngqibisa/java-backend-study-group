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
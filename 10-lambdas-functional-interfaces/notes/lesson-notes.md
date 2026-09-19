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

## 3. Before and after lambdas

Traditional anonymous class:

```java
Runnable task = new Runnable() {
    @Override
    public void run() {
        System.out.println("Task running");
    }
};

task.run();
```

With a lambda:

```java
Runnable task = () -> System.out.println("Task running");
task.run();
```

Both provide an implementation of `Runnable.run()`. The lambda is shorter.


## 4. Lambda syntax examples

No parameters:

```java
() -> System.out.println("Hello")
```

One parameter (parentheses can be omitted):

```java
name -> System.out.println(name)
```

Two parameters:

```java
(a, b) -> a + b
```

Multiple statements require braces and usually an explicit `return` when returning a value:

```java
(a, b) -> {
    int total = a + b;
    return total;
}
```
## 5. Common built-in functional interfaces

These are in `java.util.function`, except `Runnable`, which is in `java.lang`.

| Interface | Main abstract method | Purpose | Example |
|---|---|---|---|
| `Predicate<T>` | `boolean test(T value)` | Tests a condition | `n -> n > 10` |
| `Function<T, R>` | `R apply(T value)` | Converts a value to another value | `s -> s.length()` |
| `Consumer<T>` | `void accept(T value)` | Uses a value without returning a result | `s -> System.out.println(s)` |
| `Supplier<T>` | `T get()` | Supplies a value without taking input | `() -> "Hello"` |
| `Runnable` | `void run()` | Runs a task without parameters or result | `() -> doWork()` |
| `UnaryOperator<T>` | `T apply(T value)` | Takes and returns the same type | `n -> n * 2` <br/>|


## 6. `Predicate` example

A `Predicate<T>` takes a value and returns `true` or `false`.

```java
import java.util.function.Predicate;

public class PredicateExample {
    public static void main(String[] args) {
        Predicate<Integer> isAdultAge = age -> age >= 18;

        System.out.println(isAdultAge.test(20)); // true
        System.out.println(isAdultAge.test(16)); // false
    }
}
```
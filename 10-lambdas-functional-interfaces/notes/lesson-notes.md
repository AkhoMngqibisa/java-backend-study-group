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
## 7. `Function` example

A `Function<T, R>` takes a `T` and returns an `R`.

```java
import java.util.function.Function;

public class FunctionExample {
    public static void main(String[] args) {
        Function<String, Integer> getLength = text -> text.length();

        System.out.println(getLength.apply("Java")); // 4
    }
}
```
## 8. `Consumer` example

A `Consumer<T>` accepts a value and returns no result.

```java
import java.util.function.Consumer;

public class ConsumerExample {
    public static void main(String[] args) {
        Consumer<String> printUppercase =
                text -> System.out.println(text.toUpperCase());

        printUppercase.accept("akhona"); // AKHONA
    }
}
```
## 9. `Supplier` example

A `Supplier<T>` produces a value without taking an input.

```java
import java.util.function.Supplier;

public class SupplierExample {
    public static void main(String[] args) {
        Supplier<String> greeting = () -> "Welcome to Java";

        System.out.println(greeting.get());
    }
}
```

## 10. Lambdas with collections

Lambdas are commonly used with collection operations such as `forEach`, `removeIf`, and streams.

```java
import java.util.ArrayList;
import java.util.List;

public class LambdaCollectionExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>(
                List.of("Akhona", "John", "Amanda", "Thabo")
        );

        names.forEach(name -> System.out.println(name));

        names.removeIf(name -> name.length() < 5);

        System.out.println(names);
    }
}
```

The first lambda prints each name. The second removes names with fewer than five characters.

## 11. Streams example: filter and transform

```java
import java.util.List;

public class StreamExample {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);

        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .toList();

        System.out.println(evenNumbers); // [2, 4, 6]
    }
}
```

- `stream()` starts a stream pipeline.
- `filter(...)` keeps values that pass the predicate.
- `toList()` collects the results into a list (Java 16+).
## 12. Why use lambdas?

- Less boilerplate than anonymous classes.
- Make small behaviour-focused pieces of code easier to read.
- Work naturally with collections and streams.
- Let behaviour be passed into methods.

**Caution:** A lambda is not automatically better if it makes the code hard to read. Keep complex logic in a named method.

---

## Lambdas & Functional Interfaces — Exercises

### Exercise 1: Create a functional interface

Create a functional interface called `Printer` with one method `print(String message)`. Implement it with a lambda that prints:

```text
Learning Java!
```

### Exercise 2: Use a `Predicate`

Create a `Predicate<Integer>` called `isEven` that returns `true` for even numbers. Test it with `8` and `11`.

### Exercise 3: Use a `Function`

Create a `Function<String, Integer>` that returns the length of a string. Test it with `"Backend"`.

### Exercise 4: Use a `Consumer`

Create a `Consumer<String>` that prints each name in uppercase. Test it with `"Akhona"`.


### Exercise 5: Use a `Supplier`

Create a `Supplier<Double>` that supplies the value `99.99`. Print the supplied value.

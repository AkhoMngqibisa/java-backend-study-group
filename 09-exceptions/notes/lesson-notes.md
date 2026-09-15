# Exceptions

## 1. What is an exception?

An **exception** is an event that interrupts the normal flow of a program while it is running.

Examples:
- Dividing a number by zero.
- Trying to read a file that does not exist.
- Converting `"abc"` into an integer.
- Accessing a method on a `null` reference.

**Non-technical analogy:** Imagine driving to work and finding that the road is closed. You need an alternative route rather than continuing as if nothing happened. Exception handling gives a program a way to respond to certain runtime problems.

## 2. Basic example

```java
public class ExceptionExample {
    public static void main(String[] args) {
        int result = 10 / 0; // throws ArithmeticException
        System.out.println(result); // never reached
    }
}
```

This program throws an `ArithmeticException`. Without handling it, the current execution path stops and Java prints an error stack trace.

## 3. `try`, `catch`, and `finally`

- `try`: contains code that might throw an exception.
- `catch`: handles a matching exception.
- `finally`: runs when control leaves the `try`/`catch` in ordinary circumstances; commonly used for cleanup. It may not run if the JVM terminates abruptly.

```java
public class TryCatchExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0;
            System.out.println(result);
        } catch (ArithmeticException exception) {
            System.out.println("You cannot divide by zero.");
        } finally {
            System.out.println("Finished attempting the calculation.");
        }

        System.out.println("The program continues.");
    }
}
```

Expected output:

```text
You cannot divide by zero.
Finished attempting the calculation.
The program continues.
```

**Important:** Catch the most specific exception you can reasonably handle. Avoid catching `Exception` everywhere and silently ignoring the problem.
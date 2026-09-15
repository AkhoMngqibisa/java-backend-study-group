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
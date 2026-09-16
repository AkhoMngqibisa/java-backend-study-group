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

## 4. Exception hierarchy

Simplified hierarchy:

```text
Object
  └── Throwable
       ├── Error
       └── Exception
            ├── RuntimeException
            └── Other checked exceptions
```

- **`Error`**: serious JVM or environment problems; applications usually do not try to recover from these.
- **Checked exceptions**: the compiler requires code to catch them or declare them with `throws` (for example, `IOException`).
- **Unchecked exceptions**: subclasses of `RuntimeException`; the compiler does not require them to be caught or declared (for example, `NullPointerException`, `IllegalArgumentException`).

## 5. Checked exception example

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ReadFileExample {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Path.of("notes.txt"));
            System.out.println(content);
        } catch (IOException exception) {
            System.out.println("Could not read the file: " + exception.getMessage());
        }
    }
}
```

`Files.readString` can throw an `IOException`, so this example handles it.

## 6. `throw` vs `throws`

- `throw` actually throws an exception.
- `throws` declares that a method may pass an exception to its caller.

```java
public class AgeValidator {
    static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be at least 18.");
        }
    }

    public static void main(String[] args) {
        validateAge(16); // throws IllegalArgumentException
    }
}
```

Example of `throws`:

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

static String readNotes() throws IOException {
    return Files.readString(Path.of("notes.txt"));
}
```

The method declares the possibility; its caller must handle or further declare the checked exception.

## 7. Custom exceptions

Create a custom exception when a domain-specific error deserves a meaningful name.

```java
class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class BankAccount {
    private double balance;

    BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Not enough money in the account.");
        }
        balance -= amount;
    }
}
```

This custom exception is **checked** because it extends `Exception` but not `RuntimeException`.

## 8. Best practices

- Handle an exception where you can take a useful action.
- Give error messages enough context to diagnose the problem.
- Do not use exceptions as ordinary control flow.
- Do not swallow exceptions with an empty `catch` block.
- Avoid catching overly broad exception types without a clear reason.
- Use `try-with-resources` for resources such as files and streams that must be closed.

Example:

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

try (BufferedReader reader = Files.newBufferedReader(Path.of("notes.txt"))) {
    System.out.println(reader.readLine());
} catch (IOException exception) {
    System.out.println("File operation failed: " + exception.getMessage());
}
```

---
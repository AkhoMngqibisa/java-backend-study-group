## Exceptions — Exercises

Try these before checking the answers.

### Exercise 1: Handle division

Complete the program so dividing by zero prints `Cannot divide by zero` and the program continues to print `Done`.

```java
public class Exercise1 {
    public static void main(String[] args) {
        int numerator = 12;
        int denominator = 0;

        // Add try/catch here
        int result = numerator / denominator;
        System.out.println(result);

        System.out.println("Done");
    }
}
```

### Exercise 2: Validate an age

Write a method `validateAge(int age)` that throws `IllegalArgumentException` when `age` is less than 18. Test it with `20` and `15`.

### Exercise 3: `throw` or `throws`?

Explain the difference between these lines:

```java
throw new IllegalArgumentException("Invalid input");
```

```java
static void loadFile() throws IOException { ... }
```

### Exercise 4: Checked or unchecked?

Classify each as checked or unchecked:
- `IOException`
- `IllegalArgumentException`
- `NullPointerException`


### Exercise 5: Custom exception

Create a checked `InsufficientFundsException` and a `withdraw(balance, amount)` method that throws it if `amount > balance`.

<details>
<summary>Answer key</summary>

**Exercise 1**

```java
try {
    int result = numerator / denominator;
    System.out.println(result);
} catch (ArithmeticException exception) {
    System.out.println("Cannot divide by zero");
}
System.out.println("Done");
```

**Exercise 2**

```java
static void validateAge(int age) {
    if (age < 18) {
        throw new IllegalArgumentException("Age must be at least 18.");
    }
}
```

Call it inside a `try`/`catch` if you want the program to continue after invalid input.

**Exercise 3:** `throw` throws an exception object at that point in the code. `throws` declares a possible exception in a method signature.

**Exercise 4:** `IOException` is checked. `IllegalArgumentException` and `NullPointerException` are unchecked.

**Exercise 5**

```java
class InsufficientFundsException extends Exception {
    InsufficientFundsException(String message) {
        super(message);
    }
}

static double withdraw(double balance, double amount)
        throws InsufficientFundsException {
    if (amount > balance) {
        throw new InsufficientFundsException("Insufficient funds.");
    }
    return balance - amount;
}
```

</details>

---
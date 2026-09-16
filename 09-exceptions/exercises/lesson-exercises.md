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
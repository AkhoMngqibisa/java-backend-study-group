# Lesson 02 — Java Syntax, Variables & Control Flow

## Learning Objectives

By the end of this lesson, we should be able to:

* Understand basic Java syntax
* Understand how Java programs are structured
* Declare and initialize variables
* Understand Java primitive data types
* Understand reference types at a basic level
* Use operators
* Perform type conversion and casting
* Read input from the user
* Use conditional statements
* Use loops
* Understand `break` and `continue`
* Write simple Java programs using these concepts

---

## Topics

### 1. Java Program Structure

Understand:

* Classes
* The `main()` method
* Statements
* Blocks `{ }`
* Semicolons `;`
* Comments

Example:

```java
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Java");
    }
}
```

---

### 2. Variables

A variable is a named location used to store data.

Syntax:

```java
dataType variableName = value;
```

Example:

```java
int age = 25;
String name = "John";
```

### Declaration

```java
int age;
```

### Initialization

```java
age = 25;
```

### Declaration + initialization

```java
int age = 25;
```

---

## 3. Primitive Data Types

Java has eight primitive data types.

| Type      | Example                          | Description      |
| --------- | -------------------------------- | ---------------- |
| `byte`    | `byte age = 25;`                 | Small integer    |
| `short`   | `short number = 1000;`           | Integer          |
| `int`     | `int age = 25;`                  | Common integer   |
| `long`    | `long population = 8000000000L;` | Large integer    |
| `float`   | `float price = 10.5f;`           | Decimal          |
| `double`  | `double salary = 50000.50;`      | Decimal          |
| `char`    | `char grade = 'A';`              | Single character |
| `boolean` | `boolean active = true;`         | True/false       |

For most normal integer values, `int` is commonly used.

For most decimal values, `double` is commonly used.

---

## 4. Reference Types

Reference variables store references to objects.

Examples:

```java
String name = "Akhona";
int[] numbers = {1, 2, 3};
```

Other examples include:

* Arrays
* Classes
* Objects
* Interfaces
* Enums

We will study objects and classes in more detail later.

---

## 5. Constants

Use `final` when a value should not be changed.

```java
final double PI = 3.14159;
```

Trying to change it:

```java
PI = 4.5;
```

will result in a compilation error.

---

# 6. Operators

## Arithmetic Operators

```text
+
-
*
/
%
```

Example:

```java
int result = 10 + 5;
```

The `%` operator returns the remainder.

```java
int remainder = 10 % 3;
```

Result:

```text
1
```

---

## Comparison Operators

```text
==
!=
>
<
>=
<=
```

Example:

```java
int age = 25;

System.out.println(age >= 18);
```

Output:

```text
true
```

---

## Logical Operators

### AND

```java
&&
```

Both conditions must be true.

```java
age >= 18 && age <= 65
```

### OR

```java
||
```

At least one condition must be true.

### NOT

```java
!
```

Reverses a boolean value.

---

## Assignment Operators

```text
=
+=
-=
*=
/=
%=
```

Example:

```java
int number = 10;

number += 5;
```

Equivalent to:

```java
number = number + 5;
```

---

# 7. Type Casting

## Widening Conversion

Smaller type → larger type.

```java
int number = 10;
double value = number;
```

This is generally automatic.

---

## Narrowing Conversion

Larger type → smaller type.

```java
double price = 19.99;

int rounded = (int) price;
```

Result:

```text
19
```

The decimal portion is removed.

---

# 8. User Input

Java can read input using `Scanner`.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);

System.out.print("Enter your name: ");
String name = scanner.nextLine();

System.out.println("Hello " + name);
```

Useful methods include:

```java
next()
nextLine()
nextInt()
nextDouble()
nextBoolean()
```

---

# 9. Conditional Statements

## if

```java
if (age >= 18) {
    System.out.println("Adult");
}
```

## if / else

```java
if (age >= 18) {
    System.out.println("Adult");
} else {
    System.out.println("Minor");
}
```

## if / else if / else

```java
if (score >= 80) {
    System.out.println("A");
} else if (score >= 70) {
    System.out.println("B");
} else if (score >= 60) {
    System.out.println("C");
} else {
    System.out.println("Fail");
}
```

---

# 10. switch

Use `switch` when comparing one value against multiple possible cases.

```java
int day = 2;

switch (day) {
    case 1:
        System.out.println("Monday");
        break;

    case 2:
        System.out.println("Tuesday");
        break;

    default:
        System.out.println("Unknown day");
}
```

Understand why `break` is used.

---

# 11. Loops

Loops allow us to execute code repeatedly.

## for loop

```java
for (int i = 1; i <= 5; i++) {
    System.out.println(i);
}
```

## while loop

```java
int i = 1;

while (i <= 5) {
    System.out.println(i);
    i++;
}
```

## do-while loop

```java
int i = 1;

do {
    System.out.println(i);
    i++;
} while (i <= 5);
```

Important difference:

`do-while` executes the body **at least once**.

---

# 12. break

Stops a loop.

```java
for (int i = 1; i <= 10; i++) {

    if (i == 5) {
        break;
    }

    System.out.println(i);
}
```

---

# 13. continue

Skips the current iteration.

```java
for (int i = 1; i <= 5; i++) {

    if (i == 3) {
        continue;
    }

    System.out.println(i);
}
```

Output:

```text
1
2
4
5
```

---

# Discussion Questions

1. What is the difference between a variable and a constant?
2. What are Java's eight primitive data types?
3. What is the difference between `int` and `double`?
4. What is the difference between `==` and `=`?
5. What does `%` do?
6. What is type casting?
7. What is the difference between `if` and `switch`?
8. What is the difference between `for` and `while`?
9. When would you use `break`?
10. When would you use `continue`?
11. Why does a `do-while` loop always execute at least once?
12. What happens when integer division is performed?

---

# Practical Challenge

Create a program called `StudentGradeCalculator`.

The program should:

1. Ask the user for their name.
2. Ask for three marks.
3. Calculate the average.
4. Determine the student's grade.
5. Print the result.

Example:

```text
Enter your name: John
Enter mark 1: 75
Enter mark 2: 68
Enter mark 3: 82

Student: John
Average: 75.0
Grade: B
```

Use:

* Variables
* `Scanner`
* Arithmetic operators
* `if / else if / else`
* Type conversion where necessary

---

# Homework

Create a program called `NumberAnalyzer`.

The program should:

1. Ask the user for a number.
2. Determine whether it is positive, negative or zero.
3. Determine whether it is even or odd.
4. Print numbers from `1` to that number.
5. Calculate the sum of those numbers.

Example:

```text
Enter a number: 5

Positive
Odd

Numbers:
1
2
3
4
5

Sum: 15
```

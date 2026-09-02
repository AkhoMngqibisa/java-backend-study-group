# Lesson 03 — Methods, Arrays & Strings

## Learning Objectives

By the end of this lesson, we should be able to:

* Understand what methods are
* Create and call methods
* Pass parameters to methods
* Return values from methods
* Understand method scope
* Understand method overloading
* Create and use arrays
* Access array elements
* Iterate through arrays
* Understand array length
* Work with multidimensional arrays
* Understand the `String` type
* Use common String methods
* Understand String immutability at a basic level
* Compare Strings correctly

---

# Part 1 — Methods

## 1. What is a Method?

A method is a reusable block of code that performs a specific task.

Example:

```java
public static void sayHello() {
    System.out.println("Hello!");
}
```

Calling the method:

```java
sayHello();
```

Methods help us:

* Reuse code
* Organize code
* Reduce duplication
* Improve readability
* Separate responsibilities

---

# 2. Method Structure

```java
accessModifier static returnType methodName(parameters) {

    // method body

}
```

Example:

```java
public static int add(int a, int b) {

    return a + b;
}
```

---

# 3. Parameters

Parameters allow us to pass information into a method.

```java
public static void greet(String name) {

    System.out.println("Hello " + name);
}
```

Calling:

```java
greet("Akhona");
```

---

# 4. Return Values

A method can return a value.

```java
public static int add(int a, int b) {

    return a + b;
}
```

Using it:

```java
int result = add(10, 20);

System.out.println(result);
```

---

# 5. void

`void` means the method does not return a value.

```java
public static void printMessage() {

    System.out.println("Hello");
}
```

---

# 6. Method Overloading

Multiple methods can have the same name as long as their parameter lists are different.

```java
public static int add(int a, int b) {
    return a + b;
}

public static double add(double a, double b) {
    return a + b;
}
```

This is called **method overloading**.

---

# Part 2 — Arrays

## 7. What is an Array?

An array stores multiple values of the same type.

```java
int[] numbers = {10, 20, 30, 40, 50};
```

Arrays have a fixed size.

---

# 8. Accessing Elements

Array indexes start at `0`.

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers[0]);
System.out.println(numbers[1]);
System.out.println(numbers[2]);
```

Output:

```text
10
20
30
```

---

# 9. Changing Elements

```java
numbers[0] = 100;
```

---

# 10. Array Length

Use:

```java
numbers.length
```

Example:

```java
System.out.println(numbers.length);
```

---

# 11. Looping Through Arrays

Traditional `for` loop:

```java
for (int i = 0; i < numbers.length; i++) {

    System.out.println(numbers[i]);
}
```

Enhanced `for` loop:

```java
for (int number : numbers) {

    System.out.println(number);
}
```

---

# 12. Creating an Array With a Fixed Size

```java
int[] numbers = new int[5];
```

This creates an array that can store five integers.

Initially:

```text
0
0
0
0
0
```

---

# 13. Multidimensional Arrays

Example:

```java
int[][] matrix = {
    {1, 2, 3},
    {4, 5, 6},
    {7, 8, 9}
};
```

Access:

```java
System.out.println(matrix[0][1]);
```

Output:

```text
2
```

---

# Part 3 — Strings

## 14. What is String?

`String` represents text.

```java
String name = "Akhona";
```

Strings are objects in Java.

---

# 15. String Concatenation

```java
String firstName = "John";
String lastName = "Doe";

String fullName = firstName + " " + lastName;
```

---

# 16. Common String Methods

### length()

```java
String name = "Akhona";

System.out.println(name.length());
```

### toUpperCase()

```java
name.toUpperCase();
```

### toLowerCase()

```java
name.toLowerCase();
```

### charAt()

```java
name.charAt(0);
```

### contains()

```java
name.contains("hon");
```

### startsWith()

```java
name.startsWith("A");
```

### endsWith()

```java
name.endsWith("a");
```

### substring()

```java
String word = "Java";

System.out.println(word.substring(0, 2));
```

Output:

```text
Ja
```

### replace()

```java
String message = "Hello Java";

message.replace("Java", "World");
```

### trim()

```java
String name = "   John   ";

System.out.println(name.trim());
```

---

# 17. Comparing Strings

Do **not** normally compare String contents using:

```java
==
```

Use:

```java
equals()
```

Example:

```java
String name1 = "John";
String name2 = "John";

System.out.println(name1.equals(name2));
```

For case-insensitive comparison:

```java
name1.equalsIgnoreCase(name2);
```

---

# 18. String Immutability

Strings are immutable.

This means that String objects cannot be changed after they are created.

Example:

```java
String name = "John";

name.toUpperCase();

System.out.println(name);
```

The original String is still:

```text
John
```

To store the result:

```java
name = name.toUpperCase();
```

---

# Discussion Questions

## Methods

1. Why do we use methods?
2. What is the difference between a parameter and an argument?
3. What does `void` mean?
4. What is a return value?
5. What is method overloading?
6. Can two methods have the same name?
7. What determines whether two methods are overloaded?

## Arrays

8. Why do array indexes start at 0?
9. How do you find an array's length?
10. Can an array change its size after creation?
11. What happens if you access an invalid index?
12. What is the difference between a normal `for` loop and an enhanced `for` loop?

## Strings

13. Why should we use `.equals()` instead of `==` for String content comparison?
14. What does it mean that Strings are immutable?
15. What does `substring()` do?
16. What is the difference between `toUpperCase()` and changing the original String?
17. Why is String considered an object?

---

# Practical Challenge

Create a program called:

`StudentAnalyzer`

The program should:

1. Store five student marks in an array.
2. Create a method that calculates the total.
3. Create a method that calculates the average.
4. Create a method that finds the highest mark.
5. Create a method that finds the lowest mark.
6. Print all results.

Example:

```text
Marks:
75
82
68
91
77

Total: 393
Average: 78.6
Highest: 91
Lowest: 68
```

---

# String Challenge

Create a program called:

`NameAnalyzer`

Ask the user for their full name.

The program should:

1. Print the name in uppercase.
2. Print the name in lowercase.
3. Print the number of characters.
4. Print the first character.
5. Print the last character.
6. Check whether the name contains `"a"`.
7. Print the name without leading/trailing spaces.

Example:

```text
Enter your name: Akhona Mngqibisa

Uppercase: AKHONA MNGQIBISA
Lowercase: akhona mngqibisa
Characters: 15
First character: A
Last character: a
Contains 'a': true
```

---

# Advanced Challenge

Create a simple `Calculator` program.

Create separate methods for:

```text
add()
subtract()
multiply()
divide()
remainder()
```

Example:

```java
public static int add(int a, int b) {
    return a + b;
}
```

The program should ask the user for two numbers and perform the selected operation.

---

# Homework

Create a program called:

`WordAnalyzer`

Ask the user to enter a sentence.

The program should:

1. Print the sentence.
2. Print the number of characters.
3. Print the sentence in uppercase.
4. Print the sentence in lowercase.
5. Count how many times the letter `a` appears.
6. Print the first character.
7. Print the last character.
8. Check whether the sentence contains `"Java"`.
9. Store the individual words in an array.
10. Print each word on a separate line.

Example:

```text
Enter a sentence:
Java is a powerful programming language

Characters: 39
Uppercase: JAVA IS A POWERFUL PROGRAMMING LANGUAGE
Contains Java: true

Words:
Java
is
a
powerful
programming
language
```

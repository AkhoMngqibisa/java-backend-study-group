# Java Collections Presentation

## Collections Chosen

|   | Collection    | Presenter |
| - | ------------- | --------- |
|   | ArrayList     | Akhona    |
|   | LinkedList    | Akhona    |
|   | HashSet       | Akhona    |
|   | LinkedHashSet | Akhona    |

---

# 1. ArrayList

## What is an ArrayList?

`ArrayList` is a resizable array implementation of the `List` interface in Java.

Unlike a normal Java array, an `ArrayList` can automatically grow or shrink as elements are added or removed.

```java
ArrayList<String> names = new ArrayList<>();

names.add("Akhona");
names.add("John");
names.add("Sarah");

System.out.println(names);
```

Output:

```text
[Akhona, John, Sarah]
```

---

## Technical Explanation

An `ArrayList` internally uses an array to store its elements.

When the internal array becomes full, Java creates a larger array and copies the existing elements into it.

Conceptually:

```text
Initial Array

[ A ][ B ][ C ][   ]

Add D

[ A ][ B ][ C ][ D ]

If the array becomes full:

[ A ][ B ][ C ][ D ]

        ↓

Create larger array

[ A ][ B ][ C ][ D ][   ][   ]

        ↓

Copy elements

[ A ][ B ][ C ][ D ][   ][   ]
```

This is why accessing an element by its index is very fast.

```java
names.get(1);
```

The index allows Java to directly access the required position.

---

## Non-Technical Example

Think of an `ArrayList` like a row of numbered lockers.

```text
Index

  0        1        2
┌──────┬────────┬────────┐
│ John │ Sarah  │ Akhona │
└──────┴────────┴────────┘
```

If you know that Sarah is in locker `1`, you can immediately go to locker `1`.

```java
names.get(1);
```

The same concept applies to an `ArrayList`.

---

## Key Characteristics

- Maintains insertion order
- Allows duplicate elements
- Allows `null`
- Provides index-based access
- Automatically resizes
- Implements the `List` interface
- Not synchronized by default

Example:

```java
ArrayList<String> names = new ArrayList<>();

names.add("John");
names.add("John");
names.add("Sarah");

System.out.println(names);
```

Output:

```text
[John, John, Sarah]
```

Duplicates are allowed.

---

## Common Operations

### Adding elements

```java
names.add("Akhona");
```

### Getting an element

```java
String name = names.get(0);
```

### Updating an element

```java
names.set(0, "Thabo");
```

### Removing an element

```java
names.remove(0);
```

### Checking the size

```java
int size = names.size();
```

### Checking if an element exists

```java
boolean exists = names.contains("Sarah");
```

---
## Complete Example

```java
import java.util.ArrayList;

public class ArrayListExample {

    public static void main(String[] args) {

        ArrayList<String> students = new ArrayList<>();

        students.add("Akhona");
        students.add("John");
        students.add("Sarah");
        students.add("John");

        System.out.println(students);

        System.out.println(students.get(1));

        students.set(1, "Thabo");

        students.remove("Sarah");

        System.out.println(students);
    }
}
```

---

## When Should We Use ArrayList?

Use an `ArrayList` when:

- You frequently access elements using indexes.
- You need to maintain insertion order.
- Duplicate values are allowed.
- You frequently read elements.
- Insertions/removals mostly happen at the end of the list.

### Example Use Cases

```text
Students in a class
Products in a shopping cart
List of employees
List of transactions
List of API results
```

---

# 2. LinkedList

## What is a LinkedList?

`LinkedList` is a Java collection that stores elements as nodes connected to each other.

Each node contains:

1. The data
2. A reference to the next node
3. A reference to the previous node

Java's `LinkedList` implements both:

```text
List
Deque
```

---

## Technical Explanation

A `LinkedList` uses a doubly linked list.

Conceptually:

```text
NULL
  ↑
┌─────────┐
│   A     │
└─────────┘
     ↓
┌─────────┐
│   B     │
└─────────┘
     ↓
┌─────────┐
│   C     │
└─────────┘
     ↓
   NULL
```

More accurately:

```text
NULL ← [A] ⇄ [B] ⇄ [C] → NULL
```

Each node knows about the node before and after it.

---

## Non-Technical Example

Imagine a train.

```text
🚂 ← 🚃 ← 🚃 ← 🚃
```

Each train carriage is connected to another carriage.

If you remove one carriage:

```text
Before:

[A] ⇄ [B] ⇄ [C]

Remove B:

[A] ⇄ [C]
```

The connections can be changed without moving all the other elements.

---

## Key Characteristics

- Maintains insertion order
- Allows duplicates
- Allows `null`
- Implements `List`
- Implements `Deque`
- Efficient insertion/removal at the beginning and end
- Slower random/index-based access compared with `ArrayList`

---

## Adding Elements

```java
LinkedList<String> names = new LinkedList<>();

names.add("Akhona");
names.add("John");
names.add("Sarah");
```

---

## Adding at the Beginning

```java
names.addFirst("Thabo");
```

---

## Adding at the End

```java
names.addLast("Peter");
```

---

## Removing from the Beginning

```java
names.removeFirst();
```

---

## Removing from the End

```java
names.removeLast();
```

---
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

## Complete Example

```java
import java.util.LinkedList;

public class LinkedListExample {

    public static void main(String[] args) {

        LinkedList<String> queue = new LinkedList<>();

        queue.add("John");
        queue.add("Sarah");
        queue.add("Akhona");

        System.out.println(queue);

        queue.addFirst("Thabo");

        queue.addLast("Peter");

        System.out.println(queue);

        queue.removeFirst();

        queue.removeLast();

        System.out.println(queue);
    }
}
```

---

## When Should We Use LinkedList?

A `LinkedList` can be useful when:

- You frequently add/remove elements from the beginning or end.
- You need queue/deque operations.
- You don't require frequent random access by index.

### Example Use Cases

```text
Queue
Deque
Browser history-like structures
Music playlist manipulation
Task processing
```
---

# 3. HashSet

## What is a HashSet?

`HashSet` is a collection that stores **unique elements**.

It does not allow duplicate values.

```java
HashSet<String> names = new HashSet<>();

names.add("Akhona");
names.add("John");
names.add("Akhona");

System.out.println(names);
```

The second `"Akhona"` is ignored.

Possible output:

```text
[Akhona, John]
```

The order is not guaranteed.

---

## Technical Explanation

`HashSet` is backed by a hash table.

When an element is added, Java uses the object's:

```java
hashCode()
```

to determine where the element should be stored.

When Java needs to check whether an element exists, it uses the hash information and:

```java
equals()
```

to determine whether the object is already present.

Conceptually:

```text
                Hash Function
                     ↓
"Akhona" ───────────────→ Bucket
                             ↓
                         [Akhona]
```

This is why `HashSet` is generally very efficient for:

```java
add()
remove()
contains()
```

---

## Non-Technical Example

Imagine a guest list where each person can only appear once.

```text
Guest List

Akhona
John
Sarah
```

If someone tries to add:

```text
Akhona
```

again, you don't create another entry.

The list remains:

```text
Akhona
John
Sarah
```

This is the main idea behind a `Set`.

---

## Key Characteristics

- Does not allow duplicates
- Does not guarantee insertion order
- Allows one `null` element
- Uses hashing internally
- Fast lookup in typical cases
- Implements the `Set` interface

---

## Example

```java
import java.util.HashSet;

public class HashSetExample {

    public static void main(String[] args) {

        HashSet<String> names = new HashSet<>();

        names.add("Akhona");
        names.add("John");
        names.add("Sarah");
        names.add("Akhona");

        System.out.println(names);
    }
}
```

The duplicate `"Akhona"` is not added.

---

## Checking for an Element

```java
boolean exists = names.contains("Akhona");

System.out.println(exists);
```

Output:

```text
true
```

---

## Removing an Element

```java
names.remove("John");
```

---

## When Should We Use HashSet?

Use `HashSet` when:

- You need unique values.
- Ordering is not important.
- You frequently need to check whether something exists.
- You want to remove duplicates

### Example Use Cases

```text
Unique usernames
Unique email addresses
Unique product IDs
Unique tags
Unique permissions
Removing duplicate values from a list
```

---

# 4. LinkedHashSet

## What is a LinkedHashSet?

`LinkedHashSet` is similar to `HashSet`, but it additionally maintains the **insertion order** of elements.

It combines characteristics of:

```text
HashSet + Linked List ordering
```

Example:

```java
LinkedHashSet<String> names = new LinkedHashSet<>();

names.add("Akhona");
names.add("John");
names.add("Sarah");

System.out.println(names);
```

Output:

```text
[Akhona, John, Sarah]
```

The elements appear in the order they were inserted.

---

## Technical Explanation

`LinkedHashSet` uses hashing to provide efficient set operations while maintaining a linked structure between entries to preserve insertion order.

Conceptually:

```text
Hashing

Akhona → Bucket 1
John   → Bucket 4
Sarah  → Bucket 2

Insertion order:

Akhona → John → Sarah
```

The hash structure helps with lookup while the linked structure remembers insertion order.

---

## Non-Technical Example

Imagine a guest list where:

1. Every guest can only appear once.
2. We want to remember the order in which guests arrived.

```text
First:

Akhona

Second:

John

Third:

Sarah
```

The collection remembers:

```text
Akhona → John → Sarah
```

If John tries to register again, he isn't added twice.

---

## Key Characteristics

- Does not allow duplicates
- Maintains insertion order
- Allows one `null` element
- Uses hashing
- Generally provides efficient `add`, `remove`, and `contains`
- Implements the `Set` interface

---

## Example

```java
import java.util.LinkedHashSet;

public class LinkedHashSetExample {

    public static void main(String[] args) {

        LinkedHashSet<String> names = new LinkedHashSet<>();

        names.add("Akhona");
        names.add("John");
        names.add("Sarah");
        names.add("John");

        System.out.println(names);
    }
}
```

Output:

```text
[Akhona, John, Sarah]
```

John only appears once, and the insertion order is maintained.

---

## When Should We Use LinkedHashSet?

Use `LinkedHashSet` when:

- You need unique elements.
- You need to preserve insertion order.
- You want predictable iteration order.
- You still want hash-based set operations.

### Example Use Cases

```text
Unique search history
Unique recently viewed products
Unique tags while preserving their order
Unique events in the order they occurred
Removing duplicates while keeping the original order
```

---

# 5. ArrayList vs LinkedList

| Feature                 | ArrayList     | LinkedList         |
| ----------------------- | ------------- | ------------------ |
| Data structure          | Dynamic array | Doubly linked list |
| Maintains order         | Yes           | Yes                |
| Allows duplicates       | Yes           | Yes                |
| Allows null             | Yes           | Yes                |
| Index access            | Fast          | Slower             |
| Add at end              | Fast          | Fast               |
| Add/remove at beginning | Slower        | Fast               |
| Random access           | Good          | Poor               |
| Implements List         | Yes           | Yes                |
| Implements Deque        | No            | Yes                |

---

## Example Scenario

Suppose we have:

```java
List<String> students;
```

If we frequently do:

```java
students.get(500);
```

`ArrayList` is generally more suitable because it provides efficient index-based access.

If we frequently do:

```java
students.addFirst("Akhona");
students.removeFirst();
```

`LinkedList` may be more appropriate.

---

# 6. HashSet vs LinkedHashSet

| Feature                     | HashSet | LinkedHashSet |
| --------------------------- | ------- | ------------- |
| Allows duplicates           | No      | No            |
| Maintains insertion order   | No      | Yes           |
| Allows null                 | Yes     | Yes           |
| Hash-based                  | Yes     | Yes           |
| Fast lookup                 | Yes     | Yes           |
| Memory overhead             | Lower   | Higher        |
| Predictable iteration order | No      | Yes           |

---

## Example

### HashSet

```java
HashSet<String> names = new HashSet<>();

names.add("Akhona");
names.add("John");
names.add("Sarah");
```

The iteration order is not guaranteed.

### LinkedHashSet

```java
LinkedHashSet<String> names = new LinkedHashSet<>();

names.add("Akhona");
names.add("John");
names.add("Sarah");
```

Iteration follows:

```text
Akhona
John
Sarah
```

---
# 7. Quick Decision Guide

## Choose ArrayList when:

```text
I need a List
        ↓
I need index-based access
        ↓
I frequently read elements
        ↓
Duplicates are allowed
```

---

## Choose LinkedList when:

```text
I need a List/Deque
        ↓
I frequently add/remove at the beginning or end
        ↓
Index-based access is not important
```

---

## Choose HashSet when:

```text
I need unique values
        ↓
Order does NOT matter
        ↓
I want efficient lookup
```

---

## Choose LinkedHashSet when:

```text
I need unique values
        ↓
AND
        ↓
I need insertion order preserved
```

---

# 8. Real-World Example

Imagine we are building an online shopping application.

### ArrayList

We can use an `ArrayList` for products displayed to a customer.

```java
ArrayList<String> products = new ArrayList<>();

products.add("Laptop");
products.add("Mouse");
products.add("Keyboard");
```

We can access a product using its index:

```java
products.get(0);
```

---

### LinkedList

We could use a `LinkedList` for a queue of tasks waiting to be processed.

```java
LinkedList<String> tasks = new LinkedList<>();

tasks.add("Process Order #1001");
tasks.add("Process Order #1002");
tasks.add("Process Order #1003");

tasks.removeFirst();
```

The first task is processed and removed.

---

### HashSet

We could use a `HashSet` to store unique product IDs.

```java
HashSet<Integer> productIds = new HashSet<>();

productIds.add(1001);
productIds.add(1002);
productIds.add(1001);
```

Result:

```text
[1001, 1002]
```

Duplicate product IDs are not stored.

---

### LinkedHashSet

We could use a `LinkedHashSet` for a customer's unique search history while preserving the order in which searches were added.

```java
LinkedHashSet<String> searchHistory = new LinkedHashSet<>();

searchHistory.add("Java");
searchHistory.add("Spring Boot");
searchHistory.add("Angular");
```

Result:

```text
Java
Spring Boot
Angular
```

Duplicates are prevented while insertion order is preserved.

---

# 9. Big-O Performance Overview

| Operation    | ArrayList | LinkedList | HashSet | LinkedHashSet |
| ------------ | --------: | ---------: | ------: | ------------: |
| `add()`      |    O(1)\* |   O(1)\*\* |  O(1)\* |        O(1)\* |
| `get(index)` |      O(1) |       O(n) |     N/A |           N/A |
| `contains()` |      O(n) |       O(n) |  O(1)\* |        O(1)\* |
| `remove()`   |      O(n) | O(n)\*\*\* |  O(1)\* |        O(1)\* |

### Notes

`*` Average/amortized case.

`**` When adding at the end or when the node position is already known.

`***` Finding an element by value can still require traversal.

Hash-based collections can degrade in pathological collision scenarios, so `O(1)` should be understood as the typical/expected performance rather than an absolute guarantee.

---
# 10. Key Concepts to Remember

### ArrayList

> **"I need an ordered collection and fast index access."**

```text
Array → Resizable
```

### LinkedList

> **"I need a collection where adding/removing from the ends is important."**

```text
Node ⇄ Node ⇄ Node
```

### HashSet

> **"I need unique values and I don't care about order."**

```text
Unique + Fast Lookup
```

### LinkedHashSet

> **"I need unique values AND I want to preserve insertion order."**

```text
Unique + Insertion Order
```

---

# 11. Final Comparison

```text
                    COLLECTIONS
                         |
          ┌──────────────┴──────────────┐
          |                             |
         LIST                          SET
          |                             |
    ┌─────┴─────┐                ┌──────┴──────┐
    |           |                |             |
ArrayList   LinkedList        HashSet    LinkedHashSet
    |           |                |             |
 Fast       Fast insert/       Unique        Unique
 index      remove ends        values        values
 access                                     + order
```

---
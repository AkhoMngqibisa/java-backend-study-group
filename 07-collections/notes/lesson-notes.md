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
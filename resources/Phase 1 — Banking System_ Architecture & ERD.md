# Phase 1 — Banking System: High-Level Architecture & ERD

## 📌 Overview

For this lesson, we will take the Java concepts we have learned so far — especially **Classes, Objects, Encapsulation, Inheritance, and Polymorphism** — and apply them to a real-world **Banking System**.

Instead of immediately writing code, we will first learn how to think about the system from a software engineering perspective by designing its architecture and database structure.

## 🎯 What We Will Do

From the point of view of a backend developer, we will:

- Identify the main requirements of a Banking System.
- Identify the important **entities and objects** within the system.
- Discuss how our Java **classes and OOP concepts** can represent real-world banking objects.
- Create a **High-Level Architecture Diagram** showing the major parts of the system and how they communicate.
- Create an **Entity Relationship Diagram (ERD)** to design the database structure.
- Identify database **entities, attributes, primary keys, foreign keys, and relationships**.
- Understand the relationship between our **Java classes, system architecture, and database design**.
- Discuss how the design could eventually be turned into a working Java backend application.

## 🏦 Banking System Example

We will use a simplified banking system containing concepts such as:

- Customer
- Account
- Savings Account
- Current Account
- Transaction

We will use these concepts to demonstrate how the OOP principles we have learned can represent a real-world system.

For example:

- **Encapsulation** → Protect account information such as balance.
- **Inheritance** → `SavingsAccount` and `CurrentAccount` can inherit from `Account`.
- **Polymorphism** → Different account types can implement different business rules.
- **Classes & Objects** → Represent customers, accounts, and transactions.

## 🏗️ High-Level Architecture

We will design a simple architecture that shows how a user request could flow through the system:

**Client → Application/API → Business Logic → Data Access → Database**

We will focus on understanding the **responsibility of each layer** rather than implementing the system with Spring Boot at this stage.

## 🗄️ Entity Relationship Diagram

We will design the database structure for our Banking System and identify relationships such as:

- One **Customer** can have multiple **Accounts**.
- One **Account** can have multiple **Transactions**.
- An **Account** belongs to a specific customer.
- A **Transaction** belongs to an account.

We will identify the relevant:

- Primary Keys
- Foreign Keys
- Attributes
- One-to-One relationships
- One-to-Many relationships
- Many-to-Many relationships where applicable

## 🧠 Main Goal

The main goal of this lesson is to understand how the concepts we have learned in Java can be applied to designing a real-world software system.

We want to move from:

**Java Concepts → Objects → System Design → Database Design → Application**

Before we start writing the Banking System code, we should understand **what we are building, how the components interact, and how the data is structured**.
# Algebraic Equation API

A Spring Boot REST API to store, parse, and evaluate algebraic equations using
Infix → Postfix conversion and an Expression Tree (Postfix Tree).

This project is implemented as part of a backend assignment and follows
REST principles with no UI, fully testable via Postman.

---

## 🚀 Features

- Store algebraic equations (example: 3x + 2y - z)
- Convert equations from Infix to Postfix
- Build Expression Tree (Postfix Tree)
- Evaluate equations using provided variable values
- Proper validation and error handling (400 / 404)
- Fully testable using Postman
- Unit tests using JUnit 5 and MockMvc

---

## 🧰 Tech Stack

- Java 17
- Spring Boot
- Maven
- REST APIs (No UI)
- JUnit 5
- MockMvc

---

## ▶ How to Run the Project

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Ensure Java 17 is installed
4. Run AlgebraicEquationApiApplication
5. Application runs at http://localhost:8080

---

## 📁 Project Structure

src/main/java/com/example/algebraic
│
├── controller
│   └── EquationController.java
│
├── service
│   ├── InfixToPostfixConverter.java
│   └── ExpressionTreeBuilder.java
│
├── dto
│   ├── EquationItem.java
│   ├── EquationRequest.java
│   ├── VariableRequest.java
│   ├── ExpressionNode.java
│   └── StoreEquationResponse.java
│
└── AlgebraicEquationApiApplication.java

src/test/java/com/example/algebraic
│
├── EquationControllerTest.java
└── EquationControllerErrorTest.java

---

## 📌 API Endpoints

### 1️⃣ Store Equation

POST /api/equations/store

Request:
{
"equation": "3x + 2y - z"
}

Response:
200 OK

---

### 2️⃣ Get All Equations

GET /api/equations

Response:
200 OK

---

### 3️⃣ Evaluate Equation

POST /api/equations/{id}/evaluate

Request:
{
"variables": {
"x": 2,
"y": 1,
"z": 3
}
}

Response:
5

---

## ⚠ Error Handling

- 404 – Equation not found
- 400 – Missing variable
- 400 – Invalid equation

---

## 🧪 Testing

- Controller tests implemented using JUnit 5 and MockMvc
- Covers valid evaluation, missing variables, and invalid IDs

---

## ✅ Assignment Checklist

- [x] Java-based implementation
- [x] Spring Boot REST APIs (No UI)
- [x] Store algebraic equations
- [x] Infix → Postfix conversion
- [x] Postfix Expression Tree
- [x] Equation evaluation using tree
- [x] Validation & error handling
- [x] Tested using Postman
- [x] JUnit tests included
- [x] Maven project structure
- [x] README documentation

---

### 📌 Notes for Evaluators

- Expression evaluation implemented using a Postfix Expression Tree
- No third-party math libraries used
- Focus on correctness, clarity, and backend fundamentals

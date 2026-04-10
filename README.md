# Academic Platform Backend

Backend for an educational platform developed with **Spring Boot**, focused on the management of **users, roles, permissions, students, professors, and courses**, with authentication and authorization implemented through **Spring Security** and **JWT**.

This project was built based on a two-phase academic assignment:

- **Exercise 1:** implementation of security, authentication, and user management.
- **Exercise 2:** functional expansion with modeling and CRUD operations for students, professors, and courses.

---

## Project Objective

Develop a secure RESTful API for a university educational platform, allowing:

- user authentication through JWT,
- access control based on roles and permissions,
- administration of users, roles, and permissions,
- management of students, professors, and courses,
- protection of routes according to the user's access level.

---

## Main Features

### Security and Authentication
- User authentication with **Spring Security**
- Generation and validation of **JSON Web Tokens (JWT)**
- Protected endpoints based on authentication and authorization
- Public login endpoint
- Restricted access according to roles and permissions

### User Management
- CRUD operations for users
- Association of users with roles
- Administration of roles and permissions
- Restriction so that only **administrators** can create or update users

### Academic Management
- CRUD operations for students
- CRUD operations for professors
- CRUD operations for courses
- Relationships between students, professors, and courses

---

## System Roles

The system includes the following main roles:

- **ADMINISTRATOR**
- **PROFESSOR**
- **STUDENT**

---

## Access Rules

### Administrator
Can perform all CRUD operations on:
- users
- roles
- permissions
- students
- professors
- courses

### Student
Can:
- view students
- view courses

Cannot:
- create
- update
- delete entities

### Professor
Can:
- view courses
- view professors
- view students

### Optional Extra
- A professor may have **edit permissions** for the courses in which they are assigned as the instructor.

---

## Data Model

The project includes two main areas:

### 1. Security
Core entities for authentication and authorization:

- **User**
- **Role**
- **Permission**

General relationships:
- one user can have one or more roles
- one role can have one or more permissions

### 2. Academic Domain
Entities in the educational system:

- **Student**
- **Professor**
- **Course**

Business relationships:
- one course has **a list of students**
- one course has **only one assigned professor**
- one professor can teach **one or more courses**
- one student can be enrolled in **one or more courses**

---

## Technologies Used

- **Java**
- **Spring Boot**
- **Spring Web**
- **Spring Security**
- **JWT**
- **Spring Data JPA**
- **Hibernate**
- **MySQL**
- **Maven**
- **Postman**

---

## Project Structure

```bash
academic-platform-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...
│   │   │       ├── controller/
│   │   │       ├── dto/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── security/
│   │   └── resources/
│   │       ├── application.properties
│   │       └── ...
└── pom.xml

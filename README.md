# Academic Platform API with Spring Security & JWT

Backend REST API for an academic platform built with Java and Spring Boot, focused on authentication, authorization, and **role-based access control** using **Spring Security** and **JWT**.

This project also includes management of **users, roles, permissions, students, professors, and courses**, following a layered backend architecture.

## Overview

- secure authentication with JWT
- authorization based on roles and permissions
- protected REST endpoints
- academic entity management
- layered architecture with Spring Boot

It was designed as a backend application where security is one of the central components of the system.

## Features

### Security and Access Control
- Authentication with **Spring Security**
- Token-based authentication using **JWT**
- Protected endpoints based on user authentication
- Authorization using **roles and permissions**
- Public authentication endpoint for login
- Access restriction depending on the user's role

### User and Security Management
- CRUD operations for **users**
- CRUD operations for **roles**
- CRUD operations for **permissions**
- Association between users, roles, and permissions
- Administrative control over security-related entities

### Academic Management
- CRUD operations for **students**
- CRUD operations for **professors**
- CRUD operations for **courses**
- Relationships between students, professors, and courses

## Roles

The system includes three main roles:

- **ADMINISTRATOR**
- **PROFESSOR**
- **STUDENT**

## Access Rules

### Administrator
Has full access to manage:

- users
- roles
- permissions
- students
- professors
- courses

### Professor
Can access academic information such as:

- professors
- students
- courses

### Student
Can access read-only academic information such as:

- students
- courses

## Data Model

The project is divided into two main domains:

### Security Domain
Handles authentication and authorization through:

- **User**
- **Role**
- **Permission**

Relationships include:

- a user can have one or more roles
- a role can have one or more permissions

### Academic Domain
Handles the educational platform entities:

- **Student**
- **Professor**
- **Course**

Relationships include:

- one course can contain multiple students
- one course is assigned to one professor
- one professor can teach multiple courses
- one student can enroll in multiple courses

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Security
- JWT
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Postman

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

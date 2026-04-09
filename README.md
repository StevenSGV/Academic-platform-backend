# Academic Platform Backend

Backend de una plataforma educativa desarrollado con **Spring Boot**, enfocado en la **gestión de usuarios, roles, permisos, estudiantes, profesores y cursos**, con autenticación y autorización mediante **Spring Security** y **JWT**.

Este proyecto fue construido a partir de una consigna académica dividida en dos etapas:

- **Ejercicio 1:** implementación de seguridad, autenticación y gestión de usuarios.
- **Ejercicio 2:** ampliación funcional con modelado y CRUD de estudiantes, profesores y cursos.

---

## Objetivo del proyecto

Desarrollar una API RESTful segura para una plataforma educativa universitaria, permitiendo:

- autenticación de usuarios mediante JWT,
- control de acceso basado en roles y permisos,
- administración de usuarios, roles y permisos,
- gestión de estudiantes, profesores y cursos,
- protección de rutas según el nivel de acceso del usuario.

---

## Funcionalidades principales

### Seguridad y autenticación
- Autenticación de usuarios con **Spring Security**
- Generación y validación de **JSON Web Tokens (JWT)**
- Protección de endpoints según autenticación y autorización
- Ruta de login pública
- Acceso restringido según roles y permisos

### Gestión de usuarios
- CRUD de usuarios
- Asociación de usuarios con roles
- Administración de roles y permisos
- Restricción para que solo los **administradores** puedan crear o editar usuarios

### Gestión académica
- CRUD de estudiantes
- CRUD de profesores
- CRUD de cursos
- Relación entre estudiantes, profesores y cursos

---

## Roles del sistema

El sistema contempla los siguientes roles principales:

- **ADMINISTRADOR**
- **PROFESOR**
- **ESTUDIANTE**

---

## Reglas de acceso

### Administrador
Puede realizar todas las operaciones CRUD sobre:
- usuarios
- roles
- permisos
- estudiantes
- profesores
- cursos

### Estudiante
Puede:
- consultar estudiantes
- consultar cursos

No puede:
- crear
- editar
- eliminar entidades

### Profesor
Puede:
- consultar cursos
- consultar profesores
- consultar estudiantes

### Extra opcional
- Un profesor puede tener permiso de **edición** sobre los cursos en los que está asignado como docente.

---

## Modelo de datos

El proyecto contempla dos áreas principales:

### 1. Seguridad
Entidades base para autenticación y autorización:

- **User**
- **Role**
- **Permission**

Relaciones generales:
- un usuario puede tener uno o varios roles
- un rol puede tener uno o varios permisos

### 2. Dominio académico
Entidades del sistema educativo:

- **Student**
- **Professor**
- **Course**

Relaciones de negocio:
- un curso tiene **una lista de alumnos**
- un curso tiene **un solo profesor asignado**
- un profesor puede dictar **uno o varios cursos**
- un alumno puede estar inscrito en **uno o varios cursos**

---

## Tecnologías utilizadas

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

## Estructura general del proyecto

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

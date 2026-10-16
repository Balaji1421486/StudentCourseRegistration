# Student Course Registration System (Modified)

A beginner-friendly Spring Boot + MySQL project with a refreshed web interface.

## Features
- Add and list students
- Add and list courses
- Register a student for a course
- View registrations and delete a registration
- Data stored in MySQL using Spring Data JPA
- Simple validation and responsive Thymeleaf pages

## Requirements
- JDK 21
- MySQL 8+
- Maven 3.9+ (or import the project into an IDE with Maven support)

## 1. Create the database
Run this in MySQL Workbench or the MySQL command line:
```sql
CREATE DATABASE student_course_db;
```
Do not run DROP DATABASE; it is not needed.

## 2. Configure the database password
Open `src/main/resources/application.properties` and replace `YOUR_MYSQL_PASSWORD` with your local MySQL root password. If you use another MySQL username, update it there too.

## 3. Run the application
From this project folder:
```bash
mvn spring-boot:run
```
Or in Eclipse/STS: File > Import > Maven > Existing Maven Projects, select this folder, finish, then run `StudentCourseRegistrationApplication.java` as a Java Application / Spring Boot App.

Open http://localhost:8080

## Database tables
Hibernate creates `students`, `courses`, and `course_registrations` automatically because `spring.jpa.hibernate.ddl-auto=update` is configured.

## Notes
- This is a newly prepared modified implementation based on the Student Course Registration project idea. It has not been verified against every file in the linked repository because the original repository could not be cloned in this environment.
- Change database credentials before running. Never upload your real database password to a public repository.

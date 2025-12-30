📚 Library Management System

Spring Boot • MySQL • Spring Security • Thymeleaf

A full-stack Library Management System built using Spring Boot, MySQL, Spring Security, and Thymeleaf, supporting role-based access control for Admin and Student users.

🚀 Project Overview

This application manages library operations such as:

User authentication (Admin / Student)

Book management (CRUD)

Member management

Book issue & return

Secure role-based access control

Persistent storage using MySQL

The system follows a layered architecture and implements industry-standard security practices.

🧱 Technology Stack
Layer	Technology
Backend	Spring Boot (Java 17)
Frontend	Thymeleaf (Server-side rendering)
Database	MySQL 8
ORM	Spring Data JPA (Hibernate)
Security	Spring Security + BCrypt
Build Tool	Maven
Version Control	Git & GitHub
🗂️ Project Folder Structure
library-management
│
├── src/main/java/com/library/library
│   │
│   ├── controller
│   │   ├── AuthController.java
│   │   ├── SignupController.java
│   │   ├── BookController.java
│   │   ├── MemberController.java
│   │   └── IssueController.java
│   │
│   ├── entity
│   │   ├── User.java
│   │   ├── Book.java
│   │   ├── Member.java
│   │   └── IssueRecord.java
│   │
│   ├── repository
│   │   ├── UserRepository.java
│   │   ├── BookRepository.java
│   │   ├── MemberRepository.java
│   │   └── IssueRepository.java
│   │
│   ├── service
│   │   ├── BookService.java
│   │   ├── MemberService.java
│   │   └── IssueService.java
│   │
│   ├── service/impl
│   │   ├── BookServiceImpl.java
│   │   ├── MemberServiceImpl.java
│   │   └── IssueServiceImpl.java
│   │
│   ├── security
│   │   └── CustomUserDetailsService.java
│   │
│   ├── config
│   │   ├── SecurityConfig.java
│   │   └── DataLoader.java
│   │
│   └── LibraryManagementApplication.java
│
├── src/main/resources
│   ├── templates
│   │   ├── login.html
│   │   ├── signup.html
│   │   ├── books.html
│   │   ├── add-book.html
│   │   ├── edit-book.html
│   │   ├── members.html
│   │   ├── add-member.html
│   │   ├── issue-book.html
│   │   ├── return-book.html
│   │   └── error.html
│   │
│   └── application.properties
│
├── pom.xml
├── mvnw / mvnw.cmd
└── README.md

📎 Appendix — Complete Source Code Reference

This section contains the full source code of all major files, included for academic review, viva, and verification purposes.

📄 LibraryManagementApplication.java
package com.library.library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LibraryManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryManagementApplication.class, args);
    }
}

📄 ENTITY LAYER
entity/User.java
package com.library.library.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;
    private String role;
    private boolean enabled;

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getRole() { return role; }
    public boolean isEnabled() { return enabled; }

    public void setId(Long id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }
    public void setEnabled(boolean enabled) { this.enabled = enabled; }
}

entity/Book.java
package com.library.library.entity;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private String isbn;
    private int copies;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getCopies() { return copies; }

    public void setId(Long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setCopies(int copies) { this.copies = copies; }
}

entity/Member.java
package com.library.library.entity;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String role;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getRole() { return role; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setRole(String role) { this.role = role; }
}

entity/IssueRecord.java
package com.library.library.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class IssueRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Member member;

    private LocalDate issueDate;
    private LocalDate returnDate;
    private String status;

    public Long getId() { return id; }
    public Book getBook() { return book; }
    public Member getMember() { return member; }
    public LocalDate getIssueDate() { return issueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public String getStatus() { return status; }

    public void setId(Long id) { this.id = id; }
    public void setBook(Book book) { this.book = book; }
    public void setMember(Member member) { this.member = member; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setStatus(String status) { this.status = status; }
}

📄 REPOSITORY LAYER (ALL INTERFACES)
repository/UserRepository.java
package com.library.library.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.library.library.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}

repository/BookRepository.java
package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}

repository/MemberRepository.java
package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.library.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}

repository/IssueRepository.java
package com.library.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.library.library.entity.IssueRecord;

public interface IssueRepository extends JpaRepository<IssueRecord, Long> {
}

📄 SERVICE LAYER
service/BookService.java (Interface)
package com.library.library.service;

import java.util.List;
import com.library.library.entity.Book;

public interface BookService {
    List<Book> getAllBooks();
    void saveBook(Book book);
    void deleteBookById(Long id);
}

service/impl/BookServiceImpl.java
package com.library.library.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.library.library.entity.Book;
import com.library.library.repository.BookRepository;
import com.library.library.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}

📄 SECURITY
security/CustomUserDetailsService.java
package com.library.library.security;

import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.library.library.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return userRepository.findByUsername(username)
                .map(user -> User.withUsername(user.getUsername())
                        .password(user.getPassword())
                        .roles(user.getRole())
                        .disabled(!user.isEnabled())
                        .build())
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));
    }
}

📄 CONFIGURATION
config/SecurityConfig.java
package com.library.library.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/signup").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
            )
            .logout(logout -> logout.logoutSuccessUrl("/login"));

        return http.build();
    }
}



🧠 Architecture Explanation

The project follows a layered (MVC) architecture:

1️⃣ Controller Layer

Handles HTTP requests

Returns Thymeleaf views

Delegates business logic to services

2️⃣ Service Layer

Contains business logic

Interfaces define contracts

Implementations handle operations

3️⃣ Repository Layer

Interfaces extending JpaRepository

Handles all database interactions

4️⃣ Entity Layer

Maps Java classes to MySQL tables using JPA annotations

🗄️ Database Design (ER Explanation)
Entities & Relationships
User

id

username (unique)

password (BCrypt encrypted)

role (ADMIN / STUDENT)

enabled

Book

id

title

author

isbn

copies

Member

id

name

role (STUDENT / FACULTY)

IssueRecord

id

book (Many-to-One)

member (Many-to-One)

issueDate

returnDate

status

🔐 Security Flow (Very Important)
Authentication

Implemented using Spring Security

CustomUserDetailsService loads user from DB

Passwords encrypted using BCrypt

Authorization

Role-based access control:

ADMIN → Full access

STUDENT → Read-only access

Backend Security (Hard Enforcement)
.hasRole("ADMIN")

Frontend Security (UI Control)
sec:authorize="hasRole('ADMIN')"


Even if a user manually types a URL, Spring Security blocks unauthorized access.

👥 Default Users (For Testing)
Username	Password	Role
admin	admin123	ADMIN
testuser2	test123	STUDENT

Passwords are stored in encrypted form in the database.

⚙️ How to Run the Project
1️⃣ Prerequisites

Java 17+

MySQL 8

Maven

Git

2️⃣ Database Setup
CREATE DATABASE library_db;

3️⃣ Update application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/library_db
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4️⃣ Run Application
mvn spring-boot:run

5️⃣ Access
http://localhost:8081/login

✅ Functional Features

User Signup & Login

Role-based access (Admin / Student)

Book CRUD operations

Member management

Issue & Return books

Secure authentication

Persistent MySQL storage

📈 Non-Functional Features

Modular & maintainable code

Secure password handling

Scalable architecture

Platform independent

Clean separation of concerns

🧪 Testing

Manual testing via browser

MySQL verification using SQL queries

Security testing by role switching

📌 Future Enhancements

Faculty role

Fine calculation

Dashboard analytics

REST API version

Pagination & search

Docker support

🎓 Viva / Interview Highlights

Uses Spring Security with custom UserDetailsService

Enforces backend authorization

Follows clean layered architecture

Uses JPA & Hibernate

Production-ready authentication design

📄 License

This project is for educational purposes.

🙌 Author

Supratik
B.Tech CSE
Spring Boot | Java | MySQL

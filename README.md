#  Multi-Tenant SaaS POS System (Spring Boot Backend)

Enterprise-grade backend for a multi-branch Point of Sale (POS) platform that enables businesses to manage stores, products, users, and transactions from a centralized system.
The application follows a scalable multi-tenant architecture with secure authentication and role-based access control.

---

##  Features Implemented

###  Authentication & Security

* JWT-based authentication
* Spring Security integration
* Role-based access control (Admin / Manager / Cashier)
* Stateless session handling

###  User Management

* Create & manage users
* Assign roles and permissions
* Secure protected endpoints

###  Store Management

* Multi-branch store support
* Store ownership validation
* Tenant data isolation

### 📦 Product Management

* Add, update, delete products
* Store-specific product mapping
* Validation & exception handling

---

##  Tech Stack

| Layer      | Technology           |
| ---------- | -------------------- |
| Backend    | Java, Spring Boot    |
| Security   | Spring Security, JWT |
| Database   | MySQL                |
| ORM        | JPA / Hibernate      |
| API        | RESTful APIs         |
| Build Tool | Maven                |
| Testing    | Postman              |

---

##  Architecture

The project follows layered architecture:

Controller → Service → Repository → Database

Additional patterns used:

* DTO Pattern
* Global Exception Handling
* Validation Layer
* Stateless Authentication

---

##  API Modules (Current Progress)

Implemented:

* Auth APIs
* User APIs
* Store APIs
* Product APIs

Upcoming:

* Orders & Billing
* Payments Integration
* Inventory Tracking
* Reports & Analytics
* Refund Management

---

##  Running the Project Locally

### 1. Clone Repository

```bash
git clone https://github.com/dvijaykumarr/multi-tenant-pos-system
```

### 2. Configure Database

Create a database in MySQL and update:

`src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pos_db
spring.datasource.username=your_username
spring.datasource.password=your_password
jwt.secret=your_secret_key
```

### 3. Run Application

```bash
mvn spring-boot:run
```

Server starts at:

```
http://localhost:8080
```

---

##  Learning Goals of This Project

* Understand real-world backend architecture
* Implement stateless authentication using JWT
* Build scalable multi-tenant systems
* Design production-ready REST APIs

---

##  Project Status

🚧 Actively under development

This project is continuously being expanded with advanced POS features such as billing, analytics, and payment integrations.

---



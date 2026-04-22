# Multi-Tenant POS System — Backend

> A well-structured Point of Sale backend built with Spring Boot, designed for scalable multi-branch retail operations with secure role-based access control.

---

## Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Getting Started](#getting-started)
- [API Reference](#api-reference)
- [Postman Collection](#postman-collection)
- [Technical Highlights](#technical-highlights)
- [Author](#author)

---

## Overview

This project is a well-structured backend system for managing multi-tenant Point of Sale operations across multiple branches. It is built following clean architecture principles and simulates real-world retail workflows — including inventory management, order processing, billing, refund handling, and shift-based reporting.

The system is designed with extensibility and security in mind, making it a strong foundation for enterprise retail platforms.

---

## Key Features

| Feature | Description |
|---|---|
| **JWT Authentication** | Secure token-based login and session management |
| **Role-Based Access Control** | Granular permissions for Admin, Manager, and Employee roles |
| **Multi-Branch Management** | Independent management of multiple business branches |
| **Product & Inventory** | Full product lifecycle and cross-branch inventory tracking |
| **Order & Billing** | End-to-end order creation and billing processing |
| **Refund Handling** | Structured refund workflows for completed transactions |
| **Shift Reporting** | Detailed shift-based reports for business performance analysis |
| **RESTful API Design** | Clean, resource-oriented REST endpoints |

---

## Tech Stack

| Layer | Technology |
|---|---|
| Language | Java |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Database | MySQL |
| ORM | Hibernate / Spring Data JPA |
| Build Tool | Maven |
| API Testing | Postman |

---

## Architecture

The project follows a structured layered architecture to ensure clean separation of concerns and maintainability:

```
Controller  →  Service  →  Repository  →  Database
     ↑              ↑
    DTO          Mapper
```

**Architectural Highlights:**

- **Controller Layer** — Handles HTTP requests, input validation, and response formatting via DTOs
- **Service Layer** — Encapsulates all business logic and orchestrates operations
- **Repository Layer** — Manages database access through Spring Data JPA interfaces
- **DTO Layer** — Decouples API contracts from internal domain models
- **Mapper Layer** — Handles bidirectional conversion between entities and DTOs

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+

### Installation

```bash
# 1. Clone the repository
git clone https://github.com/dvijaykumarr/multi-tenant-pos

# 2. Navigate into the project directory
cd multi-tenant-pos

# 3. Configure your database credentials
# Edit src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/pos_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# 4. Build the project
mvn clean install

# 5. Run the application
mvn spring-boot:run
```

The server will start at `http://localhost:5000`.

---

## API Reference

Base URL: `http://localhost:5000`

### Authentication & Users

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/auth/signup` | Register a new user |
| `POST` | `/auth/login` | Authenticate and receive JWT token |
| `GET` | `/api/users/profile` | Get current user profile |
| `GET` | `/api/users/{id}` | Get user by ID |

### Store

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/stores` | Create a new store |
| `PUT` | `/api/stores/{id}` | Update store details |

### Categories

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/categories/{storeId}` | Create a category under a store |
| `GET` | `/api/categories/store/{storeId}` | Get all categories by store |
| `PUT` | `/api/categories/{id}` | Update a category |

### Products

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/products` | Create a new product |
| `PATCH` | `/api/products/{id}` | Update product details |

### Branches

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/branches` | Create a new branch |
| `GET` | `/api/branches/store/{storeId}` | Get all branches under a store |
| `PUT` | `/api/branches/{id}` | Update branch details |

### Inventory

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/inventories` | Create inventory entry |
| `PUT` | `/api/inventories/{id}` | Update inventory |
| `GET` | `/api/inventories/branch/{branchId}` | Get all inventory for a branch |
| `GET` | `/api/inventories/branch/{branchId}/product/{productId}` | Get inventory by branch and product |

### Employees

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/employees/store/{storeId}` | Create a store-level employee |
| `POST` | `/api/employees/branch/` | Create a branch-level employee |
| `PUT` | `/api/employees/{id}` | Update employee details |
| `GET` | `/api/employees/store/{storeId}?userRole=` | Get store employees by role |
| `GET` | `/api/employees/branch/{branchId}?userRole=` | Get branch employees by role |

### Customers

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/customers` | Create a customer |
| `GET` | `/api/customers` | Get all customers |
| `GET` | `/api/customers/search?q={keyword}` | Search customers by keyword |
| `PUT` | `/api/customers/{id}` | Update customer details |
| `DELETE` | `/api/customers/{id}` | Delete a customer |

### Orders

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/orders` | Create a new order |
| `GET` | `/api/orders/{id}` | Get order by ID |
| `GET` | `/api/orders/branch/{branchId}` | Get all orders for a branch |
| `GET` | `/api/orders/branch/{branchId}?customerId={id}` | Filter orders by branch and customer |
| `GET` | `/api/orders/branch/{branchId}?paymentType={type}` | Filter orders by payment type |
| `GET` | `/api/orders/cashier/{cashierId}` | Get orders by cashier |
| `GET` | `/api/orders/today/branch/{branchId}` | Get today's orders for a branch |
| `GET` | `/api/orders/customer/{customerId}` | Get orders by customer |
| `GET` | `/api/orders/recent/{branchId}` | Get recent orders for a branch |

### Refunds

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/refunds` | Create a refund |
| `GET` | `/api/refunds` | Get all refunds |
| `GET` | `/api/refunds/{id}` | Get refund by ID |
| `GET` | `/api/refunds/branch/{branchId}` | Get refunds by branch |
| `GET` | `/api/refunds/cashier/{cashierId}` | Get refunds by cashier |
| `GET` | `/api/refunds/cashier/{cashierId}/range?startDate=&endDate=` | Get refunds by cashier within date range |

### Shift Reports

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/api/shift-reports/start` | Start a new shift |
| `PATCH` | `/api/shift-reports/end` | End the current shift |
| `GET` | `/api/shift-reports/current` | Get current shift progress |
| `GET` | `/api/shift-reports/{id}` | Get shift report by ID |
| `GET` | `/api/shift-reports/branch/{branchId}` | Get all shift reports for a branch |
| `GET` | `/api/shift-reports/cashier/{cashierId}/by-date?date=` | Get shift report by cashier and date |

---

## Postman Collection

A complete Postman collection is included in the repository for testing all available endpoints.

**[Download Postman Collection](./vijay-pos.postman_collection.json)**

Import the file into Postman and set the `base_url` environment variable to `http://localhost:5000` to get started.

---

## Technical Highlights

- Designed a **multi-tenant architecture** where stores, branches, employees, and inventory are fully isolated per tenant — reflecting real-world enterprise system design
- Implemented **JWT-based authentication** from scratch using Spring Security, including token generation, validation, and role-based route protection
- Applied **RBAC (Role-Based Access Control)** with distinct permission levels for Store Managers, Branch Managers, and Cashiers — enforced at the API layer
- Used **DTO and Mapper patterns** to decouple the API contract from internal domain models, keeping the codebase clean and maintainable
- Modelled complex real-world business workflows including order processing, inventory tracking across branches, refund handling, and shift-based reporting
- Followed **layered architecture principles** (Controller → Service → Repository) to ensure separation of concerns and ease of testing

---

## Author

**Daarivemula Vijay Kumar**

- Email: [dvk200507@gmail.com](mailto:dvk200507@gmail.com)
- LinkedIn: [linkedin.com/in/dvijaykumarr](https://linkedin.com/in/dvijaykumarr)
- GitHub: [github.com/dvijaykumarr](https://github.com/dvijaykumarr)

---

> Built with a focus on clean architecture, security, and real-world retail business logic.

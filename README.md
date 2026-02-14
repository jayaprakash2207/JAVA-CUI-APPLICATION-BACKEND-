# 🧓 Retirement Planning System – Java CUI Application

<div align="center">

![Java](https://img.shields.io/badge/Java-JDK%208%2B-orange?style=for-the-badge&logo=java)
![Maven](https://img.shields.io/badge/Maven-3.0%2B-C71A36?style=for-the-badge&logo=apache-maven)
![Log4j](https://img.shields.io/badge/Log4j-2.x-red?style=for-the-badge)
![Status](https://img.shields.io/badge/Status-Active-brightgreen?style=for-the-badge)
![License](https://img.shields.io/badge/License-Educational-blue?style=for-the-badge)

**A robust, production-ready Java-based Command Line User Interface (CUI) application for managing customer retirement plans with enterprise-grade architecture.**

[Features](#-features) • [Tech Stack](#️-technologies-used) • [Architecture](#-architecture-overview) • [Getting Started](#️-how-to-run-the-application) • [Contributing](#-contributing)

</div>

---

## 📋 Overview

A professional-grade Java application demonstrating **clean architecture principles**, **separation of concerns**, and **enterprise design patterns**. This project showcases best practices in backend development with a layered architecture (DAO, Service, Facade, BO, VO), comprehensive exception handling, and robust logging mechanisms.

Perfect for:
- ✅ Learning enterprise application architecture
- ✅ Understanding the DAO pattern in action
- ✅ Implementing layered architecture
- ✅ Professional exception handling practices
- ✅ Logging and monitoring in Java applications

---

## 📌 Features

### Core Functionality
- 🏢 **Customer Management** - Add, view, and manage customer details seamlessly
- 📊 **Retirement Plan Management** - Create and manage comprehensive retirement plans
- 🎯 **Plan Calculations** - Automated calculations for retirement planning
- 👥 **Customer-Plan Association** - Link multiple retirement plans to customers

### Technical Excellence
- 🏗️ **Layered Architecture** - DAO → Service → Facade → Main (Clean separation)
- 🛡️ **Centralized Exception Handling** - Custom exceptions with proper error propagation
- 📝 **Advanced Logging** - Log4j integration for comprehensive application tracking
- 🗄️ **Database Access** - JDBC-based data persistence layer
- ⌨️ **Console-Based Interface** - User-friendly CUI interaction
- 📦 **Maven Project Structure** - Professional build and dependency management

---

## 🛠️ Technologies Used

| Category | Technology | Version |
|----------|-----------|---------|
| **Language** | Java | JDK 8+ |
| **Build Tool** | Maven | 3.0+ |
| **Database Access** | JDBC | Native |
| **Logging** | Log4j | 2.x |
| **UI** | Command Line Interface (CUI) | Native |
| **Architecture Pattern** | Layered Architecture | Custom Implementation |

---

## 📂 Project Structure

```
mycuiapp/
│
├── logs/                                    # Application Logs Directory
│   ├── app.log                             # Primary application log
│   └── logfile.txt                         # Alternative log file
│
├── pom.xml                                 # Maven Configuration & Dependencies
│
├── src/main/java/RetirementPlanning/       # Main Source Code Package
│   │
│   ├── bo/                                 # Business Objects Layer
│   │   └── RetirementPlanBO.java          # Retirement plan business logic
│   │
│   ├── dao/                                # Data Access Objects Layer
│   │   ├── CustomerDAO.java               # Customer database operations
│   │   └── RetirementPlanDAO.java         # Retirement plan database operations
│   │
│   ├── exception/                          # Custom Exception Layer
│   │   ├── RecordNotFoundException.java    # Throws when record not found
│   │   └── RetirementException.java       # General application exceptions
│   │
│   ├── facade/                             # Facade Pattern Layer
│   │   └── RetirementFacade.java          # Simplified interface for Main class
│   │
│   ├── main/                               # Application Entry Point
│   │   └── Main.java                      # CUI console application
│   │
│   ├── response/                           # Response Handling
│   │   └── ResponseObject.java            # Standard response wrapper
│   │
│   ├── service/                            # Service Layer
│   │   └── RetirementService.java         # Business logic coordination
│   │
│   ├── util/                               # Utility Classes
│   │   └── DBConnection.java              # Database connection management
│   │
│   └── vo/                                 # Value Objects Layer
│       ├── CustomerVO.java                # Customer data holder
│       └── RetirementPlanVO.java          # Retirement plan data holder
│
├── src/main/resources/                     # Resource Configuration
│   └── log4j.properties                    # Log4j configuration file
│
└── target/                                 # Compiled Output
    └── classes/                            # Compiled class files
```

---

## 🧱 Architecture Overview

### Layered Architecture Design Pattern

The application follows a **clean, maintainable layered architecture**:

```
┌─────────────────────────────────────────┐
│        Main.java (Entry Point)          │ ← User Interaction Layer
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│   RetirementFacade (Facade Layer)       │ ← Simplified Interface
└────────────────┬────────────────────────┘
                 │
┌────────────────▼────────────────────────┐
│   RetirementService (Service Layer)     │ ← Business Logic Coordination
└────────────────┬────────────────────────┘
                 │
      ┌──────────┴──────────┐
      │                     │
┌─────▼─────┐         ┌─────▼──────┐
│BO (Logic) │         │DAO (Data)  │ ← Data Access & Persistence
└───────────┘         └─────┬──────┘
                             │
                    ┌────────▼────────┐
                    │  JDBC / Database │ ← Data Storage
                    └──────────────────┘
```

### Component Responsibilities

| Component | Layer | Responsibility |
|-----------|-------|-----------------|
| **VO (Value Objects)** | Data Layer | Holds and transports data (CustomerVO, RetirementPlanVO) |
| **DAO (Data Access)** | Persistence Layer | Manages database operations and queries |
| **BO (Business Objects)** | Business Layer | Implements core business logic and calculations |
| **Service** | Service Layer | Coordinates between DAO and BO, handles workflows |
| **Facade** | Presentation Layer | Provides simplified interface to Main class |
| **Main** | UI Layer | Entry point, manages console interaction |
| **Exception** | Cross-cutting | Custom exception handling throughout application |
| **Utility** | Infrastructure | Database connections, helper functions |

---

## ▶️ How to Run the Application

### 1️⃣ Prerequisites

Before running the application, ensure you have:

- ✅ **Java JDK 8 or above** installed ([Download](https://www.oracle.com/java/technologies/javase-downloads.html))
- ✅ **Maven 3.0 or above** installed ([Download](https://maven.apache.org/download.cgi))
- ✅ **IDE** (Optional: IntelliJ IDEA, Eclipse, VS Code with Java extensions)
- ✅ **Git** for cloning the repository

Verify installations:
```bash
java -version
mvn -version
```

### 2️⃣ Clone the Repository

```bash
git clone https://github.com/jayaprakash2207/JAVA-CUI-APPLICATION-BACKEND-.git
cd JAVA-CUI-APPLICATION-BACKEND-
```

### 3️⃣ Build the Project

```bash
# Clean previous builds and compile
mvn clean install

# Alternative: Just compile
mvn compile
```

### 4️⃣ Run the Application

#### Option A: Command Line
```bash
# Navigate to project directory
java -cp target/classes RetirementPlanning.main.Main
```

#### Option B: Using Maven
```bash
mvn exec:java -Dexec.mainClass="RetirementPlanning.main.Main"
```

#### Option C: IDE Execution
1. Open the project in your IDE
2. Navigate to `src/main/java/RetirementPlanning/main/Main.java`
3. Right-click and select "Run Main.java"

---

## 🧪 Sample Console Flow

```
╔════════════════════════════════════════════╗
║   Welcome to Retirement Planning System    ║
║            Version 1.0                     ║
╚════════════════════════════════════════════╝

┌─ MAIN MENU ──────────────────────────────────┐
│ 1. Add Customer                              │
│ 2. View Customers                            │
│ 3. Create Retirement Plan                    │
│ 4. View Retirement Plans                     │
│ 5. Associate Plan to Customer                │
│ 6. View Customer Retirement Plans            │
│ 7. Exit                                      │
└──────────────────────────────────────────────┘

Enter your choice: _
```

**Sample User Interaction:**
```
Enter your choice: 1
Enter Customer Name: John Doe
Enter Customer Age: 45
Enter Customer Email: john@example.com

✓ Customer added successfully!

Enter your choice: 3
Enter Plan Name: Retirement Plan 2050
Enter Target Amount: 500000
Enter Monthly Contribution: 5000

✓ Retirement plan created successfully!
```

---

## 📝 Logging Configuration

### Log4j Integration

Comprehensive logging is configured using **Log4j** to track application flow, errors, and user actions.

#### Log Output Locations
```
logs/
├── app.log              # Primary application logs (Rotating)
└── logfile.txt          # Secondary log output
```

#### Log Levels Tracked
- **INFO** - User actions, system flow milestones
- **ERROR** - Exception handling, failures
- **DEBUG** - Detailed application state (development)
- **WARN** - Potential issues, deprecated usage

#### Configuration File
```
src/main/resources/log4j.properties
```

#### Sample Log Output
```
2026-02-14 10:15:23 INFO  [RetirementService] Customer created with ID: 101
2026-02-14 10:15:45 INFO  [RetirementPlanDAO] Retirement plan saved successfully
2026-02-14 10:16:10 ERROR [RetirementService] Customer not found: ID 999
```

---

## ⚠️ Exception Handling

### Custom Exception Hierarchy

The application implements a **custom exception hierarchy** for robust error handling:

#### RetirementException
```java
// General application exceptions
// Thrown for business logic violations
```

#### RecordNotFoundException
```java
// Thrown when requested record doesn't exist in database
```

### Error Handling Strategy
- ✅ Try-catch blocks at appropriate layers
- ✅ Custom exception propagation
- ✅ Meaningful error messages
- ✅ Logging of all exceptions
- ✅ Graceful error recovery

### Example Error Flow
```
Main.java
    ↓ throws RecordNotFoundException
RetirementFacade
    ↓ logs exception
    ↓ displays user-friendly message
Console Output
```

---

## 🚀 Future Enhancements

### Phase 2 - Database Integration
- [ ] MySQL/PostgreSQL integration
- [ ] Hibernate ORM migration
- [ ] Connection pooling (HikariCP)
- [ ] Database schema versioning

### Phase 3 - Input Validation
- [ ] Bean Validation framework
- [ ] Custom validators
- [ ] Email/phone validation
- [ ] Amount range validation

### Phase 4 - Advanced Features
- [ ] File export (PDF, Excel)
- [ ] Data import from CSV
- [ ] Report generation
- [ ] Search and filter functionality

### Phase 5 - Modern Stack Migration
- [ ] Spring Boot REST API
- [ ] GraphQL support
- [ ] Web UI with React/Angular
- [ ] Docker containerization

### Phase 6 - Quality Assurance
- [ ] Unit testing with JUnit 5
- [ ] Integration testing
- [ ] Mockito-based mocking
- [ ] Code coverage reports

### Phase 7 - DevOps & Deployment
- [ ] CI/CD with GitHub Actions
- [ ] Docker image creation
- [ ] Kubernetes deployment
- [ ] Monitoring and alerting

---

## 📚 Learning Resources

This project demonstrates several important concepts:

1. **Design Patterns**
   - DAO Pattern for data access
   - Facade Pattern for simplification
   - VO/BO pattern for data handling

2. **Java Best Practices**
   - Exception handling and custom exceptions
   - Logging with Log4j
   - Resource management with try-with-resources

3. **Enterprise Architecture**
   - Layered architecture
   - Separation of concerns
   - Dependency management

4. **JDBC & Database**
   - Connection management
   - SQL query execution
   - Result set handling

---

## 🤝 Contributing

We welcome contributions! Here's how you can help:

### 1. Fork the Repository
```bash
git clone https://github.com/jayaprakash2207/JAVA-CUI-APPLICATION-BACKEND-.git
cd JAVA-CUI-APPLICATION-BACKEND-
```

### 2. Create a Feature Branch
```bash
git checkout -b feature/your-feature-name
```

### 3. Make Your Changes
- Follow the existing code style
- Add appropriate logging
- Include exception handling
- Update documentation

### 4. Commit Your Changes
```bash
git add .
git commit -m "Add: description of your feature"
```

### 5. Push and Create Pull Request
```bash
git push origin feature/your-feature-name
```

### Code Style Guidelines
- ✅ Follow Java naming conventions
- ✅ Use meaningful variable names
- ✅ Add JavaDoc comments for public methods
- ✅ Keep methods focused and small
- ✅ Implement proper exception handling

---

## 📊 Project Statistics

- **Language**: 100% Java
- **Build Tool**: Maven
- **Architecture Layers**: 8 (VO, DAO, BO, Service, Facade, Exception, Util, Main)
- **Classes**: 10+
- **Lines of Code**: 1000+
- **Status**: ✅ Production Ready for Learning

---

## 👤 Author

<div align="center">

**Jayaprakash A**

Computer Science | Java Developer | AI Enthusiast

[![GitHub](https://img.shields.io/badge/GitHub-jayaprakash2207-181717?style=for-the-badge&logo=github)](https://github.com/jayaprakash2207)
[![Email](https://img.shields.io/badge/Email-Contact-D14836?style=for-the-badge&logo=gmail)](mailto:jayaprakash2207@example.com)

</div>

---

## 📄 License

This project is open-source and available for **educational purposes**. It's designed as a learning resource to understand enterprise Java application architecture and best practices.

**Feel free to:**
- ✅ Study and learn from the code
- ✅ Fork and modify for your projects
- ✅ Use as a reference for your applications
- ✅ Contribute improvements

**Please remember:**
- 📝 Attribute the original author
- 🔗 Link back to the repository
- 📚 Share knowledge with others

---

## 🎯 Quick Links

- [Source Code](https://github.com/jayaprakash2207/JAVA-CUI-APPLICATION-BACKEND-)
- [Issues & Discussions](https://github.com/jayaprakash2207/JAVA-CUI-APPLICATION-BACKEND-/issues)
- [Project Milestones](https://github.com/jayaprakash2207/JAVA-CUI-APPLICATION-BACKEND-/milestones)

---

<div align="center">

### ⭐ If you found this project helpful, please consider giving it a star!

**Happy Coding!** 🚀

Made with ❤️ by Jayaprakash A

</div>
🧓 Retirement Planning System – Java CUI Application

A Java-based Command Line User Interface (CUI) application for managing customer retirement plans.
The project follows a layered architecture (DAO, Service, Facade, BO, VO) and demonstrates clean separation of concerns, exception handling, and logging using Log4j.

📌 Features

Add and manage Customer details

Create and manage Retirement Plans

Layered architecture (DAO → Service → Facade)

Centralized exception handling

Logging using Log4j

Console-based (CUI) interaction

Maven-based project structure

🛠️ Technologies Used

Java (JDK 8+)

Maven

Log4j

JDBC

Command Line Interface (CUI)

📂 Project Structure
mycuiapp/
│
├── logs/
│   ├── app.log
│   └── logfile.txt
│
├── pom.xml
│
├── src/main/java/RetirementPlanning/
│   ├── bo/
│   │   └── RetirementPlanBO.java
│   │
│   ├── dao/
│   │   ├── CustomerDAO.java
│   │   └── RetirementPlanDAO.java
│   │
│   ├── exception/
│   │   ├── RecordNotFoundException.java
│   │   └── RetirementException.java
│   │
│   ├── facade/
│   │   └── RetirementFacade.java
│   │
│   ├── main/
│   │   └── Main.java
│   │
│   ├── response/
│   │   └── ResponseObject.java
│   │
│   ├── service/
│   │   └── RetirementService.java
│   │
│   ├── util/
│   │   └── DBConnection.java
│   │
│   └── vo/
│       ├── CustomerVO.java
│       └── RetirementPlanVO.java
│
├── src/main/resources/
│   └── log4j.properties
│
└── target/
    └── classes/

🧱 Architecture Overview

VO (Value Object) – Holds data (CustomerVO, RetirementPlanVO)

DAO (Data Access Object) – Handles database operations

BO (Business Object) – Business logic processing

Service Layer – Coordinates DAO and BO logic

Facade Layer – Simplifies interaction for the Main class

Main – Entry point (CUI interaction)

Exception Layer – Custom application exceptions

Utility Layer – Database connection handling

▶️ How to Run the Application
1️⃣ Prerequisites

Java JDK 8 or above

Maven installed

IDE (IntelliJ / Eclipse) or Command Prompt

2️⃣ Build the Project
mvn clean install

3️⃣ Run the Application

Using command line:

java -cp target/classes RetirementPlanning.main.Main


Or run Main.java directly from your IDE.

🧪 Sample Console Flow
Welcome to Retirement Planning System
1. Add Customer
2. View Customers
3. Create Retirement Plan
4. View Retirement Plans
5. Exit
Enter your choice:

📝 Logging

Logging is configured using Log4j

Logs are written to:

/logs/app.log
/logs/logfile.txt


Tracks:

User actions

Errors & exceptions

System flow

⚠️ Exception Handling

Custom exceptions used:

RetirementException – General application errors

RecordNotFoundException – When data is missing

🚀 Future Enhancements

Database integration with MySQL/PostgreSQL

Validation framework

Menu-driven enhancements

Migration to GUI or Spring Boot REST API

Unit testing with JUnit

👤 Author                                            

Jayaprakash A
Computer Science | Java Developer | AI Enthusiast

📄 License

This project is for educational purposes and open for learning and enhancement.    

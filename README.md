Port Disbursement Account (PDA) Calculator
This project is a web-based Proforma Disbursement Account (PDA) Calculator for the commercial shipping industry. It provides a tool for charterers, ship operators, and agents to estimate port-related expenses for a vessel's call at various ports, such as Mumbai and Chennai.
The application is built using Java 17, Spring Boot, Spring MVC, Spring Data JPA, and MySQL.
Features
Dynamic Cost Estimation: Calculates the total estimated cost for a port call based on vessel and cargo specifics.
Port-Specific Rates: Fetches charges from a database, allowing for different rates for different ports (e.g., Mumbai, Chennai).
Detailed Cost Breakdown: Provides an itemized breakdown of charges, including:
Port Dues
Pilotage Fees
Berth Hire Charges
Wharfage (Cargo handling fees)
Support for Voyage Types: Differentiates between 'Foreign' and 'Coastal' voyages, applying the correct rate structure.
Vessel and Cargo-Based Calculation: Considers various parameters like:
Port Name
Vessel Type (Container, RoRo, etc.)
Gross Registered Tonnage (GRT)
Duration of stay at berth
Cargo Type and Quantity
Simple and Intuitive UI: A clean web interface for easy input and clear presentation of results.
Technology Stack
Backend: Java 17, Spring Boot 3.x
Frameworks: Spring MVC, Spring Data JPA, Hibernate
Database: MySQL
Frontend: JSP (JavaServer Pages) with JSTL
Build Tool: Apache Maven
Server: Embedded Tomcat
Project Structure
The project follows a standard Spring Boot structure:
Generated code
.
├── pom.xml
├── src
│   └── main
│       ├── java/com/example/pdacalculator/
│       │   ├── controller/   # Web request handling
│       │   ├── entity/       # JPA entities mapping to DB tables
│       │   ├── model/        # Data Transfer Objects (Form/Result)
│       │   ├── repository/   # Spring Data JPA repositories
│       │   └── service/      # Business logic and calculations
│       ├── resources/
│       │   ├── application.properties  # Main configuration file
│       │   ├── schema.sql              # Database table definitions
│       │   └── data.sql                # Initial rate data for ports
│       └── webapp/WEB-INF/jsp/
│           ├── calculator.jsp          # The input form page
│           └── result.jsp              # The results display page
└── README.md
Use code with caution.
Setup and Installation
Follow these steps to set up and run the project locally.
Prerequisites
Java Development Kit (JDK) 17 or newer
Apache Maven 3.6 or newer
MySQL Server (and a client like MySQL Workbench)
1. Database Setup
Open your MySQL client (e.g., MySQL Workbench).
Create a new database for the project. By default, the application is configured to use a database named pda_calculator.
Generated sql
CREATE DATABASE pda_calculator;
Use code with caution.
SQL
No need to create tables manually. The application will automatically create the required tables and populate them with initial data from the schema.sql and data.sql files on first run.
2. Application Configuration
Clone the repository or download the source code.
Navigate to src/main/resources/application.properties.
Update the database connection properties with your MySQL username and password.
Generated properties
# ... other properties ...

# !!! IMPORTANT: Replace with your actual MySQL username and password !!!
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password

# ... other properties ...
Use code with caution.
Properties
3. Build and Run the Application
Open a terminal or command prompt in the root directory of the project.
Run the application using the Maven Spring Boot plugin:
Generated bash
mvn spring-boot:run
Use code with caution.
Bash
The application will start on the embedded Tomcat server, typically on port 8080.
How to Use
Once the application is running, open your web browser and navigate to:
http://localhost:8080
You will see the PDA Calculator form.
Fill in the required details:
Select Port: Choose between Mumbai and Chennai.
Voyage Type: Select Foreign or Coastal.
Vessel Type: Select the type of your vessel.
Gross Registered Tonnage (GRT): Enter the vessel's GRT.
Estimated Berth Stay: Enter the number of hours the vessel will be at berth.
Cargo Details (Optional): Select a cargo type and enter the quantity in Metric Tonnes (MT) to calculate wharfage.
Click the "Calculate PDA" button.
The application will display the results on a new page, showing a summary of your inputs and a detailed breakdown of the estimated costs.

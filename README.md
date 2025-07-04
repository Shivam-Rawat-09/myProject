Proforma Disbursement Account (PDA) Calculator
1. Project Overview
The PDA Calculator is a full-stack web application designed for the commercial shipping industry. Its primary purpose is to calculate a Proforma Disbursement Account (PDA), which is a detailed cost estimate for a vessel's call at a specific port.
This tool enables charterers, ship operators, and port agents to accurately estimate port-related expenses before a vessel's arrival. It includes calculations for various costs such as port dues, pilotage, tug services, and agency fees. By providing a clear and editable interface, it helps in financial planning and reduces discrepancies during final settlements.
The application features a simple web-based UI for performing calculations and a REST API for potential integration with other systems.
2. Technology Stack
This project is built using a modern Java-based stack:
Backend:
Language: Java 17
Framework: Spring Boot 3+
Data Persistence: Spring Data JPA / Hibernate
Build Tool: Apache Maven
Database:
MySQL 8
Frontend:
Templating Engine: Thymeleaf
Styling: HTML5 & CSS3
3. Prerequisites
To build and run this project locally, you will need the following software installed on your machine:
Java Development Kit (JDK): Version 17 or later.
Apache Maven: Version 3.6 or later.
MySQL Server: Version 8 or later.
An IDE of your choice (e.g., VS Code, Cursor, IntelliJ IDEA).
A database client like MySQL Workbench is recommended for managing the database.
4. Configuration
Before running the application, you need to configure the database connection.
4.1. Database Setup
Ensure your local MySQL server is running.
Create a new database schema. The application is configured to use the name pda_db. You can create it with the following SQL command:
Generated sql
CREATE DATABASE pda_db;
Use code with caution.
SQL
4.2. Application Properties
Navigate to the configuration file located at: src/main/resources/application.properties.
Update the spring.datasource properties to match your local MySQL setup. Pay close attention to the password.
Generated properties
# MySQL Database Connection
spring.datasource.url=jdbc:mysql://localhost:3306/pda_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD_HERE

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Use code with caution.
Properties
4.3. Seeding Sample Data
The application requires initial data (ports, vessels, rates) to be functional. A sample SQL script has been prepared.
Open a connection to your pda_db schema in MySQL Workbench or another SQL client.
Execute the commands found in the provided data.sql script to populate your tables with sample data.
5. How to Build and Run
You can run the application using Maven or directly from your IDE.
5.1. Using the Command Line (Maven)
Open a terminal or command prompt in the root directory of the project.
Build the project using Maven. This will compile the code and package it into a .jar file.
Generated bash
mvn clean install
Use code with caution.
Bash
Once the build is successful, run the packaged application:
Generated bash
java -jar target/pda-calculator-0.0.1-SNAPSHOT.jar
Use code with caution.
Bash
5.2. Using an IDE
Import the project as a Maven project into your IDE.
Locate the main application class: com.example.pdacalculator.PdaCalculatorApplication.java.
Right-click on the file and select "Run" or "Debug".
The application will start, and the embedded Tomcat server will be listening on port 9090 by default.
6. How to Use the Application
Once the application is running, you can access the user interface and the API.
6.1. Web Interface
Open your web browser and navigate to:
http://localhost:9090/
You will see the Proforma Disbursement Account Calculator page.
Use the dropdown menus to select a Port, Vessel, and Cargo Type.
The "Estimated Costs" table will show the default service rates for the selected port. You can edit the values in the "Rate" text boxes to perform a what-if analysis.
Click the "Calculate" button.
You will be redirected to a result page showing the total estimated cost and a detailed breakdown of all the line-item calculations.
6.2. REST API Endpoints
The application also exposes a set of RESTful API endpoints for programmatic access.
GET /api/ports: Retrieves a list of all available ports.
GET /api/vessels: Retrieves a list of all available vessels.
GET /api/cargo-types: Retrieves a list of all available cargo types.
POST /api/pdas/calculate: Calculates and saves a new PDA. Requires a JSON body with portId, vesselId, cargoTypeId, and an optional overrides map.
GET /api/pdas/{id}: Retrieves the details of a previously calculated PDA by its ID.

Project Context: PDA Calculator
This document provides the necessary context for developing the Proforma Disbursement Account (PDA) Calculator application. Please adhere strictly to the guidelines outlined below.
1. Project Overview
Objective: To build a web application that calculates a Proforma Disbursement Account (PDA). A PDA is a cost estimate for a commercial shipping vessel's call at a specific port.
Purpose: The application will help charterers, ship operators, and agents to estimate port-related expenses (like port dues, pilotage, tug services, agency fees) before a vessel's arrival. This enables better financial planning and helps avoid discrepancies.
Core Functionality: The application will accept input parameters about the vessel, port, and cargo, and then calculate an estimated total cost based on pre-defined rates, while also allowing for manual overrides.
2. Technology Stack & Constraints
Language: Java 17
Framework: Spring Boot 3+
Build Tool: Maven
Database: MySQL 8
Persistence: Spring Data JPA (Hibernate)
Key Architectural Constraint: DO NOT USE Data Transfer Objects (DTOs). All controller endpoints will accept and return JPA Entity objects directly. This is a strict project requirement.
3. Database Schema (MySQL)
We will model the domain using several related tables. The LLM should generate JPA entities corresponding to these tables.
Table: ports
Stores information about different ports.
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for the port |
| name | VARCHAR(255)| NOT NULL, UNIQUE | Official name of the port (e.g., 'Port of Singapore') |
| country | VARCHAR(100)| NOT NULL | Country where the port is located |
Table: vessels
Stores information about different vessels.
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for the vessel |
| name | VARCHAR(255)| NOT NULL, UNIQUE | Name of the vessel |
| type | VARCHAR(100)| NOT NULL | Type of vessel (e.g., 'Bulk Carrier', 'Tanker') |
| dwt | INT | NOT NULL | Deadweight Tonnage |
| gross_tonnage| INT | NOT NULL | Gross Tonnage (GT) |
| net_tonnage | INT | NOT NULL | Net Tonnage (NT) |
Table: cargo_types
Stores different types of cargo.
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for the cargo type |
| name | VARCHAR(255)| NOT NULL, UNIQUE | Name of the cargo (e.g., 'Crude Oil', 'Iron Ore') |
Table: port_service_rates
This is the core table for storing default costs. It defines the rate for a specific service at a specific port.
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for the rate |
| port_id | BIGINT | NOT NULL, FK to ports.id | The port where this rate applies |
| service_name | VARCHAR(255)| NOT NULL | The name of the service (e.g., 'Pilotage', 'Tug Service')|
| calculation_basis | VARCHAR(50) | NOT NULL | How the cost is calculated ('FLAT', 'PER_GT', 'PER_DWT') |
| rate | DECIMAL(10, 2)| NOT NULL | The cost value (e.g., 5000.00 for FLAT, or 0.50 for PER_GT) |
Table: pdas (Proforma Disbursement Accounts)
This table stores the result of a single PDA calculation.
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for the PDA calculation |
| port_id | BIGINT | NOT NULL, FK to ports.id | The port for this calculation |
| vessel_id | BIGINT | NOT NULL, FK to vessels.id| The vessel for this calculation |
| cargo_type_id | BIGINT | NOT NULL, FK to cargo_types.id | The cargo for this calculation |
| total_estimated_cost | DECIMAL(15, 2)| NOT NULL | The final calculated total cost |
| created_at | TIMESTAMP | DEFAULT CURRENT_TIMESTAMP | When the record was created |
Table: pda_line_items
Stores the individual cost components for a specific PDA.
| Column Name | Data Type | Constraints | Description |
| :--- | :--- | :--- | :--- |
| id | BIGINT | PRIMARY KEY, AUTO_INCREMENT | Unique identifier for the line item |
| pda_id | BIGINT | NOT NULL, FK to pdas.id | The parent PDA this line item belongs to |
| service_name | VARCHAR(255)| NOT NULL | Name of the service (e.g., 'Pilotage') |
| calculation_notes | VARCHAR(255)| | A note on how it was calculated (e.g., 'Rate: $0.50/GT * 50000 GT') |
| cost | DECIMAL(15, 2)| NOT NULL | The calculated cost for this specific service |
4. Application Architecture (Spring Boot)
Please generate the code following this package structure and logic.
com.example.pdacalculator.model
Contains the JPA Entity classes:
Port.java
Vessel.java
CargoType.java
PortServiceRate.java
Pda.java (Represents the pdas table)
PdaLineItem.java
Use @Entity, @Table, @Id, @GeneratedValue, @Column, and relationship annotations (@ManyToOne, @OneToMany) correctly.
In Pda.java, use @OneToMany(mappedBy="pda", cascade=CascadeType.ALL) for the list of PdaLineItems.
com.example.pdacalculator.repository
Contains Spring Data JPA repository interfaces.
Create repositories for all entities:
PortRepository extends JpaRepository<Port, Long>
VesselRepository extends JpaRepository<Vessel, Long>
CargoTypeRepository extends JpaRepository<CargoType, Long>
PortServiceRateRepository extends JpaRepository<PortServiceRate, Long>
PdaRepository extends JpaRepository<Pda, Long>
com.example.pdacalculator.service
Contains the business logic.
Create a primary service PdaCalculationService.java.
PdaCalculationService.calculateAndSavePda(PdaCalculationRequest request): This is the main method.
Input: An object PdaCalculationRequest (this is a simple POJO, not an entity, and is only used internally within the service layer or as a request body wrapper).
Generated java
// This is a simple request wrapper class, not a DTO for transfer.
// It can be a static nested class inside the controller.
public class PdaCalculationRequest {
    private Long portId;
    private Long vesselId;
    private Long cargoTypeId;
    // Key: service_name (e.g., "Pilotage"), Value: new rate to use
    private Map<String, Double> overrides; 
}
Use code with caution.
Java
Logic:
Fetch the Port and Vessel from the database using their IDs.
Fetch all PortServiceRate records for the given portId.
Initialize a new Pda entity and its pdaLineItems list.
Iterate through each PortServiceRate:
Check if the overrides map contains a new rate for this service. Use it if present; otherwise, use the rate from the database.
Calculate the cost based on calculation_basis ('FLAT', 'PER_GT', 'PER_DWT') using the vessel's details (GT, DWT).
Create a new PdaLineItem entity, set its properties (service name, cost, calculation notes), and add it to the Pda's list of line items.
Sum all line item costs to get the totalEstimatedCost.
Set all properties on the Pda entity.
Save the Pda entity using pdaRepository.save(). Because of CascadeType.ALL, the line items will also be saved.
Return the saved Pda entity.
com.example.pdacalculator.controller
Contains the REST controllers.
Create PdaController.java.
Remember the "No DTO" rule: Endpoints must accept and return JPA Entities.
Endpoint POST /api/pdas/calculate:
Accepts the PdaCalculationRequest object defined above as the @RequestBody.
Calls pdaCalculationService.calculateAndSavePda().
Returns a ResponseEntity<Pda> containing the newly created and saved Pda entity with a 201 CREATED status.
Endpoint GET /api/pdas/{id}:
Accepts a pathVariable for the PDA id.
Fetches and returns the Pda entity.
Create other controllers (PortController, VesselController) for managing the master data (e.g., GET /api/ports, GET /api/vessels).
5. Configuration (application.properties)
Use the following configuration for MySQL connection.
Generated properties
# MySQL Database Connection
spring.datasource.url=jdbc:mysql://localhost:3306/pda_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=your_password_here

# JPA/Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
spring.jpa.properties.hibernate.format_sql=true

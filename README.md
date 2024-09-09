# Railway Management System

This project is a Railway Management System built using Spring Boot. It allows user registration, login, adding trains (admin), seat availability checking, booking seats, and more. The project includes role-based access control and JWT authentication for secure APIs.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before you begin, make sure you have the following installed:
- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/install.html)
- [MySQL](https://dev.mysql.com/downloads/installer/) or [PostgreSQL](https://www.postgresql.org/download/)
- An IDE like [IntelliJ IDEA](https://www.jetbrains.com/idea/) or [VS Code](https://code.visualstudio.com/)

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/shashank77t/Railway_Management_System.git
    ```

2. Navigate to the project directory:
    ```bash
    cd Railway_Management_System
    ```

3. Create a database in MySQL:
    ```sql
    CREATE DATABASE railway_management_system;
    ```

4. Update the `application.properties` file with your database configuration:
    ```properties
    spring.application.name=RailwayManagementSystem
    server.port=8081
    spring.datasource.username=root
    spring.datasource.password=root
    spring.jpa.show-sql=true
    spring.jpa.generate-ddl=true
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/railway_management_system
    ```

## Running the Application

1. Build the project using Maven:
    ```bash
    mvn clean install
    ```

2. Run the Spring Boot application:
    ```bash
    mvn spring-boot:run
    ```

3. The application will be accessible at `http://localhost:8081`.

## API Endpoints

- **User Registration**: `/user/passengerSignUp` (POST)
  - **Description**: Registers a new passenger.
  - **Request Body**: `UserInfo` object with name, email, contact, and password.
  
- **Admin Registration**: `/user/adminSignUp` (POST)
  - **Description**: Registers a new admin.
  - **Request Body**: `UserInfo` object with name, email, contact, and password.

- **Admin Login**: `/user/adminsignin` (POST)
  - **Description**: Authenticates an admin and returns a JWT token.
  - **Request Body**: `JwtRequest` object with username and password.

- **Passenger Login**: `/user/passengersignin` (POST)
  - **Description**: Authenticates a passenger and returns a JWT token.
  - **Request Body**: `JwtRequest` object with username and password.

- **Add Train** (Admin Only): `/user/admin/addTrain` (POST)
  - **Description**: Adds a new train to the system.
  - **Request Body**: `Train` object with trainNo, trainName, source, destination, departureTime, availableSeats, arrivalTime, and trainType.
  - **Headers**: Authorization token required.

- **Get Trains Between Stations**: `/train/getTrainsBetweenStations` (GET)
  - **Description**: Retrieves trains between specified source and destination.
  - **Request Params**: `source` (String), `destination` (String)
  - **Headers**: Authorization token required.

- **Book Seat**: `/booking/bookSeat` (POST)
  - **Description**: Books seats on a train.
  - **Request Body**: `Booking` object with user, train, seatNumbers, source, destination, arrivalTime, departureTime, and trainClass.
  - **Headers**: Authorization token required.


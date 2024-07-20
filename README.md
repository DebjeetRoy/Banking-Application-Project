Sure, here is a sample README file content for your banking application project. This README includes a project description and details the recent changes you made to the codebase.

---

# Banking Application Project

## Overview

This project is a comprehensive banking application designed to manage various types of loan transactions. It leverages modern software design patterns and frameworks to ensure a robust and scalable solution. The application includes user authentication and role-based access control, as well as comprehensive health monitoring and exception handling.

## Recent Changes

### 1. Loan Transaction Builder Class
- **Builder Design Pattern**: Implemented the Builder design pattern for creating loan transactions.
- **Build Method**: The `build()` method constructs data fields based on the loan type.
- **Fields**: The following fields are included:
  - `String borrowerName`
  - `BigDecimal amount`
  - `LocalDate loanDate`
  - `BigDecimal interestRate`
  - `int durationInMonths`
  - `String loanType`
  - `String email`
  - `String mobileNo`
  - `int age`

### 2. SecurityConfig Class
- **Role-Based Authentication**: Defined role-based authentication with `USER` and `ADMIN` roles.
- **SecurityFilterChain**: Configured `securityFilterChain()` to provide access to specific mapping endpoints based on roles.

### 3. LoanUserDataResponse Class
- Created `LoanUserDataResponse` to wrap up response packages.

### 4. UserRequest Class
- Created `UserRequest` class to handle user requests sent by clients.

### 5. Entity Classes
- Developed entity classes including `CarLoan`, `HomeLoan`, `PersonalLoan`, and `User` with roles.

### 6. LoanTransactionService
- Implemented `LoanTransactionService` with the following methods:
  - `createLoanTransaction()`
  - `fetchUserLoanDetails()`
  - `fetchUserLoanDetailsById(int id)`

### 7. JPA Repositories
- Created JPA repositories for `LoanTransaction`, `Role`, and `User`.

### 8. MyDbHealthService Class
- Implemented `MyDbHealthService` class that extends `HealthIndicator` to set custom logic for actuator health checks.

### 9. ApplicationExceptionHandler Class
- Created `ApplicationExceptionHandler` class to handle exceptions. This class is annotated with `@RestControllerAdvice`.

### 10. Application Properties
- Configured `application.properties` with the following:
  - Actuator health metrics
  - MySQL database details

### 11. Test Classes
- Created JUnit test classes to handle testing for controllers and service layers.

## Getting Started

### Prerequisites
- JDK 11 or higher
- Maven 3.6.0 or higher
- MySQL database

### Installation

1. Clone the repository:
   ```sh
   git clone https://github.com/yourusername/banking-application.git
   cd banking-application
   ```

2. Update `application.properties` with your MySQL database details and actuator settings.

3. Build the project using Maven:
   ```sh
   mvn clean install
   ```

4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## Usage

- The application provides RESTful APIs to manage loan transactions.
- Role-based access control ensures that only authorized users can access specific endpoints.

## API Endpoints

- `POST /api/loans/saveLoanData` - Create a new loan transaction.
- `GET /api/loans/users` - Fetch all loan transactions.
- `GET /api/loans/users/{id}` - Fetch loan transaction details by ID.

## Testing

- JUnit tests are provided for controllers and service layers.
- Run tests using Maven:
  ```sh
  mvn test
  ```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/your-feature`)
3. Commit your changes (`git commit -m 'Add some feature'`)
4. Push to the branch (`git push origin feature/your-feature`)
5. Create a new Pull Request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

This README provides a comprehensive overview of your project, detailing the recent changes, setup instructions, usage, and contribution guidelines. Adjust the repository URL, and any other specific details as needed.

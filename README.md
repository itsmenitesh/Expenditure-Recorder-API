

<p align="center">
<a href="Java url">
    <img alt="Java" src="https://img.shields.io/badge/Java->=8-darkblue.svg" />
</a>
  <a href="Spring Boot url" >
    <img alt="Spring Boot" src="https://img.shields.io/badge/Spring Boot-3.0.6-brightgreen.svg" />
</a>
<a href="Maven url" >
    <img alt="Maven" src="https://img.shields.io/badge/maven-3.0.5-brightgreen.svg" />
</a>
  
<a >
    <img alt="MySQL" src="https://img.shields.io/badge/MySQL-blue.svg">
</a>
</p>
   
# Expenditure Recorder API

a Java and Spring Boot-built API for handling user authentication and spending.

## Frameworks and Language Used
- Java
- Spring Boot
- Spring Data JPA

## Data Flow

The data flow in this project is organized using the following components:

### Controller

- The expenditure controller responds to HTTP queries. Endpoints for adding, viewing, editing, and removing costs are included.
- UsersController: Manages user-related HTTP requests, including signUp and signin.

### Services

- AuthenticationService: Provides authentication-related functionality, including saving and retrieving authentication tokens and authenticating user credentials.
- ExpenseService: Manages expense-related operations, such as creating, reading, updating, and deleting expenses. It also provides methods to fetch expenses based on specific criteria.
- UsersService: Handles user-related operations, such as user sign up and sign in. It encrypts user passwords and verifies credentials during sign in.

### Repository

- IAuthenticationRepository: Provides methods to interact with the database for storing and retrieving authentication tokens.
- IExpenseRepository: Offers methods to perform CRUD (Create, Read, Update, Delete) operations on expense data.
- IUsersRepository: Defines database operations related to user entities.

## Model

### Users

Represents user data, including user details and expenses. It contains fields such as user ID, first name, last name, email, password, phone number, and age.

### Expense

Represents an expense with fields like ID, title, description, price, date, time, and a reference to the associated user.

### AuthenticationToken

Stores the authentication token for a user and is associated with a specific user using a one-to-one relationship.



## Endpoints

The following endpoints are available for the Expense Tracker API:

### Sign up

- Endpoint: POST http://localhost:8080/user/signup
- Request body: SignUpInput
- Response body: SignUpOutput

### Sign in

- Endpoint: POST http://localhost:8080/user/signing
- Request body: SignInInput
- Response body: SignInOutput

### Create expense

- Endpoint: POST http://localhost:8080/expenses?userEmail={userEmail}&token={token}
- Request parameters: userEmail (String), token (String)
- Request body: Expense
- Response body: String (message)

### Read all expenses

- Endpoint: GET http://localhost:8080/expenses?userEmail={userEmail}&token={token}
- Request parameters: userEmail (String), token (String)
- Response body: List<Expense>

### Update expense

- Endpoint: PUT http://localhost:8080/expenses/{id}?userEmail={userEmail}&token={token}
- Path parameter: id (Long)
- Request parameters: userEmail (String), token (String)
- Request body: Expense
- Response body: Expense

### Delete expense

- Endpoint: DELETE http://localhost:8080/expenses/{id}?userEmail={userEmail}&token={token}
- Path parameter: id (Long)
- Request parameters: userEmail (String), token (String)
- Response body: Object

### Fetch all expenses for a particular date and time

- Endpoint: GET http://localhost:8080/expenses/date-time?date={date}&time={time}&userEmail={userEmail}&token={token}
- Request parameters: date (String, optional), time (String, optional), userEmail (String), token (String)
- Response body: List<Expense>


    
    ## Project Summary

The Expense Tracker API is a Java-based web application built using Spring Boot. It provides endpoints for managing expenses and user authentication. It offers endpoints for user authentication and spending management. The program has a tiered design where services implement business logic, controllers handle HTTP requests, and repositories communicate with the database. The user and expense models are the main data structures employed in the project. The expense model represents expense data, whereas the user model represents user data. DTO (Data Transfer Object) classes for managing input and output data for sign-up and sign-in activities are also included in the project.

## Author

👤 **Nitesh Choudhary**

* GitHub: [Nitesh choudhary](https://github.com/nitesh1710)

* LinkedIn: [Nitesh choudhary](https://www.linkedin.com/in/niteshchoudhary17/)
    
---

## 🤝 Contributing

Contributions, issues and feature requests are welcome.
    
---
    
## Show your support

Give a ⭐️ if this project helped you!
    
---
    
## 📝 License

Copyright © 2023 [Nitesh Choudhary](https://github.com/nitesh1710).<br />
    
---

# MessageApp Project

MessageApp is a Spring Boot-based web application that facilitates messaging functionality. The project includes features for managing users, creating messages, and obtaining statistics on user messages.

## Key Features:

1. **User Management:**
    - Retrieve a list of all users.
    - Create a new user with a specified name and email.
    - Retrieve user details by ID.
    - Delete a user and anonymize associated messages.

2. **Message Operations:**
    - Retrieve a list of all messages.
    - Create a new message with a specified user ID, text.

3. **Message Statistics:**
    - Obtain statistics for all users' messages, including the number of messages, first and last message dates, and average text length.

4. **Exception Handling:**
    - Custom exceptions for user not found, user already exists, and failed to create a user.
    - Global exception handler for common errors with detailed error messages.

5. **Swagger Documentation:**
    - Integrated Swagger 2 for API documentation.
    - Access API documentation interactively using Swagger UI.

## Getting Started:

1. Clone the repository.
2. Build and run the project.
3. Explore the API documentation at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).

## To-Do List:

1. **Authorization and Authentication:**
    - Implement robust user authentication and authorization mechanisms.

2. **Enhanced Validations:**
    - Add more validations for incoming requests to ensure data integrity.

3. **Comprehensive Testing:**
    - Expand test coverage to encompass all possible scenarios.

4. **Statistics Enhancement:**
    - Extend user message statistics to include the last message text for each user.

# Security_EmailVerification Project
This repository contains a Java backend system that facilitates user registration, role management, secure endpoints, and email verification. The project is based on the Spring Security framework, uses the Postgres database driver for data storage, and Java Mail for email functionality.

### Overview
This repository represents a step-by-step implementation of a complete backend system as demonstrated in the[ YouTube tutorial](https://www.youtube.com/watch?v=QwQuro7ekvc " YouTube tutorial"), which covers the following key features:

- **User Registration:** Implement user sign-up functionality.
- **User Existence Check:** Ensure that user data isn't duplicated by verifying if a user already exists in the database.
- **Email Verification:** Create a token-based email verification system.
- **Role Management:** Although not explicitly mentioned in the provided transcript, it's common to include role management in user registration systems for defining user permissions.
- **Secure Endpoints:** Use Spring Security to secure endpoints and ensure that only authenticated users can access protected resources.

### Getting Started
Follow these steps to set up and run the project locally:

**1. Clone the Repository:**
> git clone <repository_url>

**2. Database Configuration:**
- By default, the database might be configured for testing purposes and set to destroy data. For production, make sure to configure the database not to lose data.

**3. Token Package and ConfirmationToken Entity:**
- Create a package called Token to store the token in the database.
- Implement the ConfirmationToken entity, responsible for storing tokens in the database.

**4. ConfirmationTokenService:**
- Create the ConfirmationTokenService, which handles the logic for creating and accessing confirmation tokens.
- Ensure that when a token is saved in the database, it is also sent to the user via email.

**5. Email Configuration:**
- Use Java Mail Sender to send emails.
- Implement an interface and class for sending emails.
- Implement error handling using try-catch blocks.

**6. Asynchronous Email Sending:**
- Create an asynchronous method for sending emails.
- Generate HTML emails containing the verification token link.

**7. Testing Duplicate Emails:**
- Implement functionality to handle cases where a user signs up with an email that already exists but hasn't been confirmed. Allow them to send another confirmation email if needed.

**8. Email Verification Process:**
- Verify that the email verification process works as expected by following the steps outlined in the tutorial.
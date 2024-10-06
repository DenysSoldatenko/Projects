# API_Account Project
This project is a Spring Boot REST API that follows the HATEOAS (Hypermedia as the Engine of Application State) principles. It provides Create, Read, Update, and Delete (CRUD) operations for managing accounts, and it includes hypermedia links in the responses to facilitate navigation within the API.

This project is based on the [YouTube video](https://www.youtube.com/watch?v=y6R3reU1vWE&list=WL "YouTube video") tutorial titled "Spring Boot REST API CRUD with HATEOAS Tutorial."

### Features
- Create Account: Create a new account with a specified account number and initial balance.
- Read Account: Retrieve account details by its unique identifier.
- Update Account: Update account information, including the account number and balance.
- Deposit: Deposit a specified amount of money into an account.
- Withdraw: Withdraw a specified amount of money from an account.
- Delete Account: Delete an account by its unique identifier.
- HATEOAS Support: API responses include hypermedia links, making it easier for clients to navigate and discover available actions.
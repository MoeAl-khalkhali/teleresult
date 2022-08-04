
# Set Up
Install Java SDK 17
Install Java 18
Install MySQL set username as root and password as rootroot
Create a database called teleresults
Build and run the project via intelliJ

# Orders API
The chosen language for the rest API requested in the task is Java using SpringBoot framework. The reason I have chosen Java and Springboot
is that I am most proficient in Java as this is the language in which my engineering journey started. Additionally, strongly typed languages have always appealed to me, it serves as a demonstration that the code is well written.
Springboot was the chosen framework as it allows someone to create a rest API with minimal effort, it has an embedded tomcat server, and requires minimal configurations to get the code up and running.
Furthermore, there is plenty of plugins which are constantly maintained which can be used.

Code Structure
Design Pattern: Web-Service-Repository-Model a variation of MVC
The design pattern used is a variation of the model view controller design pattern adapted to work with the repository pattern and rest API's.
Web - Contains controllers which manage the REST interface to business logic
Service - Contains the business logic
Repository - An implementation of the repository layer which is used for storing Beans
Model - Model is a collection of classes to interact with the database

They way the code works is a user makes a request to the REST API(web), the business logic required is then done by the service, and then if required, the repository layer is called to create/modify/get.. an object from a database.
By following this design we can maintain slim controllers, and slightly more beefy services which is considered a good design.

The interesting things provided by springboot which is used in my code is the dependency injections. It allows me to inject object into other object whilst maintaining low coupling and high cohesion. It promotes modular code which can be is open for extension and closed for modification.
Additionally, the demarcation of the packages allows for easy extension, debugging and modification, the code is written to be easy to understand and minimal complexity. The rest API can be accessed via any language, as long as the contract is API contract is maintained. This allows for a microservice architecture is good as it creates a loosely coupled environment.
Furthermore, all sql queries done using the repository layer are sanitised and protected from SQL Injections. This can be seen by the log output when querying the API.

Whilst building the project, git was used with proper branching strategy, rebasing to main and pull requests.
Assumptions:
- The date passed must follow the format yyyy-MM-dd with the POST request to /orders
- The date passed when using orders/{type}/{date}
- No malicious attempt are made
- A customer entity is not required

# What I would have liked to add
- API checked, further parameter validation on the requests made on the API, ensuring they meet the requirements
- Further Unit Tests, would like to have added more tests, testing the service and controller
- Adding a proper customer class with a onetomany relationship with the orders.
- Authentication and authorisation
- 


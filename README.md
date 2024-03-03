Restaurant Finder

This project provides a solution for finding restaurants based on a given postcode using the Just Eat API. The solution presents the restaurant data in a web interface where users can enter their postcode and retrieve information on nearby restaurants.

How to Build, Compile and Run the Solution:

1) Clone the repository
2) Open the project in your preffered IDE (for example IntelliJ IDEA)
3) Navigate to the justeat_project directory
4) Make sure to have Java and Maven installed
5) Build the project using Maven by running the command (mvn clean install) in your terminal
6) Run the Spring Boot application using the command (mvn spring-boot:run) in your terminal
7) Run the application, open your web browser and search "http://localhost:8080" in your web browser
8) Enter your postcode in the provided input field and click "Search" to retrieve nearby restaurant data

Assumptions & Clarifications

1) The solution assumes the user will enter a valid postcode format
2) It is assumed that the Just Eat API will always return a valid response for the postcode input
3) It is assumed that the web interface works across different browsers (tested on safari and chrome)

Possible Improvements

1) Implement client-side validation for the postcode input to ensure a valid postcode is being entered
2) Enhance error handling to provide error messages to users in casse of API response errors
3) Implement TDD for a more robust codebase
4) Include features like restaurant location map & feedback form

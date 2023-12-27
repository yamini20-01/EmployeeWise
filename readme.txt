Employee Management System

The Application is developed using Spring Boot,Maven , MongoDB(NO SQL DB), Gmail as email service provider

The Application provides various endpoints to do the following

1. Add Employee to a Database: In this task, the candidate needs to implement an
API endpoint to add an employee to the database. The employee data should include the
fields EmployeeName, PhoneNumber, Email, ReportsTo (which refers to the Employee ID of
the reporting manager), and ProfileImage (an URL to the Employee's profile image [use
google images]). Additionally, the candidate needs to generate a unique UUID as the ID field
for the Employee and return this ID,if the Employee is successfully added to the
database.
2. Get All Employees: The candidate needs to implement an API endpoint to retrieve
all employees from the database.
3. Delete Employee by ID: The candidate needs to implement an API endpoint to
delete an employee from the database based on their ID.
4.Update Employee by ID: The candidate needs to implement an API endpoint to
update the details of an employee in the database based on their ID
5.Get nth Level Manager of an Employee: The candidate needs to implement an API
endpoint that takes an employee ID and a level (n) as input and returns the nth level
manager of that employee.
For example, if employee A reports to B, and B reports to C, and n=1, then the API should return the manager B for employee A, and if n=2, it should return
manager C for employee A
6.Get Employees with Pagination and Sorting: Modify the "Get All Employees" task
to implement pagination and sorting options. Allow the client to specify the page number,
page size, and sorting criteria (e.g., sort by name or email). Return the appropriate subset of
employees based on the pagination parameters

Prerequisites/tools:
 JDK 17 , Intellij or any other IDE , Mongo DB Compass ,Spring Boot knowledge ,Gitbash , Heroku CLI,Postman
 
Below are the basic steps and code snippets for creating a Spring Boot application with MongoDB integration that fulfills the specified requirements:

Setup Spring Boot Project:

Create a new Spring Boot project using Maven. You can use Spring Initializer (https://start.spring.io/) or your preferred IDE. Add the necessary dependencies as mentioned below
Dependencies:
<!-- Spring Boot Starter Web for RESTful APIs -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<!-- Spring Boot Starter Data MongoDB for MongoDB integration -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-mongodb</artifactId>
</dependency>

<!-- Lombok for reducing boilerplate code -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-mail</artifactId>
</dependency>

To set up MongoDB locally, you need to follow these general steps:

Download MongoDB:

Visit the official MongoDB website: MongoDB Download Center.
Select the appropriate version and download the installer for your operating system (Windows, macOS, or Linux).
Install MongoDB:

Follow the installation instructions provided for your specific operating system on the MongoDB website
Once the MongoDB is installed , In MongoDB UI tool Create a database called emsdb(your-database-name).

The following properties need to be updated in application properties file
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=your-database-name

For sending an email to Manager on addition of new employee ,please update the application properties file with the following data
# Email Configuration for Gmail
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-gmail-email@gmail.com
spring.mail.password=your-gmail-password
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true


How to run and test the application:

Run Your Spring Boot Application:

Make sure your Spring Boot application is running locally.
Open Postman:

Open the Postman application on your computer.
Create a New Request:

Click on the "New" button in Postman to create a new request.
Choose the HTTP method for the request (e.g., POST, GET, DELETE, PUT) based on the API endpoint you want to test.
Set Request URL:

Set the request URL based on the endpoint you want to test. For example:
For adding an employee: http://localhost:8080/api/employees (assuming your application is running on the default port 8080).
For getting all employees: http://localhost:8080/api/employees
For deleting an employee by ID: http://localhost:8080/api/employees/{id} (replace {id} with the actual ID of an employee).
For updating an employee by ID: http://localhost:8080/api/employees/{id} (replace {id} with the actual ID of an employee).
Set Request Body (for POST and PUT):

If you're testing the POST or PUT endpoints, set the request body with JSON data. For example, when adding an employee:
json

{
  "employeeName": "John Doe",
  "phoneNumber": "1234567890",
  "email": "john.doe@example.com",
  "reportsTo": "abcd-1nhm-bcd1-aj89k",
  "profileImage": "https://example.com/john-doe-image.jpg"
}
Send the Request:

Click on the "Send" button to send the request to your Spring Boot application.
Review Response:

Review the response in the Postman window. Ensure that the response is as expected and that there are no errors.
Test Other Endpoints:

Repeat the above steps for other API endpoints, such as getting all employees, deleting an employee, or updating an employee.
By following these steps, you can use Postman to interact with your Spring Boot application and test the different API endpoints. Ensure that the data you input aligns with the expected format and that you handle responses appropriately in your Spring Boot application.


Endpoint details:

For adding an employee: http://localhost:8080/api/employees (assuming your application is running on the default port 8080).
For getting all employees: http://localhost:8080/api/employees
For deleting an employee by ID: http://localhost:8080/api/employees/{id} (replace {id} with the actual ID of an employee).
For updating an employee by ID: http://localhost:8080/api/employees/{id} (replace {id} with the actual ID of an employee).
For getting the nth level manager of an employee, http://localhost:8080/api/employees/{employeeId}/manager/{level}
For getting Employees with Pagination and Sorting:  http://localhost:8080/api/employees/paged?page={page}&pageSize={pageSize}&sortBy={sortBy}



Deployment on Public Platform
how to deploy the above spring boot app in hero platform

If you're looking to deploy your Spring Boot application for free, you can consider using Heroku, a cloud platform that offers a free tier for hosting applications. Here's a step-by-step guide on how to deploy your Spring Boot app on Heroku:

Prerequisites:
Heroku Account:

Sign up for a free Heroku account if you don't have one: Heroku Sign Up.
Heroku CLI:

Install the Heroku Command Line Interface (CLI) on your local machine: Heroku CLI.
Git:

Ensure that Git is installed on your machine, as Heroku uses Git for deployment.
Steps:
Prepare Your Spring Boot App:

Ensure that your Spring Boot application is working correctly locally.
Create a Git Repository:

Initialize a Git repository in your Spring Boot project if you haven't already:

git init
git add .
git commit -m "Initial commit"
Heroku Login:

Log in to your Heroku account using the Heroku CLI:
in Gitbash 

heroku login
Create a Heroku App:

Create a new Heroku app:
in Gitbash 

heroku create your-app-name
Configure MongoDB on Heroku (if applicable):

If you are using MongoDB, you might need to add a MongoDB add-on. For example, you can use the mLab MongoDB add-on:
in Gitbash 

heroku addons:create mongolab:sandbox
Configure Email on Heroku (if applicable):

If your application uses email, you might need to configure an email service. Heroku does not provide an email service, so you may need to use third-party services like SendGrid or Mailgun. Configure the necessary environment variables for email settings.
Configure Environment Variables:

Set any environment variables required by your application. For example, database connection strings, email credentials, etc. Use the following command to set environment variables on Heroku:
in Gitbash 

heroku config:set KEY=value
Update application.properties or application.yml:

Ensure that your application is configured to use environment variables for sensitive information like database credentials or API keys.
Update pom.xml:

If your Spring Boot app is using a specific port, update the pom.xml to set the port dynamically based on the environment. Add the following lines to your pom.xml:
xml

<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <spring-boot.repackage.skip>true</spring-boot.repackage.skip>
            </configuration>
        </plugin>
    </plugins>
</build>
Deploy to Heroku:

Deploy your application to Heroku using Git:
in Gitbash 

git push heroku master
Open Your App:

Open your deployed app in the browser:
in Gitbash 

heroku open
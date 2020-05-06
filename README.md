## biller-system
A mock biller system.

### Steps to run the project:
This is a Springboot java project with maven build tool. 
1. Clone this github repository
2. Go to the project root directory and run `mvn clean install` to  build the project.
3. To run the application run `mvn spring-boot:run` from command line.
4. You can also import it to eclipse or other java IDE and run the project.

This application uses H2 in memory database to store data and the initial dataset is present in a sql file located at `/src/main/resources/data.sql`. When the application is started, data is populated from this `data.sql` file into the database named `biller-payment`. You can update `application.properties` file according to your need.

Currently the application is initialized with 5 customer data with phone numbers 7892562019, 8400313493 , 8400313492, 8400313491, 8400313490

The application is deployed on Heroku and can be accessed on this url `https://mighty-inlet-73726.herokuapp.com` 

So the API endpoints are:
1. `https://mighty-inlet-73726.herokuapp.com/bills/fetch`  - To fetch the bills of a particular customer
2. `https://mighty-inlet-73726.herokuapp.com/bills/fetchReceipt` - To fetch receipt

The H2 database console can be accessed at `https://mighty-inlet-73726.herokuapp.com/h2`.

For credentials and JWT secret and schema id, refer to `application.properties` file. Ideally these secrets and credentials should not be added here, it should be kept in secret manager.

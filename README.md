# Travel Plan Management Dashboard Backend
This project is the backend of a travel plan management dashboard. It uses Java Spring Boot and MySQL for database management. The application includes RESTful APIs for managing travel plans and user registration. And JWT for the authorization. The application is hosted on AWS EC2 with CI/CD pipelines using github actions and Docker for deployment.

# Prerequisites
Java 11 or higher \
MySQL 8.0 or higher \
Docker (Optional) \
Running the Application 
# 1. Configure MySQL
Create a new database and user for the application. You can do this using the following SQL commands:

sql \
Download \
Copy code \
CREATE DATABASE travel_plan_db; \
CREATE USER 'travel_plan_user'@'localhost' IDENTIFIED BY 'your_password'; \
GRANT ALL PRIVILEGES ON travel_plan_db.* TO 'travel_plan_user'@'localhost'; \
FLUSH PRIVILEGES; 
# 2. Configure Application Properties
Open the src/main/resources/application.yml file and set the following properties:

properties \
Download \
Copy code \
spring.datasource.url=jdbc:mysql://localhost:3306/travel_plan_db?useSSL=false&serverTimezone=UTC \
spring.datasource.username=travel_plan_user \
spring.datasource.password=your_password \
spring.jpa.hibernate.ddl-auto=update \
Replace your_password with the password you set for the travel_plan_user MySQL user. 

# 3. Run the Application
You can run the application by executing the following command in the project directory:

bash \
Download \
Copy code \
./mvnw spring-boot:run 
# 4 Alternatively, you can run the application using Docker. First, build the Docker image:

bash \
Download \
Copy code \
./mvnw spring-boot:build-image \
Then, run the Docker container:

bash \
Download \
Copy code \
docker run -p 8080:8080 travel-plan-management-dashboard-backend:0.0.1-SNAPSHOT \


 # License
This project is licensed under the MIT License - see the LICENSE file for details.

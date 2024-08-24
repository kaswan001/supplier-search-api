# supplier-search-api
A proof of concept for a supplier search API built with Spring Boot.

## Overview

The Supplier Search API is a proof of concept developed for Makersharks to allow buyers to search for manufacturers based on customized requirements. It provides a RESTful API built with Spring Boot that allows users to query suppliers based on location, nature of business, and manufacturing processes.

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java 21**: Required to build and run the application.
- **Maven 3.9 and above**: For building the project and managing dependencies.
- **MySQL 8.0 and above**: Database system to store supplier data.
- **Git Bash**

## Project Setup

1. **Clone the Repository**

   ```bash
   git clone https://github.com/kaswan001/supplier-search-api.git
   cd supplier-search-api
   ```

2. **Configure the Database**

   - **Create a Database**: Open your MySQL client and create a new database named `makersharks`.

     ```sql
     CREATE DATABASE makersharks;
     ```

   - **Configure Database Connection**: Update the `src/main/resources/application.properties` file with your MySQL database connection details.

3. **Create Tables**

   - **SQL query for creating the `supplier` table**:

        ```sql
        
        CREATE TABLE supplier (
          supplier_id BIGINT AUTO_INCREMENT PRIMARY KEY,
          company_name VARCHAR(255) NOT NULL,
          location VARCHAR(255) NOT NULL,
          website VARCHAR(255) NOT NULL,
          manufacturing_process ENUM('MOULDING', '_3D_PRINTING', 'CASTING', 'COATING') NOT NULL,
          nature_of_business ENUM('SMALL_SCALE', 'MEDIUM_SCALE', 'LARGE_SCALE') NOT NULL
        );
      
        ```

   - **SQL query for creating the `roles` table**:

        ```sql
        
        CREATE TABLE roles (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255) NOT NULL
        );
 
        ```

    - **SQL query for creating the `users` table**:

        ```sql
        
        CREATE TABLE users (
          id BIGINT AUTO_INCREMENT PRIMARY KEY,
          name VARCHAR(255),
          username VARCHAR(255) NOT NULL UNIQUE,
          email VARCHAR(255) NOT NULL UNIQUE,
          password VARCHAR(255) NOT NULL
        );

        ```

    - **SQL query for creating the `users_roles` table**:

       ```sql

        CREATE TABLE users_roles (
          user_id BIGINT NOT NULL,
          role_id BIGINT NOT NULL,
          PRIMARY KEY (user_id, role_id),
          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
          FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
       );

        ```

5. **Update Application Properties**

   Open the `src/main/resources/application.properties` file and update the database credentials and JWT properties as follows:

   ```properties
   spring.application.name=suppliersearch
   server.port=8001

   spring.datasource.url=jdbc:mysql://localhost:3306/makersharks
   spring.datasource.username=root
   spring.datasource.password=root123

   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
   spring.jpa.hibernate.ddl-auto=update

   app.jwt-secret=daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755dbb
   app.jwt-expiration-milliseconds=604800000

   ```

   Update for testing `src/test/resources/application-test.properties` file (Optional) :

   ```properties
   spring.datasource.url=jdbc:h2:mem:test;MODE=MySQL;
   spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.username=
   spring.datasource.password=
   # We add the MySQL Dialect so that it understands and generates the query based on MySQL
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   
   spring.h2.console.enabled=true
   spring.jpa.defer-datasource-initialization=true
   spring.jpa.hibernate.ddl-auto=create-drop
   spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyLegacyJpaImpl
   spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
   spring.jpa.properties.hibernate.format_sql=true
   spring.jpa.hibernate.naming-strategy=org.hibernate.cfg.ImprovedNamingStrategy
   
   spring.sql.init.mode=always

   ```

## Populating the Database

**required tables in the `makersharks` database**:

  - **Insert Supplier Data**:

    Use the following SQL insert queries to populate the `supplier` table. You can add more records if desired.

    ```sql

    INSERT INTO supplier (supplier_id, company_name, website, location, nature_of_business, manufacturing_process)
     VALUES
    (1, 'Alpha Tech', 'www.alphatech.com', 'India', 'SMALL_SCALE', '_3D_PRINTING'),
    (2, 'Beta Manufacturing', 'www.betamanufacturing.com', 'India', 'MEDIUM_SCALE', 'moulding'),
    (3, 'Gamma Industries', 'www.gammaindustries.com', 'USA', 'LARGE_SCALE', 'casting'),
    (4, 'Delta Coating', 'www.deltacoating.com', 'India', 'SMALL_SCALE', 'coating'),
    (5, 'Epsilon Innovations', 'www.epsiloninnovations.com', 'Japan', 'MEDIUM_SCALE', '_3D_PRINTING'),
    (6, 'Zeta Moulding', 'www.zetamoulding.com', 'Japan', 'LARGE_SCALE', 'moulding'),
    (7, 'Eta Castings', 'www.etacastings.com', 'USA', 'SMALL_SCALE', 'casting'),
    (8, 'Theta Technologies', 'www.thetatechnologies.com', 'India', 'LARGE_SCALE', '_3D_PRINTING'),
    (9, 'Iota Coatings', 'www.iotacoatings.com', 'Japan', 'MEDIUM_SCALE', 'coating'),
    (10, 'Kappa Fabrication', 'www.kappafabrication.com', 'USA', 'SMALL_SCALE', 'moulding');

    ```

 - **Insert User and Role Data**:

   Insert the following SQL queries to populate the `users`, `roles`, and `users_roles` tables. This data is used for authentication purposes.

   1. Inserting Users:

      ```sql

      INSERT INTO users VALUES
         (1,'ramesh@gmail.com','ramesh','$2a$10$5PiyN0MsG0y886d8xWXtwuLXK0Y7zZwcN5xm82b4oDSVr7yF0O6em','ramesh'),
         (2,'admin@gmail.com','admin','$2a$10$gqHrslMttQWSsDSVRTK1OehkkBiXsJ/a4z2OURU./dizwOQu5Lovu','admin');

      ```

   2. Inserting Roles:

      ```sql

      INSERT INTO roles VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');

      ```

   3. Inserting User-Role Relationships:

      ```sql

      INSERT INTO users_roles VALUES (2,1),(1,2);

      ```

## Running the Application

1. **Build and Run the Application**

   Use Maven to build and run the application:

   ```bash
   
   mvn clean install
   mvn spring-boot:run

   ```

   The application will start on `http://localhost:8001`.

## Using the API

1. **Access Swagger UI**

   After populating the database, access the Swagger UI for API documentation and testing at:

   ```bash

   http://localhost:8001/swagger-ui/index.html
   
   ```

2. **Authenticate Using JWT**

   Swagger UI provides a way to authenticate using JWT:

   - **Login to Obtain JWT Token**

    - **Navigate to the `/auth/login` endpoint in Swagger UI**.
    - **Use the following sample payload to authenticate and receive a JWT token**:

       ```json
          {
            "usernameOrEmail": "admin",
            "password": "admin"
          }
       ```
  - **Include JWT Token**

    - **After obtaining the JWT token, click on the Authorize button in Swagger UI (top right corner)**.
    - **Enter the token in the following format in the Authorization header**:

       ```php
   
        Bearer <your-jwt-token>
   
       ```

3. **Run API Endpoints**

  - **Use the Swagger UI to test the API endpoints. For example**:

    - **POST `/api/supplier/query` to search for suppliers based on criteria**.
    - **Fill in the required parameters to perform the search and view the results**.

## API Endpoints

## Authentication

The API uses JWT (JSON Web Token) for authentication.

### 1. **Obtain a Token**

   - **Endpoint**: `/api/auth/login [POST]`

   - **Request URL**

     ```bash
   
        http://localhost:8001/api/auth/login
   
     ```

   - **Request Body**:

     ```json
     {
       "usernameOrEmail": "admin",
       "password": "admin"
     }
     ```

   - **Response**:

        ```json
        {
        "accessToken": "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyNDQ4MjU0NywiZXhwIjoxNzI1MDg3MzQ3fQ.ULcdOoGY5wKZpQ4Psb1Vsi5PvbJcxk7tfqS-tCXMkC4vauRppsgV-HfLf6su-lyi",
        "tokenType": "Bearer"
        }
        ```

   - **Example cURL Command**:
     
     ```bash
       curl -X 'POST' \
        'http://localhost:8001/api/auth/login' \
        -H 'accept: */*' \
        -H 'Content-Type: application/json' \
        -d '{
        "usernameOrEmail": "admin",
        "password": "admin"
      }'
     ```
     
### 2. **Access Protected Endpoints**

   Use the obtained JWT token in the Authorization header:

   - **Header**:

     ```plaintext
     Authorization: Bearer your-jwt-token-here
     ```


### 1. `/api/supplier/query [POST]`

- **Description**: Retrieves a list of manufacturers based on the provided criteria.

- **Request URL**

  ```bash

     http://localhost:8001/api/supplier/query?location=India&natureOfBusiness=MEDIUM_SCALE&manufacturingProcess=MOULDING&page=0&size=10

  ```

- **Request Body**:

  ```json
  {
    "location": "India",
    "nature_of_business": "MEDIUM_SCALE",
    "manufacturing_processes": "MOULDING"
  }
  ```

- **Response**:

  ```json
  {
     "totalPages": 1,
     "totalElements": 1,
     "size": 10,
     "content": [
       {
         "supplierId": 2,
         "companyName": "Beta Manufacturing",
         "location": "India",
         "website": "www.betamanufacturing.com",
         "manufacturingProcess": "MOULDING",
         "natureOfBusiness": "MEDIUM_SCALE"
       }
     ],
     "number": 0,
     "sort": {
       "empty": true,
       "sorted": false,
       "unsorted": true
     },
     "first": true,
     "last": true,
     "numberOfElements": 1,
     "pageable": {
       "pageNumber": 0,
       "pageSize": 10,
       "sort": {
         "empty": true,
         "sorted": false,
         "unsorted": true
       },
       "offset": 0,
       "paged": true,
       "unpaged": false
     },
     "empty": false
   }
  ```

 - **Example cURL Command**:

   ```bash
        curl -X 'POST' \
     'http://localhost:8001/api/supplier/query?location=India&natureOfBusiness=MEDIUM_SCALE&manufacturingProcess=MOULDING&page=0&size=10' \
     -H 'accept: */*' \
     -H 'Authorization: Bearer eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTcyNDUwOTgwNCwiZXhwIjoxNzI1MTE0NjA0fQ.EwOzApY4I4CKIGAL__9Ix_LRwVhyB0LE4P-H5CG8ZCWmtK3bxOW_1pXI5iiiloBT' \
     -d ''
   ```

## Testing

1. **Unit Tests**

   Run unit tests with Maven:

   ```bash
   mvn test
   ```

2. **Integration Tests**

   If you have integration tests, execute them with:

   ```bash
   mvn verify
   ```

## Documentation

The API documentation is available via Swagger UI:

- **URL**: `http://localhost:8001/swagger-ui.html`

## Troubleshooting

- **Common Issues**:
  - **Database Connection**: Ensure MySQL is running and accessible with the provided credentials.
  - **JWT Authentication**: Ensure you have a valid token and include it in the Authorization header.

## Contribution

Feel free to fork the repository and submit pull requests. For issues and feature requests, please create an issue on GitHub.

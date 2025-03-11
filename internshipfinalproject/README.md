# Job Portal API LHIND Internship

A Spring Boot application that provides role-based functionality for a simple job portal.

**Roles:**
- **ADMIN** – Manage (view/delete) users
- **EMPLOYER** – Post jobs, view/update applications for their jobs, add reviews
- **JOB_SEEKER** – View jobs, apply for jobs, upload resumes, and view own applications

---

## 1. Tech Stack

- **Spring Boot** (version 3.x recommended)
- **Spring Data JPA** (Hibernate)
- **MySQL** (JDBC Driver)
- **Spring Security** (Basic Auth)
- **MapStruct** (for DTO mapping)
- **JUnit & Mockito** (for testing)

---

## 2. Setup Instructions

### A. Prerequisites

- **Java 17** installed on your machine.
- **Maven** (or use the provided Maven Wrapper `./mvnw`).
- A MySQL server (local or on your VPS).

### B. Clone the Repository

Open a terminal and run:

```bash
git clone <YOUR-REPO-URL>
cd internshipfinalproject
```

### C. Configure MySQL

Edit `src/main/resources/application.properties` with your MySQL connection settings. For example:

```properties
# MySQL Configuration
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://ubot202.eastops.nl:3504/job_portal_db_v2?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=MBAQFV0XBcq4DR9ZDZB1SyCvUj6XYsnj
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update

server.port=9099
spring.main.allow-bean-definition-overriding=true

# File Upload Path
file.upload-dir=uploads/resumes/
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

### D. Build the Application

From the project root directory, run:

```bash
./mvnw clean install
```

This command compiles the code and creates an executable JAR in the `target` folder.

### E. Run the Application Locally

You can run the application in one of the following ways:

1. **Using Maven:**
   ```bash
   ./mvnw spring-boot:run
   ```
2. **Using the JAR:**
   ```bash
   java -jar target/internshipfinalproject-0.0.1-SNAPSHOT.jar
   ```

Once running, the application listens on [http://localhost:9099](http://localhost:9099/).

---

## 3. Deploying to Production on Your VPS

Your server domain is **ubot202.eastops.nl**. Follow these steps to deploy your application:

### A. Transfer the JAR to Your VPS

Use SCP from your local machine to copy the JAR file to your VPS. Replace `youruser` with your VPS username:

```bash
scp target/internshipfinalproject-0.0.1-SNAPSHOT.jar youruser@ubot202.eastops.nl:/home/youruser/internshipfinalproject/
```

### B. Configure Your VPS

1. **Open Port 9099:**

   If you use UFW as your firewall:
   ```bash
   sudo ufw allow 9099/tcp
   ```

2. **Run the Application on the VPS:**

   Navigate to the directory where you copied the JAR file and run it in the background with nohup:
   ```bash
   nohup java -jar /home/youruser/internshipfinalproject/internshipfinalproject-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
   ```

### C. Test Your Deployment Using Your Domain

After your application is running and port 9099 is open, test it using your domain. For example:

```bash
curl -I http://ubot202.eastops.nl:9099
```

You should see HTTP response headers indicating that the server is up.

---

## 4. API Endpoints Overview

### Auth (Public)
- **POST** `/api/v1/auth/register` – Register a new user  
  *Response:* Returns a success message along with the registered user’s information.

### Admin
- **GET** `/api/v1/admin/users?role={ROLE}` – List all users (optionally filtered by role).
- **DELETE** `/api/v1/admin/users/{id}` – Delete a user.  
  *Response:* Returns a confirmation message and details of the deleted user (if configured).

### Employer
- **GET** `/api/v1/employer/jobs?title=&location=` – List jobs posted by the employer.
- **POST** `/api/v1/employer/jobs` – Post a new job (employerId is set automatically based on the authenticated user).
- **GET** `/api/v1/employer/jobs/{jobId}/applications?status=` – Get applications for a specific job.
- **PUT** `/api/v1/employer/applications/{applicationId}/status?status=` – Update an application’s status (only if you own the job posting).
- **POST** `/api/v1/employer/reviews/jobs/{jobId}` – Add a review for a job you posted.

### Job Seeker
- **GET** `/api/v1/jobseeker/jobs?title=&location=&employer=` – View available jobs (filters optional).
- **POST** `/api/v1/jobseeker/applications` – Apply for a job (jobSeekerId is set automatically).
- **GET** `/api/v1/jobseeker/applications?status=&title=` – View your own applications.
- **POST** `/api/v1/jobseeker/upload-resume` – Upload your resume.  
  *Note:* The endpoint now retrieves the jobSeekerId from the authenticated user.

### Reviews (Employer)
- **GET** `/api/v1/employer/reviews?jobId=&rating=` – List reviews filtered by job ID and/or rating.

---

## 5. Sample Curl Commands

When testing on your server, replace `localhost:9099` with `ubot202.eastops.nl:9099`.

### A. Register Users

**Register a Job Seeker:**
```bash
curl -X POST http://ubot202.eastops.nl:9099/api/v1/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username": "testuser2", "password": "testpass", "role": "JOB_SEEKER"}'
```

**Register an Employer:**
```bash
curl -X POST http://ubot202.eastops.nl:9099/api/v1/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username": "employer1", "password": "empPass", "role": "EMPLOYER"}'
```

**Register an Admin:**
```bash
curl -X POST http://ubot202.eastops.nl:9099/api/v1/auth/register \
     -H "Content-Type: application/json" \
     -d '{"username": "admin", "password": "adminpass", "role": "ADMIN"}'
```

### B. Job Seeker Endpoints

**Apply for a Job:** (Assuming a job with ID 5 exists)
```bash
curl -u testuser2:testpass -X POST http://ubot202.eastops.nl:9099/api/v1/jobseeker/applications \
     -H "Content-Type: application/json" \
     -d '{"jobId": 5, "status": "PENDING"}'
```

**View Own Applications:**
```bash
curl -u testuser2:testpass http://ubot202.eastops.nl:9099/api/v1/jobseeker/applications
```

**View All Jobs:** (with filters)
```bash
curl -u testuser2:testpass "http://ubot202.eastops.nl:9099/api/v1/jobseeker/jobs?title=Software&location=New%20York&employer=Acme"
```

**Upload Resume:**  
*Note:* With the updated endpoint, you no longer need to send `jobSeekerId` manually.
```bash
curl -u testuser2:testpass -X POST http://ubot202.eastops.nl:9099/api/v1/jobseeker/upload-resume \
     -F "file=@/path/to/your/resume.pdf"
```

### C. Employer Endpoints

**Post a New Job:**
```bash
curl -u employer1:empPass -X POST http://ubot202.eastops.nl:9099/api/v1/employer/jobs \
     -H "Content-Type: application/json" \
     -d '{"title": "Software Developer", "description": "Looking for a skilled developer.", "location": "New York"}'
```

**View Employer’s Jobs:**
```bash
curl -u employer1:empPass http://ubot202.eastops.nl:9099/api/v1/employer/jobs
```

**Get Applications for a Job:** (Assuming job ID 5)
```bash
curl -u employer1:empPass "http://ubot202.eastops.nl:9099/api/v1/employer/jobs/5/applications?status=PENDING"
```

**Update Application Status:** (Assuming application ID 1)
```bash
curl -u employer1:empPass -X PUT "http://ubot202.eastops.nl:9099/api/v1/employer/applications/1/status?status=APPROVED"
```

**Add a Review for a Job:** (Assuming job ID 5)
```bash
curl -u employer1:empPass -X POST http://ubot202.eastops.nl:9099/api/v1/employer/reviews/jobs/5 \
     -H "Content-Type: application/json" \
     -d '{"content": "Excellent candidate!", "rating": 5}'
```

### D. Admin Endpoints

**Get All Users (Filtered by Role):**
```bash
curl -u admin:adminpass "http://ubot202.eastops.nl:9099/api/v1/admin/users?role=JOB_SEEKER"
```

**Delete a User:** (For example, user with ID 2)
```bash
curl -u admin:adminpass -X DELETE http://ubot202.eastops.nl:9099/api/v1/admin/users/2
```

> *Note:* The DELETE endpoint can be configured to return a JSON response with details about the deleted user or a message if the user is not found.

---

## 6. Adding New Functionalities

### Extending the API

- **New Endpoints:**  
  Create a new controller method for additional features. For example, to update job details, add a `PUT /api/v1/employer/jobs/{jobId}` endpoint in the EmployerController.

- **Service Layer:**  
  Add corresponding methods in your service interfaces (e.g., JobService) and provide implementations in your service classes (e.g., JobServiceImpl).

- **DTOs and Mappers:**  
  Update or add new DTO classes and update MapStruct mappers if you introduce new fields or entities.

### Testing New Features

- **Unit Tests:**  
  Write unit tests using JUnit and Mockito located in the `src/test/java` directory.

- **Integration Tests:**  
  Use `@SpringBootTest` to perform end-to-end tests of your API endpoints.

- **Postman Collection:**  
  Update your Postman collection (e.g., `postman/JobPortal.postman_collection.json`) with the new endpoints for easy testing.

### Code Quality & Documentation

- **Comments and JavaDocs:**  
  Add comments to your code to document new functionality.
- **README Updates:**  
  Update this README file with any changes or additional endpoints you add.

---

## 7. Running, Deploying & Monitoring

### Running Locally

Run the application with Maven:

```bash
./mvnw spring-boot:run
```

Or run the packaged JAR:

```bash
java -jar target/internshipfinalproject-0.0.1-SNAPSHOT.jar
```

### Deploying on Your VPS

1. **Transfer the JAR:**
   ```bash
   scp target/internshipfinalproject-0.0.1-SNAPSHOT.jar youruser@ubot202.eastops.nl:/home/youruser/internshipfinalproject/
   ```
2. **Open Port 9099:**
   ```bash
   sudo ufw allow 9099/tcp
   ```
3. **Run the Application:**
   Use `nohup` so that the application keeps running after you log out:
   ```bash
   nohup java -jar /home/youruser/internshipfinalproject/internshipfinalproject-0.0.1-SNAPSHOT.jar > app.log 2>&1 &
   ```

### Monitoring & Testing on Production

- **Check that the application is running:**
  ```bash
  curl -I http://ubot202.eastops.nl:9099
  ```
- **Test endpoints using curl or Postman** by replacing `localhost:9099` with your domain `ubot202.eastops.nl:9099`.

---

## 8. Postman Collection

A sample Postman collection file named `JOB_PORTAL.postman_collection.json` is included in the repository. To use it:
1. Open Postman.
2. Click **Import** and select the file.
3. Update the base URL to `http://ubot202.eastops.nl:9099` (if not already set).
4. Set the appropriate Basic Auth credentials for each request.
5. Test all endpoints as described above.
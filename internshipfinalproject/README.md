# Job Portal API LHIND Internship

A Spring Boot application that provides role-based functionality for a simple job portal.

**Roles**:  
- **ADMIN** – Manage (view/delete) users  
- **EMPLOYER** – Post jobs, view/update applications for their jobs, add reviews  
- **JOB_SEEKER** – View jobs, apply for jobs, upload resumes, view own applications  

## 1. Tech Stack

- **Spring Boot** (2.x or 3.x)
- **Spring Data JPA** (Hibernate)
- **MySQL** (JDBC Driver)
- **Spring Security** (Basic Auth)
- **MapStruct** (for DTO mapping)
- **JUnit & Mockito** (for tests)

## 2. Setup Instructions

1. **Clone the repository:**
   ```bash
   git clone <YOUR-REPO-URL>
   cd internshipfinalproject
   ```

2. **Configure MySQL** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.datasource.url=jdbc:mysql://<HOST>:<PORT>/<DB_NAME>?createDatabaseIfNotExist=true
   spring.datasource.username=<YOUR_DB_USER>
   spring.datasource.password=<YOUR_DB_PASSWORD>
   spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
   spring.jpa.hibernate.ddl-auto=update
   server.port=9099
   spring.main.allow-bean-definition-overriding=true

   # File Upload Path
   file.upload-dir=uploads/resumes/
   spring.servlet.multipart.max-file-size=10MB
   spring.servlet.multipart.max-request-size=10MB
   ```

3. **Build and Run:**
   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```
   The application will start on [http://localhost:9099](http://localhost:9099/).

4. **Database:**
   - The project will auto-create/update the schema (using `spring.jpa.hibernate.ddl-auto=update`) when the database is connected.

5. **Basic Auth:**
   - Endpoints are secured by role.
   - Register new users at `/api/v1/auth/register` (this endpoint is open, while others require authentication).
   - Use the credentials you create to authenticate (e.g., `admin:adminpass` for ADMIN).

## 3. API Endpoints

Below is an overview of the available endpoints. Use Basic Auth with the correct role to access each.

### Auth
- **POST** `/api/v1/auth/register` – Register a new user (Admin, Employer, or Job Seeker).

### Admin
- **GET** `/api/v1/admin/users` – List all users (optional `role` query parameter).
- **DELETE** `/api/v1/admin/users/{id}` – Delete a user.

### Employer
- **GET** `/api/v1/employer/jobs?title=&location=` – List employer’s posted jobs (filter optional).
- **POST** `/api/v1/employer/jobs` – Post a new job.
- **GET** `/api/v1/employer/jobs/{jobId}/applications?status=` – Get applications for a specific job.
- **PUT** `/api/v1/employer/applications/{applicationId}/status?status=` – Update application status.
- **POST** `/api/v1/employer/reviews/jobs/{jobId}` – Add a review for a job you posted.

### Job Seeker
- **GET** `/api/v1/jobseeker/jobs?title=&location=&employer=` – View all jobs (filters optional).
- **POST** `/api/v1/jobseeker/applications` – Apply for a job.
- **GET** `/api/v1/jobseeker/applications?status=&title=` – View own applications (filters optional).
- **POST** `/api/v1/jobseeker/upload-resume` – Upload a resume for your user.

### Reviews
- **GET** `/api/v1/employer/reviews?jobId=&rating=` – Filter reviews by job ID or rating.

## 4. Sample Curl Commands

### A. Register Users

1. **Register a Job Seeker:**
   ```bash
   curl -X POST http://localhost:9099/api/v1/auth/register \
        -H "Content-Type: application/json" \
        -d '{"username": "testuser2", "password": "testpass", "role": "JOB_SEEKER"}'
   ```
2. **Register an Employer:**
   ```bash
   curl -X POST http://localhost:9099/api/v1/auth/register \
        -H "Content-Type: application/json" \
        -d '{"username": "employer1", "password": "empPass", "role": "EMPLOYER"}'
   ```

### B. Job Seeker Endpoints

1. **Apply for a Job** (Assuming a job with ID 1 exists):
   ```bash
   curl -u testuser2:testpass -X POST http://localhost:9099/api/v1/jobseeker/applications \
        -H "Content-Type: application/json" \
        -d '{"jobId": 1, "status": "PENDING"}'
   ```
   *Note: The `jobSeekerId` is set automatically by the controller based on the authenticated user.*

2. **View Own Applications:**
   ```bash
   curl -u testuser2:testpass http://localhost:9099/api/v1/jobseeker/applications
   ```

3. **View All Jobs:**
   ```bash
   curl -u testuser2:testpass http://localhost:9099/api/v1/jobseeker/jobs?title=Software&location=New%20York&employer=Acme
   ```

4. **Upload Resume:**
   ```bash
   curl -u testuser2:testpass -X POST http://localhost:9099/api/v1/jobseeker/upload-resume \
        -F "jobSeekerId=1" \
        -F "file=@/path/to/your/resume.pdf"
   ```

### C. Employer Endpoints

1. **Post a New Job:**
   ```bash
   curl -u employer1:empPass -X POST http://localhost:9099/api/v1/employer/jobs \
        -H "Content-Type: application/json" \
        -d '{"title": "Software Developer", "description": "Looking for a skilled developer.", "location": "New York"}'
   ```
   *Note: The controller sets the `employerId` automatically based on the authenticated employer.*

2. **View Employer's Jobs:**
   ```bash
   curl -u employer1:empPass http://localhost:9099/api/v1/employer/jobs
   ```

3. **Get Applications for a Job** (Assuming job ID 1):
   ```bash
   curl -u employer1:empPass http://localhost:9099/api/v1/employer/jobs/1/applications?status=PENDING
   ```

4. **Update Application Status** (Assuming application ID 1):
   ```bash
   curl -u employer1:empPass -X PUT "http://localhost:9099/api/v1/employer/applications/1/status?status=APPROVED"
   ```

5. **Add a Review for a Job** (Assuming job ID 1):
   ```bash
   curl -u employer1:empPass -X POST http://localhost:9099/api/v1/employer/reviews/jobs/1 \
        -H "Content-Type: application/json" \
        -d '{"content": "Excellent candidate!", "rating": 5}'
   ```

### D. Admin Endpoints

1. **Get All Users:**
   ```bash
   curl -u admin:adminpass http://localhost:9099/api/v1/admin/users?role=JOB_SEEKER
   ```

2. **Delete a User (User ID 2):**
   ```bash
   curl -u admin:adminpass -X DELETE http://localhost:9099/api/v1/admin/users/2
   ```

## 5. Postman Collection

- A sample Postman collection is available at `postman/JobPortal.postman_collection.json`.
- Import this collection into Postman.
- Update the Basic Auth credentials and base URL (set to `http://localhost:9099` or your server domain).
- Use the collection to test all endpoints.

## 6. Port Management & Troubleshooting

If port `9099` is in use, run:
```bash
sudo lsof -i :9099
sudo kill -9 <PID>
```
Then re-run:
```bash
./mvnw clean install
./mvnw spring-boot:run
```
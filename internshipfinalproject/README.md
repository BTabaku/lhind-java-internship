# Job Portal API LHIND Internship

A Spring Boot application that provides role-based functionality for a simple job portal.  
**Roles**:  
- `ADMIN` – Manage (view/delete) users  
- `EMPLOYER` – Post jobs, view/update applications for their jobs, add reviews  
- `JOB_SEEKER` – View jobs, apply for jobs, upload resumes, view own applications  

## 1. Tech Stack

- **Spring Boot** (2.x or 3.x)
- **Spring Data JPA** (Hibernate)
- **MySQL** (JDBC Driver)
- **Spring Security** (Basic Auth)
- **MapStruct** (for DTO mapping)
- **JUnit & Mockito** (for tests)

## 2. Setup Instructions

1. **Clone** the repository:
   ```bash
   git clone <YOUR-REPO-URL>
   cd internshipfinalproject
   ```
2. **Configure MySQL** in `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://<HOST>:<PORT>/<DB_NAME>?createDatabaseIfNotExist=true
   spring.datasource.username=<YOUR_DB_USER>
   spring.datasource.password=<YOUR_DB_PASSWORD>
   ```
   Adjust server port if needed (`server.port=9099`).

3. **Build and Run**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
   The application will start on [http://localhost:9099](http://localhost:9099/).

4. **Database**:
    - Once running, the project will auto-create/update the schema (`spring.jpa.hibernate.ddl-auto=update`) if the database doesn’t exist.

5. **Basic Auth**:
    - Endpoints are secured by role. You can register new users at `/api/v1/auth/register` or seed the DB manually.
    - Use the credentials you create to authenticate. For example, `ROLE_ADMIN` => `admin:adminpass`, etc.

## 3. API Endpoints

Below is an overview of the available endpoints. Use Basic Auth with the correct role to access each:

### Admin
- **GET** `/api/v1/admin/users` – List all users (optional `role` param).
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
- **POST** `/api/v1/jobseeker/upload-resume` – Upload a resume for your user ID.

### Auth
- **POST** `/api/v1/auth/register` – Register a new user (Admin, Employer, or Job Seeker).

### Reviews
- **GET** `/api/v1/employer/reviews?jobId=&rating=` – Filter reviews by job ID or rating.

## 4. Postman Collection

- A sample Postman collection can be found under `postman/JobPortal.postman_collection.json` (example path).
- Import the collection into Postman, update the Basic Auth credentials in the “Authorization” tab for each request, and test away.

## 5. Port Management & Troubleshooting

If port `9099` is in use:
```bash
sudo lsof -i :9099
sudo kill -9 <PID>
```
Then re-run `mvn spring-boot:run`.
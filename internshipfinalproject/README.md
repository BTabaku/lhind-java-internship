Let's clean up and update the `README.md` file with more organized and clearer instructions:

```markdown
# Job Portal API

## Getting Started

### Port Management
To check and kill a process using port 9099:
```bash
sudo lsof -i :9099
sudo kill -9 <PID>
```

### Authentication Steps

1. Register Admin User:
```bash
curl -i -X POST \
  http://localhost:9099/auth/register \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "admin",
    "password": "admin123",
    "role": "ADMIN"
}'
```

2. Register Employer:
```bash
curl -i -X POST \
  http://localhost:9099/auth/register \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "employer",
    "password": "employer123",
    "role": "EMPLOYER"
}'
```

3. Register Job Seeker:
```bash
curl -i -X POST \
  http://localhost:9099/auth/register \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "jobseeker",
    "password": "jobseeker123",
    "role": "JOB_SEEKER"
}'
```

### Testing Protected Endpoints

#### Admin Operations
1. Test Admin Access:
```bash
curl -i -X GET \
  http://localhost:9099/admin/ \
  --user admin:admin123
```

2. Create Job Category:
```bash
curl -i -X POST \
  http://localhost:9099/admin/categories \
  --user admin:admin123 \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "Software Development",
    "description": "Programming and software engineering positions"
}'
```

#### Employer Operations
1. Test Employer Access:
```bash
curl -i -X GET \
  http://localhost:9099/employer/ \
  --user employer:employer123
```

2. Create Job Posting:
```bash
curl -i -X POST \
  http://localhost:9099/employer/jobs \
  --user employer:employer123 \
  -H 'Content-Type: application/json' \
  -d '{
    "title": "Java Developer",
    "description": "Looking for Java developer",
    "requirements": "Java, Spring Boot experience",
    "categoryId": 1,
    "salary": 50000,
    "location": "Amsterdam"
}'
```

#### Job Seeker Operations
1. Test Job Seeker Access:
```bash
curl -i -X GET \
  http://localhost:9099/jobseeker/ \
  --user jobseeker:jobseeker123
```

2. Search Jobs:
```bash
curl -i -X GET \
  http://localhost:9099/jobseeker/jobs/search?keyword=Java \
  --user jobseeker:jobseeker123
```

3. Apply for Job:
```bash
curl -i -X POST \
  http://localhost:9099/jobseeker/jobs/1/apply \
  --user jobseeker:jobseeker123 \
  -H 'Content-Type: multipart/form-data' \
  -F 'resume=@path/to/resume.pdf' \
  -F 'coverLetter=Interested in this position'
```

### Troubleshooting

If you receive 401 errors, try resetting the password:
```bash
curl -i -X PUT \
  http://localhost:9099/auth/reset-password \
  -H 'Content-Type: application/json' \
  -d '{
    "username": "username",
    "oldPassword": "oldpassword",
    "newPassword": "newpassword"
}'
```

Note: Replace placeholder values (usernames, passwords, file paths) with actual values when testing.
```
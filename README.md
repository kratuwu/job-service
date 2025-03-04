# job-service
## Overview
This is a Spring Boot application with JPA, Web, and other dependencies configured using Gradle.

## Prerequisites
- Java 21
- Gradle
## `GET /api/jobData`

### Description:
This endpoint retrieves a list of job data, with optional filtering and sorting options.

### Query Parameters:

- `fields` (optional): A list of fields to include in the response. If not provided, all fields will be returned.  
  **Example**: `fields=id,name,job_title`

- `sorts` (optional, default: `id:ASC`): Specifies sorting of the response. Use a comma-separated list of field names and directions (ASC for ascending, DESC for descending).  
  **Example**: `sorts=salary:DESC,name:ASC`

- `gender` (optional): Filter the results by gender.  
  **Example**: `gender=female`

- `job_title` (optional): Filter the results by job title.  
  **Example**: `job_title=Software Engineer`

- `salary_gte` (optional): Filter the results to include jobs with a salary greater than or equal to the specified value.  
  **Example**: `salary_gte=50000`

- `salary_lte` (optional): Filter the results to include jobs with a salary less than or equal to the specified value.  
  **Example**: `salary_lte=100000`

### Response:

- **200 OK**: Returns a list of job data objects matching the provided filters and sorts.

#### Example Response:
```json
[
  {
    "id": 1,
    "name": "John Doe",
    "job_title": "Software Engineer",
    "gender": "male",
    "salary": 75000
  },
  {
    "id": 2,
    "name": "Jane Smith",
    "job_title": "Product Manager",
    "gender": "female",
    "salary": 90000
  }
]

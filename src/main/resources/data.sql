CREATE TABLE salary_survey (
    id BIGINT AUTO_INCREMENT PRIMARY KEY ,
    timestamp VARCHAR(255),
    employer VARCHAR(255),
    location VARCHAR(255),
    job_title VARCHAR(255),
    years_at_employer VARCHAR(255),
    years_of_experience VARCHAR(255),
    salary NUMERIC(21,6),
    signing_bonus VARCHAR(255),
    annual_bonus VARCHAR(255),
    annual_stock_value_bonus VARCHAR(255),
    gender VARCHAR(100),
    additional_comments TEXT
);
CREATE TYPE if not exists gender AS ENUM ('MALE','FEMALE');
CREATE TABLE employees
(
    employee_id   IDENTITY,
    first_name    varchar,
    last_name     varchar,
    department_id number,
    job_title     varchar,
    gender        varchar,
    date_of_birth Date DEFAULT '1900-10-28' CHECK (date_of_birth < current_date)
);
INSERT into employees (first_name, last_name, department_id, job_title, gender, date_of_birth)
values ('Bob', 'Marley', 2, 'singer', 'MALE', '1978-01-15');
INSERT into employees (first_name, last_name, department_id, job_title, gender, date_of_birth)
values ('Viktor', 'Ivanov', 2, 'painter', 'MALE', '1988-01-15');
INSERT into employees (first_name, last_name, department_id, job_title, gender, date_of_birth)
values ('Kate', 'Petrova', 2, 'teacher', 'FEMALE', '1990-05-13');
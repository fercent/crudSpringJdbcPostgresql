DROP TABLE IF EXISTS employee;
CREATE TABLE employee(employee_id bigint PRIMARY KEY, first_name VARCHAR(100), last_name VARCHAR(100), department_id bigint,
 job_title VARCHAR(100), gender VARCHAR(6), birthday VARCHAR(8));
CREATE TABLE employees (
 id INT AUTO_INCREMENT PRIMARY KEY,
 name VARCHAR(50),
 age INT,
 email VARCHAR(50),
 department VARCHAR(50)
);

INSERT INTO employees (name, age, email, department) VALUES
 ('John Smith', 35, 'john.smith@example.com', 'Sales'),
 ('Mary Jones', 28, 'mary.jones@example.com', 'Marketing'),
 ('David Lee', 42, 'david.lee@example.com', 'Engineering'),
 ('Sarah Kim', 31, 'sarah.kim@example.com', 'Operations');

SELECT * from employees;


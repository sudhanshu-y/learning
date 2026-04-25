-- =========================================================================
-- DML & TCL
-- =========================================================================

USE company_db;

-- ==========================================
-- 1. INSERT
-- ==========================================

-- STEP 1: Insert Departments First
-- Single Insert
INSERT INTO departments (dept_name) 
VALUES ('Engineering');

-- Bulk Insert (Best practice for performance)
INSERT INTO departments (dept_name) 
VALUES 
    ('Human Resources'),
    ('Sales'),
    ('Marketing'),
    ('Finance');

SELECT * FROM departments;

-- STEP 2: Insert Employees
-- Note: We skip `emp_id` (AUTO_INCREMENT) and `hire_date` (uses DEFAULT CURRENT_DATE)
INSERT INTO employees (first_name, last_name, email, contact_number, dept_id) 
VALUES 
    ('Jane', 'Doe', 'jane.doe@company.com', '555-0100', 1),
    ('John', 'Smith', 'john.smith@company.com', '555-0101', 2),
    ('Alice', 'Johnson', 'alice.j@company.com', '555-0102', 1),
    ('Bob', 'Brown', 'bob.b@company.com', '555-0103', 3),
    ('Charlie', 'Davis', 'charlie.d@company.com', '555-0104', 4);

SELECT * FROM employees;

INSERT INTO departments (dept_name)
SELECT DISTINCT dept_name
FROM employees;

-- ==========================================
-- 2. UPDATE
-- ==========================================

-- Standard Update (Always use a WHERE clause with a Primary Key!)
UPDATE employees 
SET email = 'jane.smith@company.com', last_name = 'Smith' 
WHERE emp_id = 1;

-- ==========================================
-- SUPPORTING QUERY: SAFE UPDATES OVERRIDE
-- ==========================================
-- If you need to update a record using a non-primary key (like first_name), 
-- MySQL's "Safe Mode" will usually block it. Here is how to temporarily bypass it:
SET SQL_SAFE_UPDATES = 0; 

-- Move all 'Smith's to the Finance department (dept_id = 5)
UPDATE employees 
SET dept_id = 5 
WHERE last_name = 'Smith';

SET SQL_SAFE_UPDATES = 1; -- Always turn it back on immediately!


-- ==========================================
-- 3. DELETE
-- ==========================================

-- Standard Delete using a Primary Key
DELETE FROM employees 
WHERE emp_id = 4; -- Removes Bob Brown from the system

-- Delete with conditions
DELETE FROM employees 
WHERE dept_id = 2 AND hire_date < '2023-01-01';


-- ==========================================
-- 4. TCL: TRANSACTIONS
-- ==========================================
-- When modifying crucial data, wrap your DML in a transaction so you can undo it.

START TRANSACTION;

-- Let's say we accidentally wipe out the Engineering department employees
DELETE FROM employees WHERE dept_id = 1;

-- Supporting Query to check the damage
SELECT * FROM employees WHERE dept_id = 1; 

-- That was a mistake! Let's undo the DELETE:
ROLLBACK; 

-- Verify the rollback worked (The employees are back!)
SELECT * FROM employees WHERE dept_id = 1;

-- (If the delete was intentional, you would use COMMIT; instead of ROLLBACK;)
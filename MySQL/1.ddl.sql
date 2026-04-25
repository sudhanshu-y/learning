-- ==========================================
-- 1. DATABASE LEVEL COMMANDS
-- ==========================================

-- Check MySQL Version
SELECT VERSION();

-- Create Database
CREATE DATABASE company_db;

-- Show All Databases
SHOW DATABASES;

-- Set Active Database
USE company_db;

-- ==========================================
-- 2. TABLE CREATION & INSPECTION
-- ==========================================

-- Create Independent Table (No Foreign Keys)
CREATE TABLE departments (
    dept_id INT AUTO_INCREMENT PRIMARY KEY,
    dept_name VARCHAR(100) NOT NULL UNIQUE
);

-- Create Dependent Table (With Foreign Key)
CREATE TABLE employees (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    hire_date DATE DEFAULT (CURRENT_DATE),
    dept_id INT,
    FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);

-- Show All Tables in Active Database
SHOW TABLES;

-- View Table Structure (Columns, Types, Keys)
DESCRIBE employees;

-- View the Exact SQL Statement Used to Create the Table
SHOW CREATE TABLE employees;

-- ==========================================
-- 3. ALTERING COLUMNS
-- ==========================================

-- Add a New Column
ALTER TABLE employees 
ADD phone_number VARCHAR(15);

-- Modify an Existing Column's Data Type or Nullability
ALTER TABLE employees 
MODIFY phone_number VARCHAR(20) NOT NULL;

-- Rename a Column
ALTER TABLE employees 
RENAME COLUMN phone_number TO contact_number;

-- Set a Default Value for an Existing Column
ALTER TABLE employees 
ALTER COLUMN dept_id SET DEFAULT 1;

-- Remove a Default Value from a Column
ALTER TABLE employees 
ALTER COLUMN dept_id DROP DEFAULT;

-- Drop a Column completely
ALTER TABLE employees 
DROP COLUMN contact_number;

-- ==========================================
-- 4. CONSTRAINTS (ADDING & DROPPING)
-- ==========================================

-- Add a Check Constraint
ALTER TABLE employees 
ADD CONSTRAINT chk_dept 
CHECK (dept_id > 0);

-- Drop a Check Constraint
ALTER TABLE employees 
DROP CHECK chk_dept;

-- Add a Foreign Key Constraint (If not added during creation)
ALTER TABLE employees
ADD CONSTRAINT fk_dept
FOREIGN KEY (dept_id) REFERENCES departments(dept_id);

-- Drop a Foreign Key Constraint (Requires the exact constraint name)
ALTER TABLE employees 
DROP FOREIGN KEY fk_dept;

-- ==========================================
-- 5. INDEXING (FOR PERFORMANCE)
-- ==========================================

-- Create a Standard Index (Speeds up WHERE clauses)
CREATE INDEX idx_last_name 
ON employees(last_name);

-- Create a Unique Index (Alternative to UNIQUE constraint)
CREATE UNIQUE INDEX idx_emp_email 
ON employees(email);

-- Drop an Index
DROP INDEX idx_last_name ON employees;

-- ==========================================
-- 6. VIEWS (VIRTUAL TABLES)
-- ==========================================

-- Create a View to simplify complex queries or restrict data access
CREATE VIEW vw_employee_directory AS
SELECT first_name, last_name, email 
FROM employees;

-- Query the View (Used like a DQL command, but Views are DDL objects)
SELECT * FROM vw_employee_directory;

-- Drop a View
DROP VIEW vw_employee_directory;

-- ==========================================
-- 7. RENAMING, CLEARING, & DROPPING
-- ==========================================

-- Rename a Table
RENAME TABLE employees TO staff_members;
ALTER TABLE employees RENAME TO staff_members;

-- Rename it back
RENAME TABLE staff_members TO employees;

-- Truncate Table (Instantly deletes all rows, resets AUTO_INCREMENT, keeps structure)
TRUNCATE TABLE employees;

-- Drop Table (Destroys table structure and all data within it)
DROP TABLE employees;

-- Drop Table Safely (Prevents errors if it doesn't exist)
DROP TABLE IF EXISTS employees;

-- Drop Database (Destroys the database and everything inside it)
DROP DATABASE company_db;
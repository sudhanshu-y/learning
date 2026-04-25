-- =========================================================================
-- JOINS (LOGICAL DEFINITIONS)
-- =========================================================================
USE company_db;

-- ==========================================
-- 1. VERIFYING THE DATA
-- ==========================================
SELECT * FROM departments;
SELECT * FROM employees;

-- ==========================================
-- 2. INNER JOIN
-- ==========================================
-- LOGIC: Returns ONLY the records that have matching values in BOTH tables. 
-- If an employee has no assigned department, or a department has no employees, 
-- those records are completely excluded from the result set.

SELECT 
    e.first_name, 
    e.last_name, 
    d.dept_name
FROM employees e
INNER JOIN departments d 
    ON e.dept_id = d.dept_id;

-- ==========================================
-- 3. LEFT JOIN
-- ==========================================
-- LOGIC: Returns ALL records from the Left table (employees), regardless of 
-- whether they have a match. If a match exists in the Right table (departments), 
-- it brings the data in. If no match exists, it fills the right side with NULLs.

SELECT 
    e.first_name, 
    e.last_name, 
    d.dept_name
FROM employees e
LEFT JOIN departments d 
    ON e.dept_id = d.dept_id;

-- ==========================================
-- 4. RIGHT JOIN 
-- ==========================================
-- LOGIC: Returns ALL records from the Right table (departments), regardless of 
-- whether they have a match. If a match exists in the Left table (employees), 
-- it brings the data in. If no match exists, it fills the left side with NULLs.

SELECT 
    e.first_name, 
    e.last_name, 
    d.dept_name
FROM employees e
RIGHT JOIN departments d 
    ON e.dept_id = d.dept_id;

-- ==========================================
-- 5. FULL OUTER JOIN
-- ==========================================
-- LOGIC: Returns ALL records from BOTH tables. It returns perfect matches, 
-- plus all unmatched rows from the left, plus all unmatched rows from the right.
-- In MySQL, this is simulated by stacking a LEFT JOIN and RIGHT JOIN with UNION.

SELECT e.first_name, e.last_name, d.dept_name
FROM employees e
LEFT JOIN departments d ON e.dept_id = d.dept_id
UNION
SELECT e.first_name, e.last_name, d.dept_name
FROM employees e
RIGHT JOIN departments d ON e.dept_id = d.dept_id;

-- ==========================================
-- 6. THE ANTI-JOIN ( DIFFERENCE: A - B or B - A WHERE A = employees AND B = departments )
-- ==========================================
-- LOGIC: Returns ONLY the records from the Left table that have NO match 
-- in the Right table. This is achieved by performing a standard LEFT JOIN 
-- and then explicitly filtering out any rows where the right side successfully joined.

-- [ A - B ]: The Left Anti-Join (Employees with NO assigned department)
SELECT e.first_name, e.last_name
FROM employees e
LEFT JOIN departments d
    ON e.dept_id = d.dept_id
WHERE d.dept_id IS NULL;

-- [ B - A ]: The Right Anti-Join (Departments with ZERO employees)
SELECT d.dept_name
FROM departments d
LEFT JOIN employees e 
    ON d.dept_id = e.dept_id
WHERE e.emp_id IS NULL; 


-- ==========================================
-- 7. SEMI JOIN ( THE "EXISTS" CHECK )
-- ==========================================
-- SET LOGIC: Checks for the existence of an intersection without actually joining the columns.
-- BEHAVIOR: Highly performant. The database engine stops searching Table B the moment 
-- it finds a single match for Table A. It does not return Table B's columns.

SELECT e.first_name, e.last_name
FROM employees e
WHERE EXISTS (
    SELECT 1 
    FROM departments d 
    WHERE d.dept_id = e.dept_id
);


-- ==========================================
-- 8. ON vs WHERE
-- ==========================================
-- BEHAVIOR: The placement of your filter completely changes the Set Logic.

-- Scenario 1: Filter in the 'ON' clause (Applied DURING the join)
-- SET LOGIC: "Give me all employees. If they are in IT, show the dept name. Otherwise, show NULL."
-- Result: You still get ALL employees in the company.
SELECT e.first_name, d.dept_name
FROM employees e
LEFT JOIN departments d
    ON e.dept_id = d.dept_id AND d.dept_name = 'IT';

-- Scenario 2: Filter in the 'WHERE' clause (Applied AFTER the join)
-- SET LOGIC: "Join the tables, then throw away any row that doesn't explicitly say 'IT'."
-- Result: You effectively turned your LEFT JOIN into an INNER JOIN. You ONLY get IT staff.
SELECT e.first_name, d.dept_name
FROM employees e
LEFT JOIN departments d
    ON e.dept_id = d.dept_id
WHERE d.dept_name = 'IT';


-- ==========================================
-- 9. SELF JOIN ( CARTESIAN CONSTRAINED )
-- ==========================================
-- SET LOGIC: Comparing Set A against Set A.
-- BEHAVIOR: We MUST use aliases to treat one table as two separate entities. 
-- We MUST use `<>` or `!=` to prevent a row from joining to itself.

-- "Find all pairs of employees who work in the exact same department."
SELECT 
    A.first_name AS emp1,
    B.first_name AS emp2,
    A.dept_id
FROM employees A
JOIN employees B
    ON A.dept_id = B.dept_id
    AND A.emp_id <> B.emp_id;

-- =========================================================================
-- DQL
-- =========================================================================

USE company_db;

-- ==========================================
-- BASIC RETRIEVAL & FILTERING
-- ==========================================

-- 1. Select Specific Columns (Best Practice)
-- Bad practice for production!
SELECT * 
FROM employees;

-- Best Practice
SELECT first_name, last_name, email 
FROM employees;

-- 2. Filtering with WHERE + LIKE (Pattern Matching)
SELECT first_name, hire_date, email 
FROM employees 
WHERE hire_date >= '2020-01-01' 
  AND email LIKE '%@CoMpany.com'; -- LIKE is case-insensitive
  
SELECT first_name, hire_date, email 
FROM employees 
WHERE hire_date >= '2020-01-01' 
  AND email LIKE BINARY 'j%.com'; -- LIKE is case-sensitive

-- 3. DISTINCT Values (Remove duplicates from result set)
SELECT DISTINCT dept_id 
FROM employees;

-- 4. Sorting & Limiting (Top 5 newest employees)
SELECT first_name, last_name, hire_date 
FROM employees 
ORDER BY hire_date DESC 
LIMIT 5;

-- 5. Pagination (LIMIT + OFFSET)
SELECT first_name 
FROM employees
LIMIT 2 OFFSET 2; -- Skips the first 2 rows, returns the next 2

-- 6. BETWEEN Operator (Date ranges)
SELECT first_name, hire_date
FROM employees
WHERE hire_date BETWEEN '2020-01-01' AND '2025-01-01';

-- 7. IN Operator (Matches any value in a list)
SELECT first_name, dept_id
FROM employees
WHERE dept_id IN (1, 2, 3);

-- 8. NULL Handling
SELECT first_name
FROM employees
WHERE email IS NULL;


-- ==========================================
-- AGGREGATION & GROUPING
-- ==========================================

-- 9. Basic Counting
SELECT COUNT(emp_id) AS total_employees 
FROM employees;

-- 10. Multiple Aggregate Functions
SELECT 
    COUNT(*) AS total,
    MIN(hire_date) AS earliest_hire,
    MAX(hire_date) AS latest_hire
FROM employees;

-- 11. GROUP BY (Bucketing data)
SELECT dept_id, COUNT(emp_id) AS staff_count 
FROM employees 
GROUP BY dept_id;

-- 12. HAVING Clause (Filtering grouped buckets)
SELECT dept_id, COUNT(emp_id) AS staff_count 
FROM employees 
GROUP BY dept_id 
HAVING COUNT(emp_id) > 1;

SELECT dept_id, COUNT(emp_id) AS staff_count 
FROM employees 
GROUP BY dept_id 
HAVING staff_count > 1;


-- ==========================================
-- JOINS (RELATIONAL QUERIES)
-- ==========================================

-- 13. INNER JOIN (Exact matches only)
SELECT 
    e.first_name, 
    e.last_name, 
    d.dept_name 
FROM employees e
INNER JOIN departments d 
    ON e.dept_id = d.dept_id;

-- 14. LEFT JOIN (All employees, plus department if they have one)
SELECT 
    e.first_name, 
    d.dept_name 
FROM employees e 
LEFT JOIN departments d 
    ON e.dept_id = d.dept_id;

-- 15. The "Orphan" Query (Departments with NO employees)
SELECT d.dept_name
FROM departments d
LEFT JOIN employees e 
    ON d.dept_id = e.dept_id
WHERE e.emp_id IS NULL;

-- 16. Aggregation combined with JOIN
SELECT 
    d.dept_id,
    d.dept_name,
    COUNT(e.emp_id) AS total_employees
FROM departments d
LEFT JOIN employees e 
    ON d.dept_id = e.dept_id
GROUP BY d.dept_id, d.dept_name;


-- ==========================================
-- INTERMEDIATE LOGIC & SETS
-- ==========================================

-- 17. CASE Statement (If/Else logic in SQL)
SELECT 
    first_name,
    dept_id,
    CASE 
        WHEN dept_id = 1 THEN 'Engineering'
        WHEN dept_id = 2 THEN 'HR'
        ELSE 'Other'
    END AS department_category
FROM employees;

-- 18. Self Join (Finding employees in the same department)
SELECT 
    e1.first_name AS employee1,
    e2.first_name AS employee2,
    e1.dept_id
FROM employees e1
JOIN employees e2 
    ON e1.dept_id = e2.dept_id 
    AND e1.emp_id <> e2.emp_id;

-- 19. UNION (Stacking results and removing duplicates)
SELECT first_name FROM employees WHERE dept_id = 1
UNION
SELECT first_name FROM employees WHERE dept_id = 2;


-- 20. Simulating a FULL OUTER JOIN in MySQL
SELECT e.first_name, d.dept_name 
FROM employees e LEFT JOIN departments d ON e.dept_id = d.dept_id
UNION
SELECT e.first_name, d.dept_name 
FROM employees e RIGHT JOIN departments d ON e.dept_id = d.dept_id;


-- ==========================================
-- ADVANCED SUBQUERIES
-- ==========================================

-- 21. Subquery in WHERE (Single Value)
SELECT first_name, last_name 
FROM employees 
WHERE dept_id = (
    SELECT dept_id 
    FROM departments 
    WHERE dept_name = 'Engineering'
);

-- 22. Subquery in WHERE (Multiple Values using IN)
SELECT first_name
FROM employees
WHERE dept_id IN (
    SELECT dept_id 
    FROM departments 
    WHERE dept_name IN ('Engineering', 'Sales')
);

-- 23. EXISTS (Highly efficient boolean check)
SELECT dept_name
FROM departments d
WHERE EXISTS (
    SELECT 1 
    FROM employees e 
    WHERE e.dept_id = d.dept_id
);

-- 24. Find Nth Highest (e.g., Oldest Employee ID)
SELECT MAX(emp_id)
FROM employees
WHERE emp_id < (
    SELECT MAX(emp_id) FROM employees
);

-- 25. Derived Table (Subquery in the FROM clause - Must be aliased!)
SELECT dept_id, total
FROM (
    SELECT dept_id, COUNT(*) AS total
    FROM employees
    GROUP BY dept_id
) AS temp;


-- 26. CTE (Common Table Expression - Makes logic readable)
WITH RecentHires AS (
    SELECT emp_id, first_name, dept_id, hire_date
    FROM employees
    WHERE hire_date >= '2023-01-01'
)
SELECT 
    rh.first_name, 
    rh.hire_date, 
    d.dept_name 
FROM RecentHires rh
INNER JOIN departments d 
    ON rh.dept_id = d.dept_id;

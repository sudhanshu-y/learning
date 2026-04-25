-- =========================================================================
-- RANKING SETUP (DDL & DML)
-- =========================================================================
USE company_db;

-- Representatives
CREATE TABLE sales_reps (
    rep_id INT PRIMARY KEY AUTO_INCREMENT,
    rep_name VARCHAR(50),
    region VARCHAR(50),
    total_sales INT
);

INSERT INTO sales_reps (rep_name, region, total_sales) VALUES 
('Alice', 'North', 10000),
('Bob',   'North', 10000), -- TIED WITH ALICE
('Charlie','North', 9000),
('David', 'South', 12000),
('Eve',   'South', 8000),
('Frank', 'South', 8000);  -- TIED WITH EVE


-- =========================================================================
-- ROW_NUMBER vs RANK vs DENSE_RANK
-- =========================================================================

SELECT 
    rep_name, 
    total_sales,
    
    -- 1. ROW_NUMBER()
    -- LOGIC: Assigns a strictly unique, sequential integer to every row. 
    -- It absolutely ignores ties. If Alice and Bob have the same sales, 
    -- one arbitrarily gets 1, the other gets 2.
    ROW_NUMBER() OVER (ORDER BY total_sales DESC) AS row_num,
    
    -- 2. RANK()
    -- LOGIC: Assigns the same rank to tied values, but SKIPS the next numbers.
    -- If Alice and Bob are tied for 1st, Charlie becomes 3rd. (1, 1, 3)
    RANK() OVER (ORDER BY total_sales DESC) AS standard_ranking,
    
    -- 3. DENSE_RANK()
    -- LOGIC: Assigns the same rank to tied values, and DOES NOT skip numbers.
    -- If Alice and Bob are tied for 1st, Charlie becomes 2nd. (1, 1, 2)
    DENSE_RANK() OVER (ORDER BY total_sales DESC) AS dense_ranking
    
FROM sales_reps;

-- =========================================================================
-- PARTITIONING
-- =========================================================================
-- LOGIC: The rank resets to 1 every time the `region` changes.

SELECT 
    region,
    rep_name, 
    total_sales,
    DENSE_RANK() OVER (
        PARTITION BY region 
        ORDER BY total_sales DESC
    ) AS regional_rank
FROM sales_reps;

-- =========================================================================
-- Write a query to find the top 2 highest-selling reps in each region.
-- =========================================================================

WITH RankedSales AS (
    -- Step 1: Calculate the regional ranks inside a temporary memory space (CTE)
    SELECT 
        region,
        rep_name, 
        total_sales,
        DENSE_RANK() OVER (PARTITION BY region ORDER BY total_sales DESC) AS rank_num
    FROM sales_reps
)
-- Step 2: Query the CTE and filter the pre-calculated ranks
SELECT 
    region,
    rep_name, 
    total_sales
FROM RankedSales
WHERE rank_num <= 2;




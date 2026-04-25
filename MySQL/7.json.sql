-- Create Table with JSON
CREATE TABLE users (
  id INT PRIMARY KEY,
  info JSON
);

-- Insert JSON Data
INSERT INTO users (id, info)
VALUES (1, '{"name": "John", "age": 30, "city": "Pune"}');

-- Insert using JSON_OBJECT()
INSERT INTO users (id, info)
VALUES (
  2,
  JSON_OBJECT(
    'name', 'Alice',
    'age', 28,
    'city', 'Mumbai'
  )
);

-- Insert with Nested JSON
INSERT INTO users (id, info)
VALUES (
  3,
  JSON_OBJECT(
    'name', 'Bob',
    'age', 35,
    'address', JSON_OBJECT(
        'city', 'Delhi',
        'pincode', 110001
    )
  )
);

-- Insert with JSON Array
INSERT INTO users (id, info)
VALUES (
  4,
  JSON_OBJECT(
    'name', 'Eve',
    'skills', JSON_ARRAY('SQL', 'Python', 'Java')
  )
);

-- Create JSON Object / Array
SELECT JSON_OBJECT('name', 'John', 'age', 30);
SELECT JSON_ARRAY('IT', 'HR', 'Sales');

-- Extract Data from JSON
SELECT info->'$.name' FROM users; -- -> returns JSON ("John")
SELECT info->>'$.name' FROM users; -- ->> returns plain text (John)

SELECT JSON_EXTRACT(info, '$.age') FROM users;

-- Filter Using JSON
SELECT * FROM users WHERE info->>'$.city' = 'Pune';

-- Update JSON Values

-- Modify value
UPDATE users SET info = JSON_SET(info, '$.age', 35) WHERE id = 1;

-- Add new key
UPDATE users SET info = JSON_SET(info, '$.email', 'john@example.com') WHERE id = 1;

-- Only replace if present
UPDATE users SET info = JSON_REPLACE(info, '$.age', 40) WHERE id = 1;

-- Remove Key
UPDATE users SET info = JSON_REMOVE(info, '$.city') WHERE id = 1; 

-- Read JSON Array
SELECT bill_ids FROM orders; -- [101, 102, 103]

-- Extract Specific Element
SELECT bill_ids->'$[0]' AS first_bill FROM orders; -- 101

-- Check if Value Exists in Array
SELECT * FROM orders WHERE JSON_CONTAINS(bill_ids, '101');

-- Count Elements
SELECT JSON_LENGTH(bill_ids) AS total_bills FROM orders;

-- Indexing JSON
ALTER TABLE users
ADD COLUMN name VARCHAR(100)
GENERATED ALWAYS AS (info->>'$.name'),
ADD INDEX(name);

SELECT id, info->>'$.name' AS name FROM users WHERE info->>'$.age' > 25;



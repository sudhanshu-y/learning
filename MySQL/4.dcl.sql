-- =========================================================================
-- DCL & SECURITY: MANAGING ACCESS IN MYSQL
-- =========================================================================

-- ==========================================
-- 1. CREATING USERS
-- ==========================================
-- Create an analyst (Can connect from anywhere)
CREATE USER 'reporting_user'@'%' IDENTIFIED BY 'ReportPass2024!';

-- Create a web application user (Can only connect locally)
CREATE USER 'web_api'@'localhost' IDENTIFIED BY 'ApiSecretKey!';


-- ==========================================
-- 2. GRANTING PRIVILEGES
-- ==========================================
-- Give the analyst READ ONLY access to the entire company_db
GRANT SELECT ON company_db.* TO 'reporting_user'@'%';

-- Give the web app CRUD (Create, Read, Update, Delete) access
GRANT SELECT, INSERT, UPDATE, DELETE ON company_db.* TO 'web_api'@'localhost';

-- Give a specific user access to only ONE table (Highly restricted)
-- GRANT SELECT ON company_db.departments TO 'intern'@'%';


-- ==========================================
-- 3. VERIFYING PRIVILEGES
-- ==========================================
-- Check what permissions the web API has
SHOW GRANTS FOR 'web_api'@'localhost';


-- ==========================================
-- 4. REVOKING PRIVILEGES
-- ==========================================
-- The web app is compromised! Revoke its ability to INSERT, DELETE data immediately.
REVOKE INSERT, DELETE ON company_db.* FROM 'web_api'@'localhost';

-- Apply the changes to the server's memory
FLUSH PRIVILEGES;


-- ==========================================
-- 5. CLEANUP
-- ==========================================
-- Drop the users to keep our practice environment clean
DROP USER 'reporting_user'@'%';
DROP USER 'web_api'@'localhost';
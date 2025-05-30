# Top 10 Oracle SQL Interview Questions

1. **What are the different types of JOIN in Oracle SQL?**
   - INNER JOIN: Returns matching records from both tables
   ```sql
   -- Find all products with their category names
   SELECT p.product_id, p.name AS product_name, 
          c.name AS category_name, p.price
   FROM products p
   INNER JOIN categories c ON p.category_id = c.category_id;

   -- Find all orders with customer details
   SELECT o.order_id, o.order_date,
          c.first_name || ' ' || c.last_name AS customer_name,
          o.total_amount
   FROM orders o
   INNER JOIN customers c ON o.customer_id = c.customer_id;
   ```
   - LEFT JOIN: Returns all records from left table and matching from right
   ```sql
   -- Find all customers and their orders (including customers with no orders)
   SELECT c.first_name || ' ' || c.last_name AS customer_name,
          c.email,
          COALESCE(COUNT(o.order_id), 0) as total_orders,
          COALESCE(SUM(o.total_amount), 0) as total_spent
   FROM customers c
   LEFT JOIN orders o ON c.customer_id = o.customer_id
   GROUP BY c.customer_id, c.first_name, c.last_name, c.email;

   -- Find all products and their order history (including products never ordered)
   SELECT p.name AS product_name, 
          COALESCE(COUNT(oi.order_item_id), 0) as times_ordered,
          COALESCE(SUM(oi.quantity), 0) as total_quantity_sold
   FROM products p
   LEFT JOIN order_items oi ON p.product_id = oi.product_id
   GROUP BY p.product_id, p.name;
   ```
   - RIGHT JOIN: Returns all records from right table and matching from left
   ```sql
   -- Find all categories and their products (including categories with no products)
   SELECT c.name AS category_name,
          COALESCE(p.name, 'No products') AS product_name,
          COALESCE(p.price, 0) as price
   FROM products p
   RIGHT JOIN categories c ON p.category_id = c.category_id;

   -- Find all orders and their items (including orders with no items)
   SELECT o.order_id, 
          o.order_date,
          COALESCE(p.name, 'No items') AS product_name,
          COALESCE(oi.quantity, 0) as quantity
   FROM order_items oi
   RIGHT JOIN orders o ON oi.order_id = o.order_id
   LEFT JOIN products p ON oi.product_id = p.product_id;
   ```
   - FULL OUTER JOIN: Returns all records when there's a match in either table
   ```sql
   -- Find all possible product-category combinations
   SELECT c.name AS category_name,
          p.name AS product_name,
          COALESCE(p.price, 0) as price
   FROM categories c
   FULL OUTER JOIN products p ON c.category_id = p.category_id;

   -- Find all customer-order relationships (including unmatched on both sides)
   SELECT COALESCE(c.first_name || ' ' || c.last_name, 'No Customer') AS customer_name,
          COALESCE(o.order_id::text, 'No Order') as order_id,
          o.total_amount,
          o.status
   FROM customers c
   FULL OUTER JOIN orders o ON c.customer_id = o.customer_id;
   ```
   - CROSS JOIN: Returns Cartesian product of both tables
   ```sql
   -- Generate all possible product-category combinations (for planning)
   SELECT c.name AS category_name,
          p.name AS product_name
   FROM categories c
   CROSS JOIN products p
   LIMIT 10;

   -- Create a price matrix of all products against each other
   SELECT p1.name AS product1,
          p2.name AS product2,
          (p1.price + p2.price) as combo_price
   FROM products p1
   CROSS JOIN products p2
   WHERE p1.product_id < p2.product_id
   LIMIT 10;
   ```
   - SELF JOIN: Joining a table with itself
   ```sql
   -- Compare product prices within the same category
   SELECT p1.name AS product1,
          p2.name AS product2,
          p1.price AS price1,
          p2.price AS price2,
          (p1.price - p2.price) as price_difference
   FROM products p1
   JOIN products p2 ON p1.category_id = p2.category_id
   WHERE p1.product_id < p2.product_id;

   -- Find products with similar prices (within $10 range)
   SELECT p1.name AS product1,
          p1.price AS price1,
          p2.name AS product2,
          p2.price AS price2,
          ABS(p1.price - p2.price) as price_difference
   FROM products p1
   JOIN products p2 ON p1.product_id < p2.product_id
   WHERE ABS(p1.price - p2.price) <= 10;
   ```

2. **Explain the difference between DELETE, TRUNCATE, and DROP**
   - DELETE: DML command, removes specific rows, can be rolled back
   - TRUNCATE: DDL command, removes all rows, faster than DELETE, cannot be rolled back
   - DROP: DDL command, removes entire table structure, cannot be rolled back
   ```sql
   -- Example of DELETE
   DELETE FROM employees WHERE department_id = 10;
   ```

3. **What are Oracle Database Indexes?**
   - Data structures that improve query performance
   - Types:
     - B-tree index (default)
     - Bitmap index
     - Function-based index
     - Reverse key index
   - Best practices:
     - Create on frequently queried columns
     - Avoid on small tables
     - Consider maintenance overhead

4. **Explain Oracle Database Constraints**
   - PRIMARY KEY: Unique identifier for a row
   - FOREIGN KEY: References PRIMARY KEY in another table
   - UNIQUE: Ensures all values in column are different
   - CHECK: Ensures values meet specific conditions
   - NOT NULL: Ensures column cannot have NULL value
   ```sql
   -- Example of constraint
   ALTER TABLE employees ADD CONSTRAINT pk_emp PRIMARY KEY (employee_id);
   ```

5. **What are Oracle Database Sequences?**
   - Objects that generate unique numbers
   - Commonly used for PRIMARY KEY values
   - Properties:
     - INCREMENT BY: Step value
     - START WITH: Initial value
     - MAXVALUE/MINVALUE: Limits
     - CYCLE/NOCYCLE: Behavior at limit
   ```sql
   -- Example of sequence creation
   CREATE SEQUENCE emp_seq START WITH 1 INCREMENT BY 1;
   ```

6. **Explain Oracle Database Views**
   - Virtual tables based on SELECT statement
   - Types:
     - Simple views: Single table, no aggregations
     - Complex views: Multiple tables, aggregations
     - Materialized views: Stored query results
   - Benefits:
     - Data security
     - Query simplification
     - Data abstraction

7. **What are PL/SQL Stored Procedures and Functions?**
   - Stored Procedures:
     - Standalone programs stored in database
     - Can have IN, OUT, INOUT parameters
     - No return value
   - Functions:
     - Must return a value
     - Can be used in SQL statements
   ```sql
   -- Example of function
   CREATE OR REPLACE FUNCTION get_salary RETURN NUMBER IS
   BEGIN
     -- Function body
     RETURN salary_value;
   END;
   ```

8. **Explain Oracle Database Triggers**
   - Automated procedures that fire on events
   - Types:
     - BEFORE/AFTER triggers
     - INSTEAD OF triggers
     - Row-level/Statement-level triggers
   - Common uses:
     - Audit logging
     - Data validation
     - Complex business rules

9. **What are Oracle Database Transactions?**
   - Unit of work that must be completed entirely
   - ACID properties:
     - Atomicity
     - Consistency
     - Isolation
     - Durability
   - Commands:
     - COMMIT: Save changes
     - ROLLBACK: Undo changes
     - SAVEPOINT: Partial rollback point

10. **Explain Oracle Database Performance Tuning**
    - Key areas:
      - SQL query optimization
      - Index management
      - Statistics gathering
      - Memory configuration
    - Tools:
      - EXPLAIN PLAN
      - SQL Trace
      - AWR reports
    - Best practices:
      - Avoid SELECT *
      - Use bind variables
      - Regular statistics updates 

11. **What are the common Oracle SQL data types and their sizes?**
    - Character Types:
      - CHAR: Fixed-length string, 1-2000 bytes
      - VARCHAR2: Variable-length string, 1-4000 bytes
      - NCHAR: Fixed-length Unicode, 1-2000 characters
      - NVARCHAR2: Variable-length Unicode, 1-4000 characters
      - CLOB: Character Large Object, up to 128TB
      - NCLOB: National Character Large Object, up to 128TB
    - Numeric Types:
      - NUMBER: Variable-length number, 1-38 digits precision
      - BINARY_FLOAT: 32-bit floating point
      - BINARY_DOUBLE: 64-bit floating point
    - Date and Time Types:
      - DATE: Fixed-length 7 bytes, stores century, year, month, day, hour, minute, second
      - TIMESTAMP: Year, month, day, hour, minute, second, fractional seconds
      - INTERVAL: Stores periods of time
    - Binary Types:
      - RAW: Variable-length raw binary, 1-2000 bytes
      - BLOB: Binary Large Object, up to 128TB
    - Boolean Values:
      - Oracle doesn't have a native BOOLEAN type for table columns
      - PL/SQL BOOLEAN type exists but can only be used in programming logic
      - Common implementations in tables:
        ```sql
        -- Using NUMBER(1)
        active NUMBER(1) CHECK (active IN (0, 1)),
        
        -- Using CHAR(1)
        is_active CHAR(1) CHECK (is_active IN ('Y', 'N')),
        
        -- Using VARCHAR2 with constraints
        status VARCHAR2(10) CHECK (status IN ('TRUE', 'FALSE'))
        ```
      - Best practice is to use NUMBER(1) with CHECK constraint:
        ```sql
        CREATE TABLE employees (
            emp_id NUMBER(6),
            is_manager NUMBER(1) DEFAULT 0 CHECK (is_manager IN (0, 1)),
            is_active NUMBER(1) DEFAULT 1 CHECK (is_active IN (0, 1))
        );
        
        -- Querying boolean columns
        SELECT * FROM employees 
        WHERE is_manager = 1 
        AND is_active = 1;
        ```
    - Example Usage:
    ```sql
    CREATE TABLE employee_details (
        emp_id NUMBER(6),                     -- 6-digit employee ID
        first_name VARCHAR2(50),              -- Variable-length name up to 50 chars
        salary NUMBER(10,2),                  -- Number with 10 digits, 2 decimal places
        hire_date DATE,                       -- Date of hiring
        resume CLOB,                          -- Large text document
        profile_photo BLOB,                   -- Binary image data
        last_updated TIMESTAMP(6),            -- Timestamp with 6 decimal places
        comments NVARCHAR2(1000)              -- Unicode text up to 1000 chars
    );
    ```
    - Best Practices:
      - Use VARCHAR2 instead of VARCHAR for compatibility
      - Choose appropriate precision for NUMBER type to save space
      - Use TIMESTAMP when you need fractional seconds
      - Consider storage implications when using LOB data types 
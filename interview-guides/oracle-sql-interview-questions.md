# Top 10 Oracle SQL Interview Questions

1. **What are the different types of JOIN in Oracle SQL?**
   - INNER JOIN: Returns matching records from both tables
   - LEFT JOIN: Returns all records from left table and matching from right
   - RIGHT JOIN: Returns all records from right table and matching from left
   - FULL OUTER JOIN: Returns all records when there's a match in either table
   - CROSS JOIN: Returns Cartesian product of both tables
   - SELF JOIN: Joining a table with itself

2. **Explain the difference between DELETE, TRUNCATE, and DROP**
   - DELETE: DML command, removes specific rows, can be rolled back
   - TRUNCATE: DDL command, removes all rows, faster than DELETE, cannot be rolled back
   - DROP: DDL command, removes entire table structure, cannot be rolled back
   - Example: `DELETE FROM employees WHERE department_id = 10`

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
   - Example: `CONSTRAINT pk_emp PRIMARY KEY (employee_id)`

5. **What are Oracle Database Sequences?**
   - Objects that generate unique numbers
   - Commonly used for PRIMARY KEY values
   - Properties:
     - INCREMENT BY: Step value
     - START WITH: Initial value
     - MAXVALUE/MINVALUE: Limits
     - CYCLE/NOCYCLE: Behavior at limit
   - Example: `CREATE SEQUENCE emp_seq START WITH 1 INCREMENT BY 1`

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
     - Example: `CREATE FUNCTION get_salary RETURN NUMBER`

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
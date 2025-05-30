# Top 10 Hibernate Interview Questions

## 1. What is Hibernate and what are its advantages?
Hibernate is an Object-Relational Mapping (ORM) framework that simplifies database operations in Java applications.

**Advantages:**
- Eliminates boilerplate JDBC code
- Provides automatic table creation
- Supports database independence
- Handles relationships efficiently
- Caching mechanism for better performance
- Supports lazy loading

## 2. What is the difference between get() and load() methods in Hibernate?
- **get()**: 
  - Returns null if object isn't found
  - Always hits the database
  - Returns a fully initialized object
  - Use when you're not sure if object exists

- **load()**: 
  - Throws ObjectNotFoundException if object isn't found
  - Returns a proxy object (lazy loading)
  - Only hits database when non-identifier property is accessed
  - Use when you're sure object exists

## 3. What are the different states of a Hibernate object?
1. **Transient**: New object created but not associated with Hibernate session
2. **Persistent**: Object associated with session and has database representation
3. **Detached**: Object was in persistent state but session was closed
4. **Removed**: Object marked for deletion

## 4. What is Hibernate caching? Explain L1 and L2 cache.
- **First Level Cache (L1)**:
  - Session-level cache
  - Enabled by default
  - Cannot be shared between sessions
  
- **Second Level Cache (L2)**:
  - SessionFactory-level cache
  - Must be explicitly enabled
  - Shared between sessions
  - Examples: EHCache, Redis

## 5. What is the difference between save() and persist() methods?
- **save()**:
  - Returns generated ID immediately
  - Can be called outside transaction
  - Guarantees ID generation

- **persist()**:
  - Void return type
  - Must be called within transaction
  - More JPA-compliant

## 6. Explain different types of mapping in Hibernate
- **One-to-One**: @OneToOne
- **One-to-Many**: @OneToMany
- **Many-to-One**: @ManyToOne
- **Many-to-Many**: @ManyToMany

Each can be configured with cascade operations and fetch types (LAZY/EAGER).

## 7. What is HQL (Hibernate Query Language)?
- Object-oriented query language similar to SQL
- Works with persistent objects instead of table names
- Database independent
- Supports pagination, aggregation, and joins
- Example: `FROM Employee WHERE department.name = 'IT'`

## 8. What are different fetching strategies in Hibernate?
- **LAZY**: Data loaded only when explicitly accessed
- **EAGER**: Data loaded immediately with parent object
- **SELECT**: Separate SELECT query for related entities
- **JOIN**: Single JOIN query to fetch all data

## 9. What is the difference between merge() and update()?
- **merge()**:
  - Creates copy of detached object
  - Works with detached and transient objects
  - Always safe to use
  
- **update()**:
  - Directly reattaches detached object
  - Only works with detached objects
  - Can throw exceptions if object already exists in session

## 10. How to implement optimistic locking in Hibernate?
- Use @Version annotation
- Automatically manages concurrent access
- Options:
  ```java
  @Version
  private Integer version;
  ```
- Throws OptimisticLockException on version mismatch

---
*Note: These questions cover fundamental concepts. Real interviews might include more specific scenarios and implementation details.* 
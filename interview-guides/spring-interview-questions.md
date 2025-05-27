# Top 10 Java Spring Interview Questions

1. **What is Spring IoC Container and Dependency Injection?**
   - IoC (Inversion of Control) container is the core of Spring Framework
   - It manages object creation, configuration, and lifecycle
   - DI is a pattern where dependencies are "injected" into objects rather than created by the objects themselves
   - Example: `@Autowired` annotation for dependency injection

2. **What are the different types of Spring Bean Scopes?**
   - singleton (default) - one instance per Spring container
   - prototype - new instance every time requested
   - request - one instance per HTTP request
   - session - one instance per HTTP session
   - application - one instance per ServletContext
   - websocket - one instance per WebSocket session

3. **Explain Spring MVC Architecture**
   - Model: Data and business logic
   - View: Presentation layer (JSP, Thymeleaf, etc.)
   - Controller: Handles requests and responses (`@Controller`)
   - DispatcherServlet: Front controller that manages flow
   - ViewResolver: Resolves view names to actual views

4. **What is the difference between `@Component`, `@Service`, `@Repository`, and `@Controller`?**
   - `@Component`: Generic stereotype for Spring-managed components
   - `@Service`: Indicates business logic layer
   - `@Repository`: Indicates data access layer
   - `@Controller`: Indicates presentation layer (web controllers)
   - All are specializations of `@Component` with different semantic meanings

5. **Explain Spring Boot Auto-configuration**
   - Automatically configures Spring application based on:
     - Dependencies on classpath
     - Existing configurations
     - Property settings
   - Can be disabled with `@EnableAutoConfiguration(exclude={...})`
   - Reduces boilerplate configuration code

6. **What are Spring Boot Actuator endpoints?**
   - Provides built-in endpoints for monitoring and managing application
   - Common endpoints:
     - `/health`: Application health information
     - `/metrics`: Application metrics
     - `/info`: Application info
     - `/env`: Environment properties

7. **What is AOP in Spring?**
   - Aspect-Oriented Programming
   - Handles cross-cutting concerns
   - Key concepts:
     - Aspect: Module encapsulating cross-cutting concern
     - Pointcut: Where to apply the aspect
     - Advice: What to do (before, after, around)

8. **Explain Spring Security Authentication and Authorization**
   - Authentication: Verifying identity (who are you?)
   - Authorization: Verifying permissions (what can you do?)
   - Configured using:
     - `WebSecurityConfigurerAdapter`
     - `@PreAuthorize`, `@Secured` annotations
     - JWT tokens, OAuth2, etc.

9. **What is Spring Data JPA?**
   - Simplifies data access layer
   - Provides repository interfaces
   - Key features:
     - CRUD operations out of the box
     - Method name query generation
     - `@Query` annotation for custom queries
     - Pagination and sorting support

10. **Explain Spring Profiles**
    - Mechanism to have different configurations for different environments
    - Activated using:
      - `@Profile` annotation
      - `spring.profiles.active` property
    - Common uses:
      - Development vs Production configs
      - Different database configurations
      - Feature toggles 
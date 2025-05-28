package pnd.practice.spring_practice.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityDemoController {

    @GetMapping("/public")
    public String publicEndpoint() {
        return "This is a public endpoint, accessible by anyone";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String userEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello, " + auth.getName() + "! This is a protected endpoint for users.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminEndpoint() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "Hello, Admin " + auth.getName() + "! This is a protected endpoint for administrators.";
    }

    @GetMapping("/user-details")
    @PreAuthorize("isAuthenticated()")
    public String userDetails() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return String.format("""
            Current user details:
            Username: %s
            Roles: %s
            Is authenticated: %s
            """,
            auth.getName(),
            auth.getAuthorities(),
            auth.isAuthenticated()
        );
    }
} 
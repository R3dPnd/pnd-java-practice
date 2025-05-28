package pnd.practice.spring_practice.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // Add your custom health check logic here
        boolean isHealthy = checkHealth();
        
        if (isHealthy) {
            return Health.up()
                    .withDetail("customKey", "Everything is working fine!")
                    .withDetail("lastChecked", System.currentTimeMillis())
                    .build();
        } else {
            return Health.down()
                    .withDetail("customKey", "System is experiencing issues")
                    .withDetail("lastChecked", System.currentTimeMillis())
                    .build();
        }
    }

    private boolean checkHealth() {
        // Add your actual health check logic here
        // This is just a dummy implementation
        return true;
    }
} 
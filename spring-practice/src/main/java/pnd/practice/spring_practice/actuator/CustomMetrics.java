package pnd.practice.spring_practice.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class CustomMetrics {

    private final MeterRegistry registry;
    private Counter customCounter;

    public CustomMetrics(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void init() {
        customCounter = Counter.builder("custom.counter")
                .description("A custom counter for demonstration")
                .tag("source", "custom")
                .register(registry);
    }

    public void incrementCounter() {
        customCounter.increment();
    }
} 

package com.example.meetingscheduler.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfig {

    @Bean
    public Counter meetingsCreatedCounter(MeterRegistry registry) {
        return Counter.builder("meetingscreatedtotal")
                .description("Total meetings created")
                .register(registry);
    }
}

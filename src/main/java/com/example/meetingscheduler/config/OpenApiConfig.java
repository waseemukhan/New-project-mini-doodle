package com.example.meetingscheduler.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI meetingSchedulerOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Meeting Scheduler API")
                        .version("v1")
                        .description("Mini Doodle == the meeting scheduling service"));
    }
}

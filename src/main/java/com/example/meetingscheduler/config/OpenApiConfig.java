
package com.example.doodle.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI doodleOpenAPI() {
        return new OpenAPI()
            .info(new Info()
                .title("Mini Doodle API")
                .version("v1")
                .description("Meeting scheduling and availability API"));
    }
}

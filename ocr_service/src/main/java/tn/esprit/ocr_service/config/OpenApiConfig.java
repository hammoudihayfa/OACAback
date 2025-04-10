package tn.esprit.ocr_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("OCR Service API")
                        .version("1.0")
                        .description("API for extracting text from images using OCR."));
    }
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("ocr-service")
                .pathsToMatch("/**")
                .build();
    }
}

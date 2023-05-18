package com.mju.course.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(title = "SoftCoA Web",
                description = "CourseService Api 명세서",
                version = "v1"))
@RequiredArgsConstructor
@Configuration
public class Swagger3Config {

    @Bean
    public GroupedOpenApi chatOpenApi() {
        String[] paths = {"/course-service/**","/lecture-service/**","/admin-service/**"};

        return GroupedOpenApi.builder()
                .group("SoftCoA API v1")
                .pathsToMatch(paths)
                .build();
    }

}

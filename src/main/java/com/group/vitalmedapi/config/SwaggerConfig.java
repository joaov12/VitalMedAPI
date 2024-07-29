package com.group.vitalmedapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "VitalMed API!", version = "1.0", description = "API destinada a fazer gerenciamento de hospitais"))

public class SwaggerConfig {

}

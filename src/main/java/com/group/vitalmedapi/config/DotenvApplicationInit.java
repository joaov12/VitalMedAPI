package com.group.vitalmedapi.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;

import io.github.cdimascio.dotenv.Dotenv;

public class DotenvApplicationInit implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        Dotenv dotenv = Dotenv.load();
        Map<String, Object> envVariables = new HashMap<>();
        dotenv.entries().forEach(entry -> envVariables.put(entry.getKey(), entry.getValue()));
        applicationContext.getEnvironment().getPropertySources().addFirst(new MapPropertySource("dotenvProperties", envVariables));
    }
}
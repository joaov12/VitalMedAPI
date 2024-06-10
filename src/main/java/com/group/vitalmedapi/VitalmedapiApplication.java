package com.group.vitalmedapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.group.vitalmedapi.config.DotenvApplicationInit;

@SpringBootApplication
public class VitalmedapiApplication {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(VitalmedapiApplication.class);
        application.addInitializers(new DotenvApplicationInit());
        application.run(args);
	}

}

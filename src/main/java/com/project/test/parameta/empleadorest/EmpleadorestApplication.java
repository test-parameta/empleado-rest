package com.project.test.parameta.empleadorest;

import com.project.test.parameta.commons.configuration.ApplicationConfig;
import com.project.test.parameta.commons.configuration.SpringSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@ComponentScan(
		basePackages = "com.project.test.parameta"
)
@Import({SpringSecurityConfig.class, ApplicationConfig.class})
public class EmpleadorestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpleadorestApplication.class, args);
	}

}

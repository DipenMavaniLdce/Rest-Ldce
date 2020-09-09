package com.ldce.Main;

import com.ldce.controller.Controller;
import com.ldce.filter.JwtRequestFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.*;

import java.io.File;

@SpringBootApplication(scanBasePackages = { "com.ldce.security", "com.ldce.Model.Admin", "com.ldce.controller",
		"com.ldce.Dao", "com.ldce.Email" })
@EntityScan(basePackages = { "com.ldce.Model", "com.ldce.controller", "com.ldce.Main" })
@ComponentScan(basePackages = { "com.ldce.security", "com.ldce.Model", "com.ldce.controller", "com.ldce.Dao",
		"com.ldce.Email", "com.ldce.util", "com.ldce.filter" })
@EnableJpaRepositories(basePackages = { "com.ldce.Model", "com.ldce.Main" })
public class LdceApplication {
	public static void main(String[] args) {
		new File(Controller.uploadDirectory).mkdir();
		SpringApplication.run(LdceApplication.class, args);
	}
	@Bean
	WebMvcConfigurer webMvcConfigurer(){
		return new WebMvcConfigurer() {
			@Override
			public void addResourceHandlers(ResourceHandlerRegistry registry) {
				registry.addResourceHandler("/api/upload/**")
						.addResourceLocations("file:///C:/Users/mavan/Documents/intelij/ldce/uploads/");

			}


		};

	}

}

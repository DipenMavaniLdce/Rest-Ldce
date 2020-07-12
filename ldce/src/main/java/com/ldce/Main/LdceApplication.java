package com.ldce.Main;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;


@SpringBootApplication(scanBasePackages = {"com.ldce.security","com.ldce.admin","com.ldce.controller","com.ldce.Dao","com.ldce.Email"})
@EntityScan(basePackages = {"com.ldce.admin","com.ldce.controller","com.ldce.Main"})
@ComponentScan(basePackages = {"com.ldce.security","com.ldce.admin","com.ldce.controller","com.ldce.Dao","com.ldce.Email","com.ldce.util","com.ldce.filter"})
@EnableJpaRepositories(basePackages = {"com.ldce.admin","com.ldce.Main"})
public class LdceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LdceApplication.class, args);
	}

}

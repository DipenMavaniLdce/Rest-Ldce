package com.ldce.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import java.io.File;

@SpringBootApplication(scanBasePackages = { "com.ldce.security", "com.ldce.Model.Admin", "com.ldce.controller",
		"com.ldce.Dao", "com.ldce.Email" })
@EntityScan(basePackages = { "com.ldce.Model", "com.ldce.controller", "com.ldce.Main" })
@ComponentScan(basePackages = { "com.ldce.security", "com.ldce.Model", "com.ldce.controller", "com.ldce.Dao",
		"com.ldce.Email", "com.ldce.util", "com.ldce.filter" })
@EnableJpaRepositories(basePackages = { "com.ldce.Model", "com.ldce.Main" })
public class LdceApplication {
//CREATE SYTSTEM ENVIRONMENT FOR STROYING IMAGES
	
	//public static String remoteUrl =  System.getProperty("com.ldce.resource.location");
	//System.getProperty("user.dir")+"\\uploads";
	public static String uploadDirectory;

		@Value("${com.ldce.resource.location}")
        public void setuploadDirectory(String dir) {
			uploadDirectory = dir;
        }

	//public static String uploadDirectory = "https://elasticbeanstalk-us-east-2-156372987353.s3.us-east-2.amazonaws.com/uploads";
	public static void main(String[] args) {
		
	
		SpringApplication.run(LdceApplication.class, args);
		System.out.println(uploadDirectory);
		new File(uploadDirectory).mkdirs();
		
	}

}

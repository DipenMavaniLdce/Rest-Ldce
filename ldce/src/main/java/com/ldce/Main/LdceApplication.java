package com.ldce.Main;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import java.io.File;

@SpringBootApplication(scanBasePackages = { "com.ldce.security", "com.ldce.Model.Admin", "com.ldce.controller",
		"com.ldce.Dao", "com.ldce.Email" })
@EntityScan(basePackages = { "com.ldce.Model", "com.ldce.controller", "com.ldce.Main" })
@ComponentScan(basePackages = { "com.ldce.security", "com.ldce.Model", "com.ldce.controller", "com.ldce.Dao",
		"com.ldce.Email", "com.ldce.util", "com.ldce.filter" })
@EnableJpaRepositories(basePackages = { "com.ldce.Model", "com.ldce.Main" })
public class LdceApplication {
//CREATE SYTSTEM ENVIRONMENT FOR STROYING IMAGES
	public static String remoteUrl =  System.getenv().get("URL_S3");
	//System.getProperty("user.dir")+"\\uploads";
	public static String uploadDirectory =  remoteUrl != null ? remoteUrl :"E:/Documents/uploads";

	//public static String uploadDirectory = "https://elasticbeanstalk-us-east-2-156372987353.s3.us-east-2.amazonaws.com/uploads";
	public static void main(String[] args) {
		System.getenv().forEach((k, v) -> {
			System.out.println(k + ":" + v);
		});
		new File(uploadDirectory).mkdirs();
		SpringApplication.run(LdceApplication.class, args);
	}



	// @Bean
//	WebMvcConfigurer webMvcConfigurer(){
//		return new WebMvcConfigurer() {
//			@Override
//			public void addResourceHandlers(ResourceHandlerRegistry registry) {
//				registry.addResourceHandler("/api/upload/**")
//						.addResourceLocations("file:///C:/Users/mavan/Documents/intelij/ldce/uploads/");
//
//			}
//
//
//		};
//
//	}

}

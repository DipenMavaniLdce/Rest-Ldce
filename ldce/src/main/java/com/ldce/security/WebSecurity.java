package com.ldce.security;

import com.ldce.filter.JwtAuthenticationEntryPoint;
import com.ldce.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



	@Autowired
	UserDetailsService userDetailsService;

	@Autowired
	JwtRequestFilter jwtRequestFilter;



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(encoder());


	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable().authorizeRequests()
				.antMatchers("/api/admin/**").hasAnyRole("DEPARTMENT","SSHEAD","SSMENTOR")
				.antMatchers("/api/student/**").hasAnyRole("STUDENT")
				.antMatchers("/api/authenticate","/api/registerStudent","/signup","/api/*","/api/registerFaculty","/api/forgotPassword","/api/test","/api/upload/**","/**")
				.permitAll()
				.anyRequest()
				.authenticated()
				.and()
				.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		

	}
	@Bean
	public UserDetailsService getUserDetails(){
		return new CustomUserDetailService();
	}

	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public JwtAuthenticationEntryPoint getJwtAuthenticationEntryPoint() {
		return new JwtAuthenticationEntryPoint();
	}
}


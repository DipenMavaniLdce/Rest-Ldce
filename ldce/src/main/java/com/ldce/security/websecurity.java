package com.ldce.security;

import com.ldce.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.ldce.filter.JwtAuthenticationEntryPoint;
@Configuration
@EnableWebSecurity
public class websecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;



	@Autowired
	UserDetailsService userDetailsService;
	@Autowired
	JwtRequestFilter jwtRequestFilter;



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	System.out.println("reach");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {


		http.csrf().disable().authorizeRequests()
				.antMatchers("/api/admin/*").hasAnyRole("DEPARTMENT","SSHEAD","SSMENTOR")
				.antMatchers("/api/student/*").hasAnyRole("STUDENT")
				.antMatchers("/api/authenticate","/api/registerStudent","/signup","/api/*","/api/registerFaculty","/api/forgotPassword","/api/test","/api/upload/**")
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
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
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


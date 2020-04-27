package com.ldce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class websecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService userDetailsService;
	



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	System.out.println("reach");
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	
		
		http   
		.addFilterBefore(authenticationFilter(),UsernamePasswordAuthenticationFilter.class)
		.authorizeRequests()
		.antMatchers("/admin/*").hasAnyRole("DEPARTMENT","SSHEAD","SSMENTOR")
		.antMatchers("/student/*").hasAnyRole("STUDENT")
		.antMatchers("/","/registerFaculty").permitAll()
		.and()
		.formLogin()
		.loginPage("/login").permitAll()
		.usernameParameter("username")
        .passwordParameter("password")
      
        .permitAll()
        .and()
        .logout()
        .permitAll().and()
		.csrf().disable();
		
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}


	public LoginFilter authenticationFilter() throws Exception {
		LoginFilter filter = new LoginFilter();
	    filter.setAuthenticationManager(authenticationManagerBean());
	    filter.setAuthenticationSuccessHandler(successHandler());
	    filter.setAuthenticationFailureHandler(failureHandler());
	    return filter;
	}

	private AuthenticationFailureHandler failureHandler() {
	
		return new CustomAuthenticationFailureHandler();
	}
	


@Bean
protected AuthenticationSuccessHandler successHandler() {return new CustomAuthenticationSuccessHandler();}
	 
	
	 
	 
}


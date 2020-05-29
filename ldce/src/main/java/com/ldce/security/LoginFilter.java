package com.ldce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.annotations.Filter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Filter(name = "login")
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		
		 UsernamePasswordAuthenticationToken authRequest = getAuthRequest(request);
		 setDetails(request, authRequest);
        
       return this.getAuthenticationManager()
         .authenticate(authRequest);
	}
	

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.out.println("THY GYU");
		super.successfulAuthentication(request, response, chain, authResult);
		setAuthenticationSuccessHandler(getSuccessHandler());
	}

	@Override
	protected AuthenticationSuccessHandler getSuccessHandler() {
		System.out.println("sef");
		return super.getSuccessHandler();
	}


	private UsernamePasswordAuthenticationToken getAuthRequest(HttpServletRequest request) {
		String username = obtainUsername(request);
        String password = obtainPassword(request);
        String domain = request.getParameter("type");
 
    
 System.out.println(domain);
        String usernameDomain = username +","+ domain;
        return new UsernamePasswordAuthenticationToken(
          usernameDomain, password);
    }
	

}

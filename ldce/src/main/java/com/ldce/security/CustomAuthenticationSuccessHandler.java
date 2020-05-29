package com.ldce.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	 private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
	 @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication )throws IOException {
		 	handle(request, response, authentication);
	        clearAuthenticationAttributes(request);
	    }

	protected void handle(HttpServletRequest request,HttpServletResponse response, Authentication authentication) throws IOException {
		  		System.out.println("handle");
		        String targetUrl = determineTargetUrl(authentication);
		        System.out.println(targetUrl);
		        if (response.isCommitted()) {
		           
		            return;
		        }
		 
		        redirectStrategy.sendRedirect(request, response, targetUrl);
		    }
	  
	  protected String determineTargetUrl(Authentication authentication) {
		
	        boolean isUser = false;
	        boolean isAdmin = false;
	      
	        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
	        for (GrantedAuthority grantedAuthority : authorities) {
	        	System.out.println(grantedAuthority.getAuthority()+"cusstom");
	            if (grantedAuthority.getAuthority().equals("ROLE_STUDENT")) {
	                isUser = true;
	                break;
	            } else if (grantedAuthority.getAuthority().equals("ROLE_DEPARTMENT") ||grantedAuthority.getAuthority().equals("ROLE_SSMENTOR") ||grantedAuthority.getAuthority().equals("ROLE_SSHEAD")) {
	            	System.out.println("working");
	                isAdmin = true;
	                break;
	            }
	        }
	 
	        if (isUser) {
	            return "/student/";
	        } else if (isAdmin) {
	            return "/admin/";
	        } else {
	            throw new IllegalStateException();
	        }
	    }
	 
	    protected void clearAuthenticationAttributes(HttpServletRequest request) {
	        HttpSession session = request.getSession(false);
	        if (session == null) {
	        	System.out.println("rxfgcj");
	            return;
	        }
	     
	        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	    }
	 
	    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
	    
	        this.redirectStrategy = redirectStrategy;
	    }
	    protected RedirectStrategy getRedirectStrategy() {
	    	
	        return redirectStrategy;
	    }

	
}



package com.ldce.filter;

import com.ldce.security.CustomUserDetailService;
import com.ldce.util.JwtUtil;

import io.jsonwebtoken.ExpiredJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private CustomUserDetailService userDetailService;
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {

		Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

		final String authorizitionHeader = httpServletRequest.getHeader("Authorization");
		final String domain = httpServletRequest.getHeader("Domain");
		logger.info("authorizitionHeader :- {} , domain :- {}",authorizitionHeader,domain);
		String username = null;
		String jwt = null;
		if (authorizitionHeader != null && authorizitionHeader.startsWith("Bearer ")) {
			jwt = authorizitionHeader.substring(7);
			try {
				username = jwtUtil.extractUsername(jwt);
				logger.info("username successfully extract from token");
				logger.info("username :- {}" ,username);
			} catch (IllegalArgumentException e) {
				logger.error("Exception on IllegalArgumentException on JWT token");
				logger.error(e.toString());
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
				logger.error(e.toString());
			}catch(Exception e){
				System.out.println("Exception on JWT token");
				logger.error(e.toString());
			}

		}

		if (domain != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			String usernameDomain = username + "," + domain;

			UserDetails userDetails = this.userDetailService.loadUserByUsername(usernameDomain);
			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}

		}
		httpServletRequest.setAttribute("username",username);
		httpServletRequest.setAttribute("Domain",domain);
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}

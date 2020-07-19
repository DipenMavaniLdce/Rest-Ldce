package com.ldce.filter;

import com.ldce.security.userdetailservice;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.ldce.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
	@Autowired
	private userdetailservice userDetailService;
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			FilterChain filterChain) throws ServletException, IOException {
		final String authorizitionHeader = httpServletRequest.getHeader("Authorization");
		final String domain = httpServletRequest.getHeader("Domain");
		System.out.println(authorizitionHeader);
		String username = null;
		String jwt = null;
		if (authorizitionHeader != null && authorizitionHeader.startsWith("Bearer ")) {
			jwt = authorizitionHeader.substring(7);
			try {
				username = jwtUtil.extractUsername(jwt);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} catch (ExpiredJwtException e) {
				System.out.println("JWT Token has expired");
			}

		}

		if (domain != null && username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			String usernameDomain = username + "," + domain;
			System.out.println(usernameDomain);
			UserDetails userDetails = this.userDetailService.loadUserByUsername(usernameDomain);
			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}
}

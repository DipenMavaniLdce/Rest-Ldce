package com.ldce.util;

import com.ldce.filter.JwtRequestFilter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
@Component
public class JwtUtil {
	private String SECRET_KEY = "secret";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpirations(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		Logger logger = LoggerFactory.getLogger(JwtUtil.class);

		final Claims claims = extractAllClaims(token);
		logger.trace("Request come for adding extracted claim from token to set in claim resolver");
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		Logger logger = LoggerFactory.getLogger(JwtUtil.class);
		logger.trace("Request come for claim extraction");
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public Boolean isTokenExpired(String token) {
		Logger logger = LoggerFactory.getLogger(JwtUtil.class);
		logger.trace("Request come to check isToken Expired or not");
		return extractExpirations(token).before(new Date());
	}

	public String generateToken(UserDetails userDetails) {
		Logger logger = LoggerFactory.getLogger(JwtUtil.class);
		logger.trace("Request come for genration of new token");
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String username) {
		Logger logger = LoggerFactory.getLogger(JwtUtil.class);
		logger.trace("Request come for creation of token and set Expiration and signature algoritham algoritham");
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();

	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		Logger logger = LoggerFactory.getLogger(JwtUtil.class);
		logger.trace("Request come for token validation");
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}

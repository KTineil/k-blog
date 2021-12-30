package com.service.kblog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenProvider tokenProvider;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request
			, HttpServletResponse response
			, FilterChain filterChain) throws ServletException, IOException {

		try {
			
			final String token = parseBearerToken(request);
			log.info("filter is running");
			
			if (token != null && !token.equalsIgnoreCase("null")) {
				String userId = tokenProvider.validateAndGetUserId(token);
				
				log.info("Authentication user ID : " +  userId);
				
				// SecurityContextHolder라고 하는 쓰레드로컬에 저장되는 인증정보를 저장해주어야 스프링에서 인증된 사용자라고 생각한다고 한다.
				// 좀 더 알아봐야 할 부
				AbstractAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
							userId,
							null,
							AuthorityUtils.NO_AUTHORITIES
						);
				
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
				securityContext.setAuthentication(authentication);
				SecurityContextHolder.setContext(securityContext);
			}
		}
		catch(Exception e) {
			log.error("can't set authentication");
		}
		
		filterChain.doFilter(request, response);
	}

	private String parseBearerToken(HttpServletRequest request) {
		
		Cookie[] cookies = request.getCookies();
		
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("jwtToken")) {
				return cookie.getValue();
			}
		}
		
		return null;
	}
}

package com.service.kblog.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.service.kblog.model.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {

	private static final String SECRET_KEY = "KPP8hsIOnaJJ10";
	
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(
				Instant.now()
				.plus(1, ChronoUnit.DAYS));
		
		return Jwts.builder()
				// Header에 들어갈 알고리즘과 전자 서명 시크릿 키
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				
				// Payload에 들어갈 값들
				.setSubject(userEntity.getId())
				.setIssuer("KBros")
				.setIssuedAt(new Date())
				.setExpiration(expiryDate)
				.compact();
				
		/*
		 * { // header
		 * 	"alg":"HS512"
		 * }.
		 * { // payload
		 * 	"sub": userId
		 * 	"iss":"KBros"
		 * 	"iat": 토큰 발행 시간
		 * 	"exp": 토큰 발행 시간으로부터 1일 후 
		 * }
		 * { // signature
		 * 	SECRET_KEY를 이용해 위의 헤더와 페이로드를 전자서명한 값
		 * }
		 * 
		 */
	}
	
}
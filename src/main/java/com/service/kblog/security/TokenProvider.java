package com.service.kblog.security;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.service.kblog.model.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenProvider {

	private static final String SECRET_KEY = "KPP8hsIOnaJJ10"; // 테스트용
	
	public String create(UserEntity userEntity) {
		Date expiryDate = Date.from(
				Instant.now()
				.plus(1, ChronoUnit.DAYS));
		
		return Jwts.builder()
				// Header�� �� �˰���� ���� ���� ��ũ�� Ű
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				
				// Payload�� �� ����
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
		 * 	"iat": ��ū ���� �ð�
		 * 	"exp": ��ū ���� �ð����κ��� 1�� �� 
		 * }
		 * { // signature
		 * 	SECRET_KEY�� �̿��� ���� ����� ���̷ε带 ���ڼ����� ��
		 * }
		 * 
		 */
	}
	
	public String validateAndGetUserId(String token) {
		Claims claims = Jwts.parser().setSigningKey(SECRET_KEY)
			.parseClaimsJws(token) // not parseClaimsJw't'
			.getBody();
		
		return claims.getSubject();
	}
	
}
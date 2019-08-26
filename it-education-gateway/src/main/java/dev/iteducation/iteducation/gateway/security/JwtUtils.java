package dev.iteducation.iteducation.gateway.security;

import dev.iteducation.iteducation.gateway.config.ApplicationConfig;
import dev.iteducation.iteducation.gateway.domain.document.Account;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Sergey Kulebyakin (sekulebyakin@gmail.com) created on 19.08.2019.
 */
public class JwtUtils implements Serializable {

	private static final long serialVersionUID = -3806616018490690365L;

	private final ApplicationConfig applicationConfig;

	public JwtUtils(ApplicationConfig applicationConfig) {
		this.applicationConfig = applicationConfig;
	}

	public Claims getAllClaimsFromToken(String token) {
		final var secret = applicationConfig.getPassword().getJwt().getSecret();
		return Jwts.parser()
				.setSigningKey(Base64.getEncoder()
						.encodeToString(secret.getBytes()))
				.parseClaimsJws(token)
				.getBody();
	}

	public String getUsernameFromToken(String token) {
		return getAllClaimsFromToken(token).getSubject();
	}

	public String getUserIdFromToken(String token) {
		return getAllClaimsFromToken(token).getId();
	}

	public Date getExpirationDateFromToken(String token) {
		return getAllClaimsFromToken(token).getExpiration();
	}

	private Boolean isTokenExpired(String token) {
		final var expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(Account user) {
		var claims = new HashMap<String, Object>();
		claims.put("role", user.getRoles());
		return doGenerateToken(claims, user);
	}

	private String doGenerateToken(Map<String, Object> claims, Account account) {
		final var createdDate = new Date();
		final var expirationTime = applicationConfig.getPassword().getJwt().getExpiration();
		final var secret = applicationConfig.getPassword().getJwt().getSecret();
		final var expirationDate = new Date(createdDate.getTime() + expirationTime * 1000);
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(account.getUsername())
				.setId(account.getId())
				.setIssuedAt(createdDate)
				.setExpiration(expirationDate)
				.signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encodeToString(secret.getBytes()))
				.compact();
	}

	public Boolean validateToken(String token) {
		return !isTokenExpired(token);
	}

}

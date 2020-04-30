package io.jg_intelligence.security;



import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jg_intelligence.entity.Admin;
import io.jg_intelligence.exception.UsernameAlreadyExistException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;


import static io.jg_intelligence.security.SecurityConstants.EXPIRATION_TIME;
import static io.jg_intelligence.security.SecurityConstants.SECRET;;
@Component
public class JwtTokenProvider {
//generate token
	public String generateToken(Authentication authentication) {
		Admin admin = (Admin)authentication.getPrincipal();
		Date now = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(now.getTime()+EXPIRATION_TIME);
		
		String userId = Long.toString(admin.getId());
		
		
		Map<String, Object> claims = new HashMap<>();
		claims.put("id", (Long.toString(admin.getId())));
		claims.put("email", admin.getEmail());
		claims.put("username", admin.getUsername());
		claims.put("firstName", admin.getFirstName());
		claims.put("middleName", admin.getMiddleName());
		claims.put("lastName", admin.getLastName());
		claims.put("usertype", admin.getUsertype());
		
		 return Jwts.builder()
				 .setSubject(userId)
				 .setClaims(claims)
				 .setIssuedAt(now)
				 .setExpiration(expiryDate)
				 .signWith(SignatureAlgorithm.HS512, SECRET)
				 .compact();
	 }
	
//validate token
	 public boolean validateToken(String token) {
		 try {
			Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			System.out.println("Invalid JWT Signature key");
		}catch (MalformedJwtException e) {
			// TODO: handle exception
			System.out.println("Invalit JWT Token");
		}catch (ExpiredJwtException e) {
			// TODO: handle exception
			System.out.println("Expired JWT Token");
		}catch (UnsupportedJwtException e) {
			// TODO: handle exception
			System.out.println("Unsupported JWT Token");
		}catch (IllegalArgumentException e) {
			// TODO: handle exception
			System.out.println("JWT claims string is empty");
		}
		 
		 return false;
	 }
//get user id from token
	 public Long getUserIdFromToken(String token) {
		 //decode token
			Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
			String id = (String)claims.get("id");
			return Long.parseLong(id);
		}
}

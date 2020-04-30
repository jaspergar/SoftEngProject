package io.jg_intelligence.security;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jg_intelligence.entity.Admin;
import io.jg_intelligence.service.CustomUserDetailsService;
import static io.jg_intelligence.security.SecurityConstants.TOKEN_PREFIX;
import static io.jg_intelligence.security.SecurityConstants.HEADER_STRING;;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String jwt = getJWTFromRequest(request);
			
			if(StringUtils.hasText(jwt)&&jwtTokenProvider.validateToken(jwt)) {
				Long userId = jwtTokenProvider.getUserIdFromToken(jwt);
				Admin userDetails = customUserDetailsService.loadUserById(userId);
				
				UsernamePasswordAuthenticationToken authentication = 
						new UsernamePasswordAuthenticationToken(userDetails,null,Collections.emptyList());
			
			    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.error("Could not set user authentication in security context");
		}
		filterChain.doFilter(request, response);
		
	}
	
	public String getJWTFromRequest(HttpServletRequest request) {
          String bearerTokenString = request.getHeader(HEADER_STRING);
		
		if(StringUtils.hasText(bearerTokenString)&&bearerTokenString.startsWith(TOKEN_PREFIX)) {
			return bearerTokenString.substring(7, bearerTokenString.length());
		}
		
		return null;
	}
	
}

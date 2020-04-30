package io.jg_intelligence.security;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import io.jg_intelligence.exception.InvalidLoginResponse;



@Component
public class JwtAunthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// TODO Auto-generated method stub
		InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
		String jsonLoginResponse = new Gson().toJson(invalidLoginResponse);
		
		response.setContentType("application/json");
		response.setStatus(401);
		response.getWriter().print(jsonLoginResponse);
		
	
		
	}
  
}

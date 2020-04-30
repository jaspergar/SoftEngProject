package io.jg_intelligence.controller;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import io.jg_intelligence.entity.Admin;
import io.jg_intelligence.loginPayload.JwtLoginSuccessResponse;
import io.jg_intelligence.loginPayload.LoginRequest;
import io.jg_intelligence.security.JwtTokenProvider;
import io.jg_intelligence.service.MapValidatorService;
import io.jg_intelligence.service.AdminService;
import io.jg_intelligence.UserValidator.UserValidator;

import static io.jg_intelligence.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class AdminController {

	@Autowired
	MapValidatorService mapValidatorService;
	@Autowired
	AdminService adminService;
	@Autowired
	UserValidator userValidator;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	@Autowired
	AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequest loginRequest,BindingResult result){
		ResponseEntity<?> errorMap = mapValidatorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Authentication authentication = authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new JwtLoginSuccessResponse(true, jwt));
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody Admin admin,BindingResult result){
		 userValidator.validate(admin, result);
		
		ResponseEntity<?> errorMap = mapValidatorService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		Admin newUser = adminService.saveUser(admin);
		return new ResponseEntity<Admin>(newUser,HttpStatus.CREATED);
	}
	
	@GetMapping("/username/{username}")
	public ResponseEntity<?> getUserByUsername(Admin admin,@PathVariable String username){
		Admin user1= adminService.getUserByUsername(username);
		return new ResponseEntity<Admin>(user1,HttpStatus.OK);
	}
	
}

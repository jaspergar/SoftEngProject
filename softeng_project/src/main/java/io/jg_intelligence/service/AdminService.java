package io.jg_intelligence.service;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import io.jg_intelligence.entity.Admin;
import io.jg_intelligence.exception.EmailAlreadyExistException;
import io.jg_intelligence.exception.UsernameAlreadyExistException;
import io.jg_intelligence.repository.AdminRepository;
import javassist.compiler.ast.NewExpr;



@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Admin saveUser(Admin newUser) {
		
		Admin user1 = adminRepository.findByUsername(newUser.getUsername());
		   if(user1 != null){
			   throw new UsernameAlreadyExistException("Username Already Exist");
		   }
		
			try {
				newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
				newUser.setEmail(newUser.getEmail());  
				return adminRepository.save(newUser);
			} catch (Exception e) {
				// TODO: handle exception
				throw new EmailAlreadyExistException("Email already Exist.");
			}
			
	     
	}
	
	public Admin getUserByUsername(String username) {
		Admin user1 = adminRepository.findByUsername(username);
		return user1;
	}

}

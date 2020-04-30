package io.jg_intelligence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jg_intelligence.entity.Admin;
import io.jg_intelligence.repository.AdminRepository;



@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Admin admin = adminRepository.findByUsername(username);
		if(admin == null) new UsernameNotFoundException("Username not found");
		return admin;
	}
	@Transactional
	public Admin loadUserById(Long id) {
		Admin admin = adminRepository.getById(id);
		if(admin==null) new UsernameNotFoundException("Id not found");
		return admin;
	}
	
}

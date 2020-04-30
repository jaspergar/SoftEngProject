package io.jg_intelligence.UserValidator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import io.jg_intelligence.entity.Admin;



@Component
public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Admin.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		 Admin admin = (Admin) target;
		   
		   if(admin.getPassword().length()<6) {
			   errors.rejectValue("password", "length", "Password must contain atleast 6 characters");
		   }
		   
		   if(!admin.getPassword().equals(admin.getConfirmPassword())) {
			   errors.rejectValue("confirmPassword", "match", "Password didn't match.");
		   }
	}

	
}
